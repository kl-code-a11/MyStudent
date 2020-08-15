package com.daoimpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.Users;
import com.dao.UsersDao;
import com.util.DBUtil;

public class UsersDaoImpl extends DBUtil<Users> implements UsersDao {

	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rst=null;
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/mytest?characterEncoding=utf-8&useSSL=false";
		    conn=DriverManager.getConnection(url,"root","root");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
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
			user.setAge(rst.getInt(4));
			user.setAddress(rst.getString(5));
			
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {	
				
				if(pst!=null)
			        pst.close();
				if(conn!=null)
			        conn.close();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		return user;
	}

	@Override
	public Users getEntity(ResultSet rs) throws Exception {
		Users user=new Users();
		user.setId(rs.getInt(1));
		user.setName(rs.getString(2));
		user.setPasswd(rs.getString(3));
		user.setAge(rs.getInt(4));
		user.setAddress(rs.getString(5));
		return user;
	}

}
