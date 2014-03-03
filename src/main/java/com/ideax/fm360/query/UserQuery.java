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
	/** 1 a 2b 3ab 4o 5其他 **/
    private Byte bloodGroup;
	/**
    * 获取属性:bloodGroup
    * 1 a 2b 3ab 4o 5其他
    * @return bloodGroup
    */
	public Byte getBloodGroup () {
    	return bloodGroup;
   	}
   	/**
     * 设置属性:bloodGroup
     * 1 a 2b 3ab 4o 5其他
     * @param bloodGroup
     */
    public UserQuery setBloodGroup(Byte bloodGroup) {
    	this.bloodGroup = bloodGroup;
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
    private String invitor;
	/**
    * 获取属性:invitor
    * 邀请者
    * @return invitor
    */
	public String getInvitor () {
    	return invitor;
   	}
   	/**
     * 设置属性:invitor
     * 邀请者
     * @param invitor
     */
    public UserQuery setInvitor(String invitor) {
    	this.invitor = invitor;
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
	 * 设置排序按属性：1 a 2b 3ab 4o 5其他
	 * @param isAsc 是否升序，否则为降序
	 */	
	public UserQuery orderbyBloodGroup(boolean isAsc){
		orderFields.add(new OrderField("blood_group",isAsc?"ASC":"DESC"));
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
	public UserQuery orderbyInvitor(boolean isAsc){
		orderFields.add(new OrderField("invitor",isAsc?"ASC":"DESC"));
		return this;
	}
}
