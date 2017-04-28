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

import com.online.edu.entity.Catalog;
import com.online.edu.entity.Comment;
import com.online.edu.entity.Reply;
import com.online.edu.entity.User;
import com.online.edu.service.CommentService;
import com.online.edu.web.tool.Paging;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{
	@Autowired
	private CommentService commentService;
	@RequestMapping("/showComments")
	public void showComments(String pageNo,Integer catalogId,Writer writer){
		Integer countTotal = commentService.getAllCommentsByCatalogIdCount(catalogId);
		Paging paging = new Paging(countTotal,pageNo,3);
		List<Comment> comments = commentService.getAllCommentsByCatalogId(paging.getPageNo(), 3, catalogId);
		for (Comment comment : comments) {
			List<Reply> replies = commentService.getRepliesByCommentId(comment.getId());
			comment.setReplies(replies);
		}
		JSONObject map = new JSONObject();
		try {
			map.put("commentList", new JSONArray(comments,false));
			map.put("paging", new JSONObject(paging));
		} catch (JSONException e) {
			throw  new RuntimeException(e);
		}
		PrintWriter out = new PrintWriter(writer);
		out.print(map);
		out.flush();
		out.close();
	}
	@RequestMapping("/addComment")
	public void addComment(String content,Integer catalogId,HttpSession session,Writer writer){
		User currUser = (User) session.getAttribute("currUser");
		if(currUser == null){
			currUser = new User();
			currUser.setId(1);
		}
		Comment comment = new Comment();
		comment.setUser(currUser);
		comment.setContent(content);
		comment.setCatalog(new Catalog());
		comment.getCatalog().setId(catalogId);
		commentService.addNewComment(comment);
		Integer countTotal = commentService.getAllCommentsByCatalogIdCount(catalogId);
		Paging paging = new Paging(countTotal,"1",3);
		PrintWriter out = new PrintWriter(writer);
		out.print(paging.getPageTotal());
		out.flush();
		out.close();
	}
}
