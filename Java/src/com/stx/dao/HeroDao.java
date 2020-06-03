package com.stx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.StudentBean;
import dao.StudentDao;

public class HeroDao extends Dao {
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs= null;
	
	public int insert(HeroBean hero){
		int num=0;
		try {
			conn = getCon();
			
			String insertSql="insert into hero(id,usename,password,damage) values(?,?,?,?)";
			pst = conn.prepareStatement(insertSql);
			pst.setInt(1,hero.getId());
			pst.setString(2,hero.getUsename());
			pst.setString(3,hero.getPassword());
			pst.setInt(4,hero.getDamage());
			
			num=pst.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(conn,pst,null);
		}
		return num;
	}
	public List select(){
		List list=new ArrayList();
		
		try {
			conn=getCon();
			String selectSql="select * from hero";
			pst=conn.prepareStatement(selectSql);
			rs=pst.executeQuery();

			while(rs.next()){
				int id=rs.getInt("id");
				String usename=rs.getString("usename");
				String password=rs.getString("password");
				int damage=rs.getInt("damage");

				HeroBean hero=new HeroBean();
				hero.setId(id);
				hero.setUsename(usename);
				hero.setPassword(password);
				hero.setDamage(damage);
			
				list.add(hero);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn,pst,rs);
		}
		return list;
	}
	
	public static void main(String[] args){
		HeroBean h = new HeroBean();
		h.setId(4);
		h.setUsename("guanyu");
		h.setPassword("asd");
		h.setDamage(100);
		HeroDao d = new HeroDao();
		int n=d.insert(h);
		System.out.println(n);
		
		List l = d.select();
		for (int i = 0; i < l.size(); i++) {
			HeroBean her = (HeroBean)l.get(i);
			int id = her.getId();
			String name = her.getUsename();
			String password = her.getPassword();
			System.out.println(id+"		"+name+"	"+password);
		}
		
	}
}
