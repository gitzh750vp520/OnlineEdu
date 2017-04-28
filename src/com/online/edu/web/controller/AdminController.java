package com.online.edu.web.controller;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.online.edu.dao.CourseDao;
import com.online.edu.entity.Admin;
import com.online.edu.entity.Category;
import com.online.edu.entity.Course;
import com.online.edu.entity.Deal;
import com.online.edu.entity.Note;
import com.online.edu.entity.SuperCategory;
import com.online.edu.entity.User;
import com.online.edu.service.AdminService;
import com.online.edu.service.CategoryService;
import com.online.edu.service.CourseService;
import com.online.edu.service.DealService;
import com.online.edu.service.NoteService;
import com.online.edu.service.UserService;
import com.online.edu.web.tool.Paging;

@Controller
@RequestMapping("/mgr")
public class AdminController extends BaseController{
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private NoteService noteService;
	@Autowired
	private DealService dealService;
	@RequestMapping("/loginForm")
	public String loginForm(){
		return "mgr/admin-loginForm";
	}
	@RequestMapping("/login")
	public String login(String loginId,String loginPsw,Model model,HttpSession session){
		Integer loginCheck = adminService.loginCheck(loginId, loginPsw);
		if(loginCheck == 1){
			model.addAttribute("loginMsg", "用户名不存在");
			return "forward:loginForm";
		}else if(loginCheck == 2){
			model.addAttribute("loginMsg", "密码错误");
			return "forward:loginForm";
		}else if(loginCheck == 3){
			model.addAttribute("loginMsg", "当前用户状态为禁用，请找超级管理员启用账号");
			return "forward:loginForm";
		}else{
			Admin admin = adminService.getAdminByLoginId(loginId);
			session.setAttribute("currAdmin", admin);
			return "redirect:home";
		}
	}
	@RequestMapping("/loginOut")
	public String loginOut(HttpSession session){
		session.removeAttribute("currAdmin");
		return "redirect:loginForm";
	}
	@RequestMapping("/home")
	public String home(Model model,HttpSession session){
		Admin currAdmin = (Admin) session.getAttribute("currAdmin");
		if(!currAdmin.getType()){
			List<Admin> allNormalAdmins = adminService.getAllNormalAdmins(1, 5);
			Integer countTotal = adminService.getAllNormalAdminsCount();
			Paging paging = new Paging(countTotal, "1", 5);
			model.addAttribute("adminList", allNormalAdmins);
			model.addAttribute("paging", paging);
		}
		return "mgr/admin-home";
	}
	@RequestMapping("/checkTeacher")
	public String checkTeacher(Model model){
		return "mgr/admin-check-teacher";
	}
	@RequestMapping("/loadCheckTeacher")
	public void loadCheckTeacher(String pageNo,Writer writer){
		Integer countTotal = userService.getAllRecentRegistTeachersCount();
		Paging paging = new Paging(countTotal,pageNo,10);
		List<User> allRecentRegistTeachers = userService.getAllRecentRegistTeachers(paging.getPageNo(), 10);
		JSONObject map = new JSONObject();
		try {
			map.put("teacherList", new JSONArray(allRecentRegistTeachers,false));
			map.put("paging", new JSONObject(paging));
		} catch (JSONException e) {
			throw  new RuntimeException(e);
		}
		PrintWriter out = new PrintWriter(writer);
		out.print(map);
		out.flush();
		out.close();
	}
	@RequestMapping("/passTeacher")
	public void passTeacher(String loginId,String pageNo,Writer writer){
		User user = userService.getUserByLoginId(loginId);
		user.setHandle(102);
		user.setStatus(true);
		userService.modifyUserInfoById(user);
		Integer countTotal = userService.getAllRecentRegistTeachersCount();
		Paging paging = new Paging(countTotal, pageNo, 10);
		PrintWriter out = new PrintWriter(writer);
		out.print(paging.getPageNo());
		out.flush();
		out.close();
	}
	@RequestMapping("/refuseTeacher")
	public void refuseTeacher(String loginId,String pageNo,Writer writer){
		userService.deleteUserByLoginId(loginId);
		Integer countTotal = userService.getAllRecentRegistTeachersCount();
		Paging paging = new Paging(countTotal, pageNo, 10);
		PrintWriter out = new PrintWriter(writer);
		out.print(paging.getPageNo());
		out.flush();
		out.close();
	}
	@RequestMapping("/addAdmin")
	public String addAdmin(String loginId,String loginPsw,String name,Model model){
		Admin admin = new Admin();
		admin.setLoginId(loginId);
		admin.setLoginPsw(loginPsw);
		admin.setName(name);
		Integer result = adminService.addNewAdmin(admin);
		if(result == 2){
			model.addAttribute("addAdminMsg", "添加管理员失败：用户名已经存在");
			return "forward:home";
		}
		return "redirect:home";
	}
	@RequestMapping("/modifyPassword")
	public String modifyPassword(String oldLoginPsw,String newLoginPsw,HttpSession session,Model model){
		System.out.println("旧密码是："+oldLoginPsw);
		Admin admin = (Admin)session.getAttribute("currAdmin");
		if(!oldLoginPsw.equals(admin.getLoginPsw())){
			model.addAttribute("modifyLoginPswMsg", "输入的旧密码不正确，请重新输入");
			return "forward:home";
		}
		admin.setLoginPsw(newLoginPsw);
		adminService.updateAdminInfoById(admin);
		return "redirect:home";
	}
	@RequestMapping("/modifyAdminInfo")
	public String modifyAdminInfo(String newName,HttpSession session){
		Admin admin = (Admin)session.getAttribute("currAdmin");
		admin.setName(newName);
		adminService.updateAdminInfoById(admin);
		return "redirect:home";
	}
	@RequestMapping("/loadAdmins")
	public void loadAdmins(String pageNo,Writer writer){
		Integer countTotal = adminService.getAllNormalAdminsCount();
		Paging paging = new Paging(countTotal,pageNo,5);
		List<Admin> allNormalAdmins = adminService.getAllNormalAdmins(paging.getPageNo(), 5);
		JSONObject map = new JSONObject();
		try {
			map.put("adminList", new JSONArray(allNormalAdmins,false));
			map.put("paging", new JSONObject(paging));
		} catch (JSONException e) {
			throw  new RuntimeException(e);
		}
		PrintWriter out = new PrintWriter(writer);
		out.print(map);
		out.flush();
		out.close();
	}
	@RequestMapping("/modifyAdminStatus")
	public void modifyAdminStatus(String loginId,Integer pageNo,Writer writer){
		Admin admin = adminService.getAdminByLoginId(loginId);
		if(admin.getStatus()){
			admin.setStatus(false);
		}else{
			admin.setStatus(true);
		}
		adminService.updateAdminInfoById(admin);
		PrintWriter out = new PrintWriter(writer);
		out.print(pageNo);
		out.flush();
		out.close();
	}
	@RequestMapping("/deleteAdmin")
	public void deleteAdmin(Integer adminId,String pageNo,Writer writer){
		adminService.deleteAdminById(adminId);
		Integer countTotal = adminService.getAllNormalAdminsCount();
		Paging paging = new Paging(countTotal,pageNo,5);
		PrintWriter out = new PrintWriter(writer);
		out.print(paging.getPageNo());
		out.flush();
		out.close();
	}
	@RequestMapping("/courseMgr")
	public String courseMgrForm(){
		return "mgr/admin-course-mgr";
	}
	@RequestMapping("/loadCourse")
	public void loadCourse(String pageNo,Writer writer){
		Integer countTotal = courseService.getCoursesCount(null, null, null);
		Paging paging = new Paging(countTotal,pageNo,6);
		List<Course> courses = courseService.getCourses(paging.getPageNo(), 6, null, null, null, CourseDao.NONE, true);
		JSONObject map = new JSONObject();
		try {
			map.put("courseList", new JSONArray(courses,false));
			map.put("paging", new JSONObject(paging));
		} catch (JSONException e) {
			throw  new RuntimeException(e);
		}
		PrintWriter out = new PrintWriter(writer);
		out.print(map);
		out.flush();
		out.close();
	}
	@RequestMapping("/modifyCourseStatus")
	public void modifyCourseStatus(Integer courseId,Boolean newStatus,Integer pageNo,Writer writer){
		courseService.modifyCourseStatus(newStatus, courseId);
		PrintWriter out = new PrintWriter(writer);
		out.print(pageNo);
		out.flush();
		out.close();
	}
	@RequestMapping("/categoryMgr")
	public String categoryMgrForm(Model model){
		List<SuperCategory> allSuperCategories = categoryService.getAllSuperCategories(1, 20);
		List<Category> allSubCategories = categoryService.getAllSubCategories(1, 20);
		model.addAttribute("supList", allSuperCategories);
		model.addAttribute("categoryList", allSubCategories);
		return "mgr/admin-category-mgr";
	}
	@RequestMapping("/loadCategory")
	public void loadCategory(String pageNo,Writer writer){
		Integer countTotal = categoryService.getAllSubCategoriesCount();
		Paging paging = new Paging(countTotal,pageNo,6);
		List<Category> allSubCategories = categoryService.getAllSubCategories(paging.getPageNo(), 6);
		JSONObject map = new JSONObject();
		try {
			map.put("categoryList", new JSONArray(allSubCategories,false));
			map.put("paging", new JSONObject(paging));
		} catch (JSONException e) {
			throw  new RuntimeException(e);
		}
		PrintWriter out = new PrintWriter(writer);
		out.print(map);
		out.flush();
		out.close();
	}
	@RequestMapping("/checkCourse")
	public String checkCourse(){
		return "mgr/admin-check-course";
	}
	@RequestMapping("/loadCheckCourse")
	public void loadCheckCourse(String pageNo,Writer writer){
		Integer countTotal = courseService.getRecentUnavliableCoursesCount();
		Paging paging = new Paging(countTotal,pageNo,10);
		List<Course> recentUnavliableCourses = courseService.getRecentUnavliableCourses(paging.getPageNo(), 10);
		JSONObject map = new JSONObject();
		try {
			map.put("courseList", new JSONArray(recentUnavliableCourses,false));
			map.put("paging", new JSONObject(paging));
		} catch (JSONException e) {
			throw  new RuntimeException(e);
		}
		PrintWriter out = new PrintWriter(writer);
		out.print(map);
		out.flush();
		out.close();
	}
	@RequestMapping("/passCourse")
	public void passCourse(Integer courseId,String pageNo,Writer writer){
		courseService.modifyCourseHandle(101, courseId);
		Integer countTotal = courseService.getRecentUnavliableCoursesCount();
		Paging paging = new Paging(countTotal, pageNo, 10);
		PrintWriter out = new PrintWriter(writer);
		out.print(paging.getPageNo());
		out.flush();
		out.close();
	}
	@RequestMapping("/refuseCourse")
	public void refuseCourse(Integer courseId,String pageNo,Writer writer){
		courseService.deleteCourseById(courseId);
		Integer countTotal = courseService.getRecentUnavliableCoursesCount();
		Paging paging = new Paging(countTotal, pageNo, 10);
		PrintWriter out = new PrintWriter(writer);
		out.print(paging.getPageNo());
		out.flush();
		out.close();
	}
	@RequestMapping("/addSuperCategory")
	public String addSuperCategory(String name,Model model){
		SuperCategory s = new SuperCategory();
		s.setName(name);
		Boolean result = categoryService.addNewSuperCategory(s);
		if(result){
			model.addAttribute("addSuperCategoryMsg", "添加成功");
			return "forward:categoryMgr";
		}else{
			model.addAttribute("addSuperCategoryMsg", "添加失败：分类已经存在");
			return "forward:categoryMgr";
		}
	}
	@RequestMapping("/addCategeory")
	public String addCategeory(String name,Integer supcategoryId,Model model){
		Category category = new Category();
		category.setName(name);
		category.setSuperCategory(new SuperCategory());
		category.getSuperCategory().setId(supcategoryId);
		Boolean result = categoryService.addNewSubCategory(category);
		if(result){
			model.addAttribute("addCategoryMsg", "添加成功");
			return "forward:categoryMgr";
		}else{
			model.addAttribute("addCategoryMsg", "添加失败：分类已经存在");
			return "forward:categoryMgr";
		}
	}
	@RequestMapping("/modifySuperCategory")
	public String modifySuperCategory(String oldSupName,String newSupName,Model model){
		Integer result = categoryService.modifySuperCategoryName(oldSupName,newSupName);
		if(result == 1){
			model.addAttribute("modifySupNameMsg", "修改失败：要修改的父分类名不存在！");
			return "forward:categoryMgr";
		}else if(result == 2){
			model.addAttribute("modifySupNameMsg", "修改失败：修改后的分类名已经存在！");
			return "forward:categoryMgr";
		}else{
			model.addAttribute("modifySupNameMsg", "修改成功：原父分类名为【"+oldSupName+"】已经改为【"+newSupName+"】！");
			return "forward:categoryMgr";
		}
	}
	@RequestMapping("/modifyCategory")
	public String modifyCategory(String oldSubName,String newSubName,Model model){
		Integer result = categoryService.modifyCategoryName(oldSubName, newSubName);
		if(result == 1){
			model.addAttribute("modifySubNameMsg", "修改失败：要修改的子分类名不存在！");
			return "forward:categoryMgr";
		}else if(result == 2){
			model.addAttribute("modifySubNameMsg", "修改失败：修改后的分类名已经存在！");
			return "forward:categoryMgr";
		}else{
			model.addAttribute("modifySubNameMsg", "修改成功：原子分类名为【"+oldSubName+"】已经改为【"+newSubName+"】！");
			return "forward:categoryMgr";
		}
	}
	@RequestMapping("/userMgr")
	public String userMgr(){
		return "mgr/admin-user-mgr";
	}
	@RequestMapping("/loadAvailableUser")
	public void loadAvailableUser(String pageNo,Writer writer){
		Integer countTotal = userService.getAllAvailableUserCount();
		Paging paging = new Paging(countTotal,pageNo,10);
		List<User> allAvailableUser = userService.getAllAvailableUser(paging.getPageNo(), 10);
		JSONObject map = new JSONObject();
		try {
			map.put("userList", new JSONArray(allAvailableUser,false));
			map.put("paging", new JSONObject(paging));
		} catch (JSONException e) {
			throw  new RuntimeException(e);
		}
		PrintWriter out = new PrintWriter(writer);
		out.print(map);
		out.flush();
		out.close();
	}
	@RequestMapping("/modifyUserStatus")
	public void modifyUserStatus(String loginId,Integer pageNo,Writer writer){
		User user = userService.getUserByLoginId(loginId);
		if(user.getStatus()){
			user.setStatus(false);
		}else{
			user.setStatus(true);
		}
		userService.modifyUserInfoById(user);
		PrintWriter out = new PrintWriter(writer);
		out.print(pageNo);
		out.flush();
		out.close();
	}
	@RequestMapping("/noteMgr")
	public String noteMgr(){
		return "mgr/admin-note-mgr";
	}
	@RequestMapping("/loadPendingNote")
	public void loadPendingNote(String pageNo,Writer writer){
		Integer countTotal = noteService.getPendingNotesCount();
		Paging paging = new Paging(countTotal,pageNo,10);
		List<Note> pendingNotes = noteService.getPendingNotes(paging.getPageNo(), 10);
		JSONObject map = new JSONObject();
		try {
			map.put("noteList", new JSONArray(pendingNotes,false));
			map.put("paging", new JSONObject(paging));
		} catch (JSONException e) {
			throw  new RuntimeException(e);
		}
		PrintWriter out = new PrintWriter(writer);
		out.print(map);
		out.flush();
		out.close();
	}
	@RequestMapping("/refuseNote")
	public void refuseNote(Integer noteId,String pageNo,Writer writer){
		noteService.updateNoteStatus(noteId, 1);
		Integer countTotal = noteService.getPendingNotesCount();
		Paging paging = new Paging(countTotal, pageNo, 10);
		PrintWriter out = new PrintWriter(writer);
		out.print(paging.getPageNo());
		out.flush();
		out.close();
	}
	@RequestMapping("/giveNoteGold")
	public void giveNoteGold(Integer noteId,Integer userId,String pageNo,Integer gold,Writer writer){
		noteService.updateNoteStatus(noteId, 2);
		User user = userService.getUserById(userId);
		Deal deal = new Deal();
		deal.setUser(user);
		deal.setGold(gold);
		deal.setType(false);
		deal.setTime(new Date());
		deal.setContent("用户" + user.getName() + "发生了一笔收入交易，获得了" + gold + "个金币");
		dealService.addNewDeal(deal);
		Integer countTotal = noteService.getPendingNotesCount();
		Paging paging = new Paging(countTotal, pageNo, 10);
		PrintWriter out = new PrintWriter(writer);
		out.print(paging.getPageNo());
		out.flush();
		out.close();
	}
}
