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
			 //1.加载配置文件
	       // Properties pro = new Properties();
	        //InputStream is = druidUtils.class.getClassLoader().getResourceAsStream("druid.Properties");
			properties.load(druidUtils.class.getResourceAsStream("druid.Properties"));
	        //2.获取连接池对象
	        dataSource = getDatasource();
	        //3. 获取数据库连接对象
	        Connection con =	getConnection();
	        //4.打印输出con对象是否创建成功
	        System.out.println(con);
            //5. 创建sql语句
            String sql = "select * from movie";
            //6. 创建执行sql对象
            stmt = conn.createStatement();
            //7. 创建一个ResultSet：结果集对象，封装查询结果
            rs = stmt.executeQuery(sql);
            //8. 然后在用ResultSet里的方法 next():游标向下移动一行 判断是否有数据 有就是true，没有就是false
            while (rs.next()) {
                //rs.getString():传递的是数据库的字段
                String username = rs.getString("name");
                String password = rs.getString("content");
                //9. 输入结果
                System.out.println(username + "    " + password);
            }
			/*properties = new Properties();
			//1.加载properties文件
			InputStream is = druidUtils.class.getClassLoader().getResourceAsStream("druid.Properties");
			//加载输入流
			properties.load(is);
			//获取数据源
			// 3.获取数据源
            dataSource = getDatasource();*/
	        
		} catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 用简单单例模式确保只返回一个链接对象
     * 
     * @return
     */
   /* */
  
 
 
    // 加载数据源
    public static DataSource getDatasource() {
        try {
        	dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    	}
    // 返回一个数据源
    public static  DataSource getDataSource() {
        return dataSource;
    }
    // 返回一个链接
    public static  Connection getConnection() {
        try {
        	conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
