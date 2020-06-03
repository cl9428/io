package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;


public class HelloWorld {
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args){
		Connection c = null;
		Statement s = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
	        c = DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8","root","admin");
			s = c.createStatement();
			
			String sql = "select * from hero";
			 
            // 执行查询语句，并把结果集返回给ResultSet
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");// 可以使用字段名
                
                String name = rs.getString(2);// 也可以使用字段的顺序
              //  float hp = rs.getFloat("hp");
               // int damage = rs.getInt(4);
             // System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
                System.out.println(id+""+name);
            }
            } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(s != null)
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(c != null){
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		}
		
	}
}
