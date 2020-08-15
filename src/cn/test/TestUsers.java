package cn.test;

import java.util.List;

import cn.bean.Users;
import cn.dao.UsersDao;
import cn.daoimpl2.UsersDaoImpl;

public class TestUsers {
public static void main(String[] args) {
	UsersDao ud=new UsersDaoImpl();
	List<Users> list=ud.finadAll();
	for(Users users:list) {
		System.out.println(users);
	}
	
	
	
}
}
