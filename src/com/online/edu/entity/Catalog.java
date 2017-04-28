package com.online.edu.entity;

// 课程目录其实就是对应的一个一个的视频
public class Catalog {
	private Integer id;
	// 视频的顺序
	private Integer sequence;
	// 视频展示在页面上的名字
	private String title;
	// 视频存放在服务器上的名字
	private String videoName;
	// 视屏的播放量
	private Integer clickTimes;
	// 视频所属于的课程
	private Course course;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public Integer getClickTimes() {
		return clickTimes;
	}
	public void setClickTimes(Integer clickTimes) {
		this.clickTimes = clickTimes;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	
}
