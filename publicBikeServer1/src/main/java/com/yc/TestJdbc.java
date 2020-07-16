package com.yc;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.yc.appConfig;
import com.yc.bean.bike;
import com.yc.biz.BikeBiz;
import com.yc.biz.genCode;
import com.yc.dao.bikeDao;


@RunWith(SpringJUnit4ClassRunner.class)   // 请导入    spring-test包
@ContextConfiguration(classes = {appConfig.class})    //IOC
public class TestJdbc {
	@Autowired
	private bikeDao bikedao;
	@Autowired      //  DI  
	@Qualifier("dataSource")
	private DriverManagerDataSource dmd;
	@Autowired
	private MongoTemplate mt;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private genCode gc;
	
	
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testRedisTemplate() {
		 stringRedisTemplate.opsForValue().set("hello", "world");
		 stringRedisTemplate.opsForValue().set("hello2", "world");
	}
	
	@Test  // 准备测试数据
	public void test1() {
		double x=28.2043941;
		double y=112.959854;
		for (int i = 0; i < 100; i++) {
			x+=0.0000010;
			for (int j = 0; j < 100; j++) {
				y+=0.0000003;
				Double loc[] = new Double[] { Double.valueOf(x), Double.valueOf(y) };
				bike b=new bike();
				b.setStatus(1);
				b.setLoc(  loc);
				b.setQrcode("");
				mt.insert(b);
			}
		}
	}
	
	
	@Test
	public void testMongoTemplate() {
		System.out.println( mt.getDb().getName() );
		System.out.println(  mt.getCollectionNames()  );
	}
	
	
	
	
}