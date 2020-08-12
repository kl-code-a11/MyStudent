package daoimpl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Grade;
import dao.GradeDao;

public class GradeDaoImpl implements GradeDao {
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
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
	public Grade findById(int gid) {
		// TODO 自动生成的方法存根
		Grade grade=null;
		String sql="select * from grade where gid=?";
		try {
			pst= getConnection().prepareStatement(sql);
			pst.setInt(1,gid);
			rs=pst.executeQuery();
			if(rs.next()) {
				grade=new Grade();
				grade.setGid(rs.getInt(1));
				grade.setGname(rs.getString(2));
			}return grade;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) 
				rs.close();
				
				if(pst!=null) 
				pst.close();
		
				if(conn!=null) 
				conn.close();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return null;
		

		
	}

}
