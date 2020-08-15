package cn.dao;

import java.util.List;

import cn.bean.Users;

public interface UsersDao {
public Users login(String name,String password);
public List<Users> finadAll();
}
