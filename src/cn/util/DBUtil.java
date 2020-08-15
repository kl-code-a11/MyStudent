package cn.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;



public abstract  class DBUtil<T> {

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
	
	//通用更新模板方法
	public boolean update(String sql,List<Object> obj) {
		int row=0;
		try {
			pst=getConnection().prepareStatement(sql);
			if(obj!=null) {
				for(int i=0;i<obj.size();i++) {
					pst.setObject(i+1, obj.get(i));
				}
			}
			row=pst.executeUpdate();
			if(row>0) {
				return true;
			}
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			getClose(conn, pst, rs);
		}
		
             return false;
	}
	
	//obj...可变长度数组
	//通用查询模板方法
	public List<T> Query(String sql,Class<T> clazz,Object... obj){
	   List<T> list=new ArrayList<>();
		try {
			pst=getConnection().prepareStatement(sql);
			
				for(int i=0;i<obj.length;i++) {
					pst.setObject(i+1, obj[i]);	
			}
		
			rs=pst.executeQuery();
			//获取到rs逻辑表中的所有结构
			ResultSetMetaData rm=rs.getMetaData();	
			int cnum=rm.getColumnCount();//获取表的列数
			while(rs.next()) {
				T t=clazz.newInstance();
				for(int i=1;i<=cnum;i++) {
			
				String cname=rm.getColumnName(i);//获取每一列的名称
				Field f=clazz.getDeclaredField(cname);//通过名称获取对应属性
				//忽略权限
				f.setAccessible(true);
				//将rs中获取的值，放入指定对象的对应
				f.set(t, rs.getObject(i));
			
			}
				list.add(t);
			}

		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			getClose(conn, pst, rs);
		}
		
             return list;
	}
	
	
	//通过子类实现类，具体重写获取rs方法
	public abstract T getEntity(ResultSet rs) throws Exception;

	public void getClose(Connection conn,PreparedStatement pst,ResultSet rs) {
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
}
