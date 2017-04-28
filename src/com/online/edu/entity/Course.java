package com.online.edu.entity;

import java.util.Date;

public class Course{
	private Integer id;
	// 课程被用户收藏的次数
	private Integer collect;
	// 课程的评分
	private Double point;
	// 课程的简介--学习目标
	private String target;
	// 课程的简介--适用人群
	private String suitable;
	// 课程的简介--学习条件
	private String demand;
	// 课程存储在服务器上的图片的名称
	private String photo;
	// 课程的价格
	private Integer price;
	// 课程的名称
	private String title;
	// 课程的折扣
	private Double discount;
	// 课程可不可用：false代表禁用，true代表可用
	// 老师上传的视频最开始状态都为false，数据库有默认值，
	// 需要通过管理员审核后修改status的值
	private Boolean status;
	// 课程所属的分类
	private Category category;
	// 课程是由哪个用户上传的
	private User user;
	// 课程上传的时间
	private Date time;
	// 课程刚上传，handle值为100，表示需要经过后台管理员审核
	// 管理员审核通过，handle值为101
	// 管理员审核不通过，给用户（老师）发一封邮件，然后将课程从数据库里面删除
	private Integer handle;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCollect() {
		return collect;
	}
	public void setCollect(Integer collect) {
		this.collect = collect;
	}
	public Double getPoint() {
		return point;
	}
	public void setPoint(Double point) {
		this.point = point;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getSuitable() {
		return suitable;
	}
	public void setSuitable(String suitable) {
		this.suitable = suitable;
	}
	public String getDemand() {
		return demand;
	}
	public void setDemand(String demand) {
		this.demand = demand;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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
	
	
	public Integer getHandle() {
		return handle;
	}
	public void setHandle(Integer handle) {
		this.handle = handle;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", collect=" + collect + ", point=" + point
				+ ", target=" + target + ", suitable=" + suitable + ", demand="
				+ demand + ", photo=" + photo + ", price=" + price + ", title="
				+ title + ", discount=" + discount + ", status=" + status
				+ ", category=" + category + ", user=" + user + ", time="
				+ time + "]\n";
	}
	
}
