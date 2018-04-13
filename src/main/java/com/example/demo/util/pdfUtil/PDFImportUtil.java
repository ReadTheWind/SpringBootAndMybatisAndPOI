package com.example.demo.util.pdfUtil;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.FilteredTextRenderListener;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.RegionTextRenderFilter;
import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class PDFImportUtil {

	public static void importPDF() throws IOException{
		PDDocument document = PDDocument.load(new File("D:\\test.pdf"));
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition( true );
        Rectangle rect = new Rectangle(0, 0, 1000, 2000);
        stripper.addRegion( "class1", rect );
        PDPageTree allPages = document.getDocumentCatalog().getPages();
        PDPage firstPage = (PDPage)allPages.get(0);
        stripper.extractRegions( firstPage );
        System.out.println( "Text in the area:" + rect );
        System.out.println( stripper.getTextForRegion( "class1" ) );
	}
	
	public static List<List<String>> importPDF2(String filepath) throws IOException{
		PdfReader reader = new PdfReader(filepath);
//		PrintWriter out = new PrintWriter(new FileOutputStream(txt));
	 	Rectangle2D rect = new Rectangle2D.Double(0, 0, 1000, 2000);
	    RenderFilter filter = new RegionTextRenderFilter(rect);
	    TextExtractionStrategy strategy;
	    List<List<String>> data=new ArrayList<List<String>>();
	    for (int i = 1; i <= reader.getNumberOfPages(); i++) {
	        strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
	        String text=PdfTextExtractor.getTextFromPage(reader, i, strategy);
//	        System.out.println(text);
	        //如果表格格式没有拉伸则可以得到数据
	        String[] str=text.split("\n");
	        for (int j = 0; j < str.length; j++) {
	        	if(!str[j].trim().isEmpty()) {
	        		List<String> list = new ArrayList<String>();
	        		String[] rows=str[j].split("\\s+");
	        		for (int k = 0; k < rows.length; k++) {
	        			list.add(rows[k]);
					}
	        		data.add(list);
	        	}
			}
	    }
//		out.flush();
//		out.close();
	    reader.close();
	    return data;
		   
	}
	
	/**
	 * pdf转word
	 * @param name1
	 * @throws IOException
	 */
	public  static void importPDF3(String name1) throws IOException {
		PDDocument doc=PDDocument.load(new File(name1));
        int pagenumber=doc.getNumberOfPages();

        name1 = name1.substring(0, name1.lastIndexOf("."));
//      String dirName = "D:\\pdf\\";// 创建目录D:\\pdf\\a.doc
        String dirName = name1;// 创建目录D:\\pdf\\a.doc
        //createDir(dirName);// 调用方法创建目录
        String fileName = dirName + ".doc";// 创建文件
        File file=new File(fileName);
        FileOutputStream fos=new FileOutputStream(fileName);
        Writer writer=new OutputStreamWriter(fos,"UTF-8");
        PDFTextStripper stripper=new PDFTextStripper();

//      doc.addSignature(arg0, arg1, arg2);

        stripper.setSortByPosition(true);//排序
        //stripper.setWordSeparator("");//pdfbox对中文默认是用空格分隔每一个字，通过这个语句消除空格（视频是这么说的）
        stripper.setStartPage(1);//设置转换的开始页
        stripper.setEndPage(pagenumber);//设置转换的结束页
        stripper.writeText(doc,writer);
        writer.close();
        doc.close();
        System.out.println("pdf转换word成功！");
	}

	
}
