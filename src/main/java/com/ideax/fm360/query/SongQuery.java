package com.ideax.fm360.query;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xxjacob
 */
public class SongQuery extends BaseQuery {
	
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
    public SongQuery setId(Integer id) {
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
    public SongQuery setName(String name) {
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
    public SongQuery setBreif(String breif) {
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
    public SongQuery setAlbum(String album) {
    	this.album = album;
    	return this;
    }
	/** album_id **/
    private Integer albumId;
	/**
    * 获取属性:albumId
    * album_id
    * @return albumId
    */
	public Integer getAlbumId () {
    	return albumId;
   	}
   	/**
     * 设置属性:albumId
     * album_id
     * @param albumId
     */
    public SongQuery setAlbumId(Integer albumId) {
    	this.albumId = albumId;
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
    public SongQuery setArtist(String artist) {
    	this.artist = artist;
    	return this;
    }
	/** artist_id **/
    private Integer artistId;
	/**
    * 获取属性:artistId
    * artist_id
    * @return artistId
    */
	public Integer getArtistId () {
    	return artistId;
   	}
   	/**
     * 设置属性:artistId
     * artist_id
     * @param artistId
     */
    public SongQuery setArtistId(Integer artistId) {
    	this.artistId = artistId;
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
    public SongQuery setFeatArtist(String featArtist) {
    	this.featArtist = featArtist;
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
    public SongQuery setDuration(Integer duration) {
    	this.duration = duration;
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
    public SongQuery setLyricist(String lyricist) {
    	this.lyricist = lyricist;
    	return this;
    }
	/** lyricist_id **/
    private Integer lyricistId;
	/**
    * 获取属性:lyricistId
    * lyricist_id
    * @return lyricistId
    */
	public Integer getLyricistId () {
    	return lyricistId;
   	}
   	/**
     * 设置属性:lyricistId
     * lyricist_id
     * @param lyricistId
     */
    public SongQuery setLyricistId(Integer lyricistId) {
    	this.lyricistId = lyricistId;
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
    public SongQuery setComposer(String composer) {
    	this.composer = composer;
    	return this;
    }
	/** composer_id **/
    private Integer composerId;
	/**
    * 获取属性:composerId
    * composer_id
    * @return composerId
    */
	public Integer getComposerId () {
    	return composerId;
   	}
   	/**
     * 设置属性:composerId
     * composer_id
     * @param composerId
     */
    public SongQuery setComposerId(Integer composerId) {
    	this.composerId = composerId;
    	return this;
    }
	/** stream_url **/
    private String streamUrl;
	/**
    * 获取属性:streamUrl
    * stream_url
    * @return streamUrl
    */
	public String getStreamUrl () {
    	return streamUrl;
   	}
   	/**
     * 设置属性:streamUrl
     * stream_url
     * @param streamUrl
     */
    public SongQuery setStreamUrl(String streamUrl) {
    	this.streamUrl = streamUrl;
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
    public SongQuery setListenNum(Integer listenNum) {
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
    public SongQuery setShareNum(Integer shareNum) {
    	this.shareNum = shareNum;
    	return this;
    }
	/** 点赞 **/
    private Integer thumbNum;
	/**
    * 获取属性:thumbNum
    * 点赞
    * @return thumbNum
    */
	public Integer getThumbNum () {
    	return thumbNum;
   	}
   	/**
     * 设置属性:thumbNum
     * 点赞
     * @param thumbNum
     */
    public SongQuery setThumbNum(Integer thumbNum) {
    	this.thumbNum = thumbNum;
    	return this;
    }
	/** 点踩 **/
    private Integer buryNum;
	/**
    * 获取属性:buryNum
    * 点踩
    * @return buryNum
    */
	public Integer getBuryNum () {
    	return buryNum;
   	}
   	/**
     * 设置属性:buryNum
     * 点踩
     * @param buryNum
     */
    public SongQuery setBuryNum(Integer buryNum) {
    	this.buryNum = buryNum;
    	return this;
    }
	/** lyric **/
    private String lyric;
	/**
    * 获取属性:lyric
    * lyric
    * @return lyric
    */
	public String getLyric () {
    	return lyric;
   	}
   	/**
     * 设置属性:lyric
     * lyric
     * @param lyric
     */
    public SongQuery setLyric(String lyric) {
    	this.lyric = lyric;
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
    public SongQuery setModifyTime(Integer modifyTime) {
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
    public SongQuery setCreateTime(Integer createTime) {
    	this.createTime = createTime;
    	return this;
    }
	/** pcs_filename **/
    private String pcsFilename;
	/**
    * 获取属性:pcsFilename
    * pcs_filename
    * @return pcsFilename
    */
	public String getPcsFilename () {
    	return pcsFilename;
   	}
   	/**
     * 设置属性:pcsFilename
     * pcs_filename
     * @param pcsFilename
     */
    public SongQuery setPcsFilename(String pcsFilename) {
    	this.pcsFilename = pcsFilename;
    	return this;
    }
	/** sx_id **/
    private Integer sxId;
	/**
    * 获取属性:sxId
    * sx_id
    * @return sxId
    */
	public Integer getSxId () {
    	return sxId;
   	}
   	/**
     * 设置属性:sxId
     * sx_id
     * @param sxId
     */
    public SongQuery setSxId(Integer sxId) {
    	this.sxId = sxId;
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
    public SongQuery setCoverImg(String coverImg) {
    	this.coverImg = coverImg;
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
	public SongQuery orderbyId(boolean isAsc){
		orderFields.add(new OrderField("id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：name
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyName(boolean isAsc){
		orderFields.add(new OrderField("name",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：breif
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyBreif(boolean isAsc){
		orderFields.add(new OrderField("breif",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：album
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyAlbum(boolean isAsc){
		orderFields.add(new OrderField("album",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：album_id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyAlbumId(boolean isAsc){
		orderFields.add(new OrderField("album_id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：artist
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyArtist(boolean isAsc){
		orderFields.add(new OrderField("artist",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：artist_id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyArtistId(boolean isAsc){
		orderFields.add(new OrderField("artist_id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：1,2,3
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyFeatArtist(boolean isAsc){
		orderFields.add(new OrderField("feat_artist",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：in second
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyDuration(boolean isAsc){
		orderFields.add(new OrderField("duration",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：作词人
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyLyricist(boolean isAsc){
		orderFields.add(new OrderField("lyricist",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：lyricist_id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyLyricistId(boolean isAsc){
		orderFields.add(new OrderField("lyricist_id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：作曲人
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyComposer(boolean isAsc){
		orderFields.add(new OrderField("composer",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：composer_id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyComposerId(boolean isAsc){
		orderFields.add(new OrderField("composer_id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：stream_url
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyStreamUrl(boolean isAsc){
		orderFields.add(new OrderField("stream_url",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：listen_num
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyListenNum(boolean isAsc){
		orderFields.add(new OrderField("listen_num",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：share_num
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyShareNum(boolean isAsc){
		orderFields.add(new OrderField("share_num",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：点赞
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyThumbNum(boolean isAsc){
		orderFields.add(new OrderField("thumb_num",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：点踩
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyBuryNum(boolean isAsc){
		orderFields.add(new OrderField("bury_num",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：lyric
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyLyric(boolean isAsc){
		orderFields.add(new OrderField("lyric",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：modify_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyModifyTime(boolean isAsc){
		orderFields.add(new OrderField("modify_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：create_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyCreateTime(boolean isAsc){
		orderFields.add(new OrderField("create_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：pcs_filename
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyPcsFilename(boolean isAsc){
		orderFields.add(new OrderField("pcs_filename",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：sx_id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbySxId(boolean isAsc){
		orderFields.add(new OrderField("sx_id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：cover_img
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongQuery orderbyCoverImg(boolean isAsc){
		orderFields.add(new OrderField("cover_img",isAsc?"ASC":"DESC"));
		return this;
	}
}
