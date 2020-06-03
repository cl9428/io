package com.stx.stcp.sys;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.stx.stcp.bean.MenuBean;
import com.stx.stcp.bean.UserBean;
import com.stx.stcp.dao.MenuDao;
import com.stx.stcp.dao.UserDao;
/*   
 * ѧϰĿ�꣺�ӿ�ʼ����֣��Ӽ�ֵ�ѧ�ᣡ����
 * 
 * */
public class StcpSys {
	//����ɨ���Ƕ���
	static Scanner in=new Scanner(System.in);
	//�����û�ʵ����������ڼ�¼��ǰ��¼�û���Ϣ
	static UserBean USER=null;
	public static void main(String[] args) {
		System.out.println("--------------��ӭʹ��˫�����ϵͳ---------------");
		while(true){
			System.out.println("--1ע��  2��¼  3�鿴ȫ������ 4�������� 5�˳�ϵͳ");
			String flag=in.next();
			if("1".equals(flag)){
				//ע��
				register();
			}else if("2".equals(flag)){
				//��¼
				login();
			}else if("3".equals(flag)){
				//�鿴ȫ������
				showAll();
			}else if("4".equals(flag)){
				//��������
				search();
			}else if("5".equals(flag)){
				//�˳�ϵͳ
			}
		}
	}
	public static void search(){
		System.out.println("������ؼ���");
		String t=in.next();
		MenuDao dao=new MenuDao();
		List l=dao.selectLike(t);
		for(int i=0;i<l.size();i++){
			MenuBean menu=(MenuBean)l.get(i);
			System.out.println("id��"+menu.getId());
			System.out.println("���⣺"+menu.getTitle());
			System.out.println("���ݣ�"+menu.getContent());
			System.out.println("---------------------");
		}
	}
	public static void showAll(){
		MenuDao dao=new MenuDao();
		List l=dao.selectAll();
		for(int i=0;i<l.size();i++){
			MenuBean menu=(MenuBean)l.get(i);
			System.out.println("id��"+menu.getId());
			System.out.println("���⣺"+menu.getTitle());
			System.out.println("���ݣ�"+menu.getContent());
			System.out.println("---------------------");
		}
	}
	
	public static void login(){
		//�����˺ź����뵽���ݿ���в�ѯ
		System.out.println("�������˺�");
		String zh=in.next();
		System.out.println("����������");
		String pass=in.next();
		//����dao��ĵ�¼������ѯ����
		UserDao dao=new UserDao();
		UserBean u=dao.login(zh, pass);
		if(u!=null){
			USER=u;
			//�����û������˵�
			userOp();
		}else{
			System.out.println("��¼ʧ��");
		}
	}
	//�û������˵�������
	public static void userOp(){
		while(true){
			System.out.println("====1�����²��� 2�ҷ���Ĳ���   3ɾ���ҷ���Ĳ��� 4������һ��");
			String flag=in.next();
			if("1".equals(flag)){
				//�����²���
				newMenu();
			}else if("2".equals(flag)){
				//�ҷ���Ĳ���
				showMyAll();
			}else if("3".equals(flag)){
				//ɾ���ҷ���Ĳ���
				deleteById();
			}else if("4".equals(flag)){
				//������һ��
				break;
			}
		}
	}
	
	public static void deleteById(){
		System.out.println("������Ҫɾ�����׵�id");
		int id=in.nextInt();
		MenuDao dao=new MenuDao();
		int n=dao.delete(id);
		if(n>0){
			System.out.println("ɾ���ɹ�");
		}else{
			System.out.println("ɾ��ʧ��");
		}
	}
	public static void showMyAll(){
		//�ӵ�¼�����ȥ�Ե�ǰ��¼�û���id
		int uid=USER.getId();
		MenuDao dao=new MenuDao();
		List l=dao.selectByUid(uid);
		for(int i=0;i<l.size();i++){
			MenuBean menu=(MenuBean)l.get(i);
			System.out.println("id��"+menu.getId());
			System.out.println("���⣺"+menu.getTitle());
			System.out.println("���ݣ�"+menu.getContent());
			System.out.println("---------------------");
		}
	}
	
	public static void newMenu(){
		//�Ӽ���¼����Ϣ
		System.out.println("���������");
		String title=in.next();
		System.out.println("����������");
		String content=in.next();
		
		MenuBean menu=new MenuBean();
		menu.setTitle(title);
		menu.setContent(content);
		menu.setCreatetime(new Date());
		menu.setUid(USER.getId());
		MenuDao dao=new MenuDao();
		dao.insert(menu);
	}
	//ע�᷽��
	public static void register(){
		//�Ӽ���������Ϣ
		System.out.println("�������˺�");
		String zh=in.next();
		System.out.println("����������");
		String pass=in.next();
		System.out.println("��������ʵ����");
		String realname=in.next();
		//����Ϣ��װ��ʵ�������
		UserBean user=new UserBean();
		user.setZh(zh);
		user.setPass(pass);
		user.setRealname(realname);
		//����dao��������������������ݣ������ݿ�
		UserDao dao=new UserDao();
		dao.register(user);
	}

}
