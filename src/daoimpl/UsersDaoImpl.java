package daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import bean.Users;
import dao.UsersDao;

public class UsersDaoImpl implements UsersDao {

	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rst=null;
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/mytest?characterEncoding=utf-8&useSSL=false";
		    conn=DriverManager.getConnection(url,"root","root");
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public Users login(String account, String password) {
		Users user=null;
		String sql="select * from Users where name= ? and password= ?";
		try {
			pst=getConnection().prepareStatement(sql);
		pst.setString(1, account);
		pst.setString(2, password);
			 rst=pst.executeQuery();
			while(rst.next()) {
			  user=new Users();
			user.setId(rst.getInt(1));
			user.setName(rst.getString(2));
			user.setPasswd(rst.getString(3));
			user.setAge(rst.getString(4));
			user.setAddress(rst.getString(5));
			
			}
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			try {	
				
				if(pst!=null)
			        pst.close();
				if(conn!=null)
			        conn.close();
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}

		return user;
	}

}