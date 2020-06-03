package com.stx.keeping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stx.dao.Dao;
import com.stx.note.BqBean;

public class keepDao extends Dao{
	private Connection conn = null;// 连接对象变量
	private PreparedStatement pst = null;// 预处理执行对象变量
	private ResultSet rs = null;// 结果集变量

	// 新增方法
	public int insert(keepBean bean) {
		int num = 0;
		try {
			// 获取连接对象
			conn = getCon();
			// 根据连接对象创建执行操作的对象
			String insertSql = "insert into keep(jzsj,money,cost) values(?,?,?)";
			pst = conn.prepareStatement(insertSql);
			// 对站位符位置填充值
			// mysql datetime ：java Timestamp
			Timestamp tt = new Timestamp(bean.getJzsj().getTime());
			pst.setTimestamp(1, tt);
			pst.setFloat(2, bean.getMoney());
			pst.setString(3, bean.getCost());
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
	public List select() {
		List list = new ArrayList();

		try {
			// 获取连接对象
			conn = getCon();
			// 根据连接对象获取预处理执行对象
			String selectSql = "select * from keep";
			pst = conn.prepareStatement(selectSql);
			// 执行操作,将查询结果放到结果集
			rs = pst.executeQuery();
			// 对结果集进行处理
			while (rs.next()) {
				// 将结果集的一行数据拿出来放到对应变量
				int id = rs.getInt("id");
				float money = rs.getFloat("money");
				String cost = rs.getString("cost");
				Date jzsj = rs.getDate("jzsj");
				// 将信息封装到对象
				keepBean keep = new keepBean();
				keep.setId(id);
				keep.setJzsj(jzsj);
				keep.setMoney(money);
				keep.setCost(cost);
				// 将对象封装到list
				list.add(keep);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pst, rs);
		}
		return list;
	}
	public void delete(keepBean keep){
		try {
			conn = getCon();
			String deleteSql = "delete from keep where id=?";
			pst = conn.prepareStatement(deleteSql);
			// 对站位符位置填充真实的值
			pst.setInt(1,keep.getId());
			pst.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(conn, pst, rs);
		}
	}
	// 更新方法
	public int update(keepBean keep) {
		int num = 0;
		try {
			// 获取连接对象
			conn = getCon();
			// 获取执行对象
			String updateSql = "update keep set cost=? where id=?";
			pst = conn.prepareStatement(updateSql);
			// 对站位符位置填充真实的值
			pst.setString(1,keep.getCost());
			pst.setInt(2, keep.getId());
			num = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pst, rs);
		}
		return num;
	}
}
