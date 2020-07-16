package com.yc.dao;

import com.yc.bean.bike;

public interface bikeDao {
	
	public bike addBike(bike bk);
	
	
	public void updateBike(bike bk);
	
	
	public bike findBike(String bid);

}
