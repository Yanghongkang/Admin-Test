package cn.sh.yhk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Test_1 {

	public static void main(String[] args) throws DocumentException, FileNotFoundException {
		// TODO Auto-generated method stub
		// 1.新建document对象
		Document document = new Document();
		// 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
		// 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/AdminYHK/Desktop/test.pdf"));

		// 3.打开文档
		document.open();

		// 4.添加一个内容段落
		document.add(new Paragraph("Hello World!"));
		// 设置属性
		// 标题
		document.addTitle("this is a title");
		// 作者
		document.addAuthor("H__D");
		// 主题
		document.addSubject("this is subject");
		// 关键字
		document.addKeywords("Keywords");
		// 创建时间
		document.addCreationDate();
		// 应用程序
		document.addCreator("hd.com");
		// 5.关闭文档
		document.close();
	}

}
