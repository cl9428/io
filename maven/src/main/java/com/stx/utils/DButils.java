package com.stx.utils;


import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
�������ݿ����ӳ�
*/
public class DButils {
	//����һ��ThreadLoacl�����õ�ǰ�߳���Ϊkey
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	//��ȡ����c3p0-config Ĭ�����ô��������ݿ����ӳض���
	private static DataSource ds = new ComboPooledDataSource();
	//��ȡ���ݿ����ӳض���
	
	public static DataSource getDataSource(){
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
	}
