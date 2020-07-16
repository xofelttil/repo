package com.yc.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.bike;
import com.yc.biz.BikeBiz;
import com.yc.dao.bikeDao;
@Service
@Transactional
public class BikeBizimpl implements BikeBiz{
	@Autowired
	private bikeDao bikedao;
	
	@Autowired
	private MongoTemplate mt;

	
//	public void open(bike bk) {
//		long id =bk.getBid();
//		
//		bike b =findByBid(id);
//		if(b ==null){
//			throw new RuntimeException("查无此车");
//		}
//		
//		switch(b.getStatus()){
//		case bike.UNACTIVE:
//			throw new RuntimeException("此车未启用，请换一辆");
//		case bike.USING:
//			throw new RuntimeException("此车正在骑行中，请换一辆");
//		case bike.INTROUBLE:
//			throw new RuntimeException("此车正在维修中，请换一辆");
//			
//		
//		}
//		
//		bk.setStatus(bike.USING);
//		bikedao.updateBike(bk);
//		
//	}

	
	@Override
	public void open(bike bk) {
		if (bk.getBid() == null) {
			throw new RuntimeException("缺少待开没单车编号");
		}
		bike b = findByBid(bk.getBid());
		if (b == null) {
			throw new RuntimeException("查无此车");
		}
		switch (b.getStatus()) {
		case bike.UNACTIVE:
			throw new RuntimeException("此车暂未启用，请更换一辆");
		case bike.USING:
			throw new RuntimeException("此车正在骑行中，请更换一部...");
		case bike.INTROUBLE:
			throw new RuntimeException("此单车待维修，请更换一部");
		}
		bk.setStatus(  bike.USING );
		bikedao.updateBike(bk);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public bike findByBid(String bid) {
		bike b = null;
		try {
			b = bikedao.findBike(bid);
		} catch (Exception ex) {
			// ex.printStackTrace();
			ex.printStackTrace();
		}
		// 复杂业务
		return b;
	}


	@Override
	public bike addNewBike(bike bk) {
		bike b =bikedao.addBike(bk);
		String bid =b.getBid();
		
		bk =findByBid(bid);
		
		//根据bid生成二维码
		
		String qrcode = bid+"";
		bk.setQrcode(qrcode);
		bikedao.updateBike(bk);
		
		return bk;
		
	}

	@Override
	public List<bike> findNearBike(bike bk) {
		Query query =new Query();
		query.addCriteria(Criteria.where("status").is(bk.getStatus()))
		.addCriteria(Criteria.where("loc").near(new Point(bk.getLongitude(),bk.getLatitude())));
		
		List<bike> list =this.mt.find(query, bike.class,"bike");
		
		
		for(bike b:list){
			b.setBid(b.getId());
			b.setLongitude(b.getLoc()[1]);
			b.setLatitude(b.getLoc()[0]);
			b.setLoc(null);
			
		}
		return list;
	}

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void reportMantinant(bike bk) {
		//1. 根据bid查出车的状态, 要报修的车不能是行驶状态 2
				Query q=new Query();
				q.addCriteria(    Criteria.where("id").is(bk.getBid()));
//				Bike torepair=this.mongoTemplate.findOne(q, Bike.class,"bike");
				bike torepair=mongoTemplate.findById( bk.getBid(), bike.class,"bike");
				if(  torepair==null) {
					throw new RuntimeException("查无此车登记:"+ bk.getBid());
				}
				if( torepair.getStatus()==2) {
					throw new RuntimeException("正在报修的车:"+ bk.getBid()+"正在行驶状态，为了您的安全,请锁车后再报修");
				}
				//2. 将此信息存入到  mongo中，并加入一个状态  handleStatus: 0 暂未处理  1已经处理 
				//TODO: 根据经纬度查询具体地址. ,存到  mongo 的 torepairbikes
				this.mongoTemplate.insert(bk, "torepairbikes");
				//      以后处理完了，要加入  handler 处理人   handleTime 处理时间
				//3. 将此车的状态在  bike collection中更改为 3
				Update u=new Update();
				u.set("status", 3);
				this.mongoTemplate.updateFirst(q, u, bike.class,"bike");
		
	}

}
