package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
@Mapper
public interface UserMapper {
	//查询所有
	public List<User> getUsers();

	//id查询
	public User getUser(@Param("id")Integer id);
	
	//增加用户
	public int addUser(User user);
	
	//删除用户
	public int deleteUser(@Param("id")Integer id);
	
	//更新用户
	public int updateUser(User user);
}
