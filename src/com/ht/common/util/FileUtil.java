package com.ht.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 系统文件工具类
 * 
 * @author wyw
 * @version 1.0 2015/07/10
 */
public class FileUtil {
	
	public final static String ROOT_PATH = getRootPath();
		
	/**
	 * 文件是否存在
	 * 
	 * @param path
	 *            文件位置
	 * @return 是否存在
	 */
	public static Boolean exists(String path) {
		// 声明文件
		File file = new File(path);
		// 返回是否存在
		return file.exists();
	}

	/**
	 * 创建文件夹
	 */
	public static void CreateFolder(String folderPath) {
		// 如果文件夹不存在
		if (!exists(folderPath)) {
			// 生明文件夹
			File dir = new File(folderPath);
			// 如果不存在
			if (!dir.exists()) {
				// 创建文件夹
				dir.mkdirs();
			}
		}
	}

	/**
	 * 创建文件
	 * 
	 * @param filePath
	 *            文件地址
	 * @throws IOException
	 */
	public static void CreateFile(String filePath) throws IOException {
		// 如果文件不存在
		if (!exists(filePath)) {
			// 声明文件
			File file = new File(filePath);
			// 创建文件
			file.createNewFile();
		}
	}
	/**
	 * 删除文件
	 * @param filePath
	 * @throws IOException
	 */
	public static void DeletFile(String filePath) throws IOException {
		// 如果文件不存在
		if (exists(filePath)) {
			// 声明文件
			File file = new File(filePath);
			// 创建文件
			file.delete();
		}
	}
	
	/**
	 * 删除文件夹及文件夹中的内容
	 * @param file
	 */
	public static void DeletFile(File file){
       if(file.isDirectory()){
          File[] files = file.listFiles();
	           for(int i=0; i<files.length; i++){
	        	   DeletFile(files[i]);
           }
	    }
	      file.delete();
	 }

	/**
     * 压缩整个文件夹中的所有文件，生成指定名称的zip压缩包
     * @param filepath 文件所在目录
     * @param zippath 压缩后zip文件名称
     * @param dirFlag zip文件中第一层是否包含一级目录，true包含；false没有
     */
    public static void zip(String filepath ,String zippath, boolean dirFlag) {
        try {
            File file = new File(filepath);// 要被压缩的文件夹
            File zipFile = new File(zippath);
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for(File fileSec:files){
                    if(dirFlag){
                        recursionZip(zipOut, fileSec, file.getName() + File.separator);
                    }else{
                        recursionZip(zipOut, fileSec, "");
                    }
                }
            }
            zipOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 压缩整个文件夹中的所有文件，生成指定名称的zip压缩包,可导出到页面。
     * @param filepath 文件所在目录
     * @param fop 文件输出流
     * @param dirFlag zip文件中第一层是否包含一级目录，true包含；false没有
     */
    public static void zip(String filepath ,OutputStream fop, boolean dirFlag) {
    	try {
    		File file = new File(filepath);// 要被压缩的文件夹
    		ZipOutputStream zipOut = new ZipOutputStream(fop);
    		if(file.isDirectory()){
    			File[] files = file.listFiles();
    			for(File fileSec:files){
    				if(dirFlag){
    					recursionZip(zipOut, fileSec, file.getName() + File.separator);
    				}else{
    					recursionZip(zipOut, fileSec, "");
    				}
    			}
    		}
    		zipOut.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private static void recursionZip(ZipOutputStream zipOut, File file, String baseDir) throws Exception{
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File fileSec:files){
                recursionZip(zipOut, fileSec, baseDir + file.getName() + File.separator);
            }
        }else{
            byte[] buf = new byte[1024];
            InputStream input = new FileInputStream(file);
            zipOut.putNextEntry(new ZipEntry(baseDir + file.getName()));
            int len;
            while((len = input.read(buf)) != -1){
                zipOut.write(buf, 0, len);
            }
            input.close();
        }
    }
    
    /**
	 * 取得文件MD5码
	 * 
	 * @param is
	 *            输入流
	 * @return 文件MD5码
	 */
	public static String getFileMD5(InputStream is) {

		// 声明文件MD5码
		String md5 = null;

		try {

			// 取得文件MD5码
			md5 = DigestUtils.md5Hex(is);
		}
		// 发生输入输出异常
		catch (IOException e) {

			// 打印错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
		}

		// 返回文件MD5码
		return md5;
	}

	/**
	 * 取得文件MD5码
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 文件MD5码
	 */
	public static String getFileMD5(String filePath) {

		// 声明文件MD5码
		String md5 = null;

		try {

			// 取得文件MD5码
			md5 = DigestUtils.md5Hex(new FileInputStream(new File(filePath)));
		}
		// 发生输入输出异常
		catch (IOException e) {

			// 打印错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
		}
		// 返回文件MD5码
		return md5;
	}
	
	/**
	 * 匹配RootPath，以\结尾再进行截取，否则不截取
	 * @return
	 */
	public static String getRootPath(){
		String tempRootPath=System.getProperty("ht.root");
		Pattern p=Pattern.compile(".*\\\\$");
		Matcher m=p.matcher(tempRootPath);
		if(m.matches()){
			tempRootPath= tempRootPath.substring(0, System.getProperty("ht.root").length() - 1);
		}
		return tempRootPath;
	}

	public static void write(String sourcePath, String string)  {
		try {
			// InputStream is = new FileInputStream(sourcePath);
			 FileOutputStream os =new FileOutputStream(string);
			 InputStream is = new FileInputStream(sourcePath);
			 byte[] b = new byte[1024];
				int size = is.read(b);
				while(size>0){
					os.write(b,0,size);
					size = is.read(b);
				}
				os.flush();
				is.close();
				os.close();
		} catch (Exception e) {
			
		}
		
		
	}
}
