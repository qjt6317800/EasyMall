package com.EasyMall.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropUtils {
	private static Properties prop = new Properties();
	private PropUtils(){}
	static{
		//通过类加载器加载资源文件路径
		String path = PropUtils.class.getClassLoader()
				.getResource("conf.properties")
				.getPath();
		try {
			prop.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static Properties getProp(){
		return prop;
	}
	public static String getProperty(String key){
		return prop.getProperty(key);
	}
}
