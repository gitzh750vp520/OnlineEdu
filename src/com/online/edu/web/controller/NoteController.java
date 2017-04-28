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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.online.edu.entity.Catalog;
import com.online.edu.entity.Category;
import com.online.edu.entity.Note;
import com.online.edu.entity.SuperCategory;
import com.online.edu.entity.User;
import com.online.edu.service.CategoryService;
import com.online.edu.service.NoteService;
import com.online.edu.web.tool.Paging;


@Controller
@RequestMapping("/note")
public class NoteController extends BaseController{
	@Autowired
	private NoteService noteService;
	@Autowired
	private CategoryService categoryService;
	@RequestMapping("/loadOtherNotes")
	public void loadOtherNotes(String pageNo,Integer catalogId,HttpSession session,Writer writer){
		User currUser = (User) session.getAttribute("currUser");
		Integer countTotal = noteService.getOtherNotesByCatalogIdAndUserIdCount(catalogId,currUser.getId());
		Paging paging = new Paging(countTotal,pageNo,5);
		List<Note> notes = noteService.getOtherNotesByCatalogIdAndUserId(paging.getPageNo(), 5, catalogId,currUser.getId());
		JSONObject map = new JSONObject();
		try {
			map.put("noteList", new JSONArray(notes,false));
			map.put("paging", new JSONObject(paging));
		} catch (JSONException e) {
			throw  new RuntimeException(e);
		}
		PrintWriter out = new PrintWriter(writer);
		out.print(map);
		out.flush();
		out.close();
	}
	@RequestMapping("/loadUserNotes")
	public void loadUserNotes(String pageNo,Integer catalogId,HttpSession session,Writer writer){
		User currUser = (User) session.getAttribute("currUser");
		if(currUser == null){
			currUser = new User();
			currUser.setId(1);
		}
		Integer countTotal = noteService.getNotesByUseIdAndCatalogIdCount(catalogId, currUser.getId());
		Paging paging = new Paging(countTotal,pageNo,5);
		List<Note> notes = noteService.getNotesByUseIdAndCatalogId(paging.getPageNo(), 5, catalogId, currUser.getId());
		JSONObject map = new JSONObject();
		try {
			map.put("noteList", new JSONArray(notes,false));
			map.put("paging", new JSONObject(paging));
		} catch (JSONException e) {
			throw  new RuntimeException(e);
		}
		PrintWriter out = new PrintWriter(writer);
		out.print(map);
		out.flush();
		out.close();
	}
	@RequestMapping("/addNote")
	public void addNote(String content,Integer catalogId,HttpSession session,Writer writer){
		User currUser = (User) session.getAttribute("currUser");
		if(currUser == null){
			currUser = new User();
			currUser.setId(1);
		}
		Note note = new Note();
		note.setUser(currUser);
		note.setContent(content);
		note.setCatalog(new Catalog());
		note.getCatalog().setId(catalogId);
		noteService.addNewNote(note);
		Integer countTotal = noteService.getNotesByUseIdAndCatalogIdCount(catalogId, currUser.getId());
		Paging paging = new Paging(countTotal,"1",5);
		PrintWriter out = new PrintWriter(writer);
		out.print(paging.getPageTotal());
		out.flush();
		out.close();
	}
	@RequestMapping("/noteList")
	public String noteList(HttpSession session,Model model){
		User user = (User) session.getAttribute("currUser");
		List<SuperCategory> superList =categoryService.getAllSuperCategoriesNotesNotNull(user);
		List<Category> list = categoryService.getAllCategoriesNotesNotNull(user);
		model.addAttribute("superList",superList);
		model.addAttribute("list", list);
		return "fore/note-list";
	}
	@RequestMapping("/loadUserNotesSpecfic")
	public void loadUserNotesSpecfic(String pageNoStr,Integer superCategoryId,Integer subCategoryId,HttpSession session,Writer writer) throws JSONException{
		Object sp=session.getAttribute("note-super-categoryId");
		Object sub=session.getAttribute("note-sub-categoryId");
		if(sp==null){
			session.setAttribute("note-super-categoryId", superCategoryId);
		}
		if(sub==null){
			session.setAttribute("note-sub-categoryId", subCategoryId);
		}
		if(superCategoryId !=null){
			
			if(subCategoryId==null){
				session.setAttribute("note-sub-categoryId", subCategoryId);
			}
			if(superCategoryId==0){
				session.setAttribute("note-sub-categoryId", null);
				session.setAttribute("note-super-categoryId", null);
			}else{
				session.setAttribute("note-super-categoryId", superCategoryId);
			}
		}
		if(subCategoryId !=null){
			session.setAttribute("note-sub-categoryId", subCategoryId);
		}
		User user = (User) session.getAttribute("currUser");
		Object sessionSP=session.getAttribute("note-super-categoryId");
		Object sessionSUB=session.getAttribute("note-sub-categoryId");
		Integer spId = null;
		Integer subId = null;
		if(sessionSP!=null){
			spId = (Integer)sessionSP;
		}
		if(sessionSUB !=null){
			subId = (Integer)sessionSUB;
		}
		int count = noteService.getUserNotesCountClassify(user,spId,subId);
		int pageSize = 6;
		Paging paging = new Paging(count, pageNoStr, pageSize);
		List<Note> notes = noteService.getUserNotesClassify(paging.getPageNo(),pageSize,spId,subId,user.getId());
		JSONObject map = new JSONObject();
		map.put("notes", new JSONArray(notes,false));
		map.put("paging", new JSONObject(paging));
		PrintWriter out = new PrintWriter(writer);
		out.print(map);
		out.flush();
		out.close();
	}
	
	
}
