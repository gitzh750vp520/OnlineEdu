package com.online.edu.entity;


import org.hibernate.validator.constraints.NotEmpty;


public class User {
	private Integer id;
	@NotEmpty(message="账号不能为空")
	private String loginId;
	@NotEmpty(message="validate.loginPsw.not.empty")
	private String loginPsw;
	private String name;
	// 用户头像存储在服务器上的名字，有默认值，default.jpg
	private String photo;
	// 用户的简介，主要是老师的简介
	private String introduction;
	// 用户持有的金币数：可以通过充值，写笔记来获取，老师还可以通过分红了获取
	private Integer gold;
	private String email;
	// 用户性别：true代表男，false代表女
	private Boolean sex;
	// 用户类型：0代表老师，1代表学生
	private Integer type;
	// 用户可不可用：true代表可用，false代表不可用
	// 老师在注册的时候，状态通通为不可用，需要通过后台管理员审核后才可用
	private Boolean status;
	// 学生注册，handle值为100；
	// 老师注册，handle值为101--代表需要管理员审核
	// 管理员审核通过以后，老师的handle值为102
	// 管理员审核不通过，给老师的邮箱发一封邮件，并从数据库里面删除掉刚注册的用户（老师）
	private Integer handle;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPsw() {
		return loginPsw;
	}
	public void setLoginPsw(String loginPsw) {
		this.loginPsw = loginPsw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Integer getGold() {
		return gold;
	}
	public void setGold(Integer gold) {
		this.gold = gold;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public Integer getHandle() {
		return handle;
	}
	public void setHandle(Integer handle) {
		this.handle = handle;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", loginId=" + loginId + ", loginPsw="
				+ loginPsw + ", name=" + name + ", photo=" + photo
				+ ", introduction=" + introduction + ", gold=" + gold
				+ ", email=" + email + ", sex=" + sex + ", type=" + type
				+ ", status=" + status + ", handle=" + handle + "]";
	}

}
