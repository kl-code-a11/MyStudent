package com.daoimpl2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Student;
import com.dao.GradeDao;
import com.dao.StudentDao;
import com.util.DBUtil;

public class StudentDaoImpl extends DBUtil<Student> implements StudentDao {

	GradeDao gd=new GradeDaoImpl();
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://127.0.0.1:3306/mytest?characterEncoding=utf-8&useSSL=false";
		    conn=DriverManager.getConnection(url,"root","root");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return conn;
	}
	
	@Override
	public List<Student> findAll() {
		// TODO 自动生成的方法存根
		return 	Query("select * from student");
	}

	@Override
	public Student findById(int id) {
		String sql="select * from student where id=?";
		List<Student> list=Query(sql,id);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean save(Student student) {
		// TODO 自动生成的方法存根
		String sql="insert into student values(null,?,?,?,?,?,?,?)";
		try {
			pst=getConnection().prepareStatement(sql);	
		pst.setString(1, student.getName());
		pst.setString(2, student.getSex());
		pst.setInt(3, student.getAge());
		pst.setInt(4, student.getGrade().getGid());
		pst.setString(5, student.getAddress());
		pst.setString(6, student.getPhone());
		pst.setString(7, student.getEmail());
		int num=pst.executeUpdate();
		if(num>0) {
			return true;
		}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	
		return false;
	}

	@Override
	public boolean update(Student student) {
		// TODO 自动生成的方法存根
		String sql="update student set name=?,sex=?,age=?,gid=?,address=?,phone=?,email=? where id=?";
		try {
			pst=getConnection().prepareStatement(sql);	
		pst.setString(1, student.getName());
		pst.setString(2, student.getSex());
		pst.setInt(3, student.getAge());
		pst.setInt(4,student.getGrade().getGid());
		pst.setString(5, student.getAddress());
		pst.setString(6, student.getPhone());
		pst.setString(7, student.getEmail());
		pst.setInt(8,student.getId());
		int num=pst.executeUpdate();
		if(num>0) {
			return true;
		}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO 自动生成的方法存根
		// TODO 自动生成的方法存根
		String sql="delete from student where id=?";
		try {
			pst=getConnection().prepareStatement(sql);	
		pst.setInt(1, id);
	
		int num=pst.executeUpdate();
		if(num>0) {
			return true;
		}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	
	public void getClose(Connection conn,PreparedStatement pst,ResultSet rs) {
		try {
			if(rs!=null) 
			rs.close();
			
			if(pst!=null) 
			pst.close();
	
			if(conn!=null) 
			conn.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@Override
	public Student getEntity(ResultSet rs) throws Exception {
		Student stu=new Student();
		stu.setId(rs.getInt(1));
		stu.setName(rs.getString(2));
		stu.setSex(rs.getString(3));
		stu.setAge(rs.getInt(4));
		stu.setGrade(gd.findById(rs.getInt(5)));
		stu.setAddress(rs.getString(6));
		stu.setPhone(rs.getString(7));
		stu.setEmail(rs.getString(8));
		return stu;
	}

}