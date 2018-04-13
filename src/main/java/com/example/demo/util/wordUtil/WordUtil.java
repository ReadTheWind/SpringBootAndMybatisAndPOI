package com.example.demo.util.wordUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.FontTable;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
/**
 * word导入导出工具类
 * @author Administrator
 *
 */
public class WordUtil {

	/**
	 * 导入word方法
	 * @param filepath 文件路径
 	 * @return
	 * @throws IOException
	 */
	public static List<List<String>> importWord(String filepath) throws IOException {
		FileInputStream in=new FileInputStream(new File(filepath));
		XWPFDocument  doc=new XWPFDocument (in);
		List<List<String>> datas=new ArrayList<List<String>>();
		List<XWPFTable> tables=doc.getTables();
		for (XWPFTable table : tables) {
		    // 获取表格的行
		    List<XWPFTableRow> rows = table.getRows();
		    for (XWPFTableRow row : rows) { 
		        // 获取表格的每个单元格
		        List<XWPFTableCell> tableCells = row.getTableCells();
		        List<String> data=new ArrayList<String>();
		        for (XWPFTableCell cell : tableCells) {
		             // 获取单元格的内容
		             String text = cell.getText();
		             data.add(text);
		        }
		        datas.add(data);
		    }
		}
		return datas;
	}
	
	
	/**
	 * 导出word
	 * @param filenames 文件名
	 * @param title 标题
	 * @param data 数据
	 * @throws IOException
	 * @throws XmlException
	 */
	public static void exportWord(String filenames,String title,List<List<String>> data) throws IOException, XmlException {
		XWPFDocument document= new XWPFDocument();  
		FileOutputStream out = new FileOutputStream(new File(filenames));
		//添加标题  
        XWPFParagraph titleParagraph = document.createParagraph();  
        //设置段落居中  
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);  
  
        XWPFRun titleParagraphRun = titleParagraph.createRun();  
        titleParagraphRun.setText(title);  
        titleParagraphRun.setColor("000000");  
        titleParagraphRun.setFontSize(20);  
        //换行  
        XWPFParagraph paragraph1 = document.createParagraph();  
        XWPFRun paragraphRun1 = paragraph1.createRun();  
        paragraphRun1.setText("\r");  
       //基本信息表格  
        XWPFTable infoTable = document.createTable();  
        //去表格边框  
//        infoTable.getCTTbl().getTblPr().unsetTblBorders();  
       //列宽自动分割  
        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();  
        infoTableWidth.setType(STTblWidth.DXA);  
        infoTableWidth.setW(BigInteger.valueOf(9072));  
        
        for (int i = 0; i < data.size(); i++) {
        	XWPFTableRow row;
        	//第一条数据直接get第一行，其他的需要创建行
        	if(i==0) {
        		row=infoTable.getRow(0);
        	}else {
        		row =infoTable.createRow();
        	}
        	List<String> cols = data.get(i);
        	for (int j = 0; j < cols.size(); j++) {
        		XWPFTableCell cell;
        		//第一条数据第一个数直接get，其他的需要创建列
        		//非第一条数据直接get列
        		if(i==0) {
        			if(j==0) {
            			cell=row.getCell(0);
            		}else {
            			cell=row.addNewTableCell();
            		}
        		}else {
        			cell=row.getCell(j);
        		}
        		cell.setText(cols.get(j));
			}
		}
        
       /**加上之后读取会有问题*/
//        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();  
//        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);  
//        //添加页眉  
//        CTP ctpHeader = CTP.Factory.newInstance();  
//        CTR ctrHeader = ctpHeader.addNewR();  
//        CTText ctHeader = ctrHeader.addNewT();  
//        String headerText = "Java POI create MS word file.";  
//        ctHeader.setStringValue(headerText);  
//        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);  
//        //设置为右对齐  
//        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);  
//        XWPFParagraph[] parsHeader = new XWPFParagraph[1];  
//        parsHeader[0] = headerParagraph;  
//        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);  
//  
//  
//        //添加页脚  
//        CTP ctpFooter = CTP.Factory.newInstance();  
//        CTR ctrFooter = ctpFooter.addNewR();  
//        CTText ctFooter = ctrFooter.addNewT();  
//        String footerText = "http://blog.csdn.net/zhouseawater";  
//        ctFooter.setStringValue(footerText);  
//        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);  
//        headerParagraph.setAlignment(ParagraphAlignment.CENTER);  
//        XWPFParagraph[] parsFooter = new XWPFParagraph[1];  
//        parsFooter[0] = footerParagraph;  
//        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);  
  
        document.write(out);  
        out.close();  
        System.out.println("Word 导出成功！");  
	}
	
	/**
	 * 导入word方法doc格式
	 * @param filepath 文件路径
 	 * @return
	 * @throws IOException
	 */
	public static List<List<String>> importWord2(String filepath) throws IOException {
		File file = new File(filepath);
        FileInputStream fis = new FileInputStream(file);
        HWPFDocument doc = new HWPFDocument(fis);
        Range range = doc.getRange();
      //遍历range范围内的table。
        TableIterator tableIter = new TableIterator(range);
        TableRow row;
        TableCell cell;
        while (tableIter.hasNext()) {
	        Table table = tableIter.next();
		        int rowNum = table.numRows();
		        for (int j=0; j<rowNum; j++) {
		        row = table.getRow(j);
		        int cellNum = row.numCells();
		       for (int k=0; k<cellNum; k++) {
			       cell = row.getCell(k);
			        //输出单元格的文本
			        System.out.println(cell.text().trim());
		        }
	        }
        }
		return null;
	}
}
