package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/*
 * 1.����������ѡ��Ҫ��������������
 * 2.�������ӣ���������Ͷ�Ӧ���ݿ�֮�����ϵ
 * 3.ִ�в���������ɾ���ġ������
 * 4.�ͷ���Դ�����ڶ�����������ϵ�Ͽ�
 * 	//StudentDao���ڲ��ᴴ������student��ķ���
	//����ɾ���ġ���   ������ʹ��jdbc������
	//getConn��close����������Dao�����еķ�������Ҫ��������������ȡ��������Ϊ����
	//������dao����Ϊ����
 * */
public class StudentDao extends BaseDao{
	private Connection conn=null;//���Ӷ������
	private PreparedStatement pst=null;//Ԥ����ִ�ж������
	private ResultSet rs=null;//���������

	//��������
	public int insert(StudentBean bean){
		int num=0;
		
		try {
			//��ȡ���Ӷ���
			conn=getConn();
			//�������Ӷ��󴴽�ִ�в����Ķ���
			String insertSql="insert into student(name,java,db,html) values(?,?,?,?)";
			pst=conn.prepareStatement(insertSql);
			//��վλ��λ�����ֵ
			pst.setString(1,bean.getName() );
			pst.setInt(2, bean.getJava());
			pst.setInt(3, bean.getDb());
			pst.setInt(4, bean.getHtml());
			//Ԥ�������ִ�����
			num=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ͷ���Դ
			close(conn,pst,null);
		}
		
		
		return num;
	}

	//��ѯ����
	
	public List select(){
		List list=new ArrayList();
		
		try {
			//��ȡ���Ӷ���
			conn=getConn();
			//�������Ӷ����ȡԤ����ִ�ж���
			String selectSql="select * from student";
			pst=conn.prepareStatement(selectSql);
			//ִ�в���,����ѯ����ŵ������
			rs=pst.executeQuery();
			//�Խ�������д���
			while(rs.next()){
				//���������һ�������ó����ŵ���Ӧ����
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int java=rs.getInt("java");
				int db=rs.getInt("db");
				int html=rs.getInt("html");
				//����Ϣ��װ������
				StudentBean stud=new StudentBean();
				stud.setId(id);
				stud.setName(name);
				stud.setJava(java);
				stud.setDb(db);
				stud.setHtml(html);
				//�������װ��list
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
		//����Ϣ��װ��ʵ�������
	StudentBean bean=new StudentBean();
		bean.setName("zhangsan ");
		bean.setJava(23);
		bean.setDb(45);
		bean.setHtml(56);
		//����dao�����
		StudentDao dao=new StudentDao();
		//ͨ�����������������
		int n=dao.insert(bean);
		System.out.println(n);
	}
}
