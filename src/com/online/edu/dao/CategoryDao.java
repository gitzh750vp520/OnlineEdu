package com.online.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.online.edu.entity.Category;

public interface CategoryDao {
	/**
	 * 分页获取所有的子分类
	 * @param rowStart 起始行
	 * @param pageSize 页面最大显示的条数
	 * @return 子分类实体的列表
	 */
	List<Category> getAllCategories(@Param("rowStart")Integer rowStart,@Param("pageSize")Integer pageSize);
	/**
	 * 查询所有子分类的条数
	 * @return
	 */
	Integer getAllCategoriesCount();
	/**
	 * 通过父分类的id，分页查询其所有子分类
	 * @param id 父分类id
	 * @return 子分类实体的列表
	 */
	List<Category> getCategoriesByParentId(@Param("superCategoryId")Integer id);
	/**
	 * 通过子分类id，查询其对应的子分类对象
	 * @param id 子分类id
	 * @return 子分类实体
	 */
	Category getCategoryById(@Param("categoryId") Integer id);
	/**
	 * 根据子分类名称查找指定的子分类实体
	 * @param name
	 * @return
	 */
	Category getCategoryByName(@Param("name") String name);
	/**
	 * 添加新的子分类
	 * @param category
	 */
	void addNewCategory(Category category);
	/**
	 * 通过子分类id，更新子分类信息（
	 * 	注意：该方法sql语句使用了mybatis的动态sql技术
	 * 		更新子分类信息，主要是更新名称name,和父分类id，super_category_id
	 * 		不需要更新的字段可以传null
	 * ）
	 * @param category
	 */
	void updateCategoryInfoById(Category category);
	/**
	 * 不分页查询所有的子分类
	 * @return
	 */
	List<Category> getAllCategoriesNoPaging();
	
	List<Category> getCategoriesNotesNotNullByUserId(@Param("uid")Integer uid);
}
