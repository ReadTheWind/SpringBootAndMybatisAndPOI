package com.example.demo.util;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.XmlException;

import com.example.demo.util.excelUtil.ExcelUtil;
import com.example.demo.util.pdfUtil.PDFImportUtil;
import com.example.demo.util.pdfUtil.PDFUtil;
import com.example.demo.util.wordUtil.WordUtil;
import com.lowagie.text.DocumentException;

/**
 * Excel,Word,PDF导入/导出工具类
 * @author Administrator
 *
 */
public class ImportOrExportUtil {
	
	/**
	 * 导出工具类
	 * @param importType 导出文件格式，Excel/Word/PDF
	 * @param filepath 文件导出位置
	 * @param datas 数据
	 * @param title 标题
	 * @throws IOException 
	 * @throws XmlException
	 * @throws DocumentException
	 */
	public static void Export(String importType,String filepath,List<List<String>> datas,String title) throws IOException, XmlException, DocumentException {
		if("Excel".equals(importType)) {
			ExcelUtil.exportToExcel(filepath, datas,title);
		}else if("Word".equals(importType)) {
			WordUtil.exportWord(filepath, title, datas);
		}else if("PDF".equals(importType)) {
			PDFUtil.exportPDF(filepath,datas,title);
		}else {
			//不支持的文件类
			System.out.println("不支持的导出类型");
		}
	}

	/**
	 * 导入工具类
	 * @param exportType 导入文件格式，Excel/Word/PDF
	 * @param filepath 导入文件位置
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException 
	 */
	public static List<List<String>> Import(String exportType,String filepath) throws IOException, InvalidFormatException {
		List<List<String>>  data = null;
		if("Excel".equals(exportType)) {
			 data = ExcelUtil.importFromExcel2(filepath);
		}else if("Word".equals(exportType)) {
			 data = WordUtil.importWord(filepath);
		}else if("PDF".equals(exportType)) {
			 data = PDFImportUtil.importPDF2(filepath);
		}else {
			//不支持的文件类
			System.out.println("不支持的导入类型");
		}
		return data;
	}
}
