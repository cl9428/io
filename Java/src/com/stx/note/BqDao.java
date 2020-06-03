package com.stx.note;
import com.stx.dao.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class BqDao extends Dao {
	private Connection conn = null;// 连接对象变量
	private PreparedStatement pst = null;// 预处理执行对象变量
	private ResultSet rs = null;// 结果集变量

	// 新增方法
	public int insert(BqBean bean) {
		int num = 0;
		try {
			// 获取连接对象
			conn = getCon();
			// 根据连接对象创建执行操作的对象
			String insertSql = "insert into bq(cjsj,content) values(?,?)";
			pst = conn.prepareStatement(insertSql);
			// 对站位符位置填充值
			// mysql datetime ：java Timestamp
			Timestamp tt = new Timestamp(bean.getCjsj().getTime());
			pst.setTimestamp(1, tt);
			pst.setString(2, bean.getContent());
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
			String selectSql = "select * from bq";
			pst = conn.prepareStatement(selectSql);
			// 执行操作,将查询结果放到结果集
			rs = pst.executeQuery();
			// 对结果集进行处理
			while (rs.next()) {
				// 将结果集的一行数据拿出来放到对应变量
				int id = rs.getInt("id");
				String content = rs.getString("content");
				Date cjsj = rs.getDate("cjsj");
				// 将信息封装到对象
				BqBean bq = new BqBean();
				bq.setId(id);
				bq.setCjsj(cjsj);
				bq.setContent(content);
				// 将对象封装到list
				list.add(bq);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pst, rs);
		}
		return list;
	}

	// 更新方法
	public int update(BqBean bq) {
		int num = 0;
		try {
			// 获取连接对象
			conn = getCon();
			// 获取执行对象
			String updateSql = "update bq set content=? where id=?";
			pst = conn.prepareStatement(updateSql);
			// 对站位符位置填充真实的值
			pst.setString(1, bq.getContent());
			pst.setInt(2, bq.getId());
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
