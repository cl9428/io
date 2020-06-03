package com.stx.stcp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver"; // ����������
	private static final String DBURL = "jdbc:mysql://localhost:3306/test"; // ���ݿ��·��
	private static final String DBUSER = "root"; // �û���
	private static final String PASSWORD = "admin"; // ����
		
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
			//�ͷ���Դ���ͷ���Դ˳���ȴ����ĺ��ͷţ��󴴽������ͷ�
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