package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	Logger log=LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv=new ModelAndView();
		List<User> users=userService.getUsers();
		log.info("index:"+users.toString());
		mv.addObject("userlist", users);
		mv.setViewName("index");
		return mv;
	}
	@RequestMapping("/search")
	public ModelAndView search(Integer id) {
		ModelAndView mv=new ModelAndView();
		User user=userService.getUser(id);
		List<User> users=new ArrayList<User>(); 
		users.add(user);
		mv.addObject("userlist", users);
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(Integer id) {
		ModelAndView mv=new ModelAndView();
		int ru=userService.deleteUser(id);
//		mv.addObject("ru", ru);
		mv.setViewName("redirect:index");
		return mv;
	}
	
	@RequestMapping("/findUserById")
	public ModelAndView findUserById(Integer id) {
		ModelAndView mv=new ModelAndView();
		User user=userService.getUser(id);
		mv.addObject("user", user);
		mv.setViewName("updateUser");
		return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView  update(User user) {
		ModelAndView mv=new ModelAndView();
		int ru=userService.updateUser(user);
		mv.setViewName("redirect:index");//调整controller
		return mv;
	}
	
	@RequestMapping("/addUser")
	public ModelAndView addUser() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("addUser");
		return mv;
	}
	
	@RequestMapping("/add")
	public ModelAndView add(User user) {
		ModelAndView mv=new ModelAndView();
		int ru=userService.addUser(user);
		mv.addObject("ru", ru);
		mv.setViewName("redirect:index");
		return mv;
	}
}
