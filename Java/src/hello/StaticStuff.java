package hello;   

	public class StaticStuff{
		static int x;
		static { System.out.println("x1="+x);x+=5;}
	
	public static void main(String [] args){
		/*int i = 10;
		System.out.println("ÄãºÃ£¬Java");//×¢ÊÍ
		
		System.out.println("x2="+x);
		System.out.println(i);//×¢ÊÍ
*/		
		System.out.println("x2="+x);
		}
	static { System.out.println("x3="+x);x%=3;}
}	

