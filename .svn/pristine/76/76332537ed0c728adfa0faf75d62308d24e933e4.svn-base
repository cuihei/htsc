package com.ht.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class Download {
	
	/**
	 * 文件存放目录 
	 */
	public static  final String PATH = FileUtil.ROOT_PATH+File.separator+"ht"
			+File.separator+"resource"+File.separator+"files"+File.separator;
	
	/**
	 * 下载文件
	 * 注:此方法针对/ht/resource/file目录
	 * @param response
	 * @param fileName 文件名称
	 * @throws IOException
	 */
	public static void download (HttpServletResponse response, String fileName) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment; filename="+new String(fileName.getBytes("gb2312"), "ISO8859-1")+".xls");  
		// 获取相应文件的流
        File file = new File(PATH + fileName+".xls");
		//设置文件长度
		response.setHeader("Content-Length", (int)file.length()+"");
        //IO流复制
        InputStream inputStream = new FileInputStream(file);
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[2048];
        int length;
        while ((length = inputStream.read(b)) > 0) {
            os.write(b, 0, length);
        }
        // 释放资源 
        os.close();
        inputStream.close();
	}
}
