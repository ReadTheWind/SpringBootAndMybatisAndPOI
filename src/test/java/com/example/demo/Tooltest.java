package com.example.demo;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.ExcelStyleDateFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.excelUtil.ExcelUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Tooltest  {

	@Autowired
	UserService userService;
	@Test
	public void testExportExcel() {
		  try {
//			FileOutputStream os = new FileOutputStream(new File("d:/test.doc"));
	        List<List<String>> data = new ArrayList<List<String>>();
	        List<String> s = new ArrayList<String>();
	        s.add("id");
	        s.add("用户名");
	        s.add("性别");	
	        s.add("年龄");
	        s.add("生日");
	        s.add("邮箱");
	        s.add("手机号码");
	        s.add("住址");
	        s.add("备注");
	        data.add(s);
	        List<User> users=userService.getUsers();
	        for (User user : users) {
	        	List<String> s1 = new ArrayList<String>();
	        	s1.add(String.valueOf(user.getId()));
		        s1.add(user.getName());
		        s1.add(user.getSex());	
		        s1.add(String.valueOf(user.getAge()));
		        DateFormat df=new ExcelStyleDateFormatter("yyyy-MM-ss");
		        s1.add(df.format(user.getBirthday()));
		        s1.add(user.getEmail());
		        s1.add(user.getMobile());
		        s1.add(user.getAddress());
		        s1.add(user.getRemark());
		        data.add(s1);
			}
			ExcelUtil.exportToExcel("d:/test.xls", data,"UserDatas");
			System.out.println("data:"+data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testImportExcel() {
		try {
//			List<List<String>>  data = ExcelUtil.importFromExcel2("d:/test.xlsx");
			List<List<String>>  data = ExcelUtil.importFromExcel2("d:/test.xls");
			System.out.println("data:"+data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
