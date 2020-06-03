package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver"; // ����������
	private static final String DBURL = "jdbc:mysql://localhost:3306/stx3"; // ���ݿ��·��
	private static final String DBUSER = "root"; // �û���
	private static final String PASSWORD = "admin"; // ����
	
	// ��ȡ���ݿ�����
		public static Connection getConn() {
			Connection connection = null;
			//����DBDRIVER DBURL DBUSER PASSWORD�п���д������ϵͳ����Ԥ���쳣
			
			try {
				// װ����������
				Class.forName(DBDRIVER);
				// ��������,��ʵ�Ǵ�����һ�����Ӷ���
				//java��������ݿ�֮���һ��ͨ��
				//connection���󴴽��ɹ��������db֮�����ϵ�ͽ�����
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
