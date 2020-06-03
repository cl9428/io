package dao;

public class StudentBean {
		//表中的列名转换为实体类中的属性
		/*varchar-----String
		int------------int
		float---------float
		date---------date*/
		private int id;
		private String name;
		private int java;
		private int db;
		private int html;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getJava() {
			return java;
		}
		public void setJava(int java) {
			this.java = java;
		}
		public int getDb() {
			return db;
		}
		public void setDb(int db) {
			this.db = db;
		}
		public int getHtml() {
			return html;
		}
		public void setHtml(int html) {
			this.html = html;
		}		
}