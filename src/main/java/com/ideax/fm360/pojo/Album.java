package com.ideax.fm360.pojo;

import java.util.List;
import java.io.Serializable;

/**
 *
 * @author xxjacob
 */
public class Album implements Serializable{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** aid **/
    private Integer aid;
	/** name **/
    private String name;
	/** artist_id **/
    private Integer artistId;
	/** breif **/
    private String breif;
	/** yyyymmdd **/
    private Integer releaseTime;
	/** cover_img **/
    private String coverImg;
	/** 逗号分隔的音乐 **/
    private String musics;

	
   /**
    * 获取属性:aid
    * aid
    * @return aid
    */
   public Integer getAid() {
       return aid;
   }
   /**
    * 设置属性:aid
    * aid
    * @param aid
    */
   public void setAid(Integer aid) {
       this.aid = aid;
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
    * 获取属性:artistId
    * artist_id
    * @return artistId
    */
   public Integer getArtistId() {
       return artistId;
   }
   /**
    * 设置属性:artistId
    * artist_id
    * @param artistId
    */
   public void setArtistId(Integer artistId) {
       this.artistId = artistId;
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
    * 获取属性:releaseTime
    * yyyymmdd
    * @return releaseTime
    */
   public Integer getReleaseTime() {
       return releaseTime;
   }
   /**
    * 设置属性:releaseTime
    * yyyymmdd
    * @param releaseTime
    */
   public void setReleaseTime(Integer releaseTime) {
       this.releaseTime = releaseTime;
   }
	
   /**
    * 获取属性:coverImg
    * cover_img
    * @return coverImg
    */
   public String getCoverImg() {
       return coverImg;
   }
   /**
    * 设置属性:coverImg
    * cover_img
    * @param coverImg
    */
   public void setCoverImg(String coverImg) {
       this.coverImg = coverImg;
   }
	
   /**
    * 获取属性:musics
    * 逗号分隔的音乐
    * @return musics
    */
   public String getMusics() {
       return musics;
   }
   /**
    * 设置属性:musics
    * 逗号分隔的音乐
    * @param musics
    */
   public void setMusics(String musics) {
       this.musics = musics;
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