import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;
import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideax.fm360.pojo.SongXiami;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class CopyOfGetSongInfoFromXiami {

	public static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
	}

	static String host = "bcs.duapp.com";
	static String accessKey = "XcMrzzpIgrVlXUOtAEzL3PFz";
	static String secretKey = "BOFBT0GSnoh0cnancoAAhhSTWFVonf8d";
	static String bucket = "fmstore";

	// static Set<String> parsered = new HashSet<String>();
	// static {
	// parsered.add("1900");
	// parsered.add("2004");
	// parsered.add("2005");
	// parsered.add("2006");
	// parsered.add("best of A1");
	// parsered.add("best of avril");
	// parsered.add("best of blue");
	// parsered.add("best of eminem");
	// parsered.add("best of jet");
	// parsered.add("best of linkin park");
	// parsered.add("best of westlife");
	// parsered.add("best 周杰伦");
	//
	// }

	// ----------------------------------------

	public static void main(String[] args) throws Exception {
		BCSCredentials credentials = new BCSCredentials(accessKey, secretKey);
		BaiduBCS baiduBCS = new BaiduBCS(credentials, host);
		// baiduBCS.setDefaultEncoding("GBK");
		baiduBCS.setDefaultEncoding("UTF-8"); // Default UTF-8

		File ppdir = new File("E:\\music");
		File[] dirs = ppdir.listFiles();
		for (File pfile : dirs) {
			if (!pfile.isFile() || !pfile.getName().startsWith("result_"))
				continue;
			System.out.println("################" + pfile.getName());
			String str = FileUtils.readFileToString(pfile, "UTF-8");
			int s = -1, e = -1;
			File newResult = new File("E:\\result\\" + pfile.getName());
			PrintWriter pw = new PrintWriter(newResult, "UTF-8");
			do {
				s = str.indexOf('{', s + 1);
				e = str.indexOf('}', e + 1);
				if (s == -1)
					break;
				String json = str.substring(s, e + 1);
				SongXiami song = mapper.readValue(json, SongXiami.class);
				System.out.println("【filepath】" + song.getFilePath());
				String songLink = song.getXiamiSongLink();
				System.out.println("【songlink】" + songLink);
				if (StringUtils.isNotBlank(songLink)) {
					// 专辑图片
					Document albumDoc;
					try {
						albumDoc = Jsoup.connect(songLink).get();
						Elements coverels = albumDoc.select(".cdCDcover185");
						if (coverels.size() > 0) {
							Element img = coverels.get(0);
							String src = img.attr("src");
							if (StringUtils.isNotBlank(src))
								song.setXiamiCoverImg(src);
							else
								System.out.println("!!!!!!!!!!!!!!!!!! block");
						}
						//
						Elements albuminfo = albumDoc.select("#albums_info");
						Elements albumels = albuminfo.select(".item");
						if (albumels.size() > 0) {
							for (Element item : albumels) {
								String key = item.text();
								String value = null;
								String link = null;
								Element valueEl = item.nextElementSibling();
								if (valueEl != null) {
									try {
										Element div = valueEl.child(0);
										if (div.children().size() > 0) {
											Element a = div.child(0);
											value = a.text();
											link = a.attr("href");
										} else {
											value = div.text();
										}
									} catch (IndexOutOfBoundsException e2) {
									}
								}
								if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
									if (key.contains("专辑")) {
										song.setXiamiAlbum(value);
										song.setXiamiAlbumLink(link);
									} else if (key.contains("演唱")) {
										song.setXiamiArtist(value);
										song.setXiamiArtistLink(link);
									} else if (key.contains("词")) {
										song.setXiamiLyricist(value);
									} else if (key.contains("作曲")) {
										song.setXiamiComposer(value);
									}
								}
							}
						}
						//
						Elements lrcs = albumDoc.select(".lrc_main");
						if (lrcs.size() > 0) {
							Element lrc = lrcs.get(0);
							String lrcstr = lrc.html();
							if (StringUtils.isNotBlank(lrcstr)) {
								song.setXiamiLyric(lrcstr);
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}

				String filename = null;
				if (StringUtils.isBlank(filename) && StringUtils.isNotBlank(song.getXiamiSongName())) {
					filename = song.getXiamiSongName()
							+ (StringUtils.isBlank(song.getXiamiArtist()) ? "" : (" - " + song.getXiamiArtist()));
				}
				if (StringUtils.isBlank(filename) && StringUtils.isNotBlank(song.getName())) {
					filename = song.getName()
							+ (StringUtils.isBlank(song.getArtist()) ? "" : (" - " + song.getArtist()));
				}
				if (StringUtils.isBlank(filename)) {
					filename = song.getFileName();
				}

				song.setPcsFileName(filename + ".mp3");

				PutObjectRequest request = new PutObjectRequest(bucket, "/" + song.getPcsFileName(), new File(
						song.getFilePath()));
				ObjectMetadata metadata = new ObjectMetadata();
				// metadata.setContentType("text/html");
				request.setMetadata(metadata);
				BaiduBCSResponse<ObjectMetadata> response = baiduBCS.putObject(request);

				String jsonsong = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(song);
				pw.println(jsonsong);
				pw.flush();
				Thread.sleep(5000);
			} while (true);

			pw.flush();
			pw.close();

		}

	}

	public static String toFirstUpper(String str) {
		if (StringUtils.isNotBlank(str)) {
			char ss[] = str.toCharArray();
			boolean first = true;
			for (int i = 0; i < ss.length; i++) {
				if (ss[i] == ' ') {
					first = true;
					continue;
				}
				if (first && CharUtils.isAsciiAlphaLower(ss[i])) {
					ss[i] = (char) (ss[i] - 32);
				}
				first = false;
			}
			return new String(ss);
		}
		return str;
	}
}
