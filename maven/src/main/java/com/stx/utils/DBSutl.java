package com.stx.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSource;


public class DBSutl {
	//����һ��ThreadLoacl�����õ�ǰ�߳���Ϊkey
		private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
		//��ȡ���� druid ���ô��������ݿ����ӳض���
		private static DruidDataSource ds = new DruidDataSource();
		//��ȡ���ݿ����ӳض���
		public static DruidDataSource getDataSource(){
			return ds;
		}
		// �����ӳ��л�ȡ����
		public static Connection getConnection() throws SQLException{
			Connection con = tl.get();
			if (con == null){
				con =ds.getConnection();
				//��con��ŵ� ����tl ��
				tl.set(con);
				System.out.println("�״�����"+con);
					
			}
			return con;
		}
		//��������
		public static void startTransaction(){
			try {
				//��ȡ����
				Connection con = getConnection();
				if (con != null) { 
				// ��������,true��ʾ�Զ��ύ����
				con.setAutoCommit(false);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		//�ύ����
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
		//����ع�
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
		
		//����DruidDataSource
		
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


