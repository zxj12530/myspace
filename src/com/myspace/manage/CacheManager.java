package com.myspace.manage;

import java.util.ArrayList;
import java.util.Map;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.myspace.Entiy.LoginVO;

/**
 * 缓存管理
 * @author lenovo
 *
 */
public class CacheManager {

	/**
	 * 记录登录信息
	 */
	public static List<LoginVO> loginInfo = new ArrayList<LoginVO>();
	
	/**
	 * 记录单ip用户每天注册账户数据
	 */
	public static Map<String,Integer> signCache = new ConcurrentHashMap<String,Integer>();
	/**
	 * 记录单ip用户每天尝试登录的次数
	 */
	public static Map<String,Integer> logCountCache = new ConcurrentHashMap<String,Integer>();
	
	
}
