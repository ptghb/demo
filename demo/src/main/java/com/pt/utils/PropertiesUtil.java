package com.pt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 属性文件管理
 * @author Administrator
 *
 */
public class PropertiesUtil {
	
	/**
	 * 读取属性文件
	 * @param path 文件路径
	 * @return
	 * @throws IOException
	 */
	public static Properties read(String path) throws IOException {
		Properties pro = new Properties();
		FileInputStream in = new FileInputStream(path);
		pro.load(in);
		in.close();
		return pro;
	}
	
	/**
	 * 写入属性文件
	 * @param path 文件路径
	 * @param comment 第一行注释说明
	 * @return
	 * @throws IOException
	 */
	public static Properties write(String path,String comment) throws IOException {
		Properties pro = new Properties();
		File file = new File(path);
		FileOutputStream oFile = new FileOutputStream(file);
		pro.store(oFile, comment);
		oFile.close();
		return pro;
	}
}
