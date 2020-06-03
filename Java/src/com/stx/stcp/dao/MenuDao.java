package com.stx.stcp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.stx.dao.Dao;
import com.stx.stcp.bean.MenuBean;

public class MenuDao extends Dao {
	private Connection conn = null;// 连接对象变量
	private PreparedStatement pst = null;// 预处理执行对象变量
	private ResultSet rs = null;// 结果集变量

	// 新增方法
	public int insert(MenuBean bean) {
		int num = 0;

		try {
			// 获取连接对象
			conn = getCon();
			// 根据连接对象创建执行操作的对象
			String insertSql = "insert into menu(uid,title,content,createtime) values(?,?,?,?)";
			pst = conn.prepareStatement(insertSql);
			// 对站位符位置填充值
			pst.setInt(1, bean.getUid());
			pst.setString(2, bean.getTitle());
			pst.setString(3, bean.getContent());
			Timestamp tt = new Timestamp(bean.getCreatetime().getTime());
			pst.setTimestamp(4, tt);
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

	// 查询方法

	public List selectByUid(int uid) {
		List list = new ArrayList();

		try {
			// 获取连接对象
			conn = getCon();
			// 根据连接对象获取预处理执行对象
			String selectSql = "select * from menu where uid=?";
			pst = conn.prepareStatement(selectSql);
			pst.setInt(1, uid);
			// 执行操作,将查询结果放到结果集
			rs = pst.executeQuery();
			// 对结果集进行处理
			while (rs.next()) {
				// 将结果集的一行数据拿出来放到对应变量
				int id = rs.getInt("id");
				int uid1 = rs.getInt("uid");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date createtime = rs.getTimestamp("createtime");
				// 将信息封装到对象
				MenuBean menu = new MenuBean();
				menu.setId(id);
				menu.setUid(uid1);
				menu.setTitle(title);
				menu.setContent(content);
				menu.setCreatetime(createtime);
				// 将对象封装到list
				list.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pst, rs);
		}
		return list;
	}

	// 查询方法

	public List selectAll() {
		List list = new ArrayList();

		try {
			// 获取连接对象
			conn = getCon();
			// 根据连接对象获取预处理执行对象
			String selectSql = "select * from menu";
			pst = conn.prepareStatement(selectSql);
			// 执行操作,将查询结果放到结果集
			rs = pst.executeQuery();
			// 对结果集进行处理
			while (rs.next()) {
				// 将结果集的一行数据拿出来放到对应变量
				int id = rs.getInt("id");
				int uid1 = rs.getInt("uid");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date createtime = rs.getTimestamp("createtime");
				// 将信息封装到对象
				MenuBean menu = new MenuBean();
				menu.setId(id);
				menu.setUid(uid1);
				menu.setTitle(title);
				menu.setContent(content);
				menu.setCreatetime(createtime);
				// 将对象封装到list
				list.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pst, rs);
		}
		return list;
	}
	
	public List selectLike(String t) {
		List list = new ArrayList();

		try {
			// 获取连接对象
			conn = getCon();
			// 根据连接对象获取预处理执行对象
			String selectSql = "select * from menu where title like '%' ? '%'";
			pst = conn.prepareStatement(selectSql);
			pst.setString(1, t);
			// 执行操作,将查询结果放到结果集
			rs = pst.executeQuery();
			// 对结果集进行处理
			while (rs.next()) {
				// 将结果集的一行数据拿出来放到对应变量
				int id = rs.getInt("id");
				int uid1 = rs.getInt("uid");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date createtime = rs.getTimestamp("createtime");
				// 将信息封装到对象
				MenuBean menu = new MenuBean();
				menu.setId(id);
				menu.setUid(uid1);
				menu.setTitle(title);
				menu.setContent(content);
				menu.setCreatetime(createtime);
				// 将对象封装到list
				list.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pst, rs);
		}
		return list;
	}

	// 删除方法
	public int delete(int id) {
		int num = 0;
		try {
			// 获取连接对象
			conn = getCon();
			// 获取执行对象
			String deleteSql = "delete from menu where id=?";
			pst = conn.prepareStatement(deleteSql);
			// 对语句中占位符赋值
			pst.setInt(1, id);
			// 执行操作
			num = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放资源
			close(conn, pst, rs);
		}

		return num;
	}

}
