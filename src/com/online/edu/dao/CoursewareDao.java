package com.online.edu.dao;

import java.util.List;

import com.online.edu.entity.Courseware;

public interface CoursewareDao {
	/**
	 * 通过课程id，获取其所有的课件
	 * @param id 课程id
	 * @return 课件的列表
	 */
	List<Courseware> getCoursewaresByCourseId(Integer id);
	/**
	 * 添加新的课件
	 * @param courseware
	 */
	void addNewCourseware(Courseware courseware);
	Courseware getCoursewareByCoursewareId(Integer coursewareId);
}
