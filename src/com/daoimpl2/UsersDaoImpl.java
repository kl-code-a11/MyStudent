package com.daoimpl2;


import java.sql.ResultSet;
import java.util.List;
import cn.bean.Users;
import cn.dao.UsersDao;
import cn.util.DBUtil;


public class UsersDaoImpl extends DBUtil<Users> implements UsersDao {

	public Users getEntity(ResultSet rs) throws Exception {
		// TODO 自动生成的方法存根
		Users users=new Users();
		users.setId(rs.getInt(1));
		users.setName(rs.getString(2));
		users.setPasswd(rs.getString(3));
		users.setAge(rs.getInt(4));
		users.setAddress(rs.getString(5));
		return users;
	}

	@Override
	public Users login(String account, String password) {
		String sql="select * from users where account=? and password=?";
	     List<Users> list = Query(sql,Users.class,account,password);
		try {
			if(list.size()>0) {
			return list.get(0);	
			}
	
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Users> finadAll() {
		return Query("select * from users",Users.class);
	}

}
