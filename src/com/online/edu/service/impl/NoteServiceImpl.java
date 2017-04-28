package com.online.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.edu.dao.NoteDao;
import com.online.edu.entity.Note;
import com.online.edu.entity.User;
import com.online.edu.service.NoteService;

@Service("noteService")
public class NoteServiceImpl implements NoteService {
	@Autowired
	private NoteDao noteDao;

	@Override
	public void addNewNote(Note note) {
		noteDao.addNewNote(note);
	}

	@Override
	public List<Note> getOtherNotesByCatalogIdAndUserId(Integer pageNo,
			Integer pageSize, Integer catalogId, Integer userId) {
		Integer rowStart = (pageNo - 1) * pageSize;
		return noteDao.getOtherNotesByCatalogIdAndUserId(rowStart, pageSize,
				catalogId, userId);
	}

	@Override
	public List<Note> getNotesByUseIdAndCatalogId(Integer pageNo,
			Integer pageSize, Integer catalogId, Integer userId) {
		Integer rowStart = (pageNo - 1) * pageSize;
		return noteDao.getNotesByUseIdAndCatalogId(rowStart, pageSize, userId,
				catalogId);
	}

	@Override
	public List<Note> getNotesByUseId(Integer pageNo, Integer pageSize,
			Integer userId) {
		Integer rowStart = (pageNo - 1) * pageSize;
		return noteDao.getNotesByUserId(rowStart, pageSize, userId);
	}

	@Override
	public Integer getOtherNotesByCatalogIdAndUserIdCount(Integer catalogId,
			Integer userId) {
		return noteDao
				.getOtherNotesByCatalogIdAndUserIdCount(catalogId, userId);
	}

	@Override
	public Integer getNotesByUseIdAndCatalogIdCount(Integer catalogId,
			Integer userId) {
		return noteDao.getNotesByUseIdAndCatalogIdCount(userId, catalogId);
	}

	@Override
	public Integer getNotesByUseId(Integer userId) {
		return noteDao.getNotesByUserIdCount(userId);
	}

	@Override
	public void updateNoteStatus(Integer noteId, Integer status) {
		noteDao.updateNoteStatus(noteId, status);
	}

	@Override
	public List<Note> getPendingNotes(Integer pageNo, Integer pageSize) {
		Integer rowStart = (pageNo - 1) * pageSize;
		return noteDao.getPendingNotes(rowStart, pageSize);
	}

	@Override
	public Integer getPendingNotesCount() {
		return noteDao.getPendingNotesCount();
	}

	@Override
	public int getUserNotesCountClassify(User user, Integer superCategoryId,
			Integer subCategoryId) {
		if (superCategoryId == null && subCategoryId == null) {
			return noteDao.getNotesByUserIdCount(user.getId());
		} else {
			if (subCategoryId != null) {
				return noteDao.getUserNotesCountBySubCategoryId(
						subCategoryId, user.getId());
			}else  if (superCategoryId != null) {
				return noteDao.getUserNotesCountBySuperCategoryId(superCategoryId,
						user.getId());
			} 
		}

		return 0;
	}

	@Override
	public List<Note> getUserNotesClassify(int pageNo, int pageSize,
			Integer superCategoryId, Integer subCategoryId, Integer uid) {
		if (superCategoryId == null && subCategoryId == null) {
				return noteDao.getNotesByUserId((pageNo - 1)
						* pageSize, pageSize, uid);
		} else {
			if (subCategoryId != null) {
				return noteDao.getUserNotesBySubCategoryId((pageNo - 1)
						* pageSize, pageSize, subCategoryId, uid);
			}else if (superCategoryId != null) {
				return noteDao.getUserNotesBySuperCategoryId((pageNo - 1)
						* pageSize, pageSize, superCategoryId, uid);
			} 
		}
		return null;
	}

}
