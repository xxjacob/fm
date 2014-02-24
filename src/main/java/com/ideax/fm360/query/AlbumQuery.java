package com.ideax.fm360.query;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xxjacob
 */
public class AlbumQuery extends BaseQuery {
	
	/** ====================查询唯一单条记录使用==========================**/
	
	/**==============================批量查询、更新、删除时的Where条件设置==================================**/
	/** aid **/
    private Integer aid;
	/**
    * 获取属性:aid
    * aid
    * @return aid
    */
	public Integer getAid () {
    	return aid;
   	}
   	/**
     * 设置属性:aid
     * aid
     * @param aid
     */
    public AlbumQuery setAid(Integer aid) {
    	this.aid = aid;
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
    public AlbumQuery setName(String name) {
    	this.name = name;
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
    public AlbumQuery setArtistId(Integer artistId) {
    	this.artistId = artistId;
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
    public AlbumQuery setBreif(String breif) {
    	this.breif = breif;
    	return this;
    }
	/** yyyymmdd **/
    private Integer releaseTime;
	/**
    * 获取属性:releaseTime
    * yyyymmdd
    * @return releaseTime
    */
	public Integer getReleaseTime () {
    	return releaseTime;
   	}
   	/**
     * 设置属性:releaseTime
     * yyyymmdd
     * @param releaseTime
     */
    public AlbumQuery setReleaseTime(Integer releaseTime) {
    	this.releaseTime = releaseTime;
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
    public AlbumQuery setCoverImg(String coverImg) {
    	this.coverImg = coverImg;
    	return this;
    }
	/** 逗号分隔的音乐 **/
    private String musics;
	/**
    * 获取属性:musics
    * 逗号分隔的音乐
    * @return musics
    */
	public String getMusics () {
    	return musics;
   	}
   	/**
     * 设置属性:musics
     * 逗号分隔的音乐
     * @param musics
     */
    public AlbumQuery setMusics(String musics) {
    	this.musics = musics;
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
	 * 设置排序按属性：aid
	 * @param isAsc 是否升序，否则为降序
	 */	
	public AlbumQuery orderbyAid(boolean isAsc){
		orderFields.add(new OrderField("aid",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：name
	 * @param isAsc 是否升序，否则为降序
	 */	
	public AlbumQuery orderbyName(boolean isAsc){
		orderFields.add(new OrderField("name",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：artist_id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public AlbumQuery orderbyArtistId(boolean isAsc){
		orderFields.add(new OrderField("artist_id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：breif
	 * @param isAsc 是否升序，否则为降序
	 */	
	public AlbumQuery orderbyBreif(boolean isAsc){
		orderFields.add(new OrderField("breif",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：yyyymmdd
	 * @param isAsc 是否升序，否则为降序
	 */	
	public AlbumQuery orderbyReleaseTime(boolean isAsc){
		orderFields.add(new OrderField("release_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：cover_img
	 * @param isAsc 是否升序，否则为降序
	 */	
	public AlbumQuery orderbyCoverImg(boolean isAsc){
		orderFields.add(new OrderField("cover_img",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：逗号分隔的音乐
	 * @param isAsc 是否升序，否则为降序
	 */	
	public AlbumQuery orderbyMusics(boolean isAsc){
		orderFields.add(new OrderField("musics",isAsc?"ASC":"DESC"));
		return this;
	}
}
