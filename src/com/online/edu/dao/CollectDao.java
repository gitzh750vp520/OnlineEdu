package com.online.edu.dao;

import org.apache.ibatis.annotations.Param;

import com.online.edu.entity.Collect;

public interface CollectDao {
	/**
	 * 添加一收藏
	 * 
	 * @param collect
	 *            收藏实体（只需要user的id，和course的id即可）
	 */
	void addNewCollect(Collect collect);

	/**
	 * 根据用户id和课程id来查询收藏
	 * 
	 * @param userId
	 * @param courseId
	 * @return
	 */
	Collect getCollectByUserIdAndCourseId(@Param(value = "userId") Integer userId,
			@Param(value = "courseId") Integer courseId);
}
