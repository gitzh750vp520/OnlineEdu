package com.online.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.online.edu.entity.Catalog;

public interface CatalogDao {
	/**
	 * 通过课程id，查询出其所有的课程目录实体
	 * 
	 * @param rowStart
	 *            查询起始行
	 * @param pageSize
	 *            查询出的页面最大显示条数
	 * @param id
	 *            课程的id
	 * @return
	 */
	List<Catalog> getCatalogsByCourseId(@Param("rowStart") Integer rowStart, @Param("pageSize") Integer pageSize,
			@Param("id") Integer id);

	/**
	 * 根据课程id查询某个课程下所有课程目录（视频）的总条数
	 * 
	 * @param id
	 * @return
	 */
	Integer getCatalogsByCourseIdCount(Integer id);

	/**
	 * 添加新的课程目录
	 * 
	 * @param catalog
	 */
	void addNewCatalog(Catalog catalog);
	Catalog getCatalogByCatalogId(Integer catalogId);
	/**
	 * 根据视频id直接修改视频的播放次数
	 * @param catalogId
	 */
	void updateCatalogClickTimes(Integer catalogId);

	Integer getMaxSequenceByCourseId(Integer courseId);

}
