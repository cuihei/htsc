package com.ht.action.system.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.ht.action.base.BaseAction;

@SuppressWarnings("serial")
public class FileToolAction extends BaseAction {
	
	//上传的文件,struts2会把文件封装为File对象
    private File[] files;
    //文件名,struts2会把文件名称设置到该变量
    private String[] filesFileName;
    //文件类型,struts2会把文件类型设置到该变量
    private String[] filesContentType;
    
	public File[] getFiles()
	{
		return files;
	}

	public void setFiles(File[] files)
	{
		this.files = files;
	}

	public String[] getFilesFileName()
	{
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName)
	{
		this.filesFileName = filesFileName;
	}

	public String[] getFilesContentType()
	{
		return filesContentType;
	}

	public void setFilesContentType(String[] filesContentType)
	{
		this.filesContentType = filesContentType;
	}

	/**
	 * 初始化人员数据页面，返回成功列表页面
	 * */
	public String init() {
		return SUCCESS;
	}
  
    /** 
     * Description: 向FTP服务器上传文件 
     * @Version      1.0 
     * @param url FTP服务器hostname 
     * @param port  FTP服务器端口 
     * @param username FTP登录账号 
     * @param password  FTP登录密码 
     * @param path  FTP服务器保存目录 
     * @param filename  上传到FTP服务器上的文件名 
     * @param input   输入流 
     * @return 成功返回true，否则返回false * 
     */  
    public static boolean uploadFile(String url,// FTP服务器hostname  
            int port,// FTP服务器端口  
            String username, // FTP登录账号  
            String password, // FTP登录密码  
            String path, // FTP服务器保存目录  
            String filename, // 上传到FTP服务器上的文件名  
            InputStream input // 输入流  
    ){  
        boolean success = false;  
        FTPClient ftp = new FTPClient();  
        ftp.setControlEncoding("GBK");  
        try {  
            int reply;  
            ftp.connect(url, port);// 连接FTP服务器  
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
            ftp.login(username, password);// 登录  
            reply = ftp.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
                ftp.disconnect();  
                return success;  
            }  
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  
            ftp.makeDirectory(path);  
            ftp.changeWorkingDirectory(path);  
            ftp.storeFile(filename, input);  
            input.close();  
            ftp.logout();  
            success = true;  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (ftp.isConnected()) {  
                try {  
                    ftp.disconnect();  
                } catch (IOException ioe) {  
                }  
            }  
        }  
        return success;  
    }  
  
    /** 
     * 将本地文件上传到FTP服务器上 * 
     */  
    public static void upLoadFromProduction(String url,// FTP服务器hostname  
            int port,// FTP服务器端口  
            String username, // FTP登录账号  
            String password, // FTP登录密码  
            String path, // FTP服务器保存目录  
            String filename, // 上传到FTP服务器上的文件名  
            File file // 输入流文件名  
       ) {  
        try {
        	
            FileInputStream in = new FileInputStream(file);  
            boolean flag = uploadFile(url, port, username, password, path,filename, in);  
            System.out.println(flag);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    
    /**
     * 上传文件至ftp服务器
     * */
    public String upload() throws Exception{
        try
		{
        	if(files!=null){
        		for(int i=0;i<files.length;i++){
    			    upLoadFromProduction("192.168.43.244", 21, "dzy", "Dao1234", "/u", filesFileName[i], files[i]);  
    			}
        	}
			writeSuccessResult();
		}
		catch (Exception e)
		{
			writeFailResult(e.getMessage());
		}
        return SUCCESS;
	}
}