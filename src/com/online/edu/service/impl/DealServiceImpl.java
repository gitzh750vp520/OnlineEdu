package com.online.edu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.edu.dao.DealDao;
import com.online.edu.dao.UserDao;
import com.online.edu.entity.Course;
import com.online.edu.entity.Deal;
import com.online.edu.service.DealService;

@Service("dealService")
public class DealServiceImpl implements DealService {
	
	@Autowired
	private DealDao dealDao;
	@Autowired
	private UserDao userDao;

	@Override
	public Integer addNewDeal(Deal deal) { //交易
		deal.setTime(new Date());
		if(deal.getType()){// 消费
			if(deal.getUser().getGold() < deal.getGold()){ // 余额小于消费金额
				return 0; // 返回0,代表消费时余额不足
			}else{
				Integer oldGold = deal.getUser().getGold();
				Integer newGold = deal.getGold();
				deal.getUser().setGold(oldGold - newGold);
				if(deal.getContent()==null){
					deal.setContent("消费：购买课程["+deal.getCourse().getTitle()+"]");
				}
				userDao.updateUserInfoById(deal.getUser());
				dealDao.addNewDeal(deal);
				return 1;// 返回1,代表消费成功
			}
		}else{ // 代表充值
			Integer oldGold = deal.getUser().getGold();
			Integer newGold = deal.getGold();
			deal.getUser().setGold(oldGold + newGold);
			deal.setCourse(new Course());
			if(deal.getContent()==null){
				deal.setContent("充值");
			}
			userDao.updateUserInfoById(deal.getUser());
			dealDao.addNewDeal(deal);
			return 2;// 返回2,代表充值成功
		}
	}

	@Override
	public List<Deal> getDealsByUserId(Integer pageNO,Integer pageSize,Integer id) {
		Integer rowStart = (pageNO - 1) *pageSize;
		return dealDao.getDealsByUserId(rowStart, pageSize, id);
	}

	@Override
	public Integer getDealsByUserIdCount(Integer id) {
		return dealDao.getDealsByUserIdCount(id);
	}

}
