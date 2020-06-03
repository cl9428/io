package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/*
 * 1.加载驱动：选择要操作的数据类型
 * 2.建立连接：建立程序和对应数据库之间的联系
 * 3.执行操作：增、删、改、查操作
 * 4.释放资源：将第二步建立的联系断开
 * 	//StudentDao类内部会创建操作student表的方法
	//增、删、改、查   方法：使用jdbc来操作
	//getConn、close方法是所有Dao都会有的方法，需要将这两个方法提取出来，作为父类
	//而所有dao类作为子类
 * */
public class StudentDao extends BaseDao{
	private Connection conn=null;//连接对象变量
	private PreparedStatement pst=null;//预处理执行对象变量
	private ResultSet rs=null;//结果集变量

	//新增方法
	public int insert(StudentBean bean){
		int num=0;
		
		try {
			//获取连接对象
			conn=getConn();
			//根据连接对象创建执行操作的对象
			String insertSql="insert into student(name,java,db,html) values(?,?,?,?)";
			pst=conn.prepareStatement(insertSql);
			//对站位符位置填充值
			pst.setString(1,bean.getName() );
			pst.setInt(2, bean.getJava());
			pst.setInt(3, bean.getDb());
			pst.setInt(4, bean.getHtml());
			//预处理对象执行语句
			num=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源
			close(conn,pst,null);
		}
		
		
		return num;
	}

	//查询方法
	
	public List select(){
		List list=new ArrayList();
		
		try {
			//获取连接对象
			conn=getConn();
			//根据连接对象获取预处理执行对象
			String selectSql="select * from student";
			pst=conn.prepareStatement(selectSql);
			//执行操作,将查询结果放到结果集
			rs=pst.executeQuery();
			//对结果集进行处理
			while(rs.next()){
				//将结果集的一行数据拿出来放到对应变量
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int java=rs.getInt("java");
				int db=rs.getInt("db");
				int html=rs.getInt("html");
				//将信息封装到对象
				StudentBean stud=new StudentBean();
				stud.setId(id);
				stud.setName(name);
				stud.setJava(java);
				stud.setDb(db);
				stud.setHtml(html);
				//将对象封装到list
				list.add(stud);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn,pst,rs);
		}
		return list;
	}
		
	/*public static void main(String[] arg){
		StudentDao dao=new StudentDao();
		List l=dao.select();
		for(int i=0;i<l.size();i++){
			StudentBean stud=(StudentBean)l.get(i);
			int id=stud.getId();
			String name=stud.getName();
			System.out.println(id+" "+name);
		}
	}*/
	
	

	public static void main(String[] args){
		//将信息封装到实体类对象
	StudentBean bean=new StudentBean();
		bean.setName("zhangsan ");
		bean.setJava(23);
		bean.setDb(45);
		bean.setHtml(56);
		//创建dao类对象
		StudentDao dao=new StudentDao();
		//通过对象调用新增方法
		int n=dao.insert(bean);
		System.out.println(n);
	}
}
