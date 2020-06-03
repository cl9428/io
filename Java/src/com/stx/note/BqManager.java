package com.stx.note;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class BqManager {
	public static Scanner in=new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("------欢迎使用双体便签-------");
		while(true){
			System.out.println("--1 查看全部便签  2新增便签  3更新便签 4删除便签  5退出系统");
			//从键盘选择菜单
			String flag=in.next();
			//判断主逻辑
			if("1".equals(flag)){
				//查看全部便签
				showAll();
			}else if("2".equals(flag)){
				//新增便签
				newbq();
			}else if("3".equals(flag)){
				//更新便签
				updateBq();
			}else if("4".equals(flag)){
				//删除便签
			}else if("5".equals(flag)){
				//退出系统
				break;
			}
		}
		
	}
	
	
	public static void updateBq(){
		//输入要更新信息的id
		System.out.println("请输入要更新信息的id：");
		int id=in.nextInt();
		//输入要更新的内容
		System.out.println("请输入要更新的内容：");
		String content=in.next();
		//将信息封装到BqBean实体类对象
		BqBean bq=new BqBean();
		bq.setId(id);
		bq.setContent(content);
		
		//调用dao类更新方法
		BqDao dao=new BqDao();
		int n=dao.update(bq);
		if(n>0){
			System.out.println("更新成功");
		}else{
			System.out.println("更新失败");
		}
	}
	//查询全部数据
	public static void showAll(){
		//调用dao类查询方法查询全部便签数据
		BqDao dao=new BqDao();
		List list=dao.select();
		//展示查询结果
		for(int i=0;i<list.size();i++){
			BqBean bq=(BqBean)list.get(i);
			int id=bq.getId();
			String content=bq.getContent();
			Date d=bq.getCjsj();
			System.out.println("id："+id);
			System.out.println("创建时间："+d.toString());
			System.out.println("便签内容："+content);
			System.out.println("-------------------------");
		}
	}
	
	
	public static void newbq(){
		//从键盘录入便签信息
		System.out.println("请录入便签内容");
		String content=in.next();
		//将录入的信息封装到实体类对象
		BqBean bq=new BqBean();
		bq.setContent(content);
		//创建Date对象作为创建时间
		Date cjsj=new Date();
		bq.setCjsj(cjsj);
		//调用dao类的新增方法新增数据到数据库
		BqDao dao=new BqDao();
		dao.insert(bq);
	}
}
