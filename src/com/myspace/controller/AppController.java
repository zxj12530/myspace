package com.myspace.controller;

import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.XEasy.Annotation.URIMapping;
import com.XEasy.Annotation.URIMapping.Method;
import com.XEasy.Database.DBUtil;
import com.XEasy.Util.CookieUtil;
import com.XEasy.Util.DESUtil;
import com.XEasy.Util.DateUtil;
import com.XEasy.Util.IPUtils;
import com.alibaba.fastjson.JSON;
import com.myspace.Entiy.MoviceVO;
import com.myspace.Entiy.TuCao;
import com.myspace.Entiy.User;
import com.myspace.content.Content;
import com.myspace.manage.CacheManager;

public class AppController {

	@URIMapping(uri="/to_app",Method=Method.GET)
	public void tosign(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		response.sendRedirect(request.getContextPath().toString()+"/gamebuting.html");
	}
	@URIMapping(uri="/to_news",Method=Method.GET)
	public void tonews(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		response.sendRedirect(request.getContextPath().toString()+"/news.html");
	}
	@URIMapping(uri="/to_newshelp",Method=Method.GET)
	public void tonewshelp(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		response.sendRedirect(request.getContextPath().toString()+"/newshelp.html");
	}
	@URIMapping(uri="/to_tucao",Method=Method.GET)
	public void totucao(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		response.sendRedirect(request.getContextPath().toString()+"/tucao.html");
	}
	@URIMapping(uri="/tucao",Method=Method.GET)
	public void tucao(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		//获取所有用户
		List<User> users =  new ArrayList<User>();
		Connection con = null;
		try {
			con = DBUtil.getConnection(request);
			if(con == null){
				System.out.println("error for getDBConnection ");
			}
			String sql = "select * from m_user;";
			ResultSet rs = DBUtil.executeQury(con, sql);
			while(rs.next()){
				User user = new User();
				user.setLog_email(rs.getString("log_email"));
				user.setIs_Online("true");
				users.add(user);
			}
			
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("{\"isSuc\" : true , \"agr\" : "+JSON.toJSONString(users)+" }");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}finally{
			DBUtil.Close(con);
		}

	}
}
