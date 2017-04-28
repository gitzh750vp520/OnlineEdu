package com.online.edu.service;

import java.util.List;

import com.online.edu.entity.Comment;
import com.online.edu.entity.Reply;

public interface CommentService {
	/**
	 * 加载指定的课程目录id，对应的所有评论和回复
	 * @param id
	 * @return
	 */
	List<Comment> getCommentsAndRepliesByCatalogId(Integer id);
	List<Comment> getAllCommentsByCatalogId(Integer pageNo,Integer pageSize,Integer catalogId);
	Integer getAllCommentsByCatalogIdCount(Integer catalogId);
	/**
	 * 添加新的评论
	 * @param comment
	 */
	void addNewComment(Comment comment);
	/**
	 * 添加新的回复
	 * @param reply
	 */
	void addNewReply(Reply reply);
	/**
	 * 查看评论下的所有回复
	 * @param commentId
	 * @return
	 */
	List<Reply> getRepliesByCommentId(Integer commentId);
	Comment getCommentByCommentId(Integer commentId);
}
