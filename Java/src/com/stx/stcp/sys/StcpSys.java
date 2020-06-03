package com.stx.stcp.sys;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.stx.stcp.bean.MenuBean;
import com.stx.stcp.bean.UserBean;
import com.stx.stcp.dao.MenuDao;
import com.stx.stcp.dao.UserDao;
/*   
 * 学习目标：从开始到坚持，从坚持到学会！！！
 * 
 * */
public class StcpSys {
	//创建扫描仪对象
	static Scanner in=new Scanner(System.in);
	//创建用户实体类变量用于记录当前登录用户信息
	static UserBean USER=null;
	public static void main(String[] args) {
		System.out.println("--------------欢迎使用双体菜谱系统---------------");
		while(true){
			System.out.println("--1注册  2登录  3查看全部菜谱 4搜索菜谱 5退出系统");
			String flag=in.next();
			if("1".equals(flag)){
				//注册
				register();
			}else if("2".equals(flag)){
				//登录
				login();
			}else if("3".equals(flag)){
				//查看全部菜谱
				showAll();
			}else if("4".equals(flag)){
				//搜索菜谱
				search();
			}else if("5".equals(flag)){
				//退出系统
			}
		}
	}
	public static void search(){
		System.out.println("请输入关键字");
		String t=in.next();
		MenuDao dao=new MenuDao();
		List l=dao.selectLike(t);
		for(int i=0;i<l.size();i++){
			MenuBean menu=(MenuBean)l.get(i);
			System.out.println("id："+menu.getId());
			System.out.println("标题："+menu.getTitle());
			System.out.println("内容："+menu.getContent());
			System.out.println("---------------------");
		}
	}
	public static void showAll(){
		MenuDao dao=new MenuDao();
		List l=dao.selectAll();
		for(int i=0;i<l.size();i++){
			MenuBean menu=(MenuBean)l.get(i);
			System.out.println("id："+menu.getId());
			System.out.println("标题："+menu.getTitle());
			System.out.println("内容："+menu.getContent());
			System.out.println("---------------------");
		}
	}
	
	public static void login(){
		//根据账号和密码到数据库进行查询
		System.out.println("请输入账号");
		String zh=in.next();
		System.out.println("请输入密码");
		String pass=in.next();
		//调用dao类的登录方法查询数据
		UserDao dao=new UserDao();
		UserBean u=dao.login(zh, pass);
		if(u!=null){
			USER=u;
			//进入用户二级菜单
			userOp();
		}else{
			System.out.println("登录失败");
		}
	}
	//用户二级菜单主方法
	public static void userOp(){
		while(true){
			System.out.println("====1发表新菜谱 2我发表的菜谱   3删除我发表的菜谱 4返回上一级");
			String flag=in.next();
			if("1".equals(flag)){
				//发表新菜谱
				newMenu();
			}else if("2".equals(flag)){
				//我发表的菜谱
				showMyAll();
			}else if("3".equals(flag)){
				//删除我发表的菜谱
				deleteById();
			}else if("4".equals(flag)){
				//返回上一级
				break;
			}
		}
	}
	
	public static void deleteById(){
		System.out.println("请输入要删除菜谱的id");
		int id=in.nextInt();
		MenuDao dao=new MenuDao();
		int n=dao.delete(id);
		if(n>0){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
	}
	public static void showMyAll(){
		//从登录对象回去对当前登录用户的id
		int uid=USER.getId();
		MenuDao dao=new MenuDao();
		List l=dao.selectByUid(uid);
		for(int i=0;i<l.size();i++){
			MenuBean menu=(MenuBean)l.get(i);
			System.out.println("id："+menu.getId());
			System.out.println("标题："+menu.getTitle());
			System.out.println("内容："+menu.getContent());
			System.out.println("---------------------");
		}
	}
	
	public static void newMenu(){
		//从键盘录入信息
		System.out.println("请输入标题");
		String title=in.next();
		System.out.println("请输入内容");
		String content=in.next();
		
		MenuBean menu=new MenuBean();
		menu.setTitle(title);
		menu.setContent(content);
		menu.setCreatetime(new Date());
		menu.setUid(USER.getId());
		MenuDao dao=new MenuDao();
		dao.insert(menu);
	}
	//注册方法
	public static void register(){
		//从键盘输入信息
		System.out.println("请输入账号");
		String zh=in.next();
		System.out.println("请输入密码");
		String pass=in.next();
		System.out.println("请输入真实姓名");
		String realname=in.next();
		//将信息封装到实体类对象
		UserBean user=new UserBean();
		user.setZh(zh);
		user.setPass(pass);
		user.setRealname(realname);
		//调用dao类新增方法保存对象（数据）到数据库
		UserDao dao=new UserDao();
		dao.register(user);
	}

}
