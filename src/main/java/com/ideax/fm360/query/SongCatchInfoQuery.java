package com.ideax.fm360.query;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xxjacob
 */
public class SongCatchInfoQuery extends BaseQuery {
	
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
    public SongCatchInfoQuery setId(Integer id) {
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
    public SongCatchInfoQuery setName(String name) {
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
    public SongCatchInfoQuery setBreif(String breif) {
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
    public SongCatchInfoQuery setAlbum(String album) {
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
    public SongCatchInfoQuery setAlbumId(Integer albumId) {
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
    public SongCatchInfoQuery setArtist(String artist) {
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
    public SongCatchInfoQuery setArtistId(Integer artistId) {
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
    public SongCatchInfoQuery setFeatArtist(String featArtist) {
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
    public SongCatchInfoQuery setDuration(Integer duration) {
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
    public SongCatchInfoQuery setLyricist(String lyricist) {
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
    public SongCatchInfoQuery setLyricistId(Integer lyricistId) {
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
    public SongCatchInfoQuery setComposer(String composer) {
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
    public SongCatchInfoQuery setComposerId(Integer composerId) {
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
    public SongCatchInfoQuery setStreamUrl(String streamUrl) {
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
    public SongCatchInfoQuery setListenNum(Integer listenNum) {
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
    public SongCatchInfoQuery setShareNum(Integer shareNum) {
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
    public SongCatchInfoQuery setCommentNum(Integer commentNum) {
    	this.commentNum = commentNum;
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
    public SongCatchInfoQuery setLyric(String lyric) {
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
    public SongCatchInfoQuery setModifyTime(Integer modifyTime) {
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
    public SongCatchInfoQuery setCreateTime(Integer createTime) {
    	this.createTime = createTime;
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
	public SongCatchInfoQuery orderbyId(boolean isAsc){
		orderFields.add(new OrderField("id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：name
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyName(boolean isAsc){
		orderFields.add(new OrderField("name",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：breif
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyBreif(boolean isAsc){
		orderFields.add(new OrderField("breif",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：album
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyAlbum(boolean isAsc){
		orderFields.add(new OrderField("album",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：album_id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyAlbumId(boolean isAsc){
		orderFields.add(new OrderField("album_id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：artist
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyArtist(boolean isAsc){
		orderFields.add(new OrderField("artist",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：artist_id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyArtistId(boolean isAsc){
		orderFields.add(new OrderField("artist_id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：1,2,3
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyFeatArtist(boolean isAsc){
		orderFields.add(new OrderField("feat_artist",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：in second
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyDuration(boolean isAsc){
		orderFields.add(new OrderField("duration",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：作词人
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyLyricist(boolean isAsc){
		orderFields.add(new OrderField("lyricist",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：lyricist_id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyLyricistId(boolean isAsc){
		orderFields.add(new OrderField("lyricist_id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：作曲人
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyComposer(boolean isAsc){
		orderFields.add(new OrderField("composer",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：composer_id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyComposerId(boolean isAsc){
		orderFields.add(new OrderField("composer_id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：stream_url
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyStreamUrl(boolean isAsc){
		orderFields.add(new OrderField("stream_url",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：listen_num
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyListenNum(boolean isAsc){
		orderFields.add(new OrderField("listen_num",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：share_num
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyShareNum(boolean isAsc){
		orderFields.add(new OrderField("share_num",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：comment_num
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyCommentNum(boolean isAsc){
		orderFields.add(new OrderField("comment_num",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：lyric
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyLyric(boolean isAsc){
		orderFields.add(new OrderField("lyric",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：modify_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyModifyTime(boolean isAsc){
		orderFields.add(new OrderField("modify_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：create_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongCatchInfoQuery orderbyCreateTime(boolean isAsc){
		orderFields.add(new OrderField("create_time",isAsc?"ASC":"DESC"));
		return this;
	}
}
