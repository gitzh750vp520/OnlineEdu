package com.online.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.edu.dao.CoursewareDao;
import com.online.edu.entity.Courseware;
import com.online.edu.service.CoursewareService;

@Service("coursewareService")
public class CoursewareServiceImpl implements CoursewareService {

	@Autowired
	private CoursewareDao coursewareDao;
	@Override
	public void addNewCourseware(Courseware courseware) {
		coursewareDao.addNewCourseware(courseware);
	}


	@Override
	public List<Courseware> getCoursewaresByCourseId( Integer id) {
		return coursewareDao.getCoursewaresByCourseId(id);
	}


	@Override
	public Courseware getCoursewareByCoursewareId(Integer coursewareId) {
		
		return coursewareDao.getCoursewareByCoursewareId(coursewareId);
	}

	
}
