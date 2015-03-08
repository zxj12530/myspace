package com.myspace.manage;

import java.util.TimerTask;
import com.XEasy.Util.DateUtil;

/**
 * 清理缓存数据异步线程  每日0点
 * @author lenovo
 *
 */
public class ThreadDeleteUserCache extends TimerTask{
	public ThreadDeleteUserCache(){
	}
	@Override
	public void run() {
		try {
			System.out.println(DateUtil.getTodayDateTime()+",ThreadDeleteUserCache执行...");
			System.out.println("--------------------------------------------------");
			System.out.println("signCache before="+CacheManager.signCache.size());
			CacheManager.signCache.clear();
			System.out.println("signCache after="+CacheManager.signCache.size());
			System.out.println("--------------------------------------------------");
			System.out.println("logCountCache before="+CacheManager.logCountCache.size());
			CacheManager.logCountCache.clear();
			System.out.println("logCountCache after="+CacheManager.logCountCache.size());
			System.out.println("--------------------------------------------------");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
}
