package com.XEasy.Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
	/**
	 * cookie检查用户是否登录过
	 * @param request
	 * @return
	 */
	public static boolean logincookiecheck(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();

        String username = "";

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie c = cookies[i];

                if (c.getName().equalsIgnoreCase("xabpoUsername")) {
                    username = c.getValue();
                }
            }
        }
        
        
        if(!"".equals(username)){
        	return true;
        }
        
        return false;
	}
	
	
	/**
	 * 获取当前登录cookie
	 * @param request
	 * @return
	 */
	public static String getlogincookie(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();

        String username = "";

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie c = cookies[i];

                if (c.getName().equalsIgnoreCase("xabpoUsername")) {
                    username = c.getValue();
                }
            }
        }
        
        return username;
	}
}
