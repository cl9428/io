package com.stx.stcp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver"; // 驱动程序名
	private static final String DBURL = "jdbc:mysql://localhost:3306/test"; // 数据库的路径
	private static final String DBUSER = "root"; // 用户名
	private static final String PASSWORD = "admin"; // 密码
		
		public static Connection getCon(){
			Connection connection = null;
			try {
				Class.forName(DBDRIVER);
				connection = DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return connection;
		}
		public static void close(Connection conn,PreparedStatement pst,ResultSet rs){
			//释放资源，释放资源顺序：先创建的后释放，后创建的先释放
			try {
				if(rs!=null){
					rs.close();
				}
				if(pst!=null){
					pst.close();
				}
				if(conn!=null){
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
