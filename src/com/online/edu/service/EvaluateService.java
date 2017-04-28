package com.online.edu.service;

import java.util.List;

import com.online.edu.entity.Evaluate;

public interface EvaluateService {
	/**
	 * 
	 * @param evaluate
	 * @return 已经评价了，则返回false
	 */
	Boolean addNewEvaluate(Evaluate evaluate);
	/**
	 * 分页查询指定课程的所有评价
	 * @param pageNO
	 * @param pageSize
	 * @param courseId
	 * @return
	 */
	List<Evaluate> getEvaluatesByCourseId(Integer pageNO,Integer pageSize,Integer courseId);
	/**
	 * 查询总条数，用于分页
	 * @param courseId
	 * @return
	 */
	Integer getEvaluatesByCourseIdCount(Integer courseId);
	/**
	 * 根据用户id和课程id检查用户是否已经评价过该课程
	 * @param userId
	 * @param courseId
	 * @return 如果已经评价过就返回false，如果还没有评价就返回true
	 */
	Boolean checkEvaluateByUserIdAndCourseId(Integer userId,Integer courseId);
}
