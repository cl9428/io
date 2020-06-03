package com.stx.note;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class BqManager {
	public static Scanner in=new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("------��ӭʹ��˫���ǩ-------");
		while(true){
			System.out.println("--1 �鿴ȫ����ǩ  2������ǩ  3���±�ǩ 4ɾ����ǩ  5�˳�ϵͳ");
			//�Ӽ���ѡ��˵�
			String flag=in.next();
			//�ж����߼�
			if("1".equals(flag)){
				//�鿴ȫ����ǩ
				showAll();
			}else if("2".equals(flag)){
				//������ǩ
				newbq();
			}else if("3".equals(flag)){
				//���±�ǩ
				updateBq();
			}else if("4".equals(flag)){
				//ɾ����ǩ
			}else if("5".equals(flag)){
				//�˳�ϵͳ
				break;
			}
		}
		
	}
	
	
	public static void updateBq(){
		//����Ҫ������Ϣ��id
		System.out.println("������Ҫ������Ϣ��id��");
		int id=in.nextInt();
		//����Ҫ���µ�����
		System.out.println("������Ҫ���µ����ݣ�");
		String content=in.next();
		//����Ϣ��װ��BqBeanʵ�������
		BqBean bq=new BqBean();
		bq.setId(id);
		bq.setContent(content);
		
		//����dao����·���
		BqDao dao=new BqDao();
		int n=dao.update(bq);
		if(n>0){
			System.out.println("���³ɹ�");
		}else{
			System.out.println("����ʧ��");
		}
	}
	//��ѯȫ������
	public static void showAll(){
		//����dao���ѯ������ѯȫ����ǩ����
		BqDao dao=new BqDao();
		List list=dao.select();
		//չʾ��ѯ���
		for(int i=0;i<list.size();i++){
			BqBean bq=(BqBean)list.get(i);
			int id=bq.getId();
			String content=bq.getContent();
			Date d=bq.getCjsj();
			System.out.println("id��"+id);
			System.out.println("����ʱ�䣺"+d.toString());
			System.out.println("��ǩ���ݣ�"+content);
			System.out.println("-------------------------");
		}
	}
	
	
	public static void newbq(){
		//�Ӽ���¼���ǩ��Ϣ
		System.out.println("��¼���ǩ����");
		String content=in.next();
		//��¼�����Ϣ��װ��ʵ�������
		BqBean bq=new BqBean();
		bq.setContent(content);
		//����Date������Ϊ����ʱ��
		Date cjsj=new Date();
		bq.setCjsj(cjsj);
		//����dao������������������ݵ����ݿ�
		BqDao dao=new BqDao();
		dao.insert(bq);
	}
}
