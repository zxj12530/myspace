package com.myspace.controller;

import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.XEasy.Annotation.URIMapping;
import com.XEasy.Annotation.URIMapping.Method;
import com.XEasy.Database.DBUtil;
import com.XEasy.Util.DESUtil;
import com.XEasy.Util.DateUtil;
import com.XEasy.Util.IPUtils;
import com.myspace.content.Content;
import com.myspace.manage.CacheManager;

public class LogController {
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@URIMapping(uri="/sign",Method=Method.POST)
	public void sign(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		String sign_email = request.getParameter("sign_email");
		String sign_pssword = request.getParameter("sign_pssword1"); 
		String secret_no = request.getParameter("secret_no"); 
		
		String ip = IPUtils.getClientIpAddr(request);
		try {
			//检查
			if(CacheManager.signCache.get(ip) != null && CacheManager.signCache.get(ip) >= Content.SIGN_MAX){
				response.getWriter().write("{\"key\" : -1 , \"isSuc\" : false}");
			}else{
				//插入
				if(CacheManager.signCache.get(ip) == null){
					CacheManager.signCache.put(ip,1);
				}else{
					int count = CacheManager.signCache.get(ip);
					count = count+1;
					CacheManager.signCache.put(ip,count);
				}
				System.out.println(DateUtil.getTodayDateTime()+":"+ip+"注册"+sign_email+",次数"+CacheManager.signCache.get(ip));
				
				String rstring = signSave(request, sign_email, sign_pssword, secret_no, ip);
				response.getWriter().write(rstring);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	/**
	 * 转到注册页面
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@URIMapping(uri="/to_sign",Method=Method.GET)
	public void tosign(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		response.sendRedirect(request.getContextPath().toString()+"/sign.html");
	}
	
	/**
	 * 转到登录页面
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@URIMapping(uri="/to_login",Method=Method.GET)
	public void tologin(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		response.sendRedirect(request.getContextPath().toString()+"/login.html");
	}
	
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@URIMapping(uri="/login",Method=Method.POST)
	public void userlogin(HttpServletRequest request,HttpServletResponse response)
	{
		String login_email = request.getParameter("login_email");
		String login_password = request.getParameter("login_password"); 
		String ip = IPUtils.getClientIpAddr(request);
		
		try {
			//检查
			if(CacheManager.logCountCache.get(ip) != null && CacheManager.logCountCache.get(ip) >= Content.LOGIN_MAX){
				response.getWriter().write("{\"key\" : -1 , \"isSuc\" : false}");
			}else{
				//插入
				if(CacheManager.logCountCache.get(ip) == null){
					CacheManager.logCountCache.put(ip,1);
				}else{
					int count = CacheManager.logCountCache.get(ip);
					count = count+1;
					CacheManager.logCountCache.put(ip,count);
				}
//				是否是注册用户
				boolean issign = false;
				try {
					issign = checkSign(request, login_email,DESUtil.encrypt(login_password, DESUtil.KEY));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(issign){
					//放入cookie
			            Cookie cookieUsername = new Cookie("xabpoUsername",login_email);
			            cookieUsername.setMaxAge(2*60);
			            cookieUsername.setPath("/");
			            cookieUsername.setDomain(".itody.com"); // 设定有效域
			            response.addCookie(cookieUsername);
			            
					System.out.println(DateUtil.getTodayDateTime()+":"+ip+"登录"+login_email+",次数"+CacheManager.logCountCache.get(ip));
					response.getWriter().write("{\"isSuc\" : true}");
				}else{
					response.getWriter().write("{\"isSuc\" : false}");
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	
	
	
	
	
	
	/**
	 * 保存注册信息到db
	 * @param request
	 * @param sign_email
	 * @param sign_pssword
	 * @param secret_no
	 * @param ip
	 * @return
	 */
	private String signSave(HttpServletRequest request,String sign_email,String sign_pssword,String secret_no,String ip){
		Connection con = null;
		String message = "";
		try {
			con = DBUtil.getConnection(request);
			if(con == null){
				System.out.println("error for getDBConnection ");
			}
			
			String sql = "select 1 as c from m_user where log_email='"+sign_email+"';";
			ResultSet rs = DBUtil.executeQury(con, sql);
			boolean r = false;
			if(rs.next()){
				r = false;
			}else{
				String usql = "insert into m_user values (0,?,?,?,?,?);";
				r = DBUtil.execute(con, usql,new String[]{sign_email,DESUtil.encrypt(sign_pssword, DESUtil.KEY),secret_no,ip,DateUtil.getTodayDateTime()});
				if(r){
					System.out.println(ip+"保存成功");
				}
			}
			message = "{\"isSuc\" : "+r+"}";
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}finally{
			DBUtil.Close(con);
		}
		
		return message;
	}
	
	
	
	/**
	 * 检测是否是注册用户
	 * @param request
	 * @param sign_email
	 * @param sign_pssword
	 * @param secret_no
	 * @param ip
	 * @return
	 */
	private boolean checkSign(HttpServletRequest request,String email,String password){
		Connection con = null;
		boolean issign = false;
		try {
			con = DBUtil.getConnection(request);
			if(con == null){
				System.out.println("error for getDBConnection ");
			}
			String sql = "select 1 as c from m_user where log_email='"+email+"' and log_password= '"+password+"';";
			ResultSet rs = DBUtil.executeQury(con, sql);
			if(rs.next()){
				issign = true;
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}finally{
			DBUtil.Close(con);
		}
		return issign;
	}
	
}
