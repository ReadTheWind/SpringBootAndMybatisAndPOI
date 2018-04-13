package com.example.demo;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.ExcelStyleDateFormatter;
import org.apache.xmlbeans.XmlException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.wordUtil.WordUtil;
@RunWith(SpringRunner.class)
@SpringBootTest
public class WordToolTest {
	
	@Autowired
	UserService userService;
	
	@Test
	public void testWord() throws IOException, XmlException {
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
		 WordUtil.exportWord("d:/userdata.docx", "UserData", data);
	}

	@Test
	public void testImportWord() {
		try {
			List<List<String>> datas= WordUtil.importWord("D:/userdata.doc");
			
			List<List<String>> datas2= WordUtil.importWord("d:/userdata.docx");
			System.out.println(datas);
			System.out.println(datas2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
