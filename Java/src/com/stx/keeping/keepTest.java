package com.stx.keeping;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.stx.note.BqBean;
import com.stx.note.BqDao;

public class keepTest {
	public static Scanner in=new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("------欢迎使用记账本-------");
		while(true){
			System.out.println("--1注册   2登录");
			String flag = in.next();
			if("1".equals(flag)){
				reg();
			}else if("2".equals(flag)){
				log();
				
			}
		}
		
	}
	public static void reg(){
		System.out.println("请输入账号：");
		String zh = in.next();
		System.out.println("请输入密码：");
		String mima = in.next();
		
		userBean u = new userBean();
		u.setZh(zh);
		u.setMima(mima);
		userDao ud = new userDao();
		ud.insert(u);
	}
	public static void log(){
		System.out.println("请输入账号：");
		String zh = in.next();
		System.out.println("请输入密码：");
		String mima = in.next();
		
		userDao ud = new userDao();
		List list = ud.select(zh, mima);
		if(list.size()!=0){
			userjm();
		}else{
			System.out.println("账号或密码错误！！！");
		}
	}
	public static void userjm(){
		System.out.println("------欢迎使用记账本-------");
		while(true){
			System.out.println("--1 查看全部账单	  2新增账单	  3更新我的账单	  4删除 		 5退出系统");
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
		System.out.println("请输入要删除的id：");
		int id=in.nextInt();
		keepBean keep=new keepBean();
		keep.setId(id);
		//调用dao类更新方法		
		keepDao dao = new keepDao();
		dao.delete(keep);
		System.out.println("删除成功");
	}
	
	public static void updateKeep(){
		//输入要更新信息的id
		System.out.println("请输入要更新的id：");
		int id=in.nextInt();
		//输入要更新的内容
		System.out.println("请输入要更新的花费：");
		String cost=in.next();
		keepBean keep=new keepBean();
		keep.setId(id);
		keep.setCost(cost);		
		//调用dao类更新方法
		keepDao dao = new keepDao();
		int n=dao.update(keep);
		if(n>0){
			System.out.println("更新成功");
		}else{
			System.out.println("更新失败");
		}
	}
	//查询全部数据
	public static void showAll(){
		
	keepDao dao = new keepDao();
		List list=dao.select();
		//展示查询结果
		for(int i=0;i<list.size();i++){
			keepBean keep=(keepBean)list.get(i);
			int id=keep.getId();
			String cost=keep.getCost();
			float money = keep.getMoney();
			Date d=keep.getJzsj();
			System.out.println("id："+id);
			System.out.println("创建时间："+d.toString());
			System.out.println("花费金额："+money);
			System.out.println("花费去处："+cost);
			System.out.println("-------------------------");
		}
	}
	
	
	public static void newkeep(){
		System.out.println("请录入花费去处：");
		String cost=in.next();
		System.out.println("请输入金额");
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
