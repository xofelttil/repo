package com.yc.biz;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.yc.bean.bike;
import com.yc.bean.pagebean;
import com.yc.bean.userBean;
import com.yc.dao.backDao;
import com.yc.util.RedisUtil;


import redis.clients.jedis.Jedis;




public class backBiz {

	backDao backdao =new backDao();
	
	static Properties pro=new Properties();
	static {
		InputStream lis = backBiz.class.getClassLoader().getResourceAsStream("kafka.properties");
		System.out.println(lis);
		
		try {
			pro.load(lis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				
				lis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public pagebean find(int pageno,int pagesize, String keyword){
//		int count = bz.findCount(keyword);
//		
//		List<bike> list =bz.search(pageno, pagesize, keyword);
//		
		
		return null;
		
	}
	
	
	
	private List<bike> search(int pageno, int pagesize, String keyword) {
		
		List<bike> list =new ArrayList<bike>();
		if((keyword==null || "".equals(keyword))&& ( pageno>0)){
			
			FindIterable<Document> bike = backdao.getAllBike();
			
			MongoCursor<Document> iterator = bike.iterator();
			
			
			
			
			
			while(iterator.hasNext()){
				Document next = iterator.next();
				//System.out.println(next);
				Set<Entry<String, Object>> set = next.entrySet();
				Iterator<Entry<String, Object>> it2 = set.iterator();
				bike bk=null;
				bk=new bike();
				
							while(it2.hasNext()){
								//切分字段存入bike
								
					Entry<String, Object> entry = it2.next();
					
					Object value = entry.getValue();
					String key = entry.getKey();
					System.out.println(key +"" +value);
				 
					
					if(key.equals("_id")){
						System.out.println("true");
						bk.setId(value+"");
					} if(key.equals("loc")){
						String locString =value+"";
						bk.setLocation(locString);
					} if("status".equals(key)){
					bk.setStatus1(Double.parseDouble(value+""));
					} if(key.equals("qrcode")){
						bk.setQrcode("");
					}
					
					
				}
				
				
							list.add(bk);
				
			}
			
			return list;
			
		}
		return list;
	}



	public int findCount(String keyword){
		
		// 连接到 mongodb 服务
		List<bike> list =new ArrayList<bike>();
					if(keyword==null || "".equals(keyword)){
						
						FindIterable<Document> bike = backdao.getAllBike();
						
						MongoCursor<Document> iterator = bike.iterator();
						
						
						
						
						
						while(iterator.hasNext()){
							Document next = iterator.next();
							//System.out.println(next);
							Set<Entry<String, Object>> set = next.entrySet();
							Iterator<Entry<String, Object>> it2 = set.iterator();
							bike bk=null;
							bk=new bike();
							
										while(it2.hasNext()){
											//切分字段存入bike
											
								Entry<String, Object> entry = it2.next();
								
								Object value = entry.getValue();
								String key = entry.getKey();
								System.out.println(key +"" +value);
							 
								
								if(key.equals("_id")){
									System.out.println("true");
									bk.setId(value+"");
								} if(key.equals("loc")){
									String locString =value+"";
									bk.setLocation(locString);
								} if("status".equals(key)){
								bk.setStatus1(Double.parseDouble(value+""));
								} if(key.equals("qrcode")){
									bk.setQrcode("");
								}
								
								
							}
							
							
										list.add(bk);
							
						}
						
						return list.size();
						
					}else{
						
						FindIterable<Document> find = backdao.findByid(keyword);
						
						
						MongoCursor<Document> iterator = find.iterator();
							
							
							
							
							
							while(iterator.hasNext()){
								
								Document next = iterator.next();
								//System.out.println(next);
								Set<Entry<String, Object>> set = next.entrySet();
								Iterator<Entry<String, Object>> it2 = set.iterator();
								bike bk=null;
								bk=new bike();
								
											while(it2.hasNext()){
												//切分字段存入bike
												
									Entry<String, Object> entry = it2.next();
									
									Object value = entry.getValue();
									String key = entry.getKey();
									System.out.println(key +"" +value);
								 
									
									if(key.equals("_id")){
										System.out.println("true");
										bk.setId(value+"");
									} if(key.equals("loc")){
										String locString =value+"";
										bk.setLocation(locString);
									} if("status".equals(key)){
									bk.setStatus1(Double.parseDouble(value+""));
									} if(key.equals("qrcode")){
										bk.setQrcode("");
									}
									
									
								}
								
								
											list.add(bk);
								
							}
							
							return list.size();
						
					}
					
					 
		
	}
	
	
	public List<bike> findAllBike(){
		FindIterable<Document> bike = backdao.getAllBike();
		
		MongoCursor<Document> iterator = bike.iterator();
		
		
		List<bike> list =new ArrayList<bike>();
		
		
		while(iterator.hasNext()){
			Document next = iterator.next();
			//System.out.println(next);
			Set<Entry<String, Object>> set = next.entrySet();
			Iterator<Entry<String, Object>> it2 = set.iterator();
			bike bk=null;
			bk=new bike();
			
						while(it2.hasNext()){
							//切分字段存入bike
							
				Entry<String, Object> entry = it2.next();
				
				Object value = entry.getValue();
				String key = entry.getKey();
				System.out.println(key +"" +value);
			 
				
				if(key.equals("_id")){
					System.out.println("true");
					bk.setId(value+"");
				} if(key.equals("loc")){
					String locString =value+"";
					bk.setLocation(locString);
				} if("status".equals(key)){
				bk.setStatus1(Double.parseDouble(value+""));
				} if(key.equals("qrcode")){
					bk.setQrcode("");
				}
				
				
			}
			
			
						list.add(bk);
			
		}
		
		return list;
		
	}
	


public List<bike> delete(String id) {
	FindIterable<Document> delete = backdao.delete(id);
	
	
	MongoCursor<Document> iterator = delete.iterator();
	
	
	List<bike> list =new ArrayList<bike>();
	
	
	while(iterator.hasNext()){
		Document next = iterator.next();
		//System.out.println(next);
		Set<Entry<String, Object>> set = next.entrySet();
		Iterator<Entry<String, Object>> it2 = set.iterator();
		bike bk=null;
		bk=new bike();
		
					while(it2.hasNext()){
						//切分字段存入bike
						
			Entry<String, Object> entry = it2.next();
			
			Object value = entry.getValue();
			String key = entry.getKey();
			System.out.println(key +"" +value);
		 
			
			if(key.equals("_id")){
				System.out.println("true");
				bk.setId(value+"");
			} if(key.equals("loc")){
				String locString =value+"";
				bk.setLocation(locString);
			} if("status".equals(key)){
			bk.setStatus1(Double.parseDouble(value+""));
			} if(key.equals("qrcode")){
				bk.setQrcode("");
			}
			
			
		}
		
		
					list.add(bk);
		
	}
	
	return list;
	
}

public List<bike> findByid(String id) {
	FindIterable<Document> find = backdao.findByid(id);
	
	
MongoCursor<Document> iterator = find.iterator();
	
	
	List<bike> list =new ArrayList<bike>();
	
	
	while(iterator.hasNext()){
		
		Document next = iterator.next();
		//System.out.println(next);
		Set<Entry<String, Object>> set = next.entrySet();
		Iterator<Entry<String, Object>> it2 = set.iterator();
		bike bk=null;
		bk=new bike();
		
					while(it2.hasNext()){
						//切分字段存入bike
						
			Entry<String, Object> entry = it2.next();
			
			Object value = entry.getValue();
			String key = entry.getKey();
			System.out.println(key +"" +value);
		 
			
			if(key.equals("_id")){
				System.out.println("true");
				bk.setId(value+"");
			} if(key.equals("loc")){
				String locString =value+"";
				bk.setLocation(locString);
			} if("status".equals(key)){
			bk.setStatus1(Double.parseDouble(value+""));
			} if(key.equals("qrcode")){
				bk.setQrcode("");
			}
			
			
		}
		
		
					list.add(bk);
		
	}
	
	return list;
	
	
}

public List<bike> update(String id, String status, String loc, String qrcode) {
	FindIterable<Document> find = backdao.update(id,status,loc,qrcode);
	
MongoCursor<Document> iterator = find.iterator();
	
	
	List<bike> list =new ArrayList<bike>();
	
	
	while(iterator.hasNext()){
		
		Document next = iterator.next();
		//System.out.println(next);
		Set<Entry<String, Object>> set = next.entrySet();
		Iterator<Entry<String, Object>> it2 = set.iterator();
		bike bk=null;
		bk=new bike();
		
					while(it2.hasNext()){
						//切分字段存入bike
						
			Entry<String, Object> entry = it2.next();
			
			Object value = entry.getValue();
			String key = entry.getKey();
			System.out.println(key +"" +value);
		 
			
			if(key.equals("_id")){
				System.out.println("true");
				bk.setId(value+"");
			} if(key.equals("loc")){
				String locString =value+"";
				bk.setLocation(locString);
			} if("status".equals(key)){
			bk.setStatus1(Double.parseDouble(value+""));
			} if(key.equals("qrcode")){
				bk.setQrcode("");
			}
			
			
		}
		
		
					list.add(bk);
		
	}
	
	return list;
}

public List<bike> insert(String id, String status, String loc, String qrcode) {
	FindIterable<Document> find = backdao.insert(id,status,loc,qrcode);
	
	
MongoCursor<Document> iterator = find.iterator();
	
	
	List<bike> list =new ArrayList<bike>();
	
	
	while(iterator.hasNext()){
		
		Document next = iterator.next();
		//System.out.println(next);
		Set<Entry<String, Object>> set = next.entrySet();
		Iterator<Entry<String, Object>> it2 = set.iterator();
		bike bk=null;
		bk=new bike();
		
					while(it2.hasNext()){
						//切分字段存入bike
						
			Entry<String, Object> entry = it2.next();
			
			Object value = entry.getValue();
			String key = entry.getKey();
			System.out.println(key +"" +value);
		 
			
			if(key.equals("_id")){
				System.out.println("true");
				bk.setId(value+"");
			} if(key.equals("loc")){
				String locString =value+"";
				bk.setLocation(locString);
			} if("status".equals(key)){
			bk.setStatus1(Double.parseDouble(value+""));
			} if(key.equals("qrcode")){
				bk.setQrcode("");
			}
			
			
		}
		
		
					list.add(bk);
		
	}
	
	return list;
	
}

public Map<String, Object> onLoaddata() {
	RedisUtil ru =new RedisUtil();
	Jedis jedis = RedisUtil.getJedis();
	
	Map<String,Object> map =new HashMap();
	jedis.select(1);
	String pv =jedis.get("accesslog_analysis_total_pv");
	String uv =jedis.get("accesslog_analysis_total_uv");
	Date date = new Date();
	map.put("pv", pv);
	map.put("uv",uv );
	map.put("date", date);
	
	
	
	System.out.println("pv"+pv+"uv"+uv);
	return map;
	
}

/*
 * 
 * 查询所有的用户 包装返回
 * 
 */

public List<userBean> findAllUsers() {
	
	//获取mongo 连接
	// 连接到 mongodb 服务
				MongoClient mongoClient = new MongoClient("192.168.13.205", 27017);
				
				// 如果指定的数据库不存在，MongoDB会自动创建数据库
				MongoDatabase mongoDatabase = mongoClient.getDatabase("yc74bike");
				
				// 选择集合
				MongoCollection<Document> collection = mongoDatabase.getCollection("users");
				
				
				FindIterable<Document> find = collection.find();
				
				//包装数据 为 userbean
				
				
				
				MongoCursor<Document> iterator = find.iterator();
				
				
				List<userBean> list =new ArrayList<userBean>();
				
				
				while(iterator.hasNext()){
					Document next = iterator.next();
					//System.out.println(next);
					Set<Entry<String, Object>> set = next.entrySet();
					Iterator<Entry<String, Object>> it2 = set.iterator();
					userBean user =new userBean();
								while(it2.hasNext()){
									//切分字段存入bike
									
						Entry<String, Object> entry = it2.next();
						
						Object value = entry.getValue();
						String key = entry.getKey();
						System.out.println(key +"" +value);
					 
						
						if(key.equals("_id")){
							System.out.println("true");
							user.set_id(value+"");
						} if(key.equals("openId")){
							String locString =value+"";
							user.setOpenId(value+"");
						} if("status".equals(key)){
							user.setStatus(value+"");
						} if(key.equals("_class")){
							user.set_class(value+"");
						}
						
						
					}
					
					
								list.add(user);
					
				}
				
				return list;
	
	
	
	
}

//public static void main(String[] args) {
//	
//	backBiz bz =new backBiz();
//	List<userBean> f1 = bz.findAllUsers();
//			
//			System.out.println(f1);
//		
//	
//}



public List<userBean> deleteUsers(String id) {
		
	// 连接到 mongodb 服务
	MongoClient mongoClient = new MongoClient("192.168.13.205", 27017);
	
	// 如果指定的数据库不存在，MongoDB会自动创建数据库
	MongoDatabase mongoDatabase = mongoClient.getDatabase("yc74bike");
	
	// 选择集合
	MongoCollection<Document> collection = mongoDatabase.getCollection("users");
	System.out.println("传递的id"+id);
    //申明删除条件
	ObjectId oid =new ObjectId(id);
	
    Bson filter = Filters.eq("_id",oid);
	
	DeleteResult deleteOne = collection.deleteOne(filter);
	
	long deletedCount = deleteOne.getDeletedCount();
	System.out.println("deletedCount================================="+deletedCount);
	
	//返回删除之后的所有结果集
	
	backDao backdao =new backDao();
	backBiz bz =new backBiz();
	List<userBean> allBike = bz.findAllUsers();
	
	return allBike;
	

	
}



public List<String> getAccess() {
	
	KafkaConsumer<String,String> consumer =new KafkaConsumer<String,String>(pro);
	//System.out.println(consumer.listTopics());
	//订阅主题
	//System.out.println(pro.get("key.deserializer"));
	consumer.subscribe(Collections.singletonList("accesslog"));
	ConsumerRecords<String,String> records=consumer.poll(Duration.ofMillis(1000));
	System.out.println("总共有"+records.count()+"条记录");
	
	//SendMailUtil.sendMail(smtp, sender, mail, uname, pwd, title, builder.toString());
	List<String> list =new ArrayList();
	for(ConsumerRecord<String,String> cc:records){ 
		
	
			String value =cc.value();
			list.add(value);
		
	}
	
	
	return list;
}
public static void main(String[] args) {
	backBiz bz =new backBiz();
	bz.getAccess();
}

}
