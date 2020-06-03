package com.stx.stcp.sys;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String flag=null;
		if("1".equals(flag)){
			System.out.println("adsfasd");
		}else{
			System.out.println("222222");
		}
		//会发生空指针异常
		if(flag.equals("1")){
			System.out.println("adsfasd");
		}
	}

}
