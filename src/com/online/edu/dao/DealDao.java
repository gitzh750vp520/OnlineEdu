package com.online.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.online.edu.entity.Deal;

public interface DealDao {
	/**
	 * 分页获取用户的交易记录
	 * @param rowStart 起始行号，从零开始
	 * @param pageSize 页面显示行数
	 * @param userId 用户id
	 * @return 用户的交易记录
	 */
	List<Deal> getDealsByUserId(@Param("rowStart") Integer rowStart, @Param("pageSize") Integer pageSize,
			@Param("userId") Integer userId);
	/**
	 * 根据用户id查询该用户所产生的交易的总条数
	 * @param id
	 * @return
	 */
	Integer getDealsByUserIdCount(Integer id);
	/**
	 * 添加新的交易记录
	 * @param deal
	 */
	void addNewDeal(Deal deal);
}
