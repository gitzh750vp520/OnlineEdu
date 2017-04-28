package com.online.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.edu.dao.CategoryDao;
import com.online.edu.dao.SuperCategoryDao;
import com.online.edu.entity.Category;
import com.online.edu.entity.SuperCategory;
import com.online.edu.entity.User;
import com.online.edu.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private SuperCategoryDao superCategoryDao;
	@Override
	public List<SuperCategory> getAllSuperAndSubCategories() {
		return superCategoryDao.getAllSuperAndSubCategories();
	}

	@Override
	public List<SuperCategory> getAllSuperCategories(Integer pageNO,
			Integer pageSize) {
		return superCategoryDao.getAllSuperCategories((pageNO-1)*pageSize, pageSize);
	}
	@Override
	public Boolean addNewSuperCategory(SuperCategory superCategory) {
	SuperCategory repeat= superCategoryDao.getSuperCategoryByName(superCategory.getName());
		if(repeat!=null){
			return false;
		}
		superCategoryDao.addNewSuperCategory(superCategory);
		return true;
	
	}

	@Override
	public SuperCategory getSuperCategoryById(Integer suId) {
		return superCategoryDao.getSuperCategoryById(suId);
	}

	@Override
	public Boolean updateSuperCategoryNameById(Integer id, String name) {
		if(superCategoryDao.getSuperCategoryByName(name)!=null){
			return false;
		}
		SuperCategory superCategory = new SuperCategory();
		superCategory.setId(id);
		superCategory.setName(name);
		superCategoryDao.updateSuperCategoryInfoById(superCategory);
		return true;
	}

	@Override
	public Boolean addNewSubCategory(Category category) {
		if(categoryDao.getCategoryByName(category.getName())!=null){
			return false;
		}
		categoryDao.addNewCategory(category);
		return true;
	}

	@Override
	public List<Category> getAllSubCategories(Integer pageNO, Integer pageSize) {
		return categoryDao.getAllCategories((pageNO-1)*pageSize, pageSize);
	}

	@Override
	public List<Category> getAllSubCategoriesByParentId(Integer parentId) {
		return categoryDao.getCategoriesByParentId(parentId);
	}

	@Override
	public Category getCategoryById(Integer id) {
		return categoryDao.getCategoryById(id);
	}

	@Override
	public Integer getAllSuperCategoriesCount() {
		return superCategoryDao.getAllSuperCategoriesCount();
	}

	@Override
	public Integer getAllSubCategoriesCount() {
		return categoryDao.getAllCategoriesCount();
	}

	@Override
	public SuperCategory getSuperCategoryByName(String name) {
		return superCategoryDao.getSuperCategoryByName(name);
	}

	@Override
	public Category getCategoryByName(String name) {
		return categoryDao.getCategoryByName(name);
	}

	@Override
	public Boolean updateSubCategoryById(Category category) {
		if(categoryDao.getCategoryByName(category.getName()) != null){
			return false;
		}
		categoryDao.updateCategoryInfoById(category);
		return true;
	}

	@Override
	public List<Category> getAllCategoriesNoPaging() {
		return categoryDao.getAllCategoriesNoPaging();
	}

	/**
	 * 根据父分类的名字来查找父分类，找到后再根据新父分类的名字查找是否已经存在，如果不存在，就修改它的名字
	 * @param oldSupName
	 * @param newSupName
	 * @return 如果给的旧的父分类的名字没找到，则返回1，没找到父分类；找到了，但是新的父分类的名字已经存在，则返回2，否则就修改，修改则返回3
	 */
	@Override
	public Integer modifySuperCategoryName(String oldSupName, String newSupName) {
		SuperCategory oldSup = superCategoryDao.getSuperCategoryByName(oldSupName);
		if(oldSup == null){
			return 1;
		}else{
			SuperCategory newSup = superCategoryDao.getSuperCategoryByName(newSupName);
			if(newSup != null){
				return 2;
			}else{
				oldSup.setName(newSupName);
				superCategoryDao.updateSuperCategoryInfoById(oldSup);
				return 3;
			}
		}
	}
	/**
	 * 根据子分类的名字来查找子分类，找到后再根据新子分类的名字查找是否已经存在，如果不存在，就修改它的名字
	 * @param oldSubName
	 * @param newSubName
	 * @return 如果给的旧的子分类的名字没找到，则返回1，没找到子分类；找到了，但是新的子分类的名字已经存在，则返回2，否则就修改，修改则返回3
	 */
	@Override
	public Integer modifyCategoryName(String oldSubName, String newSubName) {
		Category oldSub = categoryDao.getCategoryByName(oldSubName);
		if(oldSub == null){
			return 1;
		}else{
			Category newSub = categoryDao.getCategoryByName(newSubName);
			if(newSub != null){
				return 2;
			}else{
				oldSub.setName(newSubName);
				categoryDao.updateCategoryInfoById(oldSub);
				return 3;
			}
		}
	}

	@Override
	public List<SuperCategory> getAllSuperCategoriesNotesNotNull(User user) {
		return superCategoryDao.getAllSuperCategoriesNotesNotNullByUserId(user.getId());
	}

	@Override
	public List<Category> getAllCategoriesNotesNotNull(User user) {
		return categoryDao.getCategoriesNotesNotNullByUserId(user.getId());
	}
	
}
