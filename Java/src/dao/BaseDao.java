package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver"; // 驱动程序名
	private static final String DBURL = "jdbc:mysql://localhost:3306/stx3"; // 数据库的路径
	private static final String DBUSER = "root"; // 用户名
	private static final String PASSWORD = "admin"; // 密码
	
	// 获取数据库连接
		public static Connection getConn() {
			Connection connection = null;
			//由于DBDRIVER DBURL DBUSER PASSWORD有可能写错，所以系统报了预期异常
			
			try {
				// 装载驱动程序
				Class.forName(DBDRIVER);
				// 建立连接,其实是创建了一个连接对象
				//java程序和数据库之间的一个通道
				//connection对象创建成功，程序和db之间的联系就建立了
					connection = DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
