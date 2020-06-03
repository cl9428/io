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
	private Connection conn = null;// ���Ӷ������
	private PreparedStatement pst = null;// Ԥ����ִ�ж������
	private ResultSet rs = null;// ���������

	// ��������
	public int insert(BqBean bean) {
		int num = 0;
		try {
			// ��ȡ���Ӷ���
			conn = getCon();
			// �������Ӷ��󴴽�ִ�в����Ķ���
			String insertSql = "insert into bq(cjsj,content) values(?,?)";
			pst = conn.prepareStatement(insertSql);
			// ��վλ��λ�����ֵ
			// mysql datetime ��java Timestamp
			Timestamp tt = new Timestamp(bean.getCjsj().getTime());
			pst.setTimestamp(1, tt);
			pst.setString(2, bean.getContent());
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
			String selectSql = "select * from bq";
			pst = conn.prepareStatement(selectSql);
			// ִ�в���,����ѯ����ŵ������
			rs = pst.executeQuery();
			// �Խ�������д���
			while (rs.next()) {
				// ���������һ�������ó����ŵ���Ӧ����
				int id = rs.getInt("id");
				String content = rs.getString("content");
				Date cjsj = rs.getDate("cjsj");
				// ����Ϣ��װ������
				BqBean bq = new BqBean();
				bq.setId(id);
				bq.setCjsj(cjsj);
				bq.setContent(content);
				// �������װ��list
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

	// ���·���
	public int update(BqBean bq) {
		int num = 0;
		try {
			// ��ȡ���Ӷ���
			conn = getCon();
			// ��ȡִ�ж���
			String updateSql = "update bq set content=? where id=?";
			pst = conn.prepareStatement(updateSql);
			// ��վλ��λ�������ʵ��ֵ
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
