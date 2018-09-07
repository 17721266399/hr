package com.dao;

import com.bean.User;

public interface UserDao {
	User queryUserByLogin(String name,String psw);
	boolean addUser(String name, String password, String email, String phone);
	boolean isExist(String name);
}
