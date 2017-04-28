package com.online.edu.service;

import java.util.List;

import com.online.edu.entity.Note;
import com.online.edu.entity.User;

public interface NoteService {
	/**
	 * 添加新的笔记
	 * 
	 * @param note
	 */
	void addNewNote(Note note);

	/**
	 * 分页查询指定的课程目录对应的除去当前用户的其他用户的所有笔记
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param catalogId
	 * @return
	 */
	List<Note> getOtherNotesByCatalogIdAndUserId(Integer pageNo,
			Integer pageSize, Integer catalogId, Integer userId);

	/**
	 * 分页查询指定的课程目录对应的指定用户的笔记
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param catalogId
	 * @param userId
	 * @return
	 */
	/**
	 * 查询总条数，用于分页
	 * 
	 * @param catalogId
	 * @return
	 */
	Integer getOtherNotesByCatalogIdAndUserIdCount(Integer catalogId,
			Integer userId);

	List<Note> getNotesByUseIdAndCatalogId(Integer pageNo, Integer pageSize,
			Integer catalogId, Integer userId);

	/**
	 * 分页获取指定用户的所有笔记
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	/**
	 * 查询总条数，用于分页
	 * 
	 * @param catalogId
	 * @param userId
	 * @return
	 */
	Integer getNotesByUseIdAndCatalogIdCount(Integer catalogId, Integer userId);

	/**
	 * 根据用户id查询该用户做的所有的笔记，用于在个人中心显示
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	List<Note> getNotesByUseId(Integer pageNo, Integer pageSize, Integer userId);

	/**
	 * 查询总条数，用于分页
	 * 
	 * @param userId
	 * @return
	 */
	Integer getNotesByUseId(Integer userId);

	List<Note> getPendingNotes(Integer pageNo, Integer pageSize);

	Integer getPendingNotesCount();

	/**
	 * 根据笔记id来修改笔记的status的值 管理员为处理的笔记的值为0，管理员已经处理了并且没给金币的值为1，管理员已经处理了并且给了金币的值为2
	 * 
	 * @param noteId
	 * @param status
	 */
	void updateNoteStatus(Integer noteId, Integer status);

	int getUserNotesCountClassify(User user, Integer superCategoryId,
			Integer subCategoryId);

	List<Note> getUserNotesClassify(int pageNo, int pageSize,
			Integer superCategoryId, Integer subCategoryId, Integer userId);
}
