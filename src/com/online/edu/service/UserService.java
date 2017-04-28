package com.online.edu.service;

import java.util.List;

import com.online.edu.entity.User;

public interface UserService {
	/**
	 * 根据loginId去数据库查询数据 如果查到了再和loginPsw对比
	 * 
	 * @param loginId
	 * @param loginPsw
	 * @param type 
	 * @return 如果没查到，或者通过loginId查询到了用户，但是类型与传入参数类型不一致，则返回1，代表用户名不存在；
	 * 如果查到了但是密码比对不正确，则返回2，代表密码错误；
	 * 如果账号被禁用，则返回3
	 * 如果是老师登录，但是验证该账号的处理码为101则返回4，代表该账户待后台处理
	 * 账号验证无误，则返回5
	 * 
	 */
	Integer loginCheck(String loginId, String loginPsw,Integer type);

	/**
	 * 注册新用户，如果是学生则handle设值为100，如果是老师则handle设值为101 如果loginId重复，则返回false
	 * 
	 * @param user
	 */
	Boolean registUser(User user);

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 */
	void modifyUserInfoById(User user);

	/**
	 * 获取所有待审核的老师列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<User> getAllRecentRegistTeachers(Integer pageNo, Integer pageSize);

	/**
	 * 获取所有待审核老师的总条数
	 * 
	 * @return
	 */
	Integer getAllRecentRegistTeachersCount();

	/**
	 * 通过id查询指定的User
	 * 
	 * @param uid
	 * @return
	 */
	User getUserById(Integer uid);

	/**
	 * 通过指定的loginId查询指定的User
	 * 
	 * @param loginId
	 * @return
	 */
	User getUserByLoginId(String loginId);

	/**
	 * 分页获取所有已审核的老师，和不需审核的老师的列表
	 * 
	 * @return
	 */
	List<User> getAllAvailableUser(Integer pageNO, Integer pageSize);

	/**
	 * 分页获取所有已审核的老师，和不需审核的老师的数量
	 * 
	 * @return
	 */
	Integer getAllAvailableUserCount();
	void deleteUserByLoginId(String loginId);
}
