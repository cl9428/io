package com.stx.keeping;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.stx.note.BqBean;
import com.stx.note.BqDao;

public class keepTest {
	public static Scanner in=new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("------��ӭʹ�ü��˱�-------");
		while(true){
			System.out.println("--1ע��   2��¼");
			String flag = in.next();
			if("1".equals(flag)){
				reg();
			}else if("2".equals(flag)){
				log();
				
			}
		}
		
	}
	public static void reg(){
		System.out.println("�������˺ţ�");
		String zh = in.next();
		System.out.println("���������룺");
		String mima = in.next();
		
		userBean u = new userBean();
		u.setZh(zh);
		u.setMima(mima);
		userDao ud = new userDao();
		ud.insert(u);
	}
	public static void log(){
		System.out.println("�������˺ţ�");
		String zh = in.next();
		System.out.println("���������룺");
		String mima = in.next();
		
		userDao ud = new userDao();
		List list = ud.select(zh, mima);
		if(list.size()!=0){
			userjm();
		}else{
			System.out.println("�˺Ż�������󣡣���");
		}
	}
	public static void userjm(){
		System.out.println("------��ӭʹ�ü��˱�-------");
		while(true){
			System.out.println("--1 �鿴ȫ���˵�	  2�����˵�	  3�����ҵ��˵�	  4ɾ�� 		 5�˳�ϵͳ");
			String flag=in.next();
			if("1".equals(flag)){
				showAll();
			}else if("2".equals(flag)){
				newkeep();
			}else if("3".equals(flag)){
				updateKeep();
			}else if("4".equals(flag)){
				dekeep();
			}else if("5".equals(flag)){
				break;
			}
		}
	}
	
	public static void dekeep(){
		System.out.println("������Ҫɾ����id��");
		int id=in.nextInt();
		keepBean keep=new keepBean();
		keep.setId(id);
		//����dao����·���		
		keepDao dao = new keepDao();
		dao.delete(keep);
		System.out.println("ɾ���ɹ�");
	}
	
	public static void updateKeep(){
		//����Ҫ������Ϣ��id
		System.out.println("������Ҫ���µ�id��");
		int id=in.nextInt();
		//����Ҫ���µ�����
		System.out.println("������Ҫ���µĻ��ѣ�");
		String cost=in.next();
		keepBean keep=new keepBean();
		keep.setId(id);
		keep.setCost(cost);		
		//����dao����·���
		keepDao dao = new keepDao();
		int n=dao.update(keep);
		if(n>0){
			System.out.println("���³ɹ�");
		}else{
			System.out.println("����ʧ��");
		}
	}
	//��ѯȫ������
	public static void showAll(){
		
	keepDao dao = new keepDao();
		List list=dao.select();
		//չʾ��ѯ���
		for(int i=0;i<list.size();i++){
			keepBean keep=(keepBean)list.get(i);
			int id=keep.getId();
			String cost=keep.getCost();
			float money = keep.getMoney();
			Date d=keep.getJzsj();
			System.out.println("id��"+id);
			System.out.println("����ʱ�䣺"+d.toString());
			System.out.println("���ѽ�"+money);
			System.out.println("����ȥ����"+cost);
			System.out.println("-------------------------");
		}
	}
	
	
	public static void newkeep(){
		System.out.println("��¼�뻨��ȥ����");
		String cost=in.next();
		System.out.println("��������");
		float money=in.nextFloat();
		
		keepBean keep = new keepBean();
		keep.setCost(cost);
		keep.setMoney(money);
		Date jzsj=new Date();
		keep.setJzsj(jzsj);
		keepDao dao = new keepDao();
		dao.insert(keep);
	}
}
