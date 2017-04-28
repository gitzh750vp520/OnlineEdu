package com.online.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.edu.dao.CommentDao;
import com.online.edu.dao.ReplyDao;
import com.online.edu.entity.Comment;
import com.online.edu.entity.Reply;
import com.online.edu.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ReplyDao replyDao;
	@Override
	public List<Comment> getCommentsAndRepliesByCatalogId(Integer id) {
		return commentDao.getCommentsAndRepliesByCatalogId(id);
	}

	@Override
	public void addNewComment(Comment comment) {
		commentDao.addNewComment(comment);
		
	}

	@Override
	public void addNewReply(Reply reply) {
		replyDao.addNewReply(reply);
		
	}

	@Override
	public List<Reply> getRepliesByCommentId(Integer commentId) {
		return replyDao.getRepliesByCommentId(commentId);
	}

	@Override
	public List<Comment> getAllCommentsByCatalogId(Integer pageNo,
			Integer pageSize, Integer catalogId) {
		Integer rowStart = (pageNo - 1) * pageSize;
		return commentDao.getAllCommentsByCatalogId(rowStart,pageSize,catalogId);
	}

	@Override
	public Integer getAllCommentsByCatalogIdCount(Integer catalogId) {
		return commentDao.getAllCommentsByCatalogIdCount(catalogId);
	}

	@Override
	public Comment getCommentByCommentId(Integer commentId) {
		return commentDao.getCommentByCommentId(commentId);
	}


}
