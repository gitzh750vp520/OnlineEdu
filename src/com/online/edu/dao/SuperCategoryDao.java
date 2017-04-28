package com.online.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.online.edu.entity.SuperCategory;

public interface SuperCategoryDao {
	/**
	 * 获取所有的父分类
	 * @return 父分类对象的集合
	 */
	List<SuperCategory> getAllSuperAndSubCategories();
	/**
	 * 从某一条开始查询指定的条数的数据
	 * @param rowStart 开始的地方
	 * @param pageSize 查询的条数
	 * @return 父分类对象的集合
	 */
	List<SuperCategory> getAllSuperCategories(@Param("rowStart")Integer rowStart,@Param("pageSize")Integer pageSize);
	/**
	 * 查询所有父分类的总条数
	 * @return
	 */
	Integer getAllSuperCategoriesCount();
	/**
	 * 根绝id来查询父分类
	 * @param id
	 * @return 唯一的父分类对象
	 */
	SuperCategory getSuperCategoryById(@Param("superCategoryId")Integer id);
	/**
	 * 往数据库里面新添加一条父分类数据
	 * @param superCategory
	 */
	void addNewSuperCategory(@Param("newSuperCategory")SuperCategory superCategory);
	/**
	 * 更新父分类，由于目前父分类只有id和name，所以这里仅能更新父分类的name
	 * 但是因为要根据id来进行修改，所以传进来的父分类对象必须是有id的父分类
	 * @param superCategory
	 */
	void updateSuperCategoryInfoById(SuperCategory superCategory);
	/**
	 * 根据父分类名称获取分类实体
	 * @param name 父分类名称
	 * @return
	 */
	SuperCategory getSuperCategoryByName(@Param("name")String name);
	List<SuperCategory> getAllSuperCategoriesNotesNotNullByUserId(@Param("uid")Integer id);
}
