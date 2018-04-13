package com.example.demo;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.ExcelStyleDateFormatter;
import org.apache.xmlbeans.XmlException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.ImportOrExportUtil;
import com.lowagie.text.DocumentException;
/**
 * 测试导入导出工具类
 * @author Administrator
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTest {

	
	@Autowired
	UserService userService;
	
	List<List<String>> data = new ArrayList<List<String>>();
	@Before
	public void befo() {
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
	}
	/**
	 * 导出文件
	 */
	@Test
	public void testExport() {
		//Excel/Word/PDF
		try {
			ImportOrExportUtil.Export("Excel", "d:/userdata.xls", data, "UserData");
			ImportOrExportUtil.Export("Excel", "d:/userdata.xlsx", data, "UserData");
			ImportOrExportUtil.Export("Word", "d:/userdata.docx", data, "UserData");
			ImportOrExportUtil.Export("Word", "d:/userdata.doc", data, "UserData");
			ImportOrExportUtil.Export("PDF", "d:/userdata.pdf", data, "UserData");
		} catch (IOException | XmlException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 导入Excel,支持xls和xlsx
	 */
	@Test
	public void testImportExcel() {
		try {
			List<List<String>>  datas=ImportOrExportUtil.Import("Excel", "d:/userdata.xls");
			System.out.println(datas);
			System.out.println("-***********************");
			List<List<String>>  datas2=ImportOrExportUtil.Import("Excel", "d:/test.xlsx");
			System.out.println(datas2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 导入word,支持doc,docx
	 */
	@Test
	public void testImportWord() {
		try {
			List<List<String>>  datas=ImportOrExportUtil.Import("Word", "d:/userdata.docx");
			System.out.println(datas);
			System.out.println("***********************");
			List<List<String>>  datas2=ImportOrExportUtil.Import("Word", "d:/userdata.doc");
			System.out.println(datas2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 导入pdf，存在很大问题、、、、
	 */
	@Test
	public void testImportPDF() {
		try {
			List<List<String>>  datas=ImportOrExportUtil.Import("PDF", "d:/userdata.pdf");
			System.out.println(datas);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
