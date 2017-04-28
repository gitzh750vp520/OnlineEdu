package com.online.edu.web.controller;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.online.edu.entity.Category;
import com.online.edu.entity.SuperCategory;
import com.online.edu.entity.User;
import com.online.edu.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController{
	@Autowired
	private CategoryService categoryService;
	@RequestMapping("/loadAllSuperCategories")
	public String loadAllSuperCategories(){
		return null;
	}
	@RequestMapping("/loadAllSubCategories")
	public String loadAllSubCategories(){
		return null;
	}
	@RequestMapping("/loadAllCategories")
	public void loadAllCategories(Writer  writer){
		PrintWriter out = new PrintWriter(writer);
		List<SuperCategory> list = categoryService.getAllSuperAndSubCategories();
		JSONArray data  = new JSONArray(list, false);
		out.print(data);
		out.flush();
		out.close();
	}
	@RequestMapping("/loadUserNotesCategories")
	public void loadUserNotesCategories(HttpSession session,Writer writer) throws JSONException{
		User user = (User) session.getAttribute("currUser");
		PrintWriter out = new PrintWriter(writer);
		List<SuperCategory> superList =categoryService.getAllSuperCategoriesNotesNotNull(user);
		List<Category> list = categoryService.getAllCategoriesNotesNotNull(user);
		JSONObject data = new JSONObject();
		data.put("superList", new JSONArray(superList, false));
		data.put("list", new JSONArray(list, false));
		out.print(data);
		out.flush();
		out.close();
	}
}
