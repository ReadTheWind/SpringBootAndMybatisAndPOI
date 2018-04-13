package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/rest")
public class UserRestController {

	@Autowired
	UserService userService;
	
	Logger log=LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/index")
	public List<User> index() {
		List<User> users=userService.getUsers();
		log.info("index:"+users.toString());
		return users;
	}
	@RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
	public User search(@PathVariable Integer id) {
		User user=userService.getUser(id);
		return user;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public int  delete(@PathVariable Integer id) {
		int ru=userService.deleteUser(id);
		return ru;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public int  update(@ModelAttribute User user) {
		int ru=userService.updateUser(user);
		return ru;
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public int add(@ModelAttribute User user) {
		int ru=userService.addUser(user);
		return ru;
	}
}
