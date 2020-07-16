package com.yc.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.yc.biz.logService;
@Service
public class logServiceImpl implements logService{
@Autowired
private MongoTemplate mt;
	@Override
	public void save(String log) {
		// TODO Auto-generated method stub
		
		mt.save(log, "log");
		
		
		
		
	}
	@Override
	public void savePayLog(String log) {
		// TODO Auto-generated method stub
		
		mt.save(log,"paylogs");
		
	}
	@Override
	public void repairlog(String log) {
		// TODO Auto-generated method stub
		mt.save(log,"repairlog");
		
	}
	@Override
	public void bikingLog(String log) {
		// TODO Auto-generated method stub
		mt.save(log,"bikingLog");
	}

}
