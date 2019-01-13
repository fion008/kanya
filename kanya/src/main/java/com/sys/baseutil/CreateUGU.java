package com.sys.baseutil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class CreateUGU {
	private String tablename;
	private String database_url;
	private String database_name;

	public String getDatabase_name() {
		return database_name;
	}

	private CreateUGU() {
	}

	public void setDatabase_name(String database_name) {
		this.database_name = database_name;
	}

	private String database_user;
	private String database_pwd;
	private boolean isCreateSeq;

	public boolean isCreateSeq() {
		return isCreateSeq;
	}

	public void setCreateSeq(boolean isCreateSeq) {
		this.isCreateSeq = isCreateSeq;
	}

	public enum database_type {
		MYSQL, SQLSERVER, ORACLE
	};

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getDatabase_url() {
		return database_url;
	}

	public void setDatabase_url(String database_url) {
		this.database_url = database_url;
	}

	public String getDatabase_user() {
		return database_user;
	}

	public void setDatabase_user(String database_user) {
		this.database_user = database_user;
	}

	public String getDatabase_pwd() {
		return database_pwd;
	}

	public void setDatabase_pwd(String database_pwd) {
		this.database_pwd = database_pwd;
	}

	public void CreateUGU(String tablename1, String database_url1, String database_user1, String database_pwd1, String databasename1) {
		this.tablename = tablename1;
		this.database_url = database_url1;
		this.database_user = database_user1;
		this.database_pwd = database_pwd1;
		this.database_name = databasename1;
	}

	public boolean createFile(Object type) {
		Connection conn = this.getConnect(type);
		if (conn != null) {
			try {
				StringBuffer sqls = new StringBuffer();
				if (type.equals(database_type.SQLSERVER)) {
					sqls.append("select \n");
					sqls.append("c.name as Field \n");
					sqls.append(",t.name as Type \n");
					sqls.append(",c.length as l \n");
					sqls.append(",'' as Comment \n");
					sqls.append(" from  \n");
					sqls.append("syscolumns c \n");
					sqls.append(",systypes t \n");
					sqls.append("where c.id=object_id('" + this.tablename + "') \n");
					sqls.append("and c.xusertype=t.xusertype \n");
				} else if (type.equals(database_type.MYSQL)) {
					sqls.append("show full fields from  " + this.tablename);
				} else if (type.equals(database_type.ORACLE)) {
					sqls.append("select lower(a.column_name) as Field,b.data_type as Type,a.comments as aa from user_col_comments a left join user_tab_columns b" + " on a.column_name=b.column_name" + " where a.table_name='" + this.tablename.toUpperCase() + "' and b.table_name='" + this.tablename.toUpperCase() + "'");
				}
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sqls.toString());
				Map<String, String> map = new HashMap<String, String>();
				Map<String, String> map2 = new HashMap<String, String>();
				while (rs.next()) {
					String field = rs.getString("Field");
					String field_type = rs.getString("Type");
					String Comment = "";
					if (type.equals(database_type.ORACLE)) {
						Comment = rs.getString("aa");
					} else {
						Comment = rs.getString("Comment");
					}

					map.put(field, field_type);
					if (Comment != null && !Comment.isEmpty())
						map2.put(field, Comment.length() == 0 ? field : Comment);
				}
				JspToHtml jsptohtml = new JspToHtml();
				if (isCreateSeq) {
					StringBuffer seq = new StringBuffer();
					if (type.equals(database_type.ORACLE)) {
						seq.append("create sequence " + this.tablename + "_seq");
						seq.append(" minvalue 1");
						seq.append(" start with 1");
						seq.append(" increment by 1");
						seq.append(" cache 20");
					} else if (type.equals(database_type.MYSQL)) {
						seq.append("alter table customers change id id int not null auto_increment primary key");
					}
					if (seq.toString().length() > 0) {
						try {
							conn.createStatement().executeUpdate(seq.toString());
						} catch (Exception e) {
							//System.out.println("SEQ生成过程中出现错误：" + e.getMessage());
						}

					}
				}
				JspToHtml.TableToClass(this.tablename, map); // 创建类
			//	JspToHtml.TableToXML(this.tablename, map, type); // 创建映射文件
		//		JspToHtml.UpdateProperties(this.tablename);// 修改Hibernateporperties
				
				
				// jsptohtml.UpdateSpring(this.tablename);//需要手动添加 documenttype
				// jsptohtml.TableToAction(this.tablename);//创建action
				// jsptohtml.TableToHelp(this.tablename);//创建help

				// <!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN"
				// "http://www.springframework.org/dtd/spring-beans.dtd">
				// jsptohtml.JspToHtmlFile(this.tablename,"add.jsp","添加测试",map2,"2fm");//INSERT
				// jsptohtml.JspToHtmlFile2(this.tablename,"get.jsp","添加测试",map2,"2fm");//SELECT
				rs.close();
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return false;
	}

	private Connection getConnect(Object type) {
		Connection conn = null;
		try {
			if (type.equals(database_type.MYSQL)) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://" + this.database_url + ":3306/" + this.database_name + "?useUnicode=true&characterEncoding=utf-8", this.database_user, this.database_pwd);
			} else if (type.equals(database_type.ORACLE)) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@" + this.database_url + ":1521:" + this.database_name, this.database_user, this.database_pwd);
			} else if (type.equals(database_type.SQLSERVER)) {
	   			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn = DriverManager.getConnection("jdbc:sqlserver://" + this.database_url + ":1433;DatabaseName=" + this.database_name, this.database_user, this.database_pwd);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) throws SQLException {

		CreateUGU ugu = new CreateUGU();
		ugu.setDatabase_url("localhost");
		ugu.setDatabase_name("kanya");
		ugu.setDatabase_user("root");
		ugu.setDatabase_pwd("root");
//    	String[] names = new String[] { "aq_yh","danwei","danwei_bumen","danwei_bumen_yuangong","zichan","zichan_biaoji","zichan_biaoji_fanwei","zichan_biaoji_fanwei_fangjian","zichan_biaoji_feiyong","zichan_gongyou","zichan_louceng","zichan_louceng","zichan_louceng_fangjian"};
		String[] names = new String[] {"interrupt_type"};
    
		StringBuffer sqls = new StringBuffer();
    	sqls.append("SELECT table_name  FROM information_schema.tables  WHERE table_schema='kanya' ");
    	Connection conn =ugu.getConnect(CreateUGU.database_type.MYSQL);
    	Statement statement=null;
    	ResultSet rs=null;
		statement = conn.createStatement();
		rs = statement.executeQuery(sqls.toString());
	//	 names = new String[100];
		int j=0;
/*		while (rs.next()) {
			String name = rs.getString(1);
			if(StringUtils.isNotBlank(name)){
				names[j++]=name;
			}
		}*/
		
		for (int i = 0; i < names.length; i++) {
			if (StringUtils.isNotBlank(names[i])) {
				ugu.setTablename(names[i]);
				ugu.createFile(CreateUGU.database_type.MYSQL);
			}
		}
		System.out.println("完事");

	}
}
