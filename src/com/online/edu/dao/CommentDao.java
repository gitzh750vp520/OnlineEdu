package com.online.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.online.edu.entity.Comment;

public interface CommentDao {
	/**
	 * 查询出对应课程目录下所有的评论和回复，该方法未做分页处理
	 * @param catalogId 课程目录id
	 * @return
	 */
	List<Comment> getCommentsAndRepliesByCatalogId(Integer catalogId);
	
	/**
	 * 添加新的评论
	 * @param comment
	 */
	void addNewComment(Comment comment);

	List<Comment> getAllCommentsByCatalogId(@Param(value="rowStart")Integer rowStart, @Param(value="pageSize")Integer pageSize,
			@Param(value="catalogId")Integer catalogId);

	Integer getAllCommentsByCatalogIdCount(Integer catalogId);

	Comment getCommentByCommentId(Integer commentId);
}
