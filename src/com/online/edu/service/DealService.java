package com.online.edu.service;

import java.util.List;

import com.online.edu.entity.Deal;

public interface DealService {
	/**
	 * 添加新的交易记录
	 * @param deal
	 */
	Integer addNewDeal(Deal deal);
	
	
	/**
	 * 分页查询用户所有的交易记录
	 * @param pageNO
	 * @param pageSize
	 * @param id
	 * @return
	 */
	List<Deal> getDealsByUserId(Integer pageNO,Integer pageSize,Integer id);
	/**
	 * 查询总条数，用于分页
	 * @param id
	 * @return
	 */
	Integer getDealsByUserIdCount(Integer id);
}
