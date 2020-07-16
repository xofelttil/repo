package com.yc.biz;

import java.util.List;

import com.yc.bean.bike;

public interface BikeBiz {
	
	/*
	 * 开锁操作
	 */
	
	public void open(bike bk);
	
	public bike findByBid(String bid);
	
	public bike addNewBike(bike bk);
	
	public List<bike> findNearBike(bike bk);
	
	/**
	 * 报修处理
	 * @param bike
	 */
	public void reportMantinant(bike bike);

}
