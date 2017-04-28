package com.online.edu.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.online.edu.entity.Catalog;
import com.online.edu.entity.Category;
import com.online.edu.entity.Course;
import com.online.edu.entity.Courseware;
import com.online.edu.entity.Deal;
import com.online.edu.entity.SuperCategory;
import com.online.edu.entity.User;
import com.online.edu.service.CatalogService;
import com.online.edu.service.CategoryService;
import com.online.edu.service.CourseService;
import com.online.edu.service.CoursewareService;
import com.online.edu.service.DealService;
import com.online.edu.web.tool.Paging;

@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DealService dealService;
	@Autowired
	private CatalogService catalogService;
	@Autowired
	private CoursewareService coursewareService;

	@RequestMapping("/collectedCourses")
	public void collectedCourses(String pageNO, HttpSession session,
			Writer writer) throws JSONException {
		PrintWriter out = new PrintWriter(writer);
		User user = (User) session.getAttribute("currUser");
		int pageSize = 6;
		int countTotal = courseService.getCoursesCountUserCollectedCount(user);
		Paging paging = new Paging(countTotal, pageNO, pageSize);
		List<Course> courses = courseService.getCoursesUserCollected(
				paging.getPageNo(), pageSize, user);
		JSONObject data = new JSONObject();
		JSONArray array = new JSONArray(courses, false);
		JSONObject pagingData = new JSONObject(paging);
		data.put("paging", pagingData);
		data.put("courses", array);
		out.print(data);
		out.flush();
		out.close();
	}

	@RequestMapping("/uploadedCourses")
	public void uploadedCourses(String pageNO, HttpSession session,
			Writer writer) throws JSONException {
		PrintWriter out = new PrintWriter(writer);
		User user = (User) session.getAttribute("currUser");
		int pageSize = 6;
		int countTotal = courseService.getCourseUserUploadedCount(user);
		Paging paging = new Paging(countTotal, pageNO, pageSize);
		List<Course> courses = courseService.getCoursesUserUploaded(
				paging.getPageNo(), pageSize, user);
		JSONObject data = new JSONObject();
		JSONArray array = new JSONArray(courses, false);
		JSONObject pagingData = new JSONObject(paging);
		data.put("paging", pagingData);
		data.put("courses", array);
		out.print(data);
		out.flush();
		out.close();
	}

	@RequestMapping("/purchasedCourses")
	public void purchasedCourses(String pageNO, HttpSession session,
			Writer writer) throws JSONException {
		PrintWriter out = new PrintWriter(writer);
		User user = (User) session.getAttribute("currUser");
		int pageSize = 6;
		int countTotal = courseService.getCoursesUserPurchasedCount(user);
		Paging paging = new Paging(countTotal, pageNO, pageSize);
		List<Course> courses = courseService.getCoursesUserPurchased(
				paging.getPageNo(), pageSize, user);
		JSONObject data = new JSONObject();
		JSONArray array = new JSONArray(courses, false);
		JSONObject pagingData = new JSONObject(paging);
		data.put("paging", pagingData);
		data.put("courses", array);
		out.print(data);
		out.flush();
		out.close();
	}

	@RequestMapping("/courseList")
	public String courseList(String pageNO, Integer superCategoryId,
			Integer subCategoryId, String title, Integer order, Boolean desc,
			HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		
		
		SuperCategory superCategory = null;
		Category category = null;
		
		/* 如果父分类，子分类，模糊搜索，不为空，则改变session中的对应属性值 */
		if (superCategoryId != null) {
			session.setAttribute("superCategoryId", superCategoryId);
			if(superCategoryId==0){
				session.setAttribute("superCategoryId", null);
			}
		}
		if (subCategoryId != null) {
			session.setAttribute("subCategoryId", subCategoryId);
			if(subCategoryId==0){
				session.setAttribute("subCategoryId", null);
			}
		}
		if (title != null) {
			session.setAttribute("title", title);
		}
		/* 从session当中取出对应你的参数，因为涉及强制转换问题，所以转换之前要判断空 */
		Object sessionSuperCategoryIdObject = session
				.getAttribute("superCategoryId");
		Object sessionSubCategoryIdObject = session
				.getAttribute("subCategoryId");
		Object sessionTitleObject = session.getAttribute("title");

		Integer sessionSuperCategoryId = null;
		Integer sessionSubCategoryId = null;
		String sesssionTitle = null;

		if (sessionSuperCategoryIdObject != null) {
			sessionSuperCategoryId = (Integer) sessionSuperCategoryIdObject;
			superCategory = new SuperCategory();
			superCategory.setId(sessionSuperCategoryId);
		}
		if (sessionSubCategoryIdObject != null) {
			sessionSubCategoryId = (Integer) sessionSubCategoryIdObject;
			category = new Category();
			category.setId(sessionSubCategoryId);
		}
		if (sessionTitleObject != null) {
			sesssionTitle = (String) sessionTitleObject;
		}

		/* 页面需要加载的父分类列表 */
		request.setAttribute("superCategories",
				categoryService.getAllSuperCategories(1, 100));

		/* 页面需要加载的子分类列表，判断如果session中没有放置父分类id，则查出所有子分类，否则，查出指定父分类对应的所有子分类 */
		if (sessionSuperCategoryId != null) {
			request.setAttribute("subCategories", categoryService
					.getAllSubCategoriesByParentId(sessionSuperCategoryId));
		} else {
			request.setAttribute("subCategories", categoryService.getAllCategoriesNoPaging());
		}

		/* 开始分页放置放置课程列表 */
		int count = courseService.getCoursesCount(superCategory,
				category, sesssionTitle);
		int pageSize = 9;
		Paging paging = new Paging(count, pageNO, pageSize);
		System.out.println("contorller--courseList-superCate:"+superCategory+"\ncontorller--courseList-subCate:"+category);
		List<Course> courses = courseService.getCourses(paging.getPageNo(), pageSize,
				superCategory, category, sesssionTitle, order,
				desc);
		session.setAttribute("courses", courses);
		session.setAttribute("paging", paging);
		session.setAttribute("order", order);
		return "fore/course-list";
	}

	@RequestMapping("/loadCourses")
	public void loadCourses(HttpSession session, String pageNO, String title,
			Integer order, Writer writer) {
		order = (Integer) session.getAttribute("order");
		if (order == null) {
			order = 5;
		}
		Integer supId = (Integer) session.getAttribute("supId");
		Integer subId = (Integer) session.getAttribute("subId");
		// System.out.println(supId + ":::" + subId);

		SuperCategory superCategory = categoryService
				.getSuperCategoryById(supId);
		Category category = categoryService.getCategoryById(subId);

		Integer countTotal = (Integer) session.getAttribute("countTotal");
		Paging paging = new Paging(countTotal, pageNO, 9);

		List<Course> courses = courseService.getCourses(paging.getPageNo(), 9,
				superCategory, category, title, order, true);

		JSONObject obj = new JSONObject();
		try {
			obj.put("courses", new JSONArray(courses, false));
			obj.put("paging", new JSONObject(paging));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		PrintWriter out = new PrintWriter(writer);
		out.print(obj);
		out.flush();
		out.close();
	}

	@RequestMapping("/search")
	public String search() {
		return null;
	}

	@RequestMapping("/buy")
	// 购买课程
	public String buy(HttpSession session, Integer id, Model model) {
		Course course = courseService.getCourseById(id);
		Deal deal = new Deal();
		deal.setCourse(course);
		User user = (User) session.getAttribute("currUser"); // 获取当前用户
		// User user = userService.getUserById(1); //模拟
		deal.setUser(user);
		deal.setType(true);
		deal.setGold(course.getPrice());
		deal.setTime(new Date());
		List<Course> courses = courseService.getCoursesUserPurchased(1, 20,
				user);
		for (Course c : courses) {
			if (c.getId() == id) {
				model.addAttribute("buyMessage", "已经购买");
				return "forward:/course/details";
			}
		}
		Integer addNewDeal = dealService.addNewDeal(deal);
		if (addNewDeal == 1) {
			model.addAttribute("buyMessage", "购买成功");
			return "forward:/course/details";
		}
		model.addAttribute("buyMessage", "余额不足");
		return "forward:/course/details"; // 跳转到充值界面
	}

	@RequestMapping("/details")
	// 加载课程详情
	public String details(Integer id, HttpSession session, String pageNO,
			Model model) {
		Course course = courseService.getCourseById(id);
		List<Catalog> catalogs = catalogService
				.getCatalogsByCourseId(1, 10, id);
		List<Courseware> coursewares = coursewareService
				.getCoursewaresByCourseId(id);
		session.setAttribute("course", course);
		session.setAttribute("catalogs", catalogs);
		session.setAttribute("coursewares", coursewares);
		return "fore/course-details";
	}

	@RequestMapping("/loadCatalog")
	public void loadCatalog(String pageNO, Writer writer, HttpSession session) {
		Course course = (Course) session.getAttribute("course");
		Integer id = course.getId();

		Integer catalogCount = catalogService.getCatalogsByCourseIdCount(id);
		Paging paging = new Paging(catalogCount, pageNO, 5);
		List<Catalog> catalogs = catalogService.getCatalogsByCourseId(
				paging.getPageNo(), 5, id);

		JSONObject obj = new JSONObject();
		try {
			obj.put("catalogs", new JSONArray(catalogs, false));
			obj.put("paging", new JSONObject(paging));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		PrintWriter out = new PrintWriter(writer);
		out.print(obj);
		out.flush();
		out.close();
	}

	@RequestMapping("/uploadForm")
	public String uploadForm(Model model) {
		List<Category> allCategoriesNoPaging = categoryService
				.getAllCategoriesNoPaging();
		model.addAttribute("categoryList", allCategoriesNoPaging);
		return "fore/upload-course";
	}

	@RequestMapping("/upload")
	public String upload(
			String title,
			Integer categoryId,
			Integer price,
			String target,
			String suitable,
			String demand,
			@RequestParam(value = "photo", required = false) MultipartFile file,
			HttpSession session, Model model) {
		User user = (User) session.getAttribute("currUser");
		if (user == null) {
			user = new User();
			user.setId(3);
		}
		Course course = new Course();
		course.setUser(user);
		course.setTitle(title);
		course.setCategory(new Category());
		course.getCategory().setId(categoryId);
		course.setPrice(price);
		course.setTarget(target);
		course.setSuitable(suitable);
		course.setDemand(demand);
		course.setDiscount(1.0);
		course.setStatus(false);
		course.setHandle(100);
		course.setTime(new Date());
		if (!file.getOriginalFilename().equals("")) {
			System.out.println(file.getOriginalFilename());
			String path = session.getServletContext().getRealPath(
					"resource/photo/course");
			String originalFilename = file.getOriginalFilename();
			String suffix = originalFilename.substring(originalFilename
					.lastIndexOf("."));
			if (!suffix.equals(".jpg") && !suffix.equals(".png")
					&& !suffix.equals(".gif") && !suffix.equals(".jpeg")) {
				model.addAttribute("loadMsg", "图片格式必须为jpg或者png或者gif或者jpeg！");
				model.addAttribute("course", course);
				return "forward:uploadForm";
			}
			String fileName = System.currentTimeMillis() + suffix;
			course.setPhoto(fileName);
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			try {
				file.transferTo(targetFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			course.setPhoto("default.jpg");
		}
		courseService.addNewCourse(course);
		return "redirect:/user/personalCenter";
	}

	@RequestMapping("/collect")
	// 收藏
	public String collect(HttpSession session, Integer id, Model model) {
		User user = (User) session.getAttribute("currUser"); // 获取当前用户
		Course course = courseService.getCourseById(id);
		Boolean collectCourse = courseService.collectCourse(user, course);
		if (collectCourse) {
			model.addAttribute("collMessage", "收藏成功");
			return "forward:/course/details";
		}
		model.addAttribute("collMessage", "已经收藏");
		return "forward:/course/details";
	}

	@RequestMapping("/loadCourseInfo")
	public void loadCourseInfo(Integer courseId, Writer writer)
			throws JSONException {
		PrintWriter out = new PrintWriter(writer);
		Course course = courseService.getCourseById(courseId);
		List<Category> categories = categoryService.getAllCategoriesNoPaging();
		JSONObject data = new JSONObject();
		data.put("course", new JSONObject(course));
		data.put("categories", new JSONArray(categories, false));
		out.print(data);
		out.flush();
		out.close();
	}

	@RequestMapping("/modifyCourseInfo")
	public void modifyCourseInfo(Integer id, String title, Integer categoryId,
			Integer price, String target, String suitable, String demand,
			Double discount, @RequestParam(value = "photo") MultipartFile file,
			HttpSession session, Writer writer) throws IllegalStateException,
			IOException {
		PrintWriter out = new PrintWriter(writer);
		User user = (User) session.getAttribute("currUser");
		Course targetCourse = courseService.getCourseById(id);
		if (targetCourse.getUser().getId() != user.getId()) {
			out.print("{\"fail\":\"没有权限修改该课程信息\"}");
			out.flush();
			out.close();
			return;
		}
		targetCourse.setTitle(title);
		targetCourse.getCategory().setId(categoryId);
		targetCourse.setPrice(price);
		targetCourse.setTarget(target);
		targetCourse.setSuitable(suitable);
		targetCourse.setDemand(demand);
		targetCourse.setDiscount(discount);
		if (!file.isEmpty()) {
			String newName = file.getOriginalFilename();
			String newExt = newName.substring(newName.lastIndexOf(".") + 1);
			if (!newExt.equals("jpg") && !newExt.equals("png")
					&& !newExt.equals("gif") && !newExt.equals("bmp")) {
				out.print("{\"fail\":\"文件格式不正确\"}");
				out.flush();
				out.close();
				return;
			}

			if (file.getSize() > 10 * 1024 * 1024) {
				out.print("{\"fail\":\"文件大小只能在10M以内\"}");
				out.flush();
				out.close();
				return;
			}
			String newSaveName = targetCourse.getPhoto();
			if (targetCourse.getPhoto().equals("default.jpg")
					|| !newExt.equals(targetCourse.getPhoto().substring(
							targetCourse.getPhoto().lastIndexOf(".") + 1))) {
				newSaveName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
						.format(new Date()) + "." + newExt;
			}
			String path = session.getServletContext().getRealPath(
					"resource/photo/course");
			file.transferTo(new File(path, newSaveName));
			targetCourse.setPhoto(newSaveName);
		}
		courseService.updateCourseInfoById(targetCourse);
		out.print("{\"success\":\"课程信息修改成功\"}");
		out.flush();
		out.close();
	}
}
