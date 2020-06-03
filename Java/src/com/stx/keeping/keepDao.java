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
	private Connection conn = null;// ���Ӷ������
	private PreparedStatement pst = null;// Ԥ����ִ�ж������
	private ResultSet rs = null;// ���������

	// ��������
	public int insert(keepBean bean) {
		int num = 0;
		try {
			// ��ȡ���Ӷ���
			conn = getCon();
			// �������Ӷ��󴴽�ִ�в����Ķ���
			String insertSql = "insert into keep(jzsj,money,cost) values(?,?,?)";
			pst = conn.prepareStatement(insertSql);
			// ��վλ��λ�����ֵ
			// mysql datetime ��java Timestamp
			Timestamp tt = new Timestamp(bean.getJzsj().getTime());
			pst.setTimestamp(1, tt);
			pst.setFloat(2, bean.getMoney());
			pst.setString(3, bean.getCost());
			// Ԥ�������ִ�����
			num = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			close(conn, pst, null);
		}
		return num;
	}

	// ��ѯ����
	public List select() {
		List list = new ArrayList();

		try {
			// ��ȡ���Ӷ���
			conn = getCon();
			// �������Ӷ����ȡԤ����ִ�ж���
			String selectSql = "select * from keep";
			pst = conn.prepareStatement(selectSql);
			// ִ�в���,����ѯ����ŵ������
			rs = pst.executeQuery();
			// �Խ�������д���
			while (rs.next()) {
				// ���������һ�������ó����ŵ���Ӧ����
				int id = rs.getInt("id");
				float money = rs.getFloat("money");
				String cost = rs.getString("cost");
				Date jzsj = rs.getDate("jzsj");
				// ����Ϣ��װ������
				keepBean keep = new keepBean();
				keep.setId(id);
				keep.setJzsj(jzsj);
				keep.setMoney(money);
				keep.setCost(cost);
				// �������װ��list
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
			// ��վλ��λ�������ʵ��ֵ
			pst.setInt(1,keep.getId());
			pst.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(conn, pst, rs);
		}
	}
	// ���·���
	public int update(keepBean keep) {
		int num = 0;
		try {
			// ��ȡ���Ӷ���
			conn = getCon();
			// ��ȡִ�ж���
			String updateSql = "update keep set cost=? where id=?";
			pst = conn.prepareStatement(updateSql);
			// ��վλ��λ�������ʵ��ֵ
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
