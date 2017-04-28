package com.online.edu.web.controller;

import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.online.edu.entity.Comment;
import com.online.edu.entity.Reply;
import com.online.edu.entity.User;
import com.online.edu.service.CommentService;

@Controller
@RequestMapping("/reply")
public class ReplyController extends BaseController{
	@Autowired
	private CommentService commentService;
	@RequestMapping("/addReply")
	public void addReply(String content,Integer pageNo,Integer commentId,HttpSession session,Writer writer){
		User sendUser = (User)session.getAttribute("currUser");
		if(sendUser == null){
			sendUser = new User();
			sendUser.setId(8);
		}
		Reply reply = new Reply();
		reply.setContent(content);
		Comment comment = commentService.getCommentByCommentId(commentId);
		reply.setComment(comment);
		reply.setSendUser(sendUser);
		reply.setReceiveUser(comment.getUser());
		commentService.addNewReply(reply);
		PrintWriter out = new PrintWriter(writer);
		out.print(pageNo);
		out.flush();
		out.close();
	}
}
