

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class DownloadImageCss {

	public static void main(String[] args) throws Exception {
		String s = FileUtils.readFileToString(new File("D:\\github\\fm\\src\\main\\webapp\\static\\css\\compiled.css"),
				"UTF-8");
		Pattern p = Pattern.compile("url\\((.*)\\)");
		Matcher m = p.matcher(s);
		HttpClientBuilder builder = HttpClientBuilder.create();
		CloseableHttpClient client = builder.build();
		Set<String> set = new HashSet<String>();
		while (m.find()) {
			String url = m.group(1);
			url = url.replace("'", "").replace("\"", "").trim();
			if (url.startsWith("/"))
				set.add(url);
		}

		int t = 0;
		for (String url : set) {
			int i = url.lastIndexOf('/');
			String dir = url.substring(0, i);
			String filename = url.substring(i + 1);
			System.out.println(url + " - " + (t++) + "/" + set.size());
			File dirFile = new File("D:\\temp" + dir);
			if (!dirFile.exists())
				FileUtils.forceMkdir(dirFile);
			File pic = new File(dirFile, filename);
			if (pic.exists())
				continue;
			CloseableHttpResponse response = client.execute(new HttpGet("http://www.pandora.com" + url));
			FileOutputStream out = new FileOutputStream(pic);
			IOUtils.copy(response.getEntity().getContent(), out);
			out.close();
			response.close();
		}
	}

}
