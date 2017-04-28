package com.online.edu.service;

import java.util.List;

import com.online.edu.entity.Admin;

public interface AdminService {
	/**
	 * 添加新的管理员
	 * @param admin 管理员实体
	 * 成功返回"1" , 失败返回"2"
	 */
	Integer addNewAdmin(Admin admin);
	
	/**
	 * 查询所有的普通管理员
	 * @param pageNO 第几页
	 * @param pageSize 页面显示条数
	 * @return Admin的列表
	 */
	List<Admin> getAllNormalAdmins(Integer pageNO,Integer pageSize);
	/**
	 * 查询条数，用于分页
	 * @return
	 */
	Integer getAllNormalAdminsCount();
	/**
	 * 登录验证
	 * @param loginId 管理员账号
	 * @param loginPsw 管理员密码
	 * @return 1:账号不存在
	 * 		   2：密码错误
	 *         3：账号被禁用
	 *         4：账号、密码正确且可用
	 */
	Integer loginCheck(String loginId,String loginPsw);
	
	
	/**
	 * 通过管理员id，获得管理员实体
	 * @param id 管理员id
	 * @return 管理员实体
	 */
	Admin getAdminById(Integer id);
	/**
	 * 通过管理员loginId，获得管理员实体
	 * @param loginId 登录账号
	 * @return 管理员实体
	 */
	Admin getAdminByLoginId(String loginId);
	/**
	 * 通过管理员id，更新管理员信息
	 * @param id 管理员id
	 */
	void updateAdminInfoById(Admin admin);
	
	/**
	 * 通过管理员id，删除管理员
	 * @param id 管理员id
	 */
	void deleteAdminById(Integer id);
}
