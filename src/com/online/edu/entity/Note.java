package com.online.edu.entity;

public class Note {
	private Integer id;
	// 笔记的内容
	private String content;
	// 笔记被后台管理员处理的状态：0代表未处理，1代表已处理但是没有给金币，2代表已处理并且给了金币
	private Integer status;
	// 笔记所属用户
	private User user;
	// 笔记所属课程目录（视频）
	private Catalog catalog;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	
}
