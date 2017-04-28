package com.online.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.edu.dao.AdminDao;
import com.online.edu.entity.Admin;
import com.online.edu.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public Integer addNewAdmin(Admin admin) {
		if(adminDao.getAdminByLoginId(admin.getLoginId()) != null){
			return 2;
		}
		adminDao.addNewAdmin(admin);
		return 1;
	}

	@Override
	public List<Admin> getAllNormalAdmins(Integer pageNO, Integer pageSize) {
		Integer rowStart = (pageNO - 1) * pageSize;
		return adminDao.getAllCommonAdmins(rowStart, pageSize);
	}

	@Override
	public Integer loginCheck(String loginId, String loginPsw) {
		Admin admin = adminDao.getAdminByLoginId(loginId);
		if(admin == null) {
			return 1;
		}
		if(!admin.getLoginPsw().equals(loginPsw)){
			return 2;
		}
		if(!admin.getStatus()){
			return 3;
		}
		return 4;
	}

	@Override
	public Admin getAdminById(Integer id) {
		return adminDao.getAdminById(id);
	}

	@Override
	public Admin getAdminByLoginId(String loginId) {
		return adminDao.getAdminByLoginId(loginId);
	}

	@Override
	public void updateAdminInfoById(Admin admin) {
		adminDao.updateAdminInfoById(admin);
	}

	@Override
	public void deleteAdminById(Integer id) {
		adminDao.deleteAdminById(id);
	}

	@Override
	public Integer getAllNormalAdminsCount() {
		return adminDao.getAllCommonAdminsCount();
	}

}
