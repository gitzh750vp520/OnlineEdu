package com.online.edu.entity;

import java.util.Date;

public class Deal {
	private Integer id;
	// 交易的金币数
	private Integer gold;
	// 交易是获得还是支出：false代表获得，true代表支出
	private Boolean type;
	// 交易明细记录：某某用户发生了一笔支出/获得交易，交易金额为xx金币
	private String content;
	// 哪个用户发生了交易
	private User user;
	// 交易的时间
	private Date time;
	// 交易购买的课程
	private Course course;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGold() {
		return gold;
	}
	public void setGold(Integer gold) {
		this.gold = gold;
	}
	public Boolean getType() {
		return type;
	}
	public void setType(Boolean type) {
		this.type = type;
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
