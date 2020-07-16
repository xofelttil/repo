package com.yc.biz.impl;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.jboss.logging.Logger;

import com.google.gson.Gson;
import com.mongodb.WriteResult;
import com.mongodb.client.result.UpdateResult;
import com.yc.bean.User;
import com.yc.biz.genCode;


@Transactional
@Service
public class genCodeimpl implements genCode{


		@Autowired
		private MongoTemplate mt;
		// TODO Auto-generated method stub
		
		// 操作redis中的v是对象类型的数据
		@Autowired
		private RedisTemplate redisTemplate;
		// 操作redis中的字符串类型数据
		@Autowired
		private StringRedisTemplate stringRedisTemplate;
		
		/*
		 * userserviceImpl类中加入发送短信的代码:
		 * 
		 * 短信服务的实现
	@Override
		public void genVerifyCode(String nationCode, String phoneNum) throws Exception {
		// 短信接口的 appkey
		// String appkey = stringRedisTemplate.opsForValue().get("appkey");
		// 生成验证码
		String code = (int) ((Math.random() * 9 + 1) * 1000) + "";
		logger.info("生成的验证码为:" + code);
		SmsUtils.sendSms(code, new String[] {nationCode+phoneNum});    //TODO: 以后发送
		// redisTemplate.
		// 将数据保存到redis中，redis的key手机号，value是验证码，有效时长120秒  
		stringRedisTemplate.opsForValue().set(phoneNum, code, 120, TimeUnit.SECONDS);
	}
		 */

		@Override
		public void genVerifyCode(String nationCode, String phoneNum) throws Exception {
			// 短信接口的 appkey
			// String appkey = stringRedisTemplate.opsForValue().get("appkey");
			// 生成验证码
			String code = (int) ((Math.random() * 9 + 1) * 1000) + "";
			System.out.println("生成的验证码为:" + code);
			// SmsUtils.sendSms(code, new String[] {nationCode+phoneNum});    //TODO: 以后发送
			// redisTemplate.
			// 将数据保存到redis中，redis的key手机号，value是验证码，有效时长120秒  
			stringRedisTemplate.opsForValue().set(phoneNum, code, 120, TimeUnit.SECONDS);
		}

		@Override
		public boolean verify(User user) {
			boolean flag = false;
			String phoneNum = user.getPhoneNum();
			String verifyCode = user.getVerifyCode();  //用户输入的验证码
			String code = stringRedisTemplate.opsForValue().get(phoneNum);    //生成的验证码
			
			String openId=user.getOpenId();
			System.out.println("openid"+openId);
			String uuid=user.getUuid();
			
			System.out.println(  user );
			if(verifyCode != null && verifyCode.equals(code)) {
				//验证成功后，将用户信息保存到  mongo中
				//mongoTemplate.save(user);
				int status=1;
				try {
					UpdateResult  result=mongoTemplate.updateFirst(new Query(Criteria.where("openId").is(openId)), new Update().set("status", status).set("phoneNum", phoneNum),  User.class);
					flag=true;
				} catch (Exception e) {
					flag=false;
					e.printStackTrace();
				}
				
			}
			return flag;
		}


		
		@Autowired
		private  MongoTemplate mongoTemplate;
				
		@Override
		public boolean identity(User user) {

			//TODO:调用第三方接口验证用户身份证是否是真实的。
			try {
				int status=3;
				UpdateResult result=mongoTemplate.updateFirst(
						new Query(Criteria.where("phoneNum").is(user.getPhoneNum())),
						new Update().set("status", status )
				        .set("name", user.getName())
				        .set("idNum", user.getIdNum()),  User.class
						   );
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}
		
		
		
		@Override
		public boolean deposit(User user) {
			int status=2;    //状态要变为2
			int money=299;    //押金数
			try {
				UpdateResult  result=mongoTemplate.updateFirst(   
						                   new Query(   Criteria.where("phoneNum").is(user.getPhoneNum())  ), 
						                   new Update().set("status", status).  set("deposit", money),  User.class);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return false;
			}
			
		}

		@Override
		public boolean recharge(double balance, String phoneNum) {
			if(  balance<0) {
				throw new RuntimeException("充值金额不能为负数,当前为:"+balance);
			}
			boolean flag = true;
			// 跟新用户的余额
			try {
				Query q=new Query(   Criteria.where("phoneNum").is(phoneNum)  );
				Update u=new Update().inc("balance", balance);
				mongoTemplate.updateFirst(q,u , User.class,"users");
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
			return flag;
		}

		@Override
		public List<User> selectMember(String openId) {
			Query q=new Query(   
					Criteria.where("openId").is(openId) );
			return this.mongoTemplate.find(q, User.class,"users");
			
		}

		@Override
		public void addMember(User u) {
			mongoTemplate.insert( u );
			
		}

		@Override
		public String redisSessionKey(String openId, String sessionkey) {
			String rsession = UUID.randomUUID().toString();
			// (3) 首先根据openId，取出来之前存的openId对应的sessionKey的值。
			String oldSeesionKey =stringRedisTemplate.opsForValue().get(   openId );
			if (oldSeesionKey != null && !"".equals(oldSeesionKey)) {
				//logger.info("oldSeesionKey==" + oldSeesionKey);
				// (4) 删除之前openId对应的缓存
				stringRedisTemplate.delete(    oldSeesionKey  );
				//logger.info("老的openId删除以后==" + stringRedisTemplate.opsForValue().get(oldSeesionKey));
			}
			// (5) 开始缓存新的sessionKey：  格式:  { uuid:{ "openId":openId,"sessionKey":sessionKey }  }
			Gson g=new Gson();
			Map<String,String> m=new HashMap<String,String>();
			m.put("openId", openId);
			m.put("sessionKey", sessionkey);
			String s=g.toJson( m);
			//stringRedisTemplate.opsForValue().set(rsession, s, 30*24*60*60, TimeUnit.SECONDS);
			stringRedisTemplate.opsForValue().set(rsession, s, 5*60, TimeUnit.SECONDS);
			
			// (6) 开始缓存新的openId与session对应关系 ：  {openId: rsession}
			//stringRedisTemplate.opsForValue().set(openId, rsession, 30*24*60*60, TimeUnit.SECONDS);
			stringRedisTemplate.opsForValue().set( openId, rsession, 5*60, TimeUnit.SECONDS);
			return rsession;
		}
		
	

}
