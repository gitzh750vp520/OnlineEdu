package com.online.edu.dao;

import java.util.List;

import com.online.edu.entity.Reply;

public interface ReplyDao {
	/**
	 * 根据评论的id来获取当前这条评论下的所有的回复
	 * @param commentId
	 * @return 回复对象的集合
	 */
	List<Reply> getRepliesByCommentId(Integer commentId);
	/**
	 * 往数据库里面添加一条心的回复数据
	 * @param reply
	 */
	void addNewReply(Reply reply);
}
