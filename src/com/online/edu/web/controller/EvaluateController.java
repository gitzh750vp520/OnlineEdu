package com.online.edu.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.online.edu.entity.Course;
import com.online.edu.entity.Evaluate;
import com.online.edu.entity.User;
import com.online.edu.service.CourseService;
import com.online.edu.service.EvaluateService;
import com.online.edu.service.UserService;

@Controller
@RequestMapping("/evaluate")
public class EvaluateController extends BaseController {

	@Autowired
	private EvaluateService evaluateService;
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;

	@RequestMapping("/addEvaluate")
	public String addEvaluate(HttpSession session, Integer id, Integer point, Model model) {
		User user = (User) session.getAttribute("currUser");//获取当前session中user
		//User user = userService.getUserById(3);//测试
		Course course = courseService.getCourseById(id);
		Boolean eva = evaluateService.checkEvaluateByUserIdAndCourseId(user.getId(), id);
		if(!eva){
			model.addAttribute("evaMsg", "你已评价");
			return "forward:/course/details";
		}
		
		Evaluate evaluate = new Evaluate();
		evaluate.setUser(user);
		evaluate.setCourse(course);
		evaluate.setPoint(point);
		evaluate.setContent(course.getTitle() + "-获得-" + point + "-评分");
		evaluateService.addNewEvaluate(evaluate);
		course.setPoint((course.getPoint() + point) / 2);
		courseService.updateCourseInfoById(course);
		model.addAttribute("evaMsg", "感谢评价");
		return "forward:/course/details";
	}
}
