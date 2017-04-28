package com.online.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.online.edu.entity.Category;
import com.online.edu.entity.Course;
import com.online.edu.entity.SuperCategory;
import com.online.edu.entity.User;

public interface CourseDao {
	/**
	 * 按id排序
	 */
	public static final int NONE = 1;
	/**
	 * 按评分排序
	 */
	public static final int ORDER_BY_POINT = 2;
	/**
	 * 按价格排序
	 */
	public static final int ORDER_BY_PRICE = 3;
	/**
	 * 按时间排序
	 */
	public static final int ORDER_BY_TIME = 4;
	/**
	 * 按收藏次数排序
	 */
	public static final int ORDER_BY_COLLECTEDCOUNT = 5;

	
	/**
	 * 根据给定的条件分页查询课程
	 * @param rowStart 查询的起始行号，从零开始
	 * @param pageSize 查询的最大条数（页面显示条数）
	 * @param superCategory 父分类，必须给父分类id
	 * @param category 子分类，必须给子分类id
	 * @param title 根据课程的标题关键字模糊查询
	 * @param order 结果排序字段 ，CourseDao中的常量
	 * @param desc 是否降序，true为降序
	 * @return 课程的列表
	 */
	List<Course> getCourses(@Param("rowStart")Integer rowStart, @Param("pageSize")Integer pageSize,
			@Param("superCategory")SuperCategory superCategory, @Param("category")Category category,@Param("title") String title,
			@Param("order") int order, @Param("desc") boolean desc);
	/**
	 * 查询指定分类，模糊匹配课程标题的总条数
	 * @param superCategory 父分类
	 * @param category 子分类
	 * @param title 课程标题的模糊匹配关键字
	 * @return
	 */
	Integer getCoursesCount(@Param("superCategory")SuperCategory superCategory, @Param("category")Category category,@Param("title") String title);
	/**
	 * 分页获取用户收藏的课程
	 * @param rowStart 查询的起始行号，从零开始
	 * @param pageSize 查询的最大条数（页面显示条数）
	 * @param user 用户
	 * @return 课程的列表
	 */
	List<Course> getCoursesUserCollected(@Param("rowStart")Integer rowStart, @Param("pageSize")Integer pageSize,
			@Param("user")User user);
	/**
	 * 查询用户收藏的所有课程的总条数
	 * @param user
	 * @return
	 */
	Integer getCoursesCountUserCollectedCount(@Param("user")User user);
	/**
	 * 分页获取用户上传的课程（该方法的用户类型只能是老师）
	 * @param rowStart 查询的起始行号，从零开始
	 * @param pageSize 查询的最大条数（页面显示条数）
	 * @param user 用户
	 * @return 课程的列表
	 */
	List<Course> getCoursesUserUploaded(@Param("rowStart")Integer rowStart, @Param("pageSize")Integer pageSize,
			@Param("user")User user);
	/**
	 * 查询指定用户上传课程的总跳数
	 * @param user
	 * @return
	 */
	Integer getCourseUserUploadedCount(@Param("user")User user);
	/**
	 * 分页查询所有状态不可用的课程，用于后台管理员管理
	 * @param rowStart 查询的其实行号，从零开始
	 * @param pageSize 查询的最大条数（页面显示条数）
	 * @return 课程的列表
	 */
	List<Course> getRecentUnavliableCourses(@Param("rowStart")Integer rowStart, @Param("pageSize")Integer pageSize);
	
	/**
	 * 获取待审核课程的总条数
	 * @return
	 */
	Integer getRecentUnavliableCoursesCount();
	/**
	 * 分页查询用户购买了的课程列表
	 * @param user
	 * @return 用户列表
	 */
	List<Course> getCoursesUserPurchased(@Param("rowStart")Integer rowStart,@Param("pageSize")Integer pageSize,@Param("user")User user);
	
	/**
	 * 获取用户购买课程的总条数
	 * @param user
	 * @return
	 */
	Integer getCoursesUserPurchasedCount(@Param("user")User user);
	
	/**
	 * 通过课程的id获取课程信息
	 * @param id 课程id
	 * @return 课程实体
	 */
	Course getCourseById(@Param("courseId")Integer id);
	/**
	 * 通过课程id，更新课程的信息（
	 * 	注意：该方法，使用了mybatis的动态sql技术
	 * 	传入的实体，只需要传入需要修改的字段即可，不需要修改的字段可以传null
	 * ）
	 * @param course 需要修改的课程实体
	 */
	void updateCourseInfoById(@Param("course")Course course);
	/**
	 * 添加新的课程
	 * @param course
	 */
	void addNewCourse(Course course);
	/**
	 * 根据课程id修改课程的状态
	 * @param status
	 * @param courseId
	 */
	void modifyCourseStatus(@Param("status")Boolean status,@Param("courseId")Integer courseId);
	void modifyCourseHandle(@Param("handle")Integer handle,@Param("courseId")Integer courseId);
	void deleteCourseById(Integer courseId);
}
