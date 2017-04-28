package com.online.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.edu.dao.CollectDao;
import com.online.edu.dao.CourseDao;
import com.online.edu.entity.Category;
import com.online.edu.entity.Collect;
import com.online.edu.entity.Course;
import com.online.edu.entity.SuperCategory;
import com.online.edu.entity.User;
import com.online.edu.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CollectDao collectDao;
	
	@Override
	public void addNewCourse(Course course) {
		courseDao.addNewCourse(course);
	}

	@Override
	public Course getCourseById(Integer id) {
		return courseDao.getCourseById(id);
	}

	@Override
	public List<Course> getCourses(Integer pageNO, Integer pageSize,
			SuperCategory superCategory, Category category, String title,
			Integer order, Boolean desc) {
		
		if(order==null){
			order = CourseDao.NONE;
		}
		if(desc==null){
			desc = true;
		}
		if(superCategory != null && category != null){
			return courseDao.getCourses((pageNO-1)*pageSize, pageSize, null, category, title, order, desc);
		}
			return courseDao.getCourses((pageNO-1)*pageSize, pageSize, superCategory, category, title, order, desc);
	}

	@Override
	public List<Course> getCoursesUserCollected(Integer pageNO,
			Integer pageSize, User user) {
		return courseDao.getCoursesUserCollected((pageNO-1)*pageSize, pageSize, user);
	}

	@Override
	public List<Course> getCoursesUserUploaded(Integer pageNO,
			Integer pageSize, User user) {
		return courseDao.getCoursesUserUploaded((pageNO-1)*pageSize, pageSize, user);
	}

	@Override
	public List<Course> getRecentUnavliableCourses(Integer pageNO,
			Integer pageSize) {
		
		return courseDao.getRecentUnavliableCourses((pageNO-1)*pageSize, pageSize);
	}


	@Override
	public void updateCourseInfoById(Course course) {
		courseDao.updateCourseInfoById(course);
		
	}

	@Override
	public List<Course> getCoursesUserPurchased(Integer pageNO,
			Integer pageSize, User user) {
		return courseDao.getCoursesUserPurchased((pageNO-1)*pageSize, pageSize, user);
	}

	@Override
	public Boolean collectCourse(User user, Course course) {
		Collect collect = collectDao.getCollectByUserIdAndCourseId(user.getId(), course.getId());
		if(collect!=null){
			return false;
		}
		Collect collectNew = new Collect();
		collectNew.setUser(user);
		collectNew.setCourse(course);
		
		collectDao.addNewCollect(collectNew);
		course.setCollect(course.getCollect() + 1);
		courseDao.updateCourseInfoById(course);
		return true;
	}

	@Override
	public Integer getCoursesCount(SuperCategory superCategory,
			Category category, String title) {
		if(superCategory!=null && category !=null){
			return courseDao.getCoursesCount(null, category, title);
		}
		return courseDao.getCoursesCount(superCategory, category, title);
	}

	@Override
	public Integer getCoursesCountUserCollectedCount(User user) {
		return courseDao.getCoursesCountUserCollectedCount(user);
	}

	@Override
	public Integer getCourseUserUploadedCount(User user) {
		return courseDao.getCourseUserUploadedCount(user);
	}

	@Override
	public Integer getRecentUnavliableCoursesCount() {
		return courseDao.getRecentUnavliableCoursesCount();
	}

	@Override
	public Integer getCoursesUserPurchasedCount(User user) {
		return courseDao.getCoursesUserPurchasedCount(user);
	}

	@Override
	public List<Course> getMostPopCourses(Integer rowAmount) {
		return courseDao.getCourses(0, rowAmount, null, null, null, CourseDao.ORDER_BY_COLLECTEDCOUNT, true);
	}

	@Override
	public List<Course> getBestPointCourses(Integer rowAmount) {
		return courseDao.getCourses(0, rowAmount, null, null, null, CourseDao.ORDER_BY_POINT, true);
	}

	@Override
	public void modifyCourseStatus(Boolean status, Integer courseId) {
		courseDao.modifyCourseStatus(status, courseId);
	}

	@Override
	public void modifyCourseHandle(Integer handle, Integer courseId) {
		courseDao.modifyCourseHandle(handle,courseId);
	}

	@Override
	public void deleteCourseById(Integer courseId) {
		courseDao.deleteCourseById(courseId);
	}
	

}
