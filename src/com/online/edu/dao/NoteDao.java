package com.online.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.online.edu.entity.Note;

public interface NoteDao {
	/**
	 * 根据课程目录（视频）的id来获取当前这个视频下除去当前用户其他所有用户做的笔记
	 * @param rowStart
	 * @param pageSize
	 * @param id
	 * @return 笔记对象的集合
	 */
	List<Note> getOtherNotesByCatalogIdAndUserId(@Param(value="rowStart")Integer rowStart,@Param(value="pageSize")Integer pageSize,@Param(value="catalogId")Integer catalogId,@Param(value="userId")Integer userId);
	
	/**
	 * 查询指定catalog下除去当前用户的其他用户的笔记总条数
	 * @param catalogId
	 * @return
	 */
	Integer getOtherNotesByCatalogIdAndUserIdCount(@Param(value="catalogId")Integer catalogId,@Param(value="userId")Integer userId);
	
	/**
	 * 根据课程目录（视频）的id和用户的id来获取当前这个视频下某个用户做的笔记
	 * @param rowStart
	 * @param pageSize
	 * @param userId
	 * @param catalogId
	 * @return 笔记对象的集合
	 */
	List<Note> getNotesByUseIdAndCatalogId(@Param(value="rowStart")Integer rowStart,@Param(value="pageSize")Integer pageSize,@Param(value="userId")Integer userId,@Param(value="catalogId")Integer catalogId);
	/**
	 * 查询指定用户指定catalog笔记的总条数
	 * @param uid
	 * @param catalogId
	 * @return
	 */
	Integer getNotesByUseIdAndCatalogIdCount(@Param(value="userId")Integer uid,@Param(value="catalogId")Integer catalogId);
	/**
	 * 根据用户的id来获取当前这个用户做的所有笔记
	 * @param rowStart
	 * @param pageSize
	 * @param id
	 * @return 笔记对象的集合
	 */
	List<Note> getNotesByUserId(@Param(value="rowStart")Integer rowStart,@Param(value="pageSize")Integer pageSize,@Param(value="userId")Integer id);
	/**
	 * 查询用户的所有笔记总条数
	 * @param uid
	 * @return
	 */
	Integer getNotesByUserIdCount(@Param("uid")Integer uid);
	/**
	 * 分页查找所有等待管理员处理的笔记
	 * @param rowStart
	 * @param pageSize
	 * @return
	 */
	List<Note> getPendingNotes(@Param("rowStart")Integer rowStart,@Param("pageSize")Integer pageSize);
	/**
	 * 查询所有等待管理员处理的笔记的总条数
	 * 用于分页处理
	 * @return
	 */
	Integer getPendingNotesCount();
	/**
	 * 往数据库里面添加一条笔记数据
	 * @param note
	 */
	void addNewNote(Note note);
	void updateNoteStatus(@Param("noteId")Integer noteId,@Param("status")Integer status);

	List<Note> getUserNotesBySubCategoryId(@Param("rowStart")int rowStart,@Param("pageSize") int pageSize,
			@Param("subCategoryId")Integer subCategoryId,@Param("uid") Integer uid);

	List<Note> getUserNotesBySuperCategoryId(@Param("rowStart")int rowStart,@Param("pageSize") int pageSize,
			@Param("superCategoryId")Integer superCategoryId,@Param("uid") Integer uid);

	int getUserNotesCountBySubCategoryId(@Param("subCategoryId")Integer subCategoryId,@Param("uid")  Integer id);

	int getUserNotesCountBySuperCategoryId(@Param("superCategoryId")Integer superCategoryId,@Param("uid")  Integer id);
}
