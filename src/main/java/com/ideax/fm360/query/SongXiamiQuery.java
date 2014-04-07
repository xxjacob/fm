package com.ideax.fm360.query;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xxjacob
 */
public class SongXiamiQuery extends BaseQuery {
	
	/** ====================查询唯一单条记录使用==========================**/
	
	/**==============================批量查询、更新、删除时的Where条件设置==================================**/
	/** id **/
    private Integer id;
	/**
    * 获取属性:id
    * id
    * @return id
    */
	public Integer getId () {
    	return id;
   	}
   	/**
     * 设置属性:id
     * id
     * @param id
     */
    public SongXiamiQuery setId(Integer id) {
    	this.id = id;
    	return this;
    }
	/** name **/
    private String name;
	/**
    * 获取属性:name
    * name
    * @return name
    */
	public String getName () {
    	return name;
   	}
   	/**
     * 设置属性:name
     * name
     * @param name
     */
    public SongXiamiQuery setName(String name) {
    	this.name = name;
    	return this;
    }
	/** breif **/
    private String breif;
	/**
    * 获取属性:breif
    * breif
    * @return breif
    */
	public String getBreif () {
    	return breif;
   	}
   	/**
     * 设置属性:breif
     * breif
     * @param breif
     */
    public SongXiamiQuery setBreif(String breif) {
    	this.breif = breif;
    	return this;
    }
	/** album **/
    private String album;
	/**
    * 获取属性:album
    * album
    * @return album
    */
	public String getAlbum () {
    	return album;
   	}
   	/**
     * 设置属性:album
     * album
     * @param album
     */
    public SongXiamiQuery setAlbum(String album) {
    	this.album = album;
    	return this;
    }
	/** artist **/
    private String artist;
	/**
    * 获取属性:artist
    * artist
    * @return artist
    */
	public String getArtist () {
    	return artist;
   	}
   	/**
     * 设置属性:artist
     * artist
     * @param artist
     */
    public SongXiamiQuery setArtist(String artist) {
    	this.artist = artist;
    	return this;
    }
	/** 1,2,3 **/
    private String featArtist;
	/**
    * 获取属性:featArtist
    * 1,2,3
    * @return featArtist
    */
	public String getFeatArtist () {
    	return featArtist;
   	}
   	/**
     * 设置属性:featArtist
     * 1,2,3
     * @param featArtist
     */
    public SongXiamiQuery setFeatArtist(String featArtist) {
    	this.featArtist = featArtist;
    	return this;
    }
	/** 作词人 **/
    private String lyricist;
	/**
    * 获取属性:lyricist
    * 作词人
    * @return lyricist
    */
	public String getLyricist () {
    	return lyricist;
   	}
   	/**
     * 设置属性:lyricist
     * 作词人
     * @param lyricist
     */
    public SongXiamiQuery setLyricist(String lyricist) {
    	this.lyricist = lyricist;
    	return this;
    }
	/** 作曲人 **/
    private String composer;
	/**
    * 获取属性:composer
    * 作曲人
    * @return composer
    */
	public String getComposer () {
    	return composer;
   	}
   	/**
     * 设置属性:composer
     * 作曲人
     * @param composer
     */
    public SongXiamiQuery setComposer(String composer) {
    	this.composer = composer;
    	return this;
    }
	/** in second **/
    private Integer duration;
	/**
    * 获取属性:duration
    * in second
    * @return duration
    */
	public Integer getDuration () {
    	return duration;
   	}
   	/**
     * 设置属性:duration
     * in second
     * @param duration
     */
    public SongXiamiQuery setDuration(Integer duration) {
    	this.duration = duration;
    	return this;
    }
	/** cover_img **/
    private String coverImg;
	/**
    * 获取属性:coverImg
    * cover_img
    * @return coverImg
    */
	public String getCoverImg () {
    	return coverImg;
   	}
   	/**
     * 设置属性:coverImg
     * cover_img
     * @param coverImg
     */
    public SongXiamiQuery setCoverImg(String coverImg) {
    	this.coverImg = coverImg;
    	return this;
    }
	/** xiami_album_link **/
    private String xiamiAlbumLink;
	/**
    * 获取属性:xiamiAlbumLink
    * xiami_album_link
    * @return xiamiAlbumLink
    */
	public String getXiamiAlbumLink () {
    	return xiamiAlbumLink;
   	}
   	/**
     * 设置属性:xiamiAlbumLink
     * xiami_album_link
     * @param xiamiAlbumLink
     */
    public SongXiamiQuery setXiamiAlbumLink(String xiamiAlbumLink) {
    	this.xiamiAlbumLink = xiamiAlbumLink;
    	return this;
    }
	/** xiami_song_name **/
    private String xiamiSongName;
	/**
    * 获取属性:xiamiSongName
    * xiami_song_name
    * @return xiamiSongName
    */
	public String getXiamiSongName () {
    	return xiamiSongName;
   	}
   	/**
     * 设置属性:xiamiSongName
     * xiami_song_name
     * @param xiamiSongName
     */
    public SongXiamiQuery setXiamiSongName(String xiamiSongName) {
    	this.xiamiSongName = xiamiSongName;
    	return this;
    }
	/** xiami_song_link **/
    private String xiamiSongLink;
	/**
    * 获取属性:xiamiSongLink
    * xiami_song_link
    * @return xiamiSongLink
    */
	public String getXiamiSongLink () {
    	return xiamiSongLink;
   	}
   	/**
     * 设置属性:xiamiSongLink
     * xiami_song_link
     * @param xiamiSongLink
     */
    public SongXiamiQuery setXiamiSongLink(String xiamiSongLink) {
    	this.xiamiSongLink = xiamiSongLink;
    	return this;
    }
	/** xiami_artist **/
    private String xiamiArtist;
	/**
    * 获取属性:xiamiArtist
    * xiami_artist
    * @return xiamiArtist
    */
	public String getXiamiArtist () {
    	return xiamiArtist;
   	}
   	/**
     * 设置属性:xiamiArtist
     * xiami_artist
     * @param xiamiArtist
     */
    public SongXiamiQuery setXiamiArtist(String xiamiArtist) {
    	this.xiamiArtist = xiamiArtist;
    	return this;
    }
	/** xiami_artist_link **/
    private String xiamiArtistLink;
	/**
    * 获取属性:xiamiArtistLink
    * xiami_artist_link
    * @return xiamiArtistLink
    */
	public String getXiamiArtistLink () {
    	return xiamiArtistLink;
   	}
   	/**
     * 设置属性:xiamiArtistLink
     * xiami_artist_link
     * @param xiamiArtistLink
     */
    public SongXiamiQuery setXiamiArtistLink(String xiamiArtistLink) {
    	this.xiamiArtistLink = xiamiArtistLink;
    	return this;
    }
	/** xiami_lyric **/
    private String xiamiLyric;
	/**
    * 获取属性:xiamiLyric
    * xiami_lyric
    * @return xiamiLyric
    */
	public String getXiamiLyric () {
    	return xiamiLyric;
   	}
   	/**
     * 设置属性:xiamiLyric
     * xiami_lyric
     * @param xiamiLyric
     */
    public SongXiamiQuery setXiamiLyric(String xiamiLyric) {
    	this.xiamiLyric = xiamiLyric;
    	return this;
    }
	/** xiami_album **/
    private String xiamiAlbum;
	/**
    * 获取属性:xiamiAlbum
    * xiami_album
    * @return xiamiAlbum
    */
	public String getXiamiAlbum () {
    	return xiamiAlbum;
   	}
   	/**
     * 设置属性:xiamiAlbum
     * xiami_album
     * @param xiamiAlbum
     */
    public SongXiamiQuery setXiamiAlbum(String xiamiAlbum) {
    	this.xiamiAlbum = xiamiAlbum;
    	return this;
    }
	/** xiami_cover_img **/
    private String xiamiCoverImg;
	/**
    * 获取属性:xiamiCoverImg
    * xiami_cover_img
    * @return xiamiCoverImg
    */
	public String getXiamiCoverImg () {
    	return xiamiCoverImg;
   	}
   	/**
     * 设置属性:xiamiCoverImg
     * xiami_cover_img
     * @param xiamiCoverImg
     */
    public SongXiamiQuery setXiamiCoverImg(String xiamiCoverImg) {
    	this.xiamiCoverImg = xiamiCoverImg;
    	return this;
    }
	/** xiami_composer **/
    private String xiamiComposer;
	/**
    * 获取属性:xiamiComposer
    * xiami_composer
    * @return xiamiComposer
    */
	public String getXiamiComposer () {
    	return xiamiComposer;
   	}
   	/**
     * 设置属性:xiamiComposer
     * xiami_composer
     * @param xiamiComposer
     */
    public SongXiamiQuery setXiamiComposer(String xiamiComposer) {
    	this.xiamiComposer = xiamiComposer;
    	return this;
    }
	/** xiami_lyricist **/
    private String xiamiLyricist;
	/**
    * 获取属性:xiamiLyricist
    * xiami_lyricist
    * @return xiamiLyricist
    */
	public String getXiamiLyricist () {
    	return xiamiLyricist;
   	}
   	/**
     * 设置属性:xiamiLyricist
     * xiami_lyricist
     * @param xiamiLyricist
     */
    public SongXiamiQuery setXiamiLyricist(String xiamiLyricist) {
    	this.xiamiLyricist = xiamiLyricist;
    	return this;
    }
	/** xiami_lyric_url **/
    private String xiamiLyricUrl;
	/**
    * 获取属性:xiamiLyricUrl
    * xiami_lyric_url
    * @return xiamiLyricUrl
    */
	public String getXiamiLyricUrl () {
    	return xiamiLyricUrl;
   	}
   	/**
     * 设置属性:xiamiLyricUrl
     * xiami_lyric_url
     * @param xiamiLyricUrl
     */
    public SongXiamiQuery setXiamiLyricUrl(String xiamiLyricUrl) {
    	this.xiamiLyricUrl = xiamiLyricUrl;
    	return this;
    }
	/** xiami_download_url **/
    private String xiamiDownloadUrl;
	/**
    * 获取属性:xiamiDownloadUrl
    * xiami_download_url
    * @return xiamiDownloadUrl
    */
	public String getXiamiDownloadUrl () {
    	return xiamiDownloadUrl;
   	}
   	/**
     * 设置属性:xiamiDownloadUrl
     * xiami_download_url
     * @param xiamiDownloadUrl
     */
    public SongXiamiQuery setXiamiDownloadUrl(String xiamiDownloadUrl) {
    	this.xiamiDownloadUrl = xiamiDownloadUrl;
    	return this;
    }
	/** listen_num **/
    private Integer listenNum;
	/**
    * 获取属性:listenNum
    * listen_num
    * @return listenNum
    */
	public Integer getListenNum () {
    	return listenNum;
   	}
   	/**
     * 设置属性:listenNum
     * listen_num
     * @param listenNum
     */
    public SongXiamiQuery setListenNum(Integer listenNum) {
    	this.listenNum = listenNum;
    	return this;
    }
	/** share_num **/
    private Integer shareNum;
	/**
    * 获取属性:shareNum
    * share_num
    * @return shareNum
    */
	public Integer getShareNum () {
    	return shareNum;
   	}
   	/**
     * 设置属性:shareNum
     * share_num
     * @param shareNum
     */
    public SongXiamiQuery setShareNum(Integer shareNum) {
    	this.shareNum = shareNum;
    	return this;
    }
	/** comment_num **/
    private Integer commentNum;
	/**
    * 获取属性:commentNum
    * comment_num
    * @return commentNum
    */
	public Integer getCommentNum () {
    	return commentNum;
   	}
   	/**
     * 设置属性:commentNum
     * comment_num
     * @param commentNum
     */
    public SongXiamiQuery setCommentNum(Integer commentNum) {
    	this.commentNum = commentNum;
    	return this;
    }
	/** modify_time **/
    private Integer modifyTime;
	/**
    * 获取属性:modifyTime
    * modify_time
    * @return modifyTime
    */
	public Integer getModifyTime () {
    	return modifyTime;
   	}
   	/**
     * 设置属性:modifyTime
     * modify_time
     * @param modifyTime
     */
    public SongXiamiQuery setModifyTime(Integer modifyTime) {
    	this.modifyTime = modifyTime;
    	return this;
    }
	/** create_time **/
    private Integer createTime;
	/**
    * 获取属性:createTime
    * create_time
    * @return createTime
    */
	public Integer getCreateTime () {
    	return createTime;
   	}
   	/**
     * 设置属性:createTime
     * create_time
     * @param createTime
     */
    public SongXiamiQuery setCreateTime(Integer createTime) {
    	this.createTime = createTime;
    	return this;
    }
	/** year **/
    private String year;
	/**
    * 获取属性:year
    * year
    * @return year
    */
	public String getYear () {
    	return year;
   	}
   	/**
     * 设置属性:year
     * year
     * @param year
     */
    public SongXiamiQuery setYear(String year) {
    	this.year = year;
    	return this;
    }
	/** 0 yes 1 no **/
    private Byte isvbr;
	/**
    * 获取属性:isvbr
    * 0 yes 1 no
    * @return isvbr
    */
	public Byte getIsvbr () {
    	return isvbr;
   	}
   	/**
     * 设置属性:isvbr
     * 0 yes 1 no
     * @param isvbr
     */
    public SongXiamiQuery setIsvbr(Byte isvbr) {
    	this.isvbr = isvbr;
    	return this;
    }
	/** 比特率 **/
    private Integer bitrate;
	/**
    * 获取属性:bitrate
    * 比特率
    * @return bitrate
    */
	public Integer getBitrate () {
    	return bitrate;
   	}
   	/**
     * 设置属性:bitrate
     * 比特率
     * @param bitrate
     */
    public SongXiamiQuery setBitrate(Integer bitrate) {
    	this.bitrate = bitrate;
    	return this;
    }
	/** album_artist **/
    private String albumArtist;
	/**
    * 获取属性:albumArtist
    * album_artist
    * @return albumArtist
    */
	public String getAlbumArtist () {
    	return albumArtist;
   	}
   	/**
     * 设置属性:albumArtist
     * album_artist
     * @param albumArtist
     */
    public SongXiamiQuery setAlbumArtist(String albumArtist) {
    	this.albumArtist = albumArtist;
    	return this;
    }
	/** file_name **/
    private String fileName;
	/**
    * 获取属性:fileName
    * file_name
    * @return fileName
    */
	public String getFileName () {
    	return fileName;
   	}
   	/**
     * 设置属性:fileName
     * file_name
     * @param fileName
     */
    public SongXiamiQuery setFileName(String fileName) {
    	this.fileName = fileName;
    	return this;
    }
	/** pcs_file_name **/
    private String pcsFileName;
	/**
    * 获取属性:pcsFileName
    * pcs_file_name
    * @return pcsFileName
    */
	public String getPcsFileName () {
    	return pcsFileName;
   	}
   	/**
     * 设置属性:pcsFileName
     * pcs_file_name
     * @param pcsFileName
     */
    public SongXiamiQuery setPcsFileName(String pcsFileName) {
    	this.pcsFileName = pcsFileName;
    	return this;
    }
	/** file_path **/
    private String filePath;
	/**
    * 获取属性:filePath
    * file_path
    * @return filePath
    */
	public String getFilePath () {
    	return filePath;
   	}
   	/**
     * 设置属性:filePath
     * file_path
     * @param filePath
     */
    public SongXiamiQuery setFilePath(String filePath) {
    	this.filePath = filePath;
    	return this;
    }
	/** song_id **/
    private Integer songId;
	/**
    * 获取属性:songId
    * song_id
    * @return songId
    */
	public Integer getSongId () {
    	return songId;
   	}
   	/**
     * 设置属性:songId
     * song_id
     * @param songId
     */
    public SongXiamiQuery setSongId(Integer songId) {
    	this.songId = songId;
    	return this;
    }
	/**==============================批量查询时的Order条件顺序设置==================================**/
	public class OrderField{
		public OrderField(String fieldName, String order) {
			super();
			this.fieldName = fieldName;
			this.order = order;
		}
		private String fieldName;
		private String order;
		public String getFieldName() {
			return fieldName;
		}
		public OrderField setFieldName(String fieldName) {
			this.fieldName = fieldName;
			return this;
		}
		public String getOrder() {
			return order;
		}
		public OrderField setOrder(String order) {
			this.order = order;
			return this;
		}
	}

