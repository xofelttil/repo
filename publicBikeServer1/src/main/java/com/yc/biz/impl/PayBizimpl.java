package com.yc.biz.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.User;
import com.yc.bean.bike;
import com.yc.biz.BikeBiz;
import com.yc.biz.PayBiz;
import com.yc.biz.genCode;
import com.yc.contraler.PayModel;
@Service
@Transactional
public class PayBizimpl implements PayBiz{
	@Autowired
	private genCode gc;
	@Autowired
	private BikeBiz bz;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	
	
	public static final int MONEYPERHOUR=4;

	
	
	@Override
	public void pay(PayModel pm) {
		System.out.println(pm);
		//1. 计算金额 	
				Long startTime=pm.getStartTime();
				Long endTime=pm.getEndTime();
				Long spendTime=endTime-startTime;
				double hours=(Double.parseDouble( spendTime+"" ) )/(60*60);
				Integer h=(int) Math.ceil(   hours );   //小时数
				int payMoney= h*MONEYPERHOUR;    //花费
				pm.setPayMoney(payMoney);
				pm.setLogTime(    new Date().toLocaleString() );
				//2. 将数据保存到mongo的 payLog ( uuid,phoneNum,openId, 结账时间(年月日小时) 起(经纬),时间, 止(经纬) ,时间, 花费) 
				this.mongoTemplate.insert(    pm,   "payLog"    );
				//3. 修改单车的经纬度, 状态为1 
				Query q=new Query(   Criteria.where("id").is(   pm.getBid() )  );
				Update u=new Update().set("latitude", pm.getLatitude()).set("longitude", pm.getLongitude());
				Double[] loc=new Double[] {pm.getLatitude(),pm.getLongitude()};
				u.set("loc", loc).set("status", 1);
				mongoTemplate.updateFirst(q, u, bike.class, "bike");
				//4. 修改用户态: status , balance-花费.
				Query qu=new Query(   Criteria.where("phoneNum").is( pm.getPhoneNum()) );
				User user=mongoTemplate.findOne(qu, User.class, "users");
				System.out.println("根据电话查到的user"+"\n"+user);
				Update uu=new Update().set("status",3 );   //用户的状态:  0 没有注册   1. 注册电话成功  2 押金缴纳成功   3. 实名认证,可以开锁   4. 骑行态. 
				System.out.println("user.Balance"+user.getBalance()+"payMONEY"+payMoney);
				uu.set("balance", user.getBalance()-payMoney    );
				mongoTemplate.updateFirst( qu, uu, User.class,"users");
				System.out.println("结账成功");
		
	}

}
