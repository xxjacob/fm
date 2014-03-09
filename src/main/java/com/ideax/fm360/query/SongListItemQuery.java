package com.ideax.fm360.query;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xxjacob
 */
public class SongListItemQuery extends BaseQuery {
	
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
    public SongListItemQuery setId(Integer id) {
    	this.id = id;
    	return this;
    }
	/** list_id **/
    private Integer listId;
	/**
    * 获取属性:listId
    * list_id
    * @return listId
    */
	public Integer getListId () {
    	return listId;
   	}
   	/**
     * 设置属性:listId
     * list_id
     * @param listId
     */
    public SongListItemQuery setListId(Integer listId) {
    	this.listId = listId;
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
    public SongListItemQuery setUid(Integer uid) {
    	this.uid = uid;
    	return this;
    }
	/** sid **/
    private Integer sid;
	/**
    * 获取属性:sid
    * sid
    * @return sid
    */
	public Integer getSid () {
    	return sid;
   	}
   	/**
     * 设置属性:sid
     * sid
     * @param sid
     */
    public SongListItemQuery setSid(Integer sid) {
    	this.sid = sid;
    	return this;
    }
	/** type **/
    private Byte type;
	/**
    * 获取属性:type
    * type
    * @return type
    */
	public Byte getType () {
    	return type;
   	}
   	/**
     * 设置属性:type
     * type
     * @param type
     */
    public SongListItemQuery setType(Byte type) {
    	this.type = type;
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
    public SongListItemQuery setCreateTime(Integer createTime) {
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
    public SongListItemQuery setModifyTime(Integer modifyTime) {
    	this.modifyTime = modifyTime;
    	return this;
    }
	/** comment **/
    private String comment;
	/**
    * 获取属性:comment
    * comment
    * @return comment
    */
	public String getComment () {
    	return comment;
   	}
   	/**
     * 设置属性:comment
     * comment
     * @param comment
     */
    public SongListItemQuery setComment(String comment) {
    	this.comment = comment;
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
	public SongListItemQuery orderbyId(boolean isAsc){
		orderFields.add(new OrderField("id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：list_id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListItemQuery orderbyListId(boolean isAsc){
		orderFields.add(new OrderField("list_id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：uid
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListItemQuery orderbyUid(boolean isAsc){
		orderFields.add(new OrderField("uid",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：sid
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListItemQuery orderbySid(boolean isAsc){
		orderFields.add(new OrderField("sid",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：type
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListItemQuery orderbyType(boolean isAsc){
		orderFields.add(new OrderField("type",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：create_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListItemQuery orderbyCreateTime(boolean isAsc){
		orderFields.add(new OrderField("create_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：modify_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListItemQuery orderbyModifyTime(boolean isAsc){
		orderFields.add(new OrderField("modify_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：comment
	 * @param isAsc 是否升序，否则为降序
	 */	
	public SongListItemQuery orderbyComment(boolean isAsc){
		orderFields.add(new OrderField("comment",isAsc?"ASC":"DESC"));
		return this;
	}
}
