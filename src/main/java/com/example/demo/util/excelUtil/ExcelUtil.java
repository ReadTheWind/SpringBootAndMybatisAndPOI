package com.example.demo.util.excelUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel
 * POI导入导出工具类
 * @author Administrator
 *
 */
public class ExcelUtil{
	 /**
     * 导出数据
     * @param os
     * @param data
     * @throws IOException
     */
    public static void exportToExcel(String filepath, List<List<String>> data,String title) throws IOException {
    	OutputStream os=new FileOutputStream(filepath);
        HSSFWorkbook wb=null;
        try {
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(title);
            for (int r = 0 ; r < data.size() ; r++) {
                HSSFRow row = sheet.createRow(r);
                List<String> cols = data.get(r);
                for (int c = 0 ; c < cols.size() ; c++) {
                    HSSFCell cell = row.createCell(c);
                    cell.setCellValue(new HSSFRichTextString(cols.get(c)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(wb!=null){
                wb.write(os);
            }
            os.flush();
            os.close();
        }
        System.out.println("Excel 导出成功！");
    }
    
    
    /**
     * XSSFWorkbook数据导入
     * @param is 文件流
     * @return
     * @throws IOException
     * @throws InvalidFormatException 
     */
    public static List<List<String>> importFromExcel2(String filepath) throws IOException, InvalidFormatException {
    	InputStream is=new FileInputStream(filepath);
//    	XSSFWorkbook wb = new XSSFWorkbook(is);
//        XSSFSheet sheet = wb.getSheetAt(0);
    	Workbook workbook = WorkbookFactory.create(is);  //上面的方法会存在excel版本兼容性问题
    	Sheet sheet = workbook.getSheetAt(0);  //示意访问sheet  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<List<String>> data = new ArrayList<List<String>>();
        try {
            for (int r = sheet.getFirstRowNum() ; r <= sheet.getLastRowNum() ; r++) {
//                XSSFRow row = sheet.getRow(r);
            	Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }

                List<String> cols = new ArrayList<String>(); 
                for (int c = row.getFirstCellNum() ; c < row.getLastCellNum() ; c++) {
//                    XSSFCell cell = row.getCell(c);
                	Cell cell = row.getCell(c);
                    if (cell == null) {
                        cols.add("");
                    } else {
                        switch(cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_STRING:
                            cols.add(cell.getStringCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            cols.add(""+cell.getBooleanCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                                cols.add(sdf.format(cell.getDateCellValue()));
                            } else {
                                cols.add(""+cell.getNumericCellValue());
                            }
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            cols.add(cell.getCellFormula());
                            break;
                        case HSSFCell.CELL_TYPE_BLANK:
                            cols.add("");
                            break;
                        }                        
                    }
                }
                data.add(cols);
            }
        } finally {
            is.close();
        }
        return data;
    }
   
}
