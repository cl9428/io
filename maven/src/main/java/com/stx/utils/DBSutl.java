package com.stx.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSource;


public class DBSutl {
	//创建一个ThreadLoacl对象，用当前线程作为key
		private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
		//读取的是 druid 配置创建的数据库连接池对象
		private static DruidDataSource ds = new DruidDataSource();
		//获取数据库连接池对象
		public static DruidDataSource getDataSource(){
			return ds;
		}
		// 从连接池中获取连接
		public static Connection getConnection() throws SQLException{
			Connection con = tl.get();
			if (con == null){
				con =ds.getConnection();
				//将con存放到 集合tl 中
				tl.set(con);
				System.out.println("首次连接"+con);
					
			}
			return con;
		}
		//开启事务
		public static void startTransaction(){
			try {
				//获取连接
				Connection con = getConnection();
				if (con != null) { 
				// 开启事务,true表示自动提交事务
				con.setAutoCommit(false);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		//提交事务
		public static void commit(){
			try {
				Connection con = tl.get();
				if (con != null) {
					con.commit();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		//事务回滚
		public static void rollback(){
			try {
				Connection con = getConnection();
				if(con != null){
					con.rollback();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	public static void main(String[] args) {
		Properties prop = new Properties();
		
		try {
			prop.load(DBSutl.class.getResourceAsStream("druid.Properties"));
			//prop.load(new FileInputStream("com.stx/druid.Properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String username =prop.getProperty("username");
		String password = prop.getProperty("password");
		String maxActive = prop.getProperty("maxActive");
		
		//声明DruidDataSource
		
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setMaxActive(Integer.parseInt(maxActive));
		
		try {
			Connection con =ds.getConnection();
			System.out.println(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(ds.getUrl());
		
	}
}


