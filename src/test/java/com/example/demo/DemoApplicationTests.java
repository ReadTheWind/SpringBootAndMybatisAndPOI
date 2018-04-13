package com.example.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	UserMapper userMapper;
	
	@Test
	public void contextLoads() {
		//
//		List<User> users= userMapper.getUsers();
//		System.out.println(users.size());
//		for (int i = 0; i < users.size(); i++) {
//			System.out.println(users.get(i).getName());
//		}
		
//		User user=userMapper.getUser(1);
//		System.out.println(user.getName());
		
//		User user=new User();
//		user.setName("李四");
//		user.setAge(18);
//		user.setSex("女");
//		user.setEmail("123123@qq.com");
//		user.setAddress("北京海定");
//		user.setMobile("1231321");
//		user.setRemark("备注");
//		int ru=userMapper.addUser(user);
//		System.out.println(ru);
		
//		int ru=userMapper.deleteUser(6);
//		System.out.println(ru);
		
		User user=new User();
		user.setId(5);
		user.setName("李四2");
		user.setAge(19);
		user.setSex("女");
		user.setEmail("51531@qq.com");
		user.setAddress("北京小胡同");
		user.setMobile("1231212321");
		user.setRemark("备注");
		int ru=userMapper.updateUser(user);
		System.out.println(ru);
	}

}
