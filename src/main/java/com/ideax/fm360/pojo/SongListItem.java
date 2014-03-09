package com.ideax.fm360.pojo;

import java.util.List;
import java.io.Serializable;

/**
 *
 * @author xxjacob
 */
public class SongListItem implements Serializable{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** id **/
    private Integer id;
	/** list_id **/
    private Integer listId;
	/** uid **/
    private Integer uid;
	/** sid **/
    private Integer sid;
	/** type **/
    private Byte type;
	/** create_time **/
    private Integer createTime;
	/** modify_time **/
    private Integer modifyTime;
	/** comment **/
    private String comment;

	
   /**
    * 获取属性:id
    * id
    * @return id
    */
   public Integer getId() {
       return id;
   }
   /**
    * 设置属性:id
    * id
    * @param id
    */
   public void setId(Integer id) {
       this.id = id;
   }
	
   /**
    * 获取属性:listId
    * list_id
    * @return listId
    */
   public Integer getListId() {
       return listId;
   }
   /**
    * 设置属性:listId
    * list_id
    * @param listId
    */
   public void setListId(Integer listId) {
       this.listId = listId;
   }
	
   /**
    * 获取属性:uid
    * uid
    * @return uid
    */
   public Integer getUid() {
       return uid;
   }
   /**
    * 设置属性:uid
    * uid
    * @param uid
    */
   public void setUid(Integer uid) {
       this.uid = uid;
   }
	
   /**
    * 获取属性:sid
    * sid
    * @return sid
    */
   public Integer getSid() {
       return sid;
   }
   /**
    * 设置属性:sid
    * sid
    * @param sid
    */
   public void setSid(Integer sid) {
       this.sid = sid;
   }
	
   /**
    * 获取属性:type
    * type
    * @return type
    */
   public Byte getType() {
       return type;
   }
   /**
    * 设置属性:type
    * type
    * @param type
    */
   public void setType(Byte type) {
       this.type = type;
   }
	
   /**
    * 获取属性:createTime
    * create_time
    * @return createTime
    */
   public Integer getCreateTime() {
       return createTime;
   }
   /**
    * 设置属性:createTime
    * create_time
    * @param createTime
    */
   public void setCreateTime(Integer createTime) {
       this.createTime = createTime;
   }
	
   /**
    * 获取属性:modifyTime
    * modify_time
    * @return modifyTime
    */
   public Integer getModifyTime() {
       return modifyTime;
   }
   /**
    * 设置属性:modifyTime
    * modify_time
    * @param modifyTime
    */
   public void setModifyTime(Integer modifyTime) {
       this.modifyTime = modifyTime;
   }
	
   /**
    * 获取属性:comment
    * comment
    * @return comment
    */
   public String getComment() {
       return comment;
   }
   /**
    * 设置属性:comment
    * comment
    * @param comment
    */
   public void setComment(String comment) {
       this.comment = comment;
   }

	/**
     * 需要更新时的代码，keys 代表主键list
     */
	private List<Long> keys;
		
	public List<Long> getKeys() {
		return keys;
	}
	
	public void setIds(List<Long> keys) {
		this.keys = keys;
	}
}