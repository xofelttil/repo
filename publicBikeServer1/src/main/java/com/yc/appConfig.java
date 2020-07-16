package com.yc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import io.undertow.Undertow;
import io.undertow.UndertowOptions;







import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;


@Configuration
@ComponentScan(basePackages="com.yc")
@EnableTransactionManagement
public class appConfig {
	
	private String ip ="192.168.13.205";
	
	@Bean
	public RedisTemplate redsiTemplate() {
		JedisConnectionFactory conn = new JedisConnectionFactory();
        conn.setDatabase(0);
        conn.setHostName(ip);
        conn.setPort(6379);
        conn.setPassword("");
        conn.setUsePool(true);
        conn.afterPropertiesSet();
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(conn);
        template.afterPropertiesSet();
        return template;
	}
	
	@Bean
	public StringRedisTemplate stringRedisTemplate() {
		JedisConnectionFactory conn = new JedisConnectionFactory();
        conn.setDatabase(0);
        conn.setHostName(ip);
        conn.setPort(6379);
        conn.setPassword("");
        conn.setUsePool(true);
        conn.afterPropertiesSet();
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(conn);
        template.afterPropertiesSet();
        return template;
	}
	
	  @Primary
	    public MongoTemplate template() {
	        return new MongoTemplate(factory());
	    }
	  /**
	     * 功能描述: 创建数据库名称对应的工厂，数据库名称可以通过配置文件导入
	     * @param
	     * @return:org.springframework.data.mongodb.MongoDbFactory
	     * @since: v1.0
	     */
	    @Bean("mongoDbFactory")
	    public MongoDbFactory factory() {
	        return new SimpleMongoDbFactory(client(), "yc74bike");
	    }
	    
	    /**
	     * 功能描述: 配置client，client中传入的ip和端口可以通过配置文件读入
	     *
	     * @param
	     * @return:com.mongodb.MongoClient
	     */
	    @Bean("mongoClient")
	    public MongoClient client() {
	    	
	        return new MongoClient(ip, 27017);
	    }
	//    @Bean("mongoClient")
//	    public MongoClient client() {
//	    	
//	        return new MongoClient("192.168.13.203", 27017);
//	    	List<ServerAddress> list = new ArrayList<ServerAddress>();
//	    	
//	    	ServerAddress sa =new ServerAddress("47.97.193.92",40000);
//	    	list.add(sa);
//	    	return new MongoClient(list);
	//    }

	
	
	@Bean    //      id: dataSource    value:  DriverManagerDataSource
	public  DriverManagerDataSource   dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://"+ip+":3306/ibike");
		dataSource.setUsername("lw");
		dataSource.setPassword("a");
		System.out.println("创建数据库");
		return dataSource;
	}
	
	@Bean
	@Autowired
	public    DataSourceTransactionManager  tx(  DriverManagerDataSource ds    ){
		DataSourceTransactionManager dtm=new DataSourceTransactionManager();
		//AnnotationTransactionAspect.aspectOf().setTransactionManager(dtm);  //表示当前的  tx 事务管理器是mo认的  @Transaction的事务管理器
		dtm.setDataSource(   ds );
		return dtm;
	}
	
	
	//因为用了  %D， 所以要开启undertow记时:  
	   @Bean
	    public UndertowServletWebServerFactory  undertowServletWebServerFactory() {
	        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
	        factory.addBuilderCustomizers(new UndertowBuilderCustomizer() {
	            @Override
	            public void customize(Undertow.Builder builder) {
	                builder.setServerOption(UndertowOptions.RECORD_REQUEST_START_TIME, true);
	            }
	        });
	        return factory;
	    }

}
