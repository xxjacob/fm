package com.ideax.fm360.pojo;

import java.util.List;
import java.io.Serializable;

/**
 *
 * @author xxjacob
 */
public class SongCatchInfo implements Serializable{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** id **/
    private Integer id;
	/** name **/
    private String name;
	/** breif **/
    private String breif;
	/** album **/
    private String album;
	/** album_id **/
    private Integer albumId;
	/** artist **/
    private String artist;
	/** artist_id **/
    private Integer artistId;
	/** 1,2,3 **/
    private String featArtist;
	/** in second **/
    private Integer duration;
	/** 作词人 **/
    private String lyricist;
	/** lyricist_id **/
    private Integer lyricistId;
	/** 作曲人 **/
    private String composer;
	/** composer_id **/
    private Integer composerId;
	/** stream_url **/
    private String streamUrl;
	/** listen_num **/
    private Integer listenNum;
	/** share_num **/
    private Integer shareNum;
	/** comment_num **/
    private Integer commentNum;
	/** lyric **/
    private String lyric;
	/** modify_time **/
    private Integer modifyTime;
	/** create_time **/
    private Integer createTime;

	
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
    * 获取属性:album
    * album
    * @return album
    */
   public String getAlbum() {
       return album;
   }
   /**
    * 设置属性:album
    * album
    * @param album
    */
   public void setAlbum(String album) {
       this.album = album;
   }
	
   /**
    * 获取属性:albumId
    * album_id
    * @return albumId
    */
   public Integer getAlbumId() {
       return albumId;
   }
   /**
    * 设置属性:albumId
    * album_id
    * @param albumId
    */
   public void setAlbumId(Integer albumId) {
       this.albumId = albumId;
   }
	
   /**
    * 获取属性:artist
    * artist
    * @return artist
    */
   public String getArtist() {
       return artist;
   }
   /**
    * 设置属性:artist
    * artist
    * @param artist
    */
   public void setArtist(String artist) {
       this.artist = artist;
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
    * 获取属性:featArtist
    * 1,2,3
    * @return featArtist
    */
   public String getFeatArtist() {
       return featArtist;
   }
   /**
    * 设置属性:featArtist
    * 1,2,3
    * @param featArtist
    */
   public void setFeatArtist(String featArtist) {
       this.featArtist = featArtist;
   }
	
   /**
    * 获取属性:duration
    * in second
    * @return duration
    */
   public Integer getDuration() {
       return duration;
   }
   /**
    * 设置属性:duration
    * in second
    * @param duration
    */
   public void setDuration(Integer duration) {
       this.duration = duration;
   }
	
   /**
    * 获取属性:lyricist
    * 作词人
    * @return lyricist
    */
   public String getLyricist() {
       return lyricist;
   }
   /**
    * 设置属性:lyricist
    * 作词人
    * @param lyricist
    */
   public void setLyricist(String lyricist) {
       this.lyricist = lyricist;
   }
	
   /**
    * 获取属性:lyricistId
    * lyricist_id
    * @return lyricistId
    */
   public Integer getLyricistId() {
       return lyricistId;
   }
   /**
    * 设置属性:lyricistId
    * lyricist_id
    * @param lyricistId
    */
   public void setLyricistId(Integer lyricistId) {
       this.lyricistId = lyricistId;
   }
	
   /**
    * 获取属性:composer
    * 作曲人
    * @return composer
    */
   public String getComposer() {
       return composer;
   }
   /**
    * 设置属性:composer
    * 作曲人
    * @param composer
    */
   public void setComposer(String composer) {
       this.composer = composer;
   }
	
   /**
    * 获取属性:composerId
    * composer_id
    * @return composerId
    */
   public Integer getComposerId() {
       return composerId;
   }
   /**
    * 设置属性:composerId
    * composer_id
    * @param composerId
    */
   public void setComposerId(Integer composerId) {
       this.composerId = composerId;
   }
	
   /**
    * 获取属性:streamUrl
    * stream_url
    * @return streamUrl
    */
   public String getStreamUrl() {
       return streamUrl;
   }
   /**
    * 设置属性:streamUrl
    * stream_url
    * @param streamUrl
    */
   public void setStreamUrl(String streamUrl) {
       this.streamUrl = streamUrl;
   }
	
   /**
    * 获取属性:listenNum
    * listen_num
    * @return listenNum
    */
   public Integer getListenNum() {
       return listenNum;
   }
   /**
    * 设置属性:listenNum
    * listen_num
    * @param listenNum
    */
   public void setListenNum(Integer listenNum) {
       this.listenNum = listenNum;
   }
	
   /**
    * 获取属性:shareNum
    * share_num
    * @return shareNum
    */
   public Integer getShareNum() {
       return shareNum;
   }
   /**
    * 设置属性:shareNum
    * share_num
    * @param shareNum
    */
   public void setShareNum(Integer shareNum) {
       this.shareNum = shareNum;
   }
	
   /**
    * 获取属性:commentNum
    * comment_num
    * @return commentNum
    */
   public Integer getCommentNum() {
       return commentNum;
   }
   /**
    * 设置属性:commentNum
    * comment_num
    * @param commentNum
    */
   public void setCommentNum(Integer commentNum) {
       this.commentNum = commentNum;
   }
	
   /**
    * 获取属性:lyric
    * lyric
    * @return lyric
    */
   public String getLyric() {
       return lyric;
   }
   /**
    * 设置属性:lyric
    * lyric
    * @param lyric
    */
   public void setLyric(String lyric) {
       this.lyric = lyric;
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