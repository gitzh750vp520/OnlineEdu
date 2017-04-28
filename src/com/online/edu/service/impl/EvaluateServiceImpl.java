package com.online.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.edu.dao.EvaluateDao;
import com.online.edu.entity.Evaluate;
import com.online.edu.service.EvaluateService;

@Service("evaluateService")
public class EvaluateServiceImpl implements EvaluateService {
	@Autowired
	private EvaluateDao evaluateDao;
	@Override
	public Boolean addNewEvaluate(Evaluate evaluate) {
		if(evaluateDao.getEvaluateByUserIdAndCourseId(evaluate.getUser().getId(), evaluate.getCourse().getId()) != null){
			return false;
		}
		evaluateDao.addNewEvaluate(evaluate);
		return true;
	}

	@Override
	public List<Evaluate> getEvaluatesByCourseId(Integer pageNO,
			Integer pageSize, Integer courseId) {
		Integer rowStart = (pageNO - 1) * pageSize;
		return evaluateDao.getEvaluatesByCourseId(rowStart, pageSize, courseId);
	}

	@Override
	public Integer getEvaluatesByCourseIdCount(Integer courseId) {
		return evaluateDao.getEvaluatesByCourseIdCount(courseId);
	}

	@Override
	public Boolean checkEvaluateByUserIdAndCourseId(Integer userId,
			Integer courseId) {
		return evaluateDao.getEvaluateByUserIdAndCourseId(userId, courseId) == null;
	}

}
