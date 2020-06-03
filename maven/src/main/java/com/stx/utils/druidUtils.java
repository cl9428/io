package com.stx.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class druidUtils {
	 static Properties properties = new Properties();
	 static DataSource dataSource ;
     static Connection conn =null;

	private druidUtils(){
		
	}
    static Statement stmt = null;
    static ResultSet rs = null;
	public static void main(String [] args) {
		try{
			 //1.���������ļ�
	       // Properties pro = new Properties();
	        //InputStream is = druidUtils.class.getClassLoader().getResourceAsStream("druid.Properties");
			properties.load(druidUtils.class.getResourceAsStream("druid.Properties"));
	        //2.��ȡ���ӳض���
	        dataSource = getDatasource();
	        //3. ��ȡ���ݿ����Ӷ���
	        Connection con =	getConnection();
	        //4.��ӡ���con�����Ƿ񴴽��ɹ�
	        System.out.println(con);
            //5. ����sql���
            String sql = "select * from movie";
            //6. ����ִ��sql����
            stmt = conn.createStatement();
            //7. ����һ��ResultSet����������󣬷�װ��ѯ���
            rs = stmt.executeQuery(sql);
            //8. Ȼ������ResultSet��ķ��� next():�α������ƶ�һ�� �ж��Ƿ������� �о���true��û�о���false
            while (rs.next()) {
                //rs.getString():���ݵ������ݿ���ֶ�
                String username = rs.getString("name");
                String password = rs.getString("content");
                //9. ������
                System.out.println(username + "    " + password);
            }
			/*properties = new Properties();
			//1.����properties�ļ�
			InputStream is = druidUtils.class.getClassLoader().getResourceAsStream("druid.Properties");
			//����������
			properties.load(is);
			//��ȡ����Դ
			// 3.��ȡ����Դ
            dataSource = getDatasource();*/
	        
		} catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * �ü򵥵���ģʽȷ��ֻ����һ�����Ӷ���
     * 
     * @return
     */
   /* */
  
 
 
    // ��������Դ
    public static DataSource getDatasource() {
        try {
        	dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    	}
    // ����һ������Դ
    public static  DataSource getDataSource() {
        return dataSource;
    }
    // ����һ������
    public static  Connection getConnection() {
        try {
        	conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
