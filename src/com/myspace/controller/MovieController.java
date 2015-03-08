package com.myspace.controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.XEasy.Annotation.URIMapping;
import com.XEasy.Annotation.URIMapping.Method;
import com.XEasy.Database.DBUtil;
import com.XEasy.Util.CookieUtil;
import com.XEasy.Util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.myspace.Entiy.MoviceVO;
import com.myspace.content.Content;

public class MovieController {
	
	@URIMapping(uri="/movie0",Method=Method.GET)
	public void movie0(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		boolean islog = CookieUtil.logincookiecheck(request);
		if(islog){
			response.getWriter().write("{\"isSuc\" : true , \"movieurl\" : \""+Content.movie0+"\" }");
		}else{
			response.getWriter().write("{\"isSuc\" : false}");
		}
	}
	
	
	
	@URIMapping(uri="/movie1",Method=Method.GET)
	public void movie1(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		boolean islog = CookieUtil.logincookiecheck(request);
		if(islog){
			response.getWriter().write("{\"isSuc\" : true , \"movieurl\" : \""+Content.movie1+"\" }");
		}else{
			response.getWriter().write("{\"isSuc\" : false}");
		}
	}
	
	
	@URIMapping(uri="/more_movie0",Method=Method.GET)
	public void moremovie0(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		response.getWriter().write("{\"isSuc\" : true , \"agr\" : \"more_movie1\" }");
		
	}
	
	@URIMapping(uri="/more_movie1",Method=Method.GET)
	public void moremovie1(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		List<Object> volist = new ArrayList<Object>();
		
		Connection con = null;
		try {
			con = DBUtil.getConnection(request);
			if(con == null){
				System.out.println("error for getDBConnection ");
			}
			String sql = "select * from m_movice_info order by movice_time desc limit 10;";
			ResultSet rs = DBUtil.executeQury(con, sql);
			while(rs.next()){
				MoviceVO vo = new MoviceVO();
				vo.setMovice_time(DateUtil.format("yyyy-MM-dd",rs.getDate("movice_time")));
				vo.setMovice_title(rs.getString("movice_title"));
				vo.setMovice_message(rs.getString("movice_message"));
				vo.setMovice_url(rs.getString("movice_url"));
				volist.add(vo);
			}
			
			
			
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("{\"isSuc\" : true , \"agr\" : "+JSON.toJSONString(volist)+" }");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}finally{
			DBUtil.Close(con);
		}
	}
	
}
