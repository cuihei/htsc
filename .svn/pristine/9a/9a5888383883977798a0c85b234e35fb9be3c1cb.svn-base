package com.ht.common.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * doc转pdf
 * @author dou
 *
 */
public class File2PdfUtil {
	public static void main(String[] args) throws IOException {
		//test();
		test1();
		//Blob blob = Hibernate.createBlob(fileInputStream);
	}
	
	/**
	 * 测试
	 */
	public static void test (){
		String fs1 = "D:\\text\\17.doc";
		String fd1 = "D:\\text\\out.pdf";
		convert(fs1, fd1);
	}
	
	/**
	 * 测试
	 * @throws IOException 
	 */
	public static void test1 () throws IOException{
		try {
			FileInputStream fileInputStream = new FileInputStream("D:\\text\\17.doc");
			convert(fileInputStream, "D:\\text\\19.doc", "D:\\text\\19.pdf");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 方法描述：将文件转为pdf。需要开启OpenOffice服务。
	 * 在windows中开启服务命令如下：<br/>
	 *  cd C:\Program Files (x86)\OpenOffice.org 3\program
	 *  soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard
	 * 
	 * @param input office文件的绝对路径
	 * @param output 生成后文件的绝对路径
	 */
	public static void convert(String input, String output){   
        File inputFile = new File(input);
        File outputFile = new File(output);   
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);   
        try {   
            connection.connect();   
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);   
            converter.convert(inputFile, outputFile);   
        } catch(Exception e) {   
            e.printStackTrace();   
        } finally {   
            try{ if(connection != null){connection.disconnect(); connection = null;}}catch(Exception e){}   
        }   
    }
	/**
	 * 方法描述：将文件转为pdf。需要开启OpenOffice服务。
	 * 在windows中开启服务命令如下：<br/>  openoffice 3的开启方式
	 *  cd C:\Program Files (x86)\OpenOffice.org 3\program
	 *  soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard
	 * 在windows中开启服务命令如下：<br/>  openoffice 4的开启方式
	 * cd C:\Program Files (x86)\OpenOffice 4\program
	 * soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard
	 * @param input office文件的绝对路径
	 * @param output 生成后文件的绝对路径
	 */
	public static void convert(File inputFile,File outputFile){   
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);   
		try {   
			connection.connect();   
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);   
			converter.convert(inputFile, outputFile);   
		} catch(Exception e) {   
			e.printStackTrace();   
		} finally {   
			try{ if(connection != null){connection.disconnect(); connection = null;}}catch(Exception e){}   
		}   
	}
	
	/**
	 * @param inputStream 读取到的word文件流,InputStream读的必须是word文件
	 * @param input 将word文件存放的地址 例 ： D:\\1.doc
	 * @param output 将pdf文件存放的地址 例 ：D:\\1.pdf
	 */
	public static void convert(InputStream inputStream,String input, String output){   
        File inputFile = new File(input);
        File outputFile = new File(output);
        //将读取的word流写入File文件
        streamSaveAsFile(inputStream, inputFile);
        //下面是转pfd的操作
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);   
        try {   
            connection.connect();
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(connection != null){connection.disconnect(); connection = null;}}catch(Exception e){}
        }   
    }
	
	/**
	 * 把字节数组保存为一个文件
	 * @param b
	 * @param outputFile 文件路径
	 * @return
	 */
	public static File getFileFromBytes(byte[] b, String outputFile) {
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return file;
	}
	
	/**
	 * 将流另存为文件
	 * @param is
	 * @param outfile
	 */
	public static void streamSaveAsFile(InputStream is, File outfile) {
		FileOutputStream fos = null;
		try {
			File file = outfile;
			fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				is.close();
				fos.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
	}
	
	
	
	/**
	 * Read an input stream into a string
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String streamToString(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}
	
	/**
	 * Read an input stream into a byte[]
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static byte[] stream2Byte(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		byte[] b = new byte[1024];
		while ((len = is.read(b, 0, b.length)) != -1) {
			baos.write(b, 0, len);
		}
		byte[] buffer = baos.toByteArray();
		return buffer;
	}
	
	
	/**
	 * @方法功能 byte 转为 InputStream
	 * @param 字节数组
	 * @return InputStream
	 * @throws Exception
	 */
	public static InputStream byte2InputStream(byte[] b) throws Exception {
		InputStream is = new ByteArrayInputStream(b);
		return is;
	}
	
	/**
	 * @方法功能 blob 转为 InputStream
	 * @param blob
	 * @return
	 * @throws Exception
	 */
	public static InputStream blob2InputStream(Blob blob) throws Exception {
		InputStream is = new ByteArrayInputStream(BlobUtil.blobToBytes(blob));
		return is;
	}
	public static void toPDF(InputStream inputStream,String sourcePath, String targetpath) {  
		File inputfile=new File(sourcePath);
		//将读取的word流写入File文件
        streamSaveAsFile(inputStream, inputfile);
        if(sourcePath.endsWith(".txt")) {  
            //先保存为.odt  
            StringBuffer odtPath = new StringBuffer(sourcePath.substring(0 , sourcePath.lastIndexOf(".")));  
            odtPath.append(".odt");  
  
            FileUtil.write(sourcePath, odtPath.toString());  
            sourcePath = odtPath.toString();  
        }  
        OfficeManager officeManager = new DefaultOfficeManagerConfiguration().setOfficeHome("C:\\Program Files (x86)\\OpenOffice 4")  
        .buildOfficeManager();  
        officeManager.start();  
  
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);  
        converter.convert(new File(sourcePath), new File(targetpath));  
              
        officeManager.stop();  
	 }  
	public static void startServer() {  
        String cmd = "cmd /k soffice -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";  
        try {  
            Runtime.getRuntime().exec(cmd , null , new File("C:\\Program Files (x86)\\OpenOffice 4\\program"));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
