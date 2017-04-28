package com.online.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.online.edu.entity.Admin;

public interface AdminDao {

	/**
	 * 分页获取所有的普通管理员
	 * 
	 * @param rowStart
	 *            起始行
	 * @param pageSize
	 *            页面显示最大行数
	 * @return Admin的列表
	 */
	List<Admin> getAllCommonAdmins(@Param("rowStart") Integer rowStart, @Param("pageSize") Integer pageSize);

	/**
	 * 获取所有普通管理员条数
	 * 
	 * @return
	 */
	Integer getAllCommonAdminsCount();

	/**
	 * 根据管理员的loginId获取管理员
	 * 
	 * @param loginId
	 *            登录账号
	 * @return 管理员实体
	 */
	Admin getAdminByLoginId(String loginId);

	/**
	 * 根据管理员的id获取管理员
	 * 
	 * @param id
	 *            管理员序号
	 * @return 管理员实体
	 */
	Admin getAdminById(Integer id);

	/**
	 * 添加新的管理员
	 * 
	 * @param admin
	 *            管理员实体
	 */
	Integer addNewAdmin(Admin admin);

	/**
	 * 通过管理员id，来更新管理员信息（ 注意： 该方法是会默认更新所有字段，密码、姓名、状态
	 * 如果只更新一个字段，必须保证另外的字段和数据库中的数据一致 ）
	 * 
	 * @param admin
	 */
	void updateAdminInfoById(Admin admin);

	/**
	 * 通过管理员id，删除管理员
	 * 
	 * @param id
	 *            管理员id
	 */
	void deleteAdminById(Integer id);
}
