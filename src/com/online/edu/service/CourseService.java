package com.online.edu.service;

import java.util.List;


import com.online.edu.entity.Category;
import com.online.edu.entity.Course;
import com.online.edu.entity.SuperCategory;
import com.online.edu.entity.User;

public interface CourseService {
	/**
	 * 添加新的课程
	 * @param course
	 */
	void addNewCourse(Course course);
	/**
	 * 通过课程id，查询指定的课程
	 * @param id
	 * @return
	 */
	Course getCourseById(Integer id);
	/**
	 * 根据不同的条件查询出对应的课程
	 * @param pageNO  页码
	 * @param pageSize 页面显示的最大条数
	 * @param superCategory 父分类
	 * @param category 子分类
	 * @param title 模糊查询关键字
	 * @param order 排序规则，是接口CourseDao中的静态常量
	 * @param desc 是否降序
	 * @return
	 */
	List<Course> getCourses(Integer pageNO,Integer pageSize,
	   SuperCategory superCategory, Category category, String title,
	   Integer order,  Boolean desc);
	/**
	 * 查询指定分类，模糊匹配课程标题的总条数
	 * @param superCategory 父分类
	 * @param category 子分类
	 * @param title 课程标题的模糊匹配关键字
	 * @return
	 */
	Integer getCoursesCount(SuperCategory superCategory, Category category,String title);
	
	/**
	 * 分页获取用户收藏的课程列表
	 * @param pageNO
	 * @param pageSize
	 * @param user
	 * @return
	 */
	List<Course> getCoursesUserCollected(Integer pageNO, Integer pageSize, User user);
	
	
	/**
	 * 查询用户收藏的所有课程的总条数
	 * @param user
	 * @return
	 */
	Integer getCoursesCountUserCollectedCount(User user);
	/**
	 * 分页获取用户上传的课程列表
	 * @param pageNO
	 * @param pageSize
	 * @param user
	 * @return
	 */
	List<Course> getCoursesUserUploaded(Integer pageNO, Integer pageSize, User user);
	
	/**
	 * 查询指定用户上传课程的总跳数
	 * @param user
	 * @return
	 */
	Integer getCourseUserUploadedCount(User user);
	/**
	 * 分页获取刚上传的，待审核的课程，用于管理员管理
	 * @param pageNO 
	 * @param pageSize 
	 * @return
	 */
	List<Course> getRecentUnavliableCourses(Integer pageNO, Integer pageSize);
	
	
	/**
	 * 获取待审核课程的总条数
	 * @return
	 */
	Integer getRecentUnavliableCoursesCount();
	
	/**
	 * 分页获取用户购买了的课程列表
	 * @param pageNO
	 * @param pageSize
	 * @param user
	 * @return
	 */
	List<Course> getCoursesUserPurchased(Integer pageNO, Integer pageSize,User user);
	/**
	 * 获取用户购买课程的总条数
	 * @param user
	 * @return
	 */
	Integer getCoursesUserPurchasedCount(User user);
	
	/**
	 * 通过课程id，更新课程信息
	 * @param course
	 */
	void updateCourseInfoById(Course course);
	/**
	 * 根据课程id修改课程的状态
	 * @param status
	 * @param courseId
	 */
	void modifyCourseStatus(Boolean status,Integer courseId);
	void modifyCourseHandle(Integer handle,Integer courseId);
	/**
	 * 收藏指定的课程
	 * @param user
	 * @param course
	 * @return 已经收藏，则返回false
	 */
	Boolean  collectCourse(User user,Course course);
	/**
	 * 获取收藏次数排名前rowAmount的课程列表
	 * @param rowAmount
	 * @return
	 */
	List<Course> getMostPopCourses(Integer rowAmount);
	/**
	 * 获取评分最高的前rowAmount的课程列表
	 * @param rowAmount
	 * @return
	 */
	List<Course> getBestPointCourses(Integer rowAmount);
	void deleteCourseById(Integer courseId);
}
