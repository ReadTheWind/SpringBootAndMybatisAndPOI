package com.example.demo;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.ExcelStyleDateFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.pdfUtil.PDFImportUtil;
import com.example.demo.util.pdfUtil.PDFUtil;
import com.lowagie.text.DocumentException;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PDFToolTest {

	@Autowired
	UserService userService;
	@Test
	public void test() throws DocumentException, IOException {
		List<List<String>> data = new ArrayList<List<String>>();
        List<String> s = new ArrayList<String>();
        s.add("id");
        s.add("用户名");
        s.add("性别");	
        s.add("年龄");
        s.add("生日");
        s.add("邮箱地址");
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
		PDFUtil.exportPDF("d:/test2.pdf",data,"UserData");
	}

	@Test
	public void testImport() {
		try {
//			List<List<String>> datas=PDFImportUtil.importPDF2("d:/201803281815.pdf");
			List<List<String>> datas=PDFImportUtil.importPDF2("d:/test2.pdf");
			System.out.println(datas);
			//pdf转word
//			PDFImportUtil.importPDF3("d:/201803281815.pdf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
