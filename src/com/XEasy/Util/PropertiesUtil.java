package com.XEasy.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	/**
	 * 读取配置文件
	 */
	public static Properties readProperties(String url){
	     try {
	    	 Properties p = new Properties();   
		     File redisProperties = new File(url);
		     InputStream orderInputStream = new FileInputStream(redisProperties);
		     p.load(orderInputStream);
		     return p;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	     return null;
	}
}
