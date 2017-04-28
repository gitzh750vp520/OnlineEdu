package com.online.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.online.edu.entity.Evaluate;

public interface EvaluateDao {
	/**
	 * 根据课程id来获取该课程的所有的评价
	 * 
	 * @param rowStart
	 * @param pageSize
	 * @param id
	 * @return 评价对象的集合
	 */
	List<Evaluate> getEvaluatesByCourseId(@Param(value = "rowStart") Integer rowStart,
			@Param(value = "pageSize") Integer pageSize, @Param(value = "courseId") Integer courseId);

	/**
	 * 根据课程id查询该课程下所有评价的总条数
	 * 
	 * @param id
	 * @return
	 */
	Integer getEvaluatesByCourseIdCount(Integer id);

	/**
	 * 往数据库里面插入一条评价数据
	 * 
	 * @param evaluate
	 */
	void addNewEvaluate(Evaluate evaluate);

	/**
	 * 根据用户id和课程id查询评价信息
	 * 
	 * @param userId
	 * @param courseId
	 * @return
	 */
	Evaluate getEvaluateByUserIdAndCourseId(@Param(value = "userId") Integer userId,
			@Param(value = "courseId") Integer courseId);
}
