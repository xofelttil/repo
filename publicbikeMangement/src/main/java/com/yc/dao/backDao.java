package com.yc.dao;

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
import com.mongodb.client.result.UpdateResult;


public class backDao {
	public FindIterable<Document> getAllBike(){
		

		// 连接到 mongodb 服务
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			
			// 如果指定的数据库不存在，MongoDB会自动创建数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase("yc74bike");
			
			// 选择集合
			MongoCollection<Document> collection = mongoDatabase.getCollection("bike");
			
			
			FindIterable<Document> find = collection.find();
			
			return find;
			
	}
	
	/*
	 * 删除操作
	 */


	public FindIterable<Document> delete(String id) {
		// 连接到 mongodb 服务
					MongoClient mongoClient = new MongoClient("localhost", 27017);
					
					// 如果指定的数据库不存在，MongoDB会自动创建数据库
					MongoDatabase mongoDatabase = mongoClient.getDatabase("yc74bike");
					
					// 选择集合
					MongoCollection<Document> collection = mongoDatabase.getCollection("bike");
					
				    //申明删除条件
					ObjectId oid =new ObjectId(id);
					
				    Bson filter = Filters.eq("_id",oid);
					
					DeleteResult deleteOne = collection.deleteOne(filter);
					
					long deletedCount = deleteOne.getDeletedCount();
					System.out.println("deletedCount"+deletedCount);
					
					//返回删除之后的所有结果集
					
					backDao backdao =new backDao();
					
					FindIterable<Document> allBike = backdao.getAllBike();
					
					return allBike;
		
	}

	public FindIterable<Document> findByid(String id) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		
		// 如果指定的数据库不存在，MongoDB会自动创建数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("yc74bike");
		
		// 选择集合
		MongoCollection<Document> collection = mongoDatabase.getCollection("bike");
		
		if(id.equals("") || id==null){
			backDao backdao =new backDao();
			
			FindIterable<Document> allBike = backdao.getAllBike();
			
			return allBike;
			
		}else{
		
	    //申明查询条件
		ObjectId oid =new ObjectId(id);
		
	    Bson filter = Filters.eq("_id",oid);
	    
	    FindIterable<Document> find = collection.find(filter);
		
		return find;
		
		}
	}

	public FindIterable<Document> update(String id, String status, String loc, String qrcode) {
		// 连接到 mongodb 服务
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		
		// 如果指定的数据库不存在，MongoDB会自动创建数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("yc74bike");
		
		// 选择集合
		MongoCollection<Document> collection = mongoDatabase.getCollection("bike");
		
		Bson filter = Filters.eq("_id",id);
		Document update = new Document("$set",
				new Document("status",status)
		

		        .append("loc",loc)

		        .append("qrcode",qrcode));
		UpdateResult updateOne = collection.updateOne(filter, update);
		
		backDao backdao =new backDao();
		
		FindIterable<Document> allBike = backdao.getAllBike();
		
		return allBike;
	}

	public FindIterable<Document> insert(String id, String status, String loc, String qrcode) {
		// 连接到 mongodb 服务
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		
		// 如果指定的数据库不存在，MongoDB会自动创建数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("yc74bike");
		
		// 选择集合
		
		ObjectId objectid=new ObjectId();
		MongoCollection<Document> collection = mongoDatabase.getCollection("bike");
		
		Document doc = new Document("_id",objectid)

		        .append("status",status)

		        .append("loc",loc)

		        .append("qrcode",qrcode);
		
		
		collection.insertOne(doc);
		        
		backDao backdao =new backDao();
		
		FindIterable<Document> allBike = backdao.getAllBike();
		
		return allBike;
		
		
		
	}
	
	
	public static void main(String[] args) {
//		backDao bd =new backDao();
//		bd.insert("111111", "0", "0", "test");
		backDao bd =new backDao();
		
		bd.update("111111", "1", "1", "update");
		
		
		
	}

}
