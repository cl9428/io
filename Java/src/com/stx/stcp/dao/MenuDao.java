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
	private Connection conn = null;// ���Ӷ������
	private PreparedStatement pst = null;// Ԥ����ִ�ж������
	private ResultSet rs = null;// ���������

	// ��������
	public int insert(MenuBean bean) {
		int num = 0;

		try {
			// ��ȡ���Ӷ���
			conn = getCon();
			// �������Ӷ��󴴽�ִ�в����Ķ���
			String insertSql = "insert into menu(uid,title,content,createtime) values(?,?,?,?)";
			pst = conn.prepareStatement(insertSql);
			// ��վλ��λ�����ֵ
			pst.setInt(1, bean.getUid());
			pst.setString(2, bean.getTitle());
			pst.setString(3, bean.getContent());
			Timestamp tt = new Timestamp(bean.getCreatetime().getTime());
			pst.setTimestamp(4, tt);
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

	public List selectByUid(int uid) {
		List list = new ArrayList();

		try {
			// ��ȡ���Ӷ���
			conn = getCon();
			// �������Ӷ����ȡԤ����ִ�ж���
			String selectSql = "select * from menu where uid=?";
			pst = conn.prepareStatement(selectSql);
			pst.setInt(1, uid);
			// ִ�в���,����ѯ����ŵ������
			rs = pst.executeQuery();
			// �Խ�������д���
			while (rs.next()) {
				// ���������һ�������ó����ŵ���Ӧ����
				int id = rs.getInt("id");
				int uid1 = rs.getInt("uid");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date createtime = rs.getTimestamp("createtime");
				// ����Ϣ��װ������
				MenuBean menu = new MenuBean();
				menu.setId(id);
				menu.setUid(uid1);
				menu.setTitle(title);
				menu.setContent(content);
				menu.setCreatetime(createtime);
				// �������װ��list
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

	// ��ѯ����

	public List selectAll() {
		List list = new ArrayList();

		try {
			// ��ȡ���Ӷ���
			conn = getCon();
			// �������Ӷ����ȡԤ����ִ�ж���
			String selectSql = "select * from menu";
			pst = conn.prepareStatement(selectSql);
			// ִ�в���,����ѯ����ŵ������
			rs = pst.executeQuery();
			// �Խ�������д���
			while (rs.next()) {
				// ���������һ�������ó����ŵ���Ӧ����
				int id = rs.getInt("id");
				int uid1 = rs.getInt("uid");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date createtime = rs.getTimestamp("createtime");
				// ����Ϣ��װ������
				MenuBean menu = new MenuBean();
				menu.setId(id);
				menu.setUid(uid1);
				menu.setTitle(title);
				menu.setContent(content);
				menu.setCreatetime(createtime);
				// �������װ��list
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
			// ��ȡ���Ӷ���
			conn = getCon();
			// �������Ӷ����ȡԤ����ִ�ж���
			String selectSql = "select * from menu where title like '%' ? '%'";
			pst = conn.prepareStatement(selectSql);
			pst.setString(1, t);
			// ִ�в���,����ѯ����ŵ������
			rs = pst.executeQuery();
			// �Խ�������д���
			while (rs.next()) {
				// ���������һ�������ó����ŵ���Ӧ����
				int id = rs.getInt("id");
				int uid1 = rs.getInt("uid");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date createtime = rs.getTimestamp("createtime");
				// ����Ϣ��װ������
				MenuBean menu = new MenuBean();
				menu.setId(id);
				menu.setUid(uid1);
				menu.setTitle(title);
				menu.setContent(content);
				menu.setCreatetime(createtime);
				// �������װ��list
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

	// ɾ������
	public int delete(int id) {
		int num = 0;
		try {
			// ��ȡ���Ӷ���
			conn = getCon();
			// ��ȡִ�ж���
			String deleteSql = "delete from menu where id=?";
			pst = conn.prepareStatement(deleteSql);
			// �������ռλ����ֵ
			pst.setInt(1, id);
			// ִ�в���
			num = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			close(conn, pst, rs);
		}

		return num;
	}

}
