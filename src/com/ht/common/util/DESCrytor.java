package com.ht.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.http.fileupload.util.Streams;


public class DESCrytor{
	
    private String seed;

    public void setSeed(String seed) {
        this.seed = seed;
    }
    
    
    /**
     * 转换密钥
     * @param key
     * @return
     * @throws Exception
     */
    private Key getKey() throws Exception {
        byte[] key=null;
        key= new byte[16];
        System.arraycopy(seed.getBytes("UTF-8"), 0, key, 0, key.length);
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES"); 
        return skeySpec;
    }

    /**
     * 获取加密密钥
     * @return
     * @throws Exception
     */
    public Cipher getEncryptCipher() throws Exception{
    	Key k = getKey();
        byte[] key=null;
        key= new byte[16];
        System.arraycopy(seed.getBytes("UTF-8"), 0, key, 0, key.length);
        Cipher cipher;
        IvParameterSpec iv = new IvParameterSpec(key);
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, k,iv);  
        return cipher;
    }
   
    /**
     * 获取解密密钥
     * @return
     * @throws Exception
     */
    public Cipher getDecryptCipher() throws Exception{
    	Key k = getKey();
        byte[] key=null;
        key= new byte[16];
        System.arraycopy(seed.getBytes("UTF-8"), 0, key, 0, key.length);
        Cipher cipher;  
        IvParameterSpec iv = new IvParameterSpec(key);
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, k,iv);  
        return cipher;
    }
    
    /**
     * 对Zip进行解密
     * @param zipSourcePath
     * @param zipEncryptPath
     */
    public static void decryptZip(String zipEncryptPath,String zipDecryptPath,String seed){
    	DESCrytor des = new DESCrytor();
    	des.setSeed(seed);
    	FileInputStream in = null;  
		OutputStream out = null;  
		CipherOutputStream cout = null;
		try{
			in = new FileInputStream(zipEncryptPath);  
			out = new FileOutputStream(zipDecryptPath);  
			cout = new CipherOutputStream(out, des.getDecryptCipher());  
			int i;
			byte[] buffer = new byte[1024]; 
			while ((i = in.read(buffer)) != -1) {  
				cout.write(buffer, 0, i);  
			} 
			in.close();
			out.flush();
			cout.close();
		}
		catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
		}
    }
    
    
    /**
     * 对Zip进行加密
     * @param zipSourcePath
     * @param zipEncryptPath
     */
    public static void encryptZip(String zipSourcePath,String zipEncryptPath,String seed){
    	DESCrytor des = new DESCrytor();
    	des.setSeed(seed);
    	FileInputStream in = null;  
		OutputStream out = null;  
		CipherInputStream cin = null;
		try{
			in = new FileInputStream(zipSourcePath);  
			out = new FileOutputStream(zipEncryptPath);  
			cin = new CipherInputStream(in, des.getEncryptCipher());  
			int i;
			byte[] buffer = new byte[1024]; 
			while ((i = cin.read(buffer)) != -1) {  
				out.write(buffer, 0, i);  
			} 
			in.close();
			out.flush();
			cin.close();
		}
		catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
		}
    }
}
