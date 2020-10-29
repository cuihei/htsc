package com.ht.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class PoiUtil {

	
	
	/** 内部使用的文档对象 **/
	public static CustomXWPFDocument document;

	private static BookMarks bookMarks = null;
	
	//存放表格更新后的单元格内容
	public static Map<String, Object> param = new HashMap<String, Object>();
	
	
	/**
	 * 为文档设置模板
	 * 
	 * @param templatePath
	 *            模板文件名称
	 */
	public static void setTemplate(String templatePath) {
		try {
			document = new CustomXWPFDocument(
					POIXMLDocument.openPackage(templatePath));
			bookMarks = new BookMarks(document);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 为文档设置模板
	 * @param templatePath  模板文件名称
	 */
	public static void setTemplate(InputStream in) {
		try {
			document = new CustomXWPFDocument(in);
			
			bookMarks = new BookMarks(document);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	/**
	 * 为文档设置模板
	 * @param templatePath  模板文件名称
	 */
	public static void setTemplate(CustomXWPFDocument doc) {
		document =doc;
		
		bookMarks = new BookMarks(document);		
	}
	public static List<String> getBookMarkStrings(){
		Collection<BookMark> bookmarkList = bookMarks.getBookmarkList();
		List<String> list=new ArrayList<String>();
		for (BookMark bookMark : bookmarkList) {
			if(bookMark.getBookmarkName().contains("table")){
				list.add(bookMark.getBookmarkName());
			}
		}
		return list;
	}
	  /** 
     *  
     * @param imgUrl 图片地址 
     * @return  
     */  
    public static BufferedImage getBufferedImage(String imgUrl) {  
        URL url = null;  
        InputStream is = null;  
        BufferedImage img = null;  
        try {  
//            url = new URL(imgUrl);  
//            is = url.openStream();  
            img = ImageIO.read(new File(imgUrl));  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        return img;  
    }  
	/**
	 * 给Word文件替换普通文本，非表格部分
	 * @param maps 要替换的Map文本值
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static Map<String, Object> replaceTitle(Map<String, String> maps) throws FileNotFoundException{
		Set<Entry<String, String>> entrySet = maps.entrySet();
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
			@SuppressWarnings("unchecked")
			Entry<String,String> entry = (Entry<String, String>) iterator.next();
			if(entry.getKey().startsWith("img")){
				String imageUrl=entry.getValue();
				 BufferedImage image=getBufferedImage(imageUrl);  
			        if (image!=null)  
			        {  
			        	Map<String, Object> header = new HashMap<String, Object>();
						header.put("width", image.getWidth());
						header.put("height", image.getHeight());
						header.put("type",entry.getValue().split("\\.")[1]);
//						String imgPath = imgPaths.get(index);
						//"d:\\text\\1.jpg"
						header.put("content", WordUtil.inputStream2ByteArray(
								new FileInputStream(imageUrl), true));
						param.put(entry.getKey(), header);
			        }  
				
			}else{
				param.put(entry.getKey(), entry.getValue());
			}
		}
		return param;
	}

	
	
	/**
	 * 
	 * @param bookmarkMap
	 * @param bookMarkName
	 * @param imgPaths
	 * @param filePath word文档的路径
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Map<String, Object> replaceText(Map<String, String> bookmarkMap,
			String bookMarkName) throws FileNotFoundException{
		// 首先得到标签
		BookMark bookMark = bookMarks.getBookmark(bookMarkName);
		// 获得书签标记的表格
		XWPFTable table = bookMark.getContainerTable();
		// 获得所有的表
		if (table != null) {
			// 得到该表的所有行
			int rcount = table.getNumberOfRows();
			for (int i = 0; i < rcount; i++) {
				XWPFTableRow row = table.getRow(i);
				// 获到改行的所有单元格
				List<XWPFTableCell> cells = row.getTableCells();
				
				for (XWPFTableCell c : cells) {
					for (Entry<String, String> e : bookmarkMap.entrySet()) {
						if (c.getText().contains(e.getKey())
								&& !c.getText().startsWith("img")) {
							
							param.put(c.getText(), e.getValue());
							
						} else if (c.getText().contains(e.getKey())
								&& c.getText().startsWith("img")) {
							 String imageUrl=e.getValue();
							 BufferedImage image=getBufferedImage(imageUrl);  
						        if (image!=null)  
						        {  
						        	Map<String, Object> header = new HashMap<String, Object>();
									header.put("width", image.getWidth());
									header.put("height", image.getHeight());
									header.put("type",e.getValue().split("\\.")[1]);
//									String imgPath = imgPaths.get(index);
									//"d:\\text\\1.jpg"
									header.put("content", WordUtil.inputStream2ByteArray(
											new FileInputStream(imageUrl), true));
									param.put(c.getText(), header);
						        }  
						} else {

						}
					}
				}	
			}
		}
		return param;
	}
	
	
	
	/**
	 * 
	 * @param bookmarkMap
	 * @param bookMarkName
	 * @param imgPaths
	 * @param filePath word文档的路径
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Map<String, Object> replaceText(Map<String, Object> bookmarkMap,
			String bookMarkName,List<String> imgPaths,String filePath) throws FileNotFoundException{
		if (bookMarks==null) {
			setTemplate(filePath);
		}
		// 首先得到标签
		BookMark bookMark = bookMarks.getBookmark(bookMarkName);
		// 获得书签标记的表格
		XWPFTable table = bookMark.getContainerTable();
		//用来获取图片
		int index = 0;
		// 获得所有的表
		if (table != null) {
			// 得到该表的所有行
			int rcount = table.getNumberOfRows();
			for (int i = 0; i < rcount; i++) {
				XWPFTableRow row = table.getRow(i);
				// 获到改行的所有单元格
				List<XWPFTableCell> cells = row.getTableCells();
				
				for (XWPFTableCell c : cells) {
					for (Entry<String, Object> e : bookmarkMap.entrySet()) {
						if (c.getText().contains(e.getKey())
								&& !c.getText().startsWith("img")) {
							
							param.put(c.getText(), e.getValue());
							
						} else if (c.getText().contains(e.getKey())
								&& c.getText().startsWith("img")) {
							Map<String, Object> header = new HashMap<String, Object>();
							header.put("width", 100);
							header.put("height", 150);
							header.put("type", "jpg");
							String imgPath = imgPaths.get(index);
							index++;
							//"d:\\text\\1.jpg"
							header.put("content", WordUtil.inputStream2ByteArray(
									new FileInputStream(imgPath), true));
							param.put(c.getText(), header);
						} else {

						}
					}
				}	
			}
		}
		return param;
	}
	
	
	
	/**
	 * 
	 * @param filePath word文件的路径  例如 ：d:\\text\\Word模版.docx
	 * @param newFilePath 生成word文件的路径  例如 ：d:\\text\\1.docx
	 * @param tableParameter 要替换表格新值得map集合  键为要替换的单元格文本，值为要替换的新值
	 * @param imgPaths   存放插入表格图片的地址
	 * @throws IOException
	 */
	public static void updateTable (String filePath,String newFilePath,
			Map<String, Object> tableParameter) throws IOException{
		CustomXWPFDocument doc = WordUtil.generateWord(tableParameter,filePath);
		FileOutputStream fopts = new FileOutputStream(newFilePath);
		doc.write(fopts);
		fopts.close();
	}
	
	
	/**
	 * 
	 * @param filePath word文件的路径  例如 ：d:\\text\\Word模版.docx
	 * @param newFilePath 生成word文件的路径  例如 ：d:\\text\\1.docx
	 * @param tableParameter 要替换表格新值得map集合  键为要替换的单元格文本，值为要替换的新值
	 * @param imgPaths   存放插入表格图片的地址
	 * @throws IOException
	 */
	public static OutputStream  updateTable (CustomXWPFDocument in,OutputStream outputStream,
			Map<String, Object> tableParameter) throws IOException{
		CustomXWPFDocument doc = WordUtil.generateWord(tableParameter,in);
		doc.write(outputStream);
		return outputStream;
	}
	
	/**
	 * 释放资源
	 */
	public static void close() {
		if (document!=null) {
			document = null;
		}
		
		if (bookMarks!=null) {
			bookMarks = null;
		}
		
		if (param!=null) {
			param = new HashMap<String, Object>();
		}
		
		if (WordUtil.doc!=null) {
			WordUtil.doc = null;
		}
	}
	
	
	public static void test4() throws IOException {
		//word文件的路径
		String filePath = "c:\\Word模版.docx";
		//生成word文件的路径
		String newFilePath = "d:\\Word模版_REPLACE.docx";
		//要替换表格新值得map集合
//		Map<String, Object> tableParameter = new HashMap<String, Object>();
//		tableParameter.put("text_customer_xm", "jia");
//		tableParameter.put("text_customer_zy", "软件工程");
//		tableParameter.put("img_customer_tx", "");
//		//表格标签名称
//		String tableId = "table_customer";
//		
//		//替换Word的标头信息
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("MONTH", "jia");
		maps.put("TECH_CENTER", "软件技术");
		replaceTitle(maps);
//		
//		//存放插入表格图片的地址
//		List<String> imgPaths = new ArrayList<String>();
//		imgPaths.add("d:\\text\\1.jpg");
//		replaceText(tableParameter, "table_customer",imgPaths,filePath);
//		
//		//要替换表格新值得map集合
//		Map<String, Object> param1 = new HashMap<String, Object>();
//		param1.put("text_student_xm", "jia");
//		param1.put("text_student_xh", "1111");
//		replaceText(param1, "table_student",null,filePath);
//		
//		//要替换表格新值得map集合
//		Map<String, Object> param2 = new HashMap<String, Object>();
//		param2.put("text_test_test", "测试");
//		param2.put("text_test_test1", "测试1");
//		param2.put("text_test_test2", "测试2");
//		param2.put("text_test_test3", "测试3");
//		param2.put("text_test_test4", "测试4");
//		param2.put("text_test_test5", "测试5");
//		replaceText(param2, "table_test",null,filePath);
		
		//执行替换
		updateTable(filePath, newFilePath, PoiUtil.param);
		
		//释放资源
		close();
	}
	
	public static void main(String[] args) {
		try {
			test4();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
