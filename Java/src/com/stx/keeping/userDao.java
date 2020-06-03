package com.stx.keeping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import com.stx.dao.Dao;

public class userDao extends Dao{
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	public int insert(userBean user){
		int num = 0;
		try {
			con = getCon();
			String insertSql = "insert into user(zh,mima) values(?,?)";
			pst = con.prepareStatement(insertSql);
			pst.setString(1,user.getZh());
			pst.setString(2,user.getMima());
			num = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pst, rs);
		}
		
		return num;
	}
	
	public List select(String zh,String mima){
		List list = new ArrayList();
		try {
			con = getCon();
			String selectSql = "select * from user where zh=? and mima=?";
			pst = con.prepareStatement(selectSql);
			pst.setString(1,zh);
			pst.setString(2,mima);
			rs = pst.executeQuery();
			if(rs.next()){
				userBean user = new userBean();
				String myzh = rs.getString("zh");
				String pass = rs.getString("mima");
				
				user.setZh(myzh);
				user.setMima(pass);
				list.add(user);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close(con, pst, rs);
		}
		return list;
	}
}
