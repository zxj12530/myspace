package com.myspace.controller;

import java.io.IOException;




import java.sql.Connection;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.XEasy.Annotation.URIMapping;
import com.XEasy.Annotation.URIMapping.Method;
import com.XEasy.Database.DBUtil;
import com.XEasy.Util.DateUtil;
import com.XEasy.Util.IPUtils;

public class HuodongController {
	@URIMapping(uri="/huodong/guaguaka",Method=Method.GET)
	public void huodongView(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		response.sendRedirect(request.getContextPath().toString()+"/guaguaka.html");
	}
	
	@URIMapping(uri="/huodong/guaguaka/chou",Method=Method.POST)
	public void guaguaka(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		String phone_no = request.getParameter("phone_no");
		String ip = IPUtils.getClientIpAddr(request);
		
		Connection con = null;
		try {
			con = DBUtil.getConnection(request);
			if(con == null){
				System.out.println("error for getDBConnection ");
			}
			String usql = "insert into m_guaguaka values (0,?,?,?);";
			boolean r = DBUtil.execute(con, usql,new String[]{phone_no,ip,DateUtil.getTodayDateTime()});
			
			if(r){
				Random random = new Random();
				int value = random.nextInt(100);
				
				response.getWriter().write("{\"value\" : \""+value+"\" ,\"isSuc\" : "+true+"}");
			}else{
				response.getWriter().write("{\"isSuc\" : "+false+"}");
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}finally{
			DBUtil.Close(con);
		}
	}
}
