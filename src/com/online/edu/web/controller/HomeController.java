package com.online.edu.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.online.edu.dao.CourseDao;
import com.online.edu.entity.Category;
import com.online.edu.entity.Course;
import com.online.edu.entity.SuperCategory;
import com.online.edu.service.CourseService;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController{
	@Autowired
	private CourseService courseService;
	@RequestMapping("/index")
	public String index(Model model){
		List<Course> coursesRecommended = courseService.getCourses(1, 6, null, null, null, CourseDao.ORDER_BY_TIME, true);
		List<Course> mostPopCourses = courseService.getMostPopCourses(3);
		List<Course> bestPointCourses = courseService.getBestPointCourses(3);
		model.addAttribute("mostPopCourses", mostPopCourses);
		model.addAttribute("bestPointCourses", bestPointCourses);
		model.addAttribute("coursesRecommended", coursesRecommended);
		return "fore/home";
	}
}
