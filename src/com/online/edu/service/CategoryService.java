package com.online.edu.service;

import java.util.List;

import com.online.edu.entity.Category;
import com.online.edu.entity.SuperCategory;
import com.online.edu.entity.User;

public interface CategoryService {
	/**
	 * 获取所有的父分类的子分类，用于前台主页加载数据
	 * @return
	 */
	List<SuperCategory> getAllSuperAndSubCategories();
	/**
	 * 分页获取父分类
	 * @param pageNO 页码
	 * @param pageSize 页面最大显示条数
	 * @return
	 */
	List<SuperCategory> getAllSuperCategories(Integer pageNO,Integer pageSize);
	/**
	 * 查询条数，用于分页
	 * @return
	 */
	Integer getAllSuperCategoriesCount();
	/**
	 * 添加新的父分类
	 * @param superCategory
	 * @return 如果父分类名称重复则返回false，否则添加成功，返货true
	 */
	Boolean addNewSuperCategory(SuperCategory superCategory);
	/**
	 * 根据id查询指定的父分类
	 * @param suId 父分类id
	 * @return
	 */
	SuperCategory getSuperCategoryById(Integer suId);
	/**
	 * 根据指定的父分类名称查找指定的父分类实体
	 * @param name 父分类名称
	 * @return
	 */
	SuperCategory getSuperCategoryByName(String name);
	/**
	 * 根据指定的父分类id，更新该分类的名称
	 * @param id 父分类id
	 * @param name 需要更改的名称
	 */
	Boolean updateSuperCategoryNameById(Integer id,String name);
	/**
	 * 添加新的子分类
	 * @param category
	 */
	Boolean addNewSubCategory(Category category);
	/**
	 * 分页查找子分类
	 * @param pageNO 页码
	 * @param pageSize 页面最大显示条数
	 * @return
	 */
	List<Category> getAllSubCategories(Integer pageNO,Integer pageSize);
	/**
	 * 查询条数，用于分页
	 * @return
	 */
	Integer getAllSubCategoriesCount();
	/**
	 * 通过父分类的id，查找其所有的子分类
	 * @param parentId
	 * @return
	 */
	List<Category> getAllSubCategoriesByParentId(Integer parentId);
	/**
	 * 根据id查找指定的子分类
	 * @param id
	 * @return
	 */
	Category getCategoryById(Integer id);
	/**
	 * 根据子分类名称查找指定的子分类实体
	 * @param name
	 * @return
	 */
	Category getCategoryByName(String name);
	
	/**
	 * 根据id，更新指定的子分类信息
	 * @param category
	 */
	Boolean updateSubCategoryById(Category category);
	/**
	 * 查询所有子分类，用于用户上传课程时让其选择课程的分类
	 * @return
	 */
	List<Category> getAllCategoriesNoPaging();
	/**
	 * 根据父分类的名字来查找父分类，找到后再根据新父分类的名字查找是否已经存在，如果不存在，就修改它的名字
	 * @param oldSupName
	 * @param newSupName
	 * @return 如果给的旧的父分类的名字没找到，则返回1，没找到父分类；找到了，但是新的父分类的名字已经存在，则返回2，否则就修改，修改则返回3
	 */
	Integer modifySuperCategoryName(String oldSupName, String newSupName);
	/**
	 * 根据子分类的名字来查找子分类，找到后再根据新子分类的名字查找是否已经存在，如果不存在，就修改它的名字
	 * @param oldSubName
	 * @param newSubName
	 * @return 如果给的旧的子分类的名字没找到，则返回1，没找到子分类；找到了，但是新的子分类的名字已经存在，则返回2，否则就修改，修改则返回3
	 */
	Integer modifyCategoryName(String oldSubName, String newSubName);
	
	/**
	 * 获取所有的子分类和父分类，针对指定的用户且笔记不能为空
	 * @return
	 */
	List<SuperCategory> getAllSuperCategoriesNotesNotNull(User user);
	List<Category> getAllCategoriesNotesNotNull(User user);
}
