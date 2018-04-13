package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {
		//查询所有
		public List<User> getUsers();

		//id查询
		public User getUser(Integer id);
		
		//增加用户
		public int addUser(User user);
		
		//删除用户
		public int deleteUser(Integer id);
		
		//更新用户
		public int updateUser(User user);
}
