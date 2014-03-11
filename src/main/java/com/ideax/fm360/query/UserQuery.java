package com.ideax.fm360.query;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xxjacob
 */
public class UserQuery extends BaseQuery {
	
	/** ====================查询唯一单条记录使用==========================**/
	
	/**==============================批量查询、更新、删除时的Where条件设置==================================**/
	/** uid **/
    private Integer id;
	/**
    * 获取属性:id
    * uid
    * @return id
    */
	public Integer getId () {
    	return id;
   	}
   	/**
     * 设置属性:id
     * uid
     * @param id
     */
    public UserQuery setId(Integer id) {
    	this.id = id;
    	return this;
    }
	/** email **/
    private String email;
	/**
    * 获取属性:email
    * email
    * @return email
    */
	public String getEmail () {
    	return email;
   	}
   	/**
     * 设置属性:email
     * email
     * @param email
     */
    public UserQuery setEmail(String email) {
    	this.email = email;
    	return this;
    }
	/** password **/
    private String password;
	/**
    * 获取属性:password
    * password
    * @return password
    */
	public String getPassword () {
    	return password;
   	}
   	/**
     * 设置属性:password
     * password
     * @param password
     */
    public UserQuery setPassword(String password) {
    	this.password = password;
    	return this;
    }
	/** username **/
    private String username;
	/**
    * 获取属性:username
    * username
    * @return username
    */
	public String getUsername () {
    	return username;
   	}
   	/**
     * 设置属性:username
     * username
     * @param username
     */
    public UserQuery setUsername(String username) {
    	this.username = username;
    	return this;
    }
	/** 昵称 **/
    private String nickname;
	/**
    * 获取属性:nickname
    * 昵称
    * @return nickname
    */
	public String getNickname () {
    	return nickname;
   	}
   	/**
     * 设置属性:nickname
     * 昵称
     * @param nickname
     */
    public UserQuery setNickname(String nickname) {
    	this.nickname = nickname;
    	return this;
    }
	/** 性别 **/
    private Byte gender;
	/**
    * 获取属性:gender
    * 性别
    * @return gender
    */
	public Byte getGender () {
    	return gender;
   	}
   	/**
     * 设置属性:gender
     * 性别
     * @param gender
     */
    public UserQuery setGender(Byte gender) {
    	this.gender = gender;
    	return this;
    }
	/** 出生年 **/
    private Integer birthY;
	/**
    * 获取属性:birthY
    * 出生年
    * @return birthY
    */
	public Integer getBirthY () {
    	return birthY;
   	}
   	/**
     * 设置属性:birthY
     * 出生年
     * @param birthY
     */
    public UserQuery setBirthY(Integer birthY) {
    	this.birthY = birthY;
    	return this;
    }
	/** 出生月日 1005 **/
    private Integer birthMd;
	/**
    * 获取属性:birthMd
    * 出生月日 1005
    * @return birthMd
    */
	public Integer getBirthMd () {
    	return birthMd;
   	}
   	/**
     * 设置属性:birthMd
     * 出生月日 1005
     * @param birthMd
     */
    public UserQuery setBirthMd(Integer birthMd) {
    	this.birthMd = birthMd;
    	return this;
    }
	/** 头像 **/
    private String figureurl;
	/**
    * 获取属性:figureurl
    * 头像
    * @return figureurl
    */
	public String getFigureurl () {
    	return figureurl;
   	}
   	/**
     * 设置属性:figureurl
     * 头像
     * @param figureurl
     */
    public UserQuery setFigureurl(String figureurl) {
    	this.figureurl = figureurl;
    	return this;
    }
	/** 大头像 **/
    private String figureurl2;
	/**
    * 获取属性:figureurl2
    * 大头像
    * @return figureurl2
    */
	public String getFigureurl2 () {
    	return figureurl2;
   	}
   	/**
     * 设置属性:figureurl2
     * 大头像
     * @param figureurl2
     */
    public UserQuery setFigureurl2(String figureurl2) {
    	this.figureurl2 = figureurl2;
    	return this;
    }
	/** 区域代码ppcccccctttttt **/
    private Long region;
	/**
    * 获取属性:region
    * 区域代码ppcccccctttttt
    * @return region
    */
	public Long getRegion () {
    	return region;
   	}
   	/**
     * 设置属性:region
     * 区域代码ppcccccctttttt
     * @param region
     */
    public UserQuery setRegion(Long region) {
    	this.region = region;
    	return this;
    }
	/** 简介 **/
    private String introduce;
	/**
    * 获取属性:introduce
    * 简介
    * @return introduce
    */
	public String getIntroduce () {
    	return introduce;
   	}
   	/**
     * 设置属性:introduce
     * 简介
     * @param introduce
     */
    public UserQuery setIntroduce(String introduce) {
    	this.introduce = introduce;
    	return this;
    }
	/** 学历,1 高中，2大专，3本科，4硕士，5博士，6博士后 **/
    private Byte education;
	/**
    * 获取属性:education
    * 学历,1 高中，2大专，3本科，4硕士，5博士，6博士后
    * @return education
    */
	public Byte getEducation () {
    	return education;
   	}
   	/**
     * 设置属性:education
     * 学历,1 高中，2大专，3本科，4硕士，5博士，6博士后
     * @param education
     */
    public UserQuery setEducation(Byte education) {
    	this.education = education;
    	return this;
    }
	/** 收入 **/
    private Byte earning;
	/**
    * 获取属性:earning
    * 收入
    * @return earning
    */
	public Byte getEarning () {
    	return earning;
   	}
   	/**
     * 设置属性:earning
     * 收入
     * @param earning
     */
    public UserQuery setEarning(Byte earning) {
    	this.earning = earning;
    	return this;
    }
	/** profession **/
    private Byte profession;
	/**
    * 获取属性:profession
    * profession
    * @return profession
    */
	public Byte getProfession () {
    	return profession;
   	}
   	/**
     * 设置属性:profession
     * profession
     * @param profession
     */
    public UserQuery setProfession(Byte profession) {
    	this.profession = profession;
    	return this;
    }
	/** 0 未激活,1 未完善, 2,可用 **/
    private Byte status;
	/**
    * 获取属性:status
    * 0 未激活,1 未完善, 2,可用
    * @return status
    */
	public Byte getStatus () {
    	return status;
   	}
   	/**
     * 设置属性:status
     * 0 未激活,1 未完善, 2,可用
     * @param status
     */
    public UserQuery setStatus(Byte status) {
    	this.status = status;
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
    public UserQuery setCreateTime(Integer createTime) {
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
    public UserQuery setModifyTime(Integer modifyTime) {
    	this.modifyTime = modifyTime;
    	return this;
    }
	/** 邀请者 **/
    private String qqId;
	/**
    * 获取属性:qqId
    * 邀请者
    * @return qqId
    */
	public String getQqId () {
    	return qqId;
   	}
   	/**
     * 设置属性:qqId
     * 邀请者
     * @param qqId
     */
    public UserQuery setQqId(String qqId) {
    	this.qqId = qqId;
    	return this;
    }
	/** qq_token **/
    private String qqToken;
	/**
    * 获取属性:qqToken
    * qq_token
    * @return qqToken
    */
	public String getQqToken () {
    	return qqToken;
   	}
   	/**
     * 设置属性:qqToken
     * qq_token
     * @param qqToken
     */
    public UserQuery setQqToken(String qqToken) {
    	this.qqToken = qqToken;
    	return this;
    }
	/** qq_token_time **/
    private Integer qqTokenTime;
	/**
    * 获取属性:qqTokenTime
    * qq_token_time
    * @return qqTokenTime
    */
	public Integer getQqTokenTime () {
    	return qqTokenTime;
   	}
   	/**
     * 设置属性:qqTokenTime
     * qq_token_time
     * @param qqTokenTime
     */
    public UserQuery setQqTokenTime(Integer qqTokenTime) {
    	this.qqTokenTime = qqTokenTime;
    	return this;
    }
	/** weibo_id **/
    private String weiboId;
	/**
    * 获取属性:weiboId
    * weibo_id
    * @return weiboId
    */
	public String getWeiboId () {
    	return weiboId;
   	}
   	/**
     * 设置属性:weiboId
     * weibo_id
     * @param weiboId
     */
    public UserQuery setWeiboId(String weiboId) {
    	this.weiboId = weiboId;
    	return this;
    }
	/** weibo_token **/
    private String weiboToken;
	/**
    * 获取属性:weiboToken
    * weibo_token
    * @return weiboToken
    */
	public String getWeiboToken () {
    	return weiboToken;
   	}
   	/**
     * 设置属性:weiboToken
     * weibo_token
     * @param weiboToken
     */
    public UserQuery setWeiboToken(String weiboToken) {
    	this.weiboToken = weiboToken;
    	return this;
    }
	/** weibo_token_time **/
    private Integer weiboTokenTime;
	/**
    * 获取属性:weiboTokenTime
    * weibo_token_time
    * @return weiboTokenTime
    */
	public Integer getWeiboTokenTime () {
    	return weiboTokenTime;
   	}
   	/**
     * 设置属性:weiboTokenTime
     * weibo_token_time
     * @param weiboTokenTime
     */
    public UserQuery setWeiboTokenTime(Integer weiboTokenTime) {
    	this.weiboTokenTime = weiboTokenTime;
    	return this;
    }
	/** douban_id **/
    private String doubanId;
	/**
    * 获取属性:doubanId
    * douban_id
    * @return doubanId
    */
	public String getDoubanId () {
    	return doubanId;
   	}
   	/**
     * 设置属性:doubanId
     * douban_id
     * @param doubanId
     */
    public UserQuery setDoubanId(String doubanId) {
    	this.doubanId = doubanId;
    	return this;
    }
	/** douban_token **/
    private String doubanToken;
	/**
    * 获取属性:doubanToken
    * douban_token
    * @return doubanToken
    */
	public String getDoubanToken () {
    	return doubanToken;
   	}
   	/**
     * 设置属性:doubanToken
     * douban_token
     * @param doubanToken
     */
    public UserQuery setDoubanToken(String doubanToken) {
    	this.doubanToken = doubanToken;
    	return this;
    }
	/** douban_token_time **/
    private Integer doubanTokenTime;
	/**
    * 获取属性:doubanTokenTime
    * douban_token_time
    * @return doubanTokenTime
    */
	public Integer getDoubanTokenTime () {
    	return doubanTokenTime;
   	}
   	/**
     * 设置属性:doubanTokenTime
     * douban_token_time
     * @param doubanTokenTime
     */
    public UserQuery setDoubanTokenTime(Integer doubanTokenTime) {
    	this.doubanTokenTime = doubanTokenTime;
    	return this;
    }
	/** douban_refresh_token **/
    private String doubanRefreshToken;
	/**
    * 获取属性:doubanRefreshToken
    * douban_refresh_token
    * @return doubanRefreshToken
    */
	public String getDoubanRefreshToken () {
    	return doubanRefreshToken;
   	}
   	/**
     * 设置属性:doubanRefreshToken
     * douban_refresh_token
     * @param doubanRefreshToken
     */
    public UserQuery setDoubanRefreshToken(String doubanRefreshToken) {
    	this.doubanRefreshToken = doubanRefreshToken;
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
	 * 设置排序按属性：uid
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyId(boolean isAsc){
		orderFields.add(new OrderField("id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：email
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyEmail(boolean isAsc){
		orderFields.add(new OrderField("email",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：password
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyPassword(boolean isAsc){
		orderFields.add(new OrderField("password",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：username
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyUsername(boolean isAsc){
		orderFields.add(new OrderField("username",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：昵称
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyNickname(boolean isAsc){
		orderFields.add(new OrderField("nickname",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：性别
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyGender(boolean isAsc){
		orderFields.add(new OrderField("gender",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：出生年
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyBirthY(boolean isAsc){
		orderFields.add(new OrderField("birth_y",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：出生月日 1005
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyBirthMd(boolean isAsc){
		orderFields.add(new OrderField("birth_md",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：头像
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyFigureurl(boolean isAsc){
		orderFields.add(new OrderField("figureurl",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：大头像
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyFigureurl2(boolean isAsc){
		orderFields.add(new OrderField("figureurl2",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：区域代码ppcccccctttttt
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyRegion(boolean isAsc){
		orderFields.add(new OrderField("region",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：简介
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyIntroduce(boolean isAsc){
		orderFields.add(new OrderField("introduce",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：学历,1 高中，2大专，3本科，4硕士，5博士，6博士后
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyEducation(boolean isAsc){
		orderFields.add(new OrderField("education",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：收入
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyEarning(boolean isAsc){
		orderFields.add(new OrderField("earning",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：profession
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyProfession(boolean isAsc){
		orderFields.add(new OrderField("profession",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：0 未激活,1 未完善, 2,可用
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyStatus(boolean isAsc){
		orderFields.add(new OrderField("status",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：create_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyCreateTime(boolean isAsc){
		orderFields.add(new OrderField("create_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：modify_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyModifyTime(boolean isAsc){
		orderFields.add(new OrderField("modify_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：邀请者
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyQqId(boolean isAsc){
		orderFields.add(new OrderField("qq_id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：qq_token
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyQqToken(boolean isAsc){
		orderFields.add(new OrderField("qq_token",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：qq_token_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyQqTokenTime(boolean isAsc){
		orderFields.add(new OrderField("qq_token_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：weibo_id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyWeiboId(boolean isAsc){
		orderFields.add(new OrderField("weibo_id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：weibo_token
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyWeiboToken(boolean isAsc){
		orderFields.add(new OrderField("weibo_token",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：weibo_token_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyWeiboTokenTime(boolean isAsc){
		orderFields.add(new OrderField("weibo_token_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：douban_id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyDoubanId(boolean isAsc){
		orderFields.add(new OrderField("douban_id",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：douban_token
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyDoubanToken(boolean isAsc){
		orderFields.add(new OrderField("douban_token",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：douban_token_time
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyDoubanTokenTime(boolean isAsc){
		orderFields.add(new OrderField("douban_token_time",isAsc?"ASC":"DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：douban_refresh_token
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyDoubanRefreshToken(boolean isAsc){
		orderFields.add(new OrderField("douban_refresh_token",isAsc?"ASC":"DESC"));
		return this;
	}
}
