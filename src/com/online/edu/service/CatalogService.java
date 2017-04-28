package com.online.edu.service;

import java.util.List;

import com.online.edu.entity.Catalog;

public interface CatalogService {
	/**
	 * 添加新的目录
	 * @param catalog 目录实体
	 * 成功返回"1" , 失败返回"2"
	 */
	void addNewCatalog(Catalog catalog);
	/**
	 * 获取对应课程id下，所有的目录
	 * @param pageNO 页码
	 * @param pageSize 页面最大显示条数
	 * @param courseId 课程id
	 * @return
	 */
	List<Catalog> getCatalogsByCourseId(Integer pageNO,Integer pageSize,Integer courseId);
	/**
	 * 查询条数，用于分页
	 * @param courseId
	 * @return
	 */
	Integer getCatalogsByCourseIdCount(Integer courseId);
	/**
	 * 根据课程目录（视频）id来获取相应的视频对象
	 * @param catalogId
	 * @return
	 */
	Catalog getCatalogByCatalogId(Integer catalogId);
	Catalog watch(Integer catalogId);
	Integer getMaxSequenceByCourseId(Integer courseId);
}
