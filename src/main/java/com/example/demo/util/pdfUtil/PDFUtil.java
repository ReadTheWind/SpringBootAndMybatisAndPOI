package com.example.demo.util.pdfUtil;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * PDF导入导出工具类
 * @author Administrator
 *
 */
public class PDFUtil {
	
	public static void importPDF() throws FileNotFoundException{
//		FileInputStream in=new FileInputStream(new File("d:/test.pdf"));
//		PdfReader reader=new PdfReader(in);
//		com.itextpdf.text.Rectangle rect = new com.itextpdf.text.Rectangle(70, 80, 490, 580);
//	    RenderFilter filter = new RegionTextRenderFilter(rect);
//	    TextExtractionStrategy strategy;
//	    for (int i = 1; i <= reader.getNumberOfPages(); i++) {
//	        strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
//	        out.println(PdfTextExtractor.getTextFromPage(reader, i, strategy));
//	    }
//	    out.flush();
//	    out.close();
//	    reader.close();
		
//		PdfReader reader = new PdfReader("d:/test.pdf");
//	    Rectangle rect = new Rectangle();
//	    RenderFilter filter = new RegionTextRenderFilter(rect);
//	    TextExtractionStrategy strategy;
//	    for (int i = 1; i <= reader.getNumberOfPages(); i++) {
//	        strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
//	        PdfTextExtractor.getTextFromPage(i));
//	    }
//	    reader.close();
	}
	

	/**
	 * 
	 * @param filepath
	 * @param datas
	 * @param title
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void exportPDF(String filepath,List<List<String>> datas,String title) throws DocumentException, IOException {
		 	BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
	        Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);

	        // 第一步，创建document对象
	        Rectangle rectPageSize = new Rectangle(PageSize.A3);
//	        Rectangle rectPageSize = new Rectangle(0,0,1000,1000);
	        //下面代码设置页面横置
	        //rectPageSize = rectPageSize.rotate();
	        
	        //创建document对象并指定边距
//	        Document doc = new Document(rectPageSize,10,10,20,10);
	        Document document = new Document(rectPageSize,10,10,20,10);
	        try
	        {
	            // 第二步,将Document实例和文件输出流用PdfWriter类绑定在一起
	            //从而完成向Document写，即写入PDF文档
	            PdfWriter.getInstance(document,new FileOutputStream(filepath));
	            //第3步,打开文档
	            document.open();
	            //第3步,向文档添加文字. 文档由段组成
//	            document.add(new Paragraph(title));

	            Paragraph par = new Paragraph(title,FontChinese);
	            par.setAlignment(1);
	            par.setFont(new Font(bfChinese, 8, Font.BOLD));
	            document.add(par);
	            
	            int colnum=datas.get(0).size();
	            PdfPTable table = new PdfPTable(colnum);
	            table.setSpacingBefore(15f);   //表格之前设置空格
	            table.setSpacingAfter(1);
	            for(int i=0;i<datas.size();i++){
	            	for (int j = 0; j < colnum; j++) {
	                    PdfPCell cell = new PdfPCell();
	//                    cell.setColspan(colnum);
	                    if(i==0) {
	                    	cell.setBackgroundColor(new Color(180,180,180));
	                    }
	                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                    cell.addElement(new Paragraph(datas.get(i).get(j) , FontChinese));
	                    cell.setFixedHeight(30f);
	                    cell.setNoWrap(true); //设置不折行
	                    table.addCell(cell);
	            	}
	            }
	            document.add(table);
	            
	        }
	        catch (DocumentException de)
	        {
	            System.err.println(de.getMessage());
	        }
	        catch (IOException ioe)
	        {
	            System.err.println(ioe.getMessage());
	        }
	        //关闭document
	        document.close();
	        
	        System.out.println("Pdf 导出成功！");
	     }
	    
}
