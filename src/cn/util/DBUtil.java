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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return conn;
	}
	
	//ͨ�ø���ģ�巽��
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			getClose(conn, pst, rs);
		}
		
             return false;
	}
	
	//obj...�ɱ䳤������
	//ͨ�ò�ѯģ�巽��
	public List<T> Query(String sql,Class<T> clazz,Object... obj){
	   List<T> list=new ArrayList<>();
		try {
			pst=getConnection().prepareStatement(sql);
			
				for(int i=0;i<obj.length;i++) {
					pst.setObject(i+1, obj[i]);	
			}
		
			rs=pst.executeQuery();
			//��ȡ��rs�߼����е����нṹ
			ResultSetMetaData rm=rs.getMetaData();	
			int cnum=rm.getColumnCount();//��ȡ�������
			while(rs.next()) {
				T t=clazz.newInstance();
				for(int i=1;i<=cnum;i++) {
			
				String cname=rm.getColumnName(i);//��ȡÿһ�е�����
				Field f=clazz.getDeclaredField(cname);//ͨ�����ƻ�ȡ��Ӧ����
				//����Ȩ��
				f.setAccessible(true);
				//��rs�л�ȡ��ֵ������ָ������Ķ�Ӧ
				f.set(t, rs.getObject(i));
			
			}
				list.add(t);
			}

		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			getClose(conn, pst, rs);
		}
		
             return list;
	}
	
	
	//ͨ������ʵ���࣬������д��ȡrs����
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
