package com.online.edu.entity;

import java.util.List;

public class Comment {
	private Integer id;
	
	// 评论内容
	private String content;
	// 评论由哪个用户发出的
	private User user;
	// 评论是发在哪个课程目录（视频）下的
	private Catalog catalog;
	// 评论下所有的回复
	private List<Reply> replies;
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
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	
	
}
