package com.myspace.controller;

import java.io.IOException;



import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.XEasy.Annotation.URIMapping;
import com.XEasy.Annotation.URIMapping.Method;
import com.XEasy.Database.DBUtil;

public class MainPageController {
	@URIMapping(uri="",Method=Method.GET)
	public String mainView(HttpServletRequest request,HttpServletResponse response)
	{
		return "index0.html";
	}
	
	@URIMapping(uri="/getpvuv",Method=Method.GET)
	public void getpvuv(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
//		System.out.println(CookieUtil.logincookiecheck(request));
		
		int pv = 0;
		int uv = 0;
		Connection con = null;
		try {
			con = DBUtil.getConnection(request);
			if(con == null){
				System.out.println("error for getDBConnection ");
			}
			String sql = "select count(1) as c from m_log_info;";
			ResultSet rs = DBUtil.executeQury(con, sql);
			if(rs.next()){
				pv = rs.getInt("c");
			}
			
			String usql = "select count(1) as uc from (select log_ip from m_log_info group by log_ip) a;";
			ResultSet urs = DBUtil.executeQury(con, usql);
			if(urs.next()){
				uv = urs.getInt("uc");
			}
			
			response.getWriter().write("{\"uv\" : \""+uv+"\" ,\"pv\" : \""+pv+"\" , \"isSuc\" : "+true+"}");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}finally{
			DBUtil.Close(con);
		}
	}
}
