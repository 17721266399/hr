package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.User;
import com.dao.UserDao;
import com.utils.DBPoolUtils;

public class UserDaoImpl implements UserDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	@Override
	public User queryUserByLogin(String name, String psw) {
		User user=null;
		try {
			conn=DBPoolUtils.getConn();
			String sql="select name,psw from user where name=? and psw=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, psw);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setName(rs.getString("name"));
				user.setPsw(rs.getString("psw"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBPoolUtils.close(rs, pstmt, conn);
		}
		return user;
	}
	@Override
	public boolean addUser(String name, String password, String email, String phone) {
		try {
			conn=DBPoolUtils.getConn();
			String sql="insert into user(name, psw,e_mail,phone) values(?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
			pstmt.setString(4, phone);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBPoolUtils.close(rs, pstmt, conn);
		}		
		return false;
	}
	@Override
	public boolean isExist(String name) {
		try {
			conn=DBPoolUtils.getConn();
			String sql="select name,psw from user where name=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBPoolUtils.close(rs, pstmt, conn);
		}
		return false;
	}

}
