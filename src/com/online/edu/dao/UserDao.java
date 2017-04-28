package com.online.edu.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.online.edu.entity.User;

public interface UserDao {
	/**
	 * 根据id来查询用户
	 * @param id
	 * @return 唯一的用户对象
	 */
	User getUserById(@Param("id")Integer id);
	/**
	 * 根据loginId来查询用户
	 * @param loginId
	 * @return 唯一的用户对象
	 */
	User getUserByLoginId(@Param("loginId")String loginId);
	/**
	 * 分页获取所有已审核的老师，和不需审核的学生的列表
	 * @param rowStart
	 * @param pageSize
	 * @return
	 */
	List<User> getAllAvailableUser(@Param("rowStart")Integer rowStart,@Param("pageSize")Integer pageSize);
	
	
	/**
	 * 获取所有已审核的老师，和不需审核的学生的总条数
	 * @return
	 */
	Integer getAllAvailableUserCount();
	/**
	 * 分页查询待审核的所有老师
	 * @param rowStart
	 * @param pageSize
	 * @return
	 */
	List<User> getRecentRegistTeachers(@Param("rowStart")Integer rowStart,@Param("pageSize")Integer pageSize);

	/**
	 * 查询待审核所有老师的总条数
	 * @return
	 */
	Integer getRecentRegistTeachersCount();
	/**
	 * 添加一用户
	 * @param user
	 */
	void addNewUser(@Param("insertUser")User user);
	/**
	 * 更新用户的信息
	 * @param user
	 */
	void updateUserInfoById(@Param("updateUser")User user);
	void deleteUserByLoginId(String loginId);
}
