package com.online.edu.entity;

public class Evaluate {
	private Integer id;
	// 评价的内容
	private String content;
	// 评价的得分：只有1，2，3，4，5五个分值
	private Integer point;
	// 哪个用户发起的评价
	private User user;
	// 评价的哪个课程
	private Course course;
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
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
}
