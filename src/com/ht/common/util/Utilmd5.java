/**
 * 
 */
package com.ht.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类，提供字符串MD5加密（校验）、文件MD5值获取（校验）功能
 */
public class Utilmd5 {
	/**
	 * 16进制字符集
	 */
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 指定算法为MD5的MessageDigest
	 */
	private MessageDigest messageDigest = null;

	/**
	 * 初始化messageDigest的加密算法为MD5
	 */
	private Utilmd5() {
		try {
			this.messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static Utilmd5 New() {
		return new Utilmd5();
	}

	/**
	 * 获取文件的MD5值
	 * 
	 * @param file
	 *            目标文件
	 * @return MD5字符串
	 */
	public String getFileMD5String(File file) {
		String ret = "";
		FileInputStream in = null;
		FileChannel ch = null;

		try {
			in = new FileInputStream(file);
			byte[] buffer = new byte[2048];
			int length = -1;
			while ((length = in.read(buffer)) != -1) {
				messageDigest.update(buffer, 0, length);
			}
			ret = bytesToHex(messageDigest.digest()).substring(8, 24);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (ch != null) {
				try {
					ch.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	/**
	 * 获取文件的MD5值
	 * 
	 * @param fileName
	 *            目标文件的完整名称
	 * @return MD5字符串
	 */
	public String getFileMD5String(String fileName) {
		return getFileMD5String(new File(fileName));
	}

	/**
	 * MD5加密字符串
	 * 
	 * @param str
	 *            目标字符串
	 * @return MD5加密后的字符串
	 */
	public String getMD5String(String str) {
		return getMD5String(str.getBytes());
	}

	/**
	 * MD5加密以byte数组表示的字符串
	 * 
	 * @param bytes
	 *            目标byte数组
	 * @return MD5加密后的字符串
	 */
	public String getMD5String(byte[] bytes) {
		messageDigest.update(bytes);
		return bytesToHex(messageDigest.digest());
	}

	/**
	 * MD5加密以byte数组表示的字符串
	 * 
	 * @param bytes
	 *            目标byte数组
	 * @return MD5加密后的字符串
	 */
	public byte[] getMD5Bytes(byte[] bytes) {
		messageDigest.update(bytes);
		return messageDigest.digest();
	}

	/**
	 * 校验密码与其MD5是否一致
	 * 
	 * @param pwd
	 *            密码字符串
	 * @param md5
	 *            基准MD5值
	 * @return 检验结果
	 */
	public boolean checkPassword(String pwd, String md5) {
		return getMD5String(pwd).equalsIgnoreCase(md5);
	}

	/**
	 * 校验密码与其MD5是否一致
	 * 
	 * @param pwd
	 *            以字符数组表示的密码
	 * @param md5
	 *            基准MD5值
	 * @return 检验结果
	 */
	public boolean checkPassword(char[] pwd, String md5) {
		return checkPassword(new String(pwd), md5);
	}

	/**
	 * 检验文件的MD5值
	 * 
	 * @param file
	 *            目标文件
	 * @param md5
	 *            基准MD5值
	 * @return 检验结果
	 */
	public boolean checkFileMD5(File file, String md5) {
		return getFileMD5String(file).equalsIgnoreCase(md5);
	}

	/**
	 * 检验文件的MD5值
	 * 
	 * @param fileName
	 *            目标文件的完整名称
	 * @param md5
	 *            基准MD5值
	 * @return 检验结果
	 */
	public boolean checkFileMD5(String fileName, String md5) {
		return checkFileMD5(new File(fileName), md5);
	}

	/**
	 * 不关闭stream
	 * 
	 * @param in
	 * @return
	 */
	public String getStreamMD5String(InputStream in) {
		String ret = "";

		try {
			byte[] buffer = new byte[2048];
			int length = -1;
			while ((length = in.read(buffer)) != -1) {
				messageDigest.update(buffer, 0, length);
			}
			ret = bytesToHex(messageDigest.digest()).substring(8, 24);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			
		}

		return ret;
	}

	/**
	 * 将字节数组转换成16进制字符串
	 * 
	 * @param bytes
	 *            目标字节数组
	 * @return 转换结果
	 */
	public String bytesToHex(byte bytes[]) {
		return bytesToHex(bytes, 0, bytes.length);
	}

	/**
	 * 将字节数组中指定区间的子数组转换成16进制字符串
	 * 
	 * @param bytes
	 *            目标字节数组
	 * @param start
	 *            起始位置（包括该位置）
	 * @param end
	 *            结束位置（不包括该位置）
	 * @return 转换结果
	 */
	public String bytesToHex(byte bytes[], int start, int end) {
		StringBuffer sb = new StringBuffer();

		for (int i = start; i < start + end; i++) {
			sb.append(byteToHex(bytes[i]));
		}

		return sb.toString();
	}

	/**
	 * 将单个字节码转换成16进制字符串
	 * 
	 * @param bt
	 *            目标字节
	 * @return 转换结果
	 */
	public String byteToHex(byte bt) {
		return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];
	}

	/***
	 * MD5加码 生成32位md5码
	 */
	public String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}
}
