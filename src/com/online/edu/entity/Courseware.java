package com.online.edu.entity;

// 课程课件实体
public class Courseware {
	private Integer id;
	// 课程课件在页面显示的名字
	private String name;
	// 课程课件存储在服务器上的名字
	private String fileName;
	// 课程课件所属的课程
	private Course course;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
}