	/**==============================批量查询时的Order条件顺序设置==================================**/
	/**排序列表字段**/
	private List<OrderField> orderFields = new ArrayList<OrderField>();
	/**
	 * 设置排序按属性：id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyId(boolean isAsc){
		orderFields.add(new OrderField("id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：name
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyName(boolean isAsc){
		orderFields.add(new OrderField("name",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：breif
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyBreif(boolean isAsc){
		orderFields.add(new OrderField("breif",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：album
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyAlbum(boolean isAsc){
		orderFields.add(new OrderField("album",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：artist
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyArtist(boolean isAsc){
		orderFields.add(new OrderField("artist",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：1,2,3
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyFeatArtist(boolean isAsc){
		orderFields.add(new OrderField("feat_artist",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：作词人
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyLyricist(boolean isAsc){
		orderFields.add(new OrderField("lyricist",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：作曲人
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyComposer(boolean isAsc){
		orderFields.add(new OrderField("composer",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：in second
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyDuration(boolean isAsc){
		orderFields.add(new OrderField("duration",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：cover_img
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyCoverImg(boolean isAsc){
		orderFields.add(new OrderField("cover_img",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：xiami_album_link
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyXiamiAlbumLink(boolean isAsc){
		orderFields.add(new OrderField("xiami_album_link",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：xiami_song_name
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyXiamiSongName(boolean isAsc){
		orderFields.add(new OrderField("xiami_song_name",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：xiami_song_link
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyXiamiSongLink(boolean isAsc){
		orderFields.add(new OrderField("xiami_song_link",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：xiami_artist
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyXiamiArtist(boolean isAsc){
		orderFields.add(new OrderField("xiami_artist",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：xiami_artist_link
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyXiamiArtistLink(boolean isAsc){
		orderFields.add(new OrderField("xiami_artist_link",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：xiami_lyric
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyXiamiLyric(boolean isAsc){
		orderFields.add(new OrderField("xiami_lyric",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：xiami_album
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyXiamiAlbum(boolean isAsc){
		orderFields.add(new OrderField("xiami_album",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：xiami_cover_img
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyXiamiCoverImg(boolean isAsc){
		orderFields.add(new OrderField("xiami_cover_img",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：xiami_composer
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyXiamiComposer(boolean isAsc){
		orderFields.add(new OrderField("xiami_composer",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：xiami_lyricist
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyXiamiLyricist(boolean isAsc){
		orderFields.add(new OrderField("xiami_lyricist",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：xiami_lyric_url
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyXiamiLyricUrl(boolean isAsc){
		orderFields.add(new OrderField("xiami_lyric_url",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：xiami_download_url
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyXiamiDownloadUrl(boolean isAsc){
		orderFields.add(new OrderField("xiami_download_url",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：listen_num
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyListenNum(boolean isAsc){
		orderFields.add(new OrderField("listen_num",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：share_num
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyShareNum(boolean isAsc){
		orderFields.add(new OrderField("share_num",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：comment_num
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyCommentNum(boolean isAsc){
		orderFields.add(new OrderField("comment_num",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：modify_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyModifyTime(boolean isAsc){
		orderFields.add(new OrderField("modify_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：create_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyCreateTime(boolean isAsc){
		orderFields.add(new OrderField("create_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：year
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyYear(boolean isAsc){
		orderFields.add(new OrderField("year",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：0 yes 1 no
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyIsvbr(boolean isAsc){
		orderFields.add(new OrderField("isvbr",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：比特率
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyBitrate(boolean isAsc){
		orderFields.add(new OrderField("bitrate",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：album_artist
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyAlbumArtist(boolean isAsc){
		orderFields.add(new OrderField("album_artist",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：file_name
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyFileName(boolean isAsc){
		orderFields.add(new OrderField("file_name",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：pcs_file_name
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyPcsFileName(boolean isAsc){
		orderFields.add(new OrderField("pcs_file_name",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：file_path
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbyFilePath(boolean isAsc){
		orderFields.add(new OrderField("file_path",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：song_id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongXiamiQuery orderbySongId(boolean isAsc){
		orderFields.add(new OrderField("song_id",isAsc?"ASC":"DESC"));
		return this;
	}
}
