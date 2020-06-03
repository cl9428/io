package com.stx.stcp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.stx.stcp.bean.UserBean;

public class UserDao extends Dao {
	private Connection conn = null;// ���Ӷ������
	private PreparedStatement pst = null;// Ԥ����ִ�ж������
	private ResultSet rs = null;// ���������

	// �û�ע�᷽��
	public int register(UserBean bean) {
		int num = 0;
		try {
			// ��ȡ���Ӷ���
			conn = getCon();
			// �������Ӷ��󴴽�ִ�в����Ķ���
			String insertSql = "insert into user(zh,mima,realname) values(?,?,?)";
			pst = conn.prepareStatement(insertSql);
			// ��վλ��λ�����ֵ
			pst.setString(1, bean.getZh());
			pst.setString(2, bean.getPass());
			pst.setString(3, bean.getRealname());
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
	

	//��¼�����������˺ź�������в�ѯ�������û�����
	//�����ѯ�����ݷ���һ���û����������ѯ������Ϣ����null
	public UserBean login(String zh,String pass){
		UserBean user=null;
		try {
			//��ȡ���Ӷ���
			conn=getCon();
			//�������Ӷ���ȡԤ����ִ�ж���
			String selectSql="select * from user where zh=? and pass=?";
			pst=conn.prepareStatement(selectSql);
			pst.setString(1, zh);
			pst.setString(2, pass);
			//ִ�в���,����ѯ����ŵ������
			rs=pst.executeQuery();
			//�Խ�������д���
			if(rs.next()){
				user=new UserBean();
				//���������һ�������ó����ŵ���Ӧ����
				int id=rs.getInt("id");
				String zh1=rs.getString("zh");
				String pass1=rs.getString("pass");
				String realname=rs.getString("realname");
				//����Ϣ��װ������
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
