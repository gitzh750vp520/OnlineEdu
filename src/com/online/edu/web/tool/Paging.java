package com.online.edu.web.tool;

public class Paging {
	private int pageNo = 1;
	private int pagePrev;
	private int pageNext;
	private int pageTotal;
	private int countTotal;
	
	public Paging(int countTotal,String pageNoStr,int pageSize){
		if(pageNoStr != null){
			pageNo = Integer.parseInt(pageNoStr);
		}
		pageNo = pageNo < 1 ? 1 : pageNo;
		this.countTotal = countTotal;
		pageTotal = countTotal % pageSize == 0 ? countTotal / pageSize : countTotal / pageSize + 1;
		pageTotal = pageTotal == 0 ? 1 : pageTotal;
		pageNo = pageNo > pageTotal ? pageTotal : pageNo;
		pagePrev = pageNo == 1 ? 1 : pageNo - 1;
		pageNext = pageNo == pageTotal ? pageTotal : pageNo + 1;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public int getPagePrev() {
		return pagePrev;
	}
	public int getPageNext() {
		return pageNext;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public int getCountTotal() {
		return countTotal;
	}
	
	
	
}
