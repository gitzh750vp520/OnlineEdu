package com.online.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.edu.dao.UserDao;
import com.online.edu.entity.User;
import com.online.edu.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public Integer loginCheck(String loginId, String loginPsw,Integer type) {
		User user = userDao.getUserByLoginId(loginId);
		System.out.println("service User:"+user);
		System.out.println("service User Type:" + user.getType() + type);
		System.out.println(user.getType() + type);
		System.out.println(user.getType() != type);
		System.out.println(user==null);
		if(user==null || user.getType() != type){
			return 1;
		}
		if(!user.getLoginPsw().equals(loginPsw)){
			return 2;
		}
		if(!user.getStatus()){
			return 3;
		}
		if(user.getType()==0 && user.getHandle()==101){
			return 4;
		}
		return 5;
	}

	@Override
	public Boolean registUser(User user) {
		System.out.println("service:"+user);
		user.setGold(0);
		if(user.getPhoto()==null){
			user.setPhoto("default.jpg");
		}
		if(userDao.getUserByLoginId(user.getLoginId())!=null){
			return false;
		}
		if(user.getType()==0){
			user.setStatus(false);
			user.setHandle(101);
		}else if(user.getType()==1){
			user.setStatus(true);
			user.setHandle(100);
		}
		userDao.addNewUser(user);
		return true;
	}

	@Override
	public void modifyUserInfoById(User user) {
		userDao.updateUserInfoById(user);
	}

	@Override
	public List<User> getAllRecentRegistTeachers(Integer pageNo,
			Integer pageSize) {
		return userDao.getRecentRegistTeachers((pageNo-1)*pageSize, pageSize);
	}

	@Override
	public User getUserById(Integer uid) {
		return userDao.getUserById(uid);
	}
	@Override
	public User getUserByLoginId(String loginId) {
		return userDao.getUserByLoginId(loginId);
	}
	@Override
	public Integer getAllRecentRegistTeachersCount() {
		return userDao.getRecentRegistTeachersCount();
	}

	@Override
	public List<User> getAllAvailableUser(Integer pageNO, Integer pageSize) {
		return userDao.getAllAvailableUser((pageNO-1)*pageSize, pageSize);
	}

	@Override
	public Integer getAllAvailableUserCount() {
		return userDao.getAllAvailableUserCount();
	}

	@Override
	public void deleteUserByLoginId(String loginId) {
		userDao.deleteUserByLoginId(loginId);
	}

	
}
