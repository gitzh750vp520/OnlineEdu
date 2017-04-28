package com.online.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.edu.dao.CatalogDao;
import com.online.edu.entity.Catalog;
import com.online.edu.service.CatalogService;

@Service("catalogService")
public class CatalogServiceImpl implements CatalogService{

	@Autowired
	private CatalogDao catalogDao;
	
	@Override
	public void addNewCatalog(Catalog catalog) {
		catalogDao.addNewCatalog(catalog);
	}

	@Override
	public List<Catalog> getCatalogsByCourseId(Integer pageNO,
			Integer pageSize, Integer courseId) {
		Integer rowStart = (pageNO -1) * pageSize;
		return catalogDao.getCatalogsByCourseId(rowStart, pageSize, courseId);
	}

	@Override
	public Integer getCatalogsByCourseIdCount(Integer courseId) {
		return catalogDao.getCatalogsByCourseIdCount(courseId);
	}

	@Override
	public Catalog getCatalogByCatalogId(Integer catalogId) {
		return catalogDao.getCatalogByCatalogId(catalogId);
	}

	@Override
	public Catalog watch(Integer catalogId) {
		catalogDao.updateCatalogClickTimes(catalogId);
		return catalogDao.getCatalogByCatalogId(catalogId);
	}

	@Override
	public Integer getMaxSequenceByCourseId(Integer courseId) {
		return catalogDao.getMaxSequenceByCourseId(courseId);
	}

}
