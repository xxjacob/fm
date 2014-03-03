package com.ideax.fm360.pojo;

import java.util.List;
import java.io.Serializable;

/**
 *
 * @author xxjacob
 */
public class SongList implements Serializable{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** id **/
    private Integer id;
	/** uid **/
    private Integer uid;
	/** name **/
    private String name;
	/** breif **/
    private String breif;
	/** 1、点赞歌单； 2踩掉歌单 **/
    private Byte type;
	/** 1,2,3 **/
    private String songs;
	/** create_time **/
    private Integer createTime;
	/** modify_time **/
    private Integer modifyTime;
	/** extra **/
    private String extra;

	
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
    * 获取属性:name
    * name
    * @return name
    */
   public String getName() {
       return name;
   }
   /**
    * 设置属性:name
    * name
    * @param name
    */
   public void setName(String name) {
       this.name = name;
   }
	
   /**
    * 获取属性:breif
    * breif
    * @return breif
    */
   public String getBreif() {
       return breif;
   }
   /**
    * 设置属性:breif
    * breif
    * @param breif
    */
   public void setBreif(String breif) {
       this.breif = breif;
   }
	
   /**
    * 获取属性:type
    * 1、点赞歌单； 2踩掉歌单
    * @return type
    */
   public Byte getType() {
       return type;
   }
   /**
    * 设置属性:type
    * 1、点赞歌单； 2踩掉歌单
    * @param type
    */
   public void setType(Byte type) {
       this.type = type;
   }
	
   /**
    * 获取属性:songs
    * 1,2,3
    * @return songs
    */
   public String getSongs() {
       return songs;
   }
   /**
    * 设置属性:songs
    * 1,2,3
    * @param songs
    */
   public void setSongs(String songs) {
       this.songs = songs;
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
    * 获取属性:extra
    * extra
    * @return extra
    */
   public String getExtra() {
       return extra;
   }
   /**
    * 设置属性:extra
    * extra
    * @param extra
    */
   public void setExtra(String extra) {
       this.extra = extra;
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