package com.ideax.fm360.query;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xxjacob
 */
public class SongListQuery extends BaseQuery {
	
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
    public SongListQuery setId(Integer id) {
    	this.id = id;
    	return this;
    }
	/** uid **/
    private Integer uid;
	/**
    * 获取属性:uid
    * uid
    * @return uid
    */
	public Integer getUid () {
    	return uid;
   	}
   	/**
     * 设置属性:uid
     * uid
     * @param uid
     */
    public SongListQuery setUid(Integer uid) {
    	this.uid = uid;
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
    public SongListQuery setName(String name) {
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
    public SongListQuery setBreif(String breif) {
    	this.breif = breif;
    	return this;
    }
	/** 1、点赞歌单； 2踩掉歌单 **/
    private Byte type;
	/**
    * 获取属性:type
    * 1、点赞歌单； 2踩掉歌单
    * @return type
    */
	public Byte getType () {
    	return type;
   	}
   	/**
     * 设置属性:type
     * 1、点赞歌单； 2踩掉歌单
     * @param type
     */
    public SongListQuery setType(Byte type) {
    	this.type = type;
    	return this;
    }
	/** 1,2,3 **/
    private String songs;
	/**
    * 获取属性:songs
    * 1,2,3
    * @return songs
    */
	public String getSongs () {
    	return songs;
   	}
   	/**
     * 设置属性:songs
     * 1,2,3
     * @param songs
     */
    public SongListQuery setSongs(String songs) {
    	this.songs = songs;
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
    public SongListQuery setCreateTime(Integer createTime) {
    	this.createTime = createTime;
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
    public SongListQuery setModifyTime(Integer modifyTime) {
    	this.modifyTime = modifyTime;
    	return this;
    }
	/** extra **/
    private String extra;
	/**
    * 获取属性:extra
    * extra
    * @return extra
    */
	public String getExtra () {
    	return extra;
   	}
   	/**
     * 设置属性:extra
     * extra
     * @param extra
     */
    public SongListQuery setExtra(String extra) {
    	this.extra = extra;
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
	public SongListQuery orderbyId(boolean isAsc){
		orderFields.add(new OrderField("id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：uid
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListQuery orderbyUid(boolean isAsc){
		orderFields.add(new OrderField("uid",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：name
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListQuery orderbyName(boolean isAsc){
		orderFields.add(new OrderField("name",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：breif
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListQuery orderbyBreif(boolean isAsc){
		orderFields.add(new OrderField("breif",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：1、点赞歌单； 2踩掉歌单
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListQuery orderbyType(boolean isAsc){
		orderFields.add(new OrderField("type",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：1,2,3
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListQuery orderbySongs(boolean isAsc){
		orderFields.add(new OrderField("songs",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：create_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListQuery orderbyCreateTime(boolean isAsc){
		orderFields.add(new OrderField("create_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：modify_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListQuery orderbyModifyTime(boolean isAsc){
		orderFields.add(new OrderField("modify_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：extra
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListQuery orderbyExtra(boolean isAsc){
		orderFields.add(new OrderField("extra",isAsc?"ASC":"DESC"));
		return this;
	}
}
