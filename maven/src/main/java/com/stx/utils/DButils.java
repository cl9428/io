package com.stx.utils;


import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
创建数据库连接池
*/
public class DButils {
	//创建一个ThreadLoacl对象，用当前线程作为key
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	//读取的是c3p0-config 默认配置创建的数据库连接池对象
	private static DataSource ds = new ComboPooledDataSource();
	//获取数据库连接池对象
	
	public static DataSource getDataSource(){
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
	}
