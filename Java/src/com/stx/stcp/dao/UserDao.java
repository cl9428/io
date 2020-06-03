package com.stx.stcp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.stx.stcp.bean.UserBean;

public class UserDao extends Dao {
	private Connection conn = null;// 连接对象变量
	private PreparedStatement pst = null;// 预处理执行对象变量
	private ResultSet rs = null;// 结果集变量

	// 用户注册方法
	public int register(UserBean bean) {
		int num = 0;
		try {
			// 获取连接对象
			conn = getCon();
			// 根据连接对象创建执行操作的对象
			String insertSql = "insert into user(zh,mima,realname) values(?,?,?)";
			pst = conn.prepareStatement(insertSql);
			// 对站位符位置填充值
			pst.setString(1, bean.getZh());
			pst.setString(2, bean.getPass());
			pst.setString(3, bean.getRealname());
			// 预处理对象执行语句
			num = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放资源
			close(conn, pst, null);
		}
		return num;
	}
	

	//登录方法：根据账号和密码进行查询，返回用户对象
	//如果查询到数据返回一个用户对象，如果查询不到信息返回null
	public UserBean login(String zh,String pass){
		UserBean user=null;
		try {
			//获取连接对象
			conn=getCon();
			//根据连接对象取预处理执行对象
			String selectSql="select * from user where zh=? and pass=?";
			pst=conn.prepareStatement(selectSql);
			pst.setString(1, zh);
			pst.setString(2, pass);
			//执行操作,将查询结果放到结果集
			rs=pst.executeQuery();
			//对结果集进行处理
			if(rs.next()){
				user=new UserBean();
				//将结果集的一行数据拿出来放到对应变量
				int id=rs.getInt("id");
				String zh1=rs.getString("zh");
				String pass1=rs.getString("pass");
				String realname=rs.getString("realname");
				//将信息封装到对象
				user.setId(id);
				user.setZh(zh1);
				user.setPass(pass1);
				user.setRealname(realname);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn,pst,rs);
		}
		return user;
	}
}
