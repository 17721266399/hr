package com.service.impl;

import com.bean.User;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao=new UserDaoImpl();
	@Override
	public User loginCheck(String loginName, String password) {
		// TODO Auto-generated method stub
		return userDao.queryUserByLogin(loginName, password);
	}
	@Override
	public boolean registUser(String name, String password, String email, String phone) {
		// TODO Auto-generated method stub
		return userDao.addUser(name, password, email, phone);
	}
	@Override
	public boolean registCheck(String name) {
		// TODO Auto-generated method stub
		return userDao.isExist(name);
	}

}
