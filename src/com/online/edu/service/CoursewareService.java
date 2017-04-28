package com.online.edu.service;

import java.util.List;

import com.online.edu.entity.Courseware;

public interface CoursewareService {
	/**
	 * 添加新的课件
	 * @param courseware
	 */
	void addNewCourseware(Courseware courseware);
	/**
	 * 查询指定课程的所有课件
	 * @param id
	 * @return
	 */
	List<Courseware> getCoursewaresByCourseId(Integer id);
	Courseware getCoursewareByCoursewareId(Integer coursewareId);
}
