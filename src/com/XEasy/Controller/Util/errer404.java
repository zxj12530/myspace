package com.XEasy.Controller.Util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class errer404
{
	public static String get404Code(String url)
	{
		return "<html>"
				 +"<head> "
				 +"<title>XEasy - Error report</title> "
				 +"<style>"
					+"H1 {"
					+"font-family:Tahoma,Arial,sans-serif; "
					+"color:white; "
							+"background-color:#525D76; "
							+"font-size:22px; "
							+"}  "
					+"H2 { "
							+"font-family:Tahoma,Arial,sans-serif; "
							+"color:white; "
							+"background-color:#525D76; "
							+"font-size:16px; "
							+"}  "
					+"H3 { "
							+"font-family:Tahoma,Arial,sans-serif; "
							+"color:white; "
							+"background-color:#525D76;font-size:14px; "
							+"}  "
					+"BODY{ "
							+"font-family:Tahoma,Arial,sans-serif; "
							+"color:black; "
							+"background-color:white; "
							+"}  "
					+"B  	{ "
							+"font-family:Tahoma,Arial,sans-serif; "
							+"color:white; "
							+"background-color:#525D76;	 "
							+"}  "
					+"P 	{ "
							+"font-family:Tahoma,Arial,sans-serif; "
							+"background:white; "
							+"color:black; "
							+"font-size:12px; "
							+"} "
					+"A 	{ "
							+"color : black; "
							+"} "
					+"A.name  "
						+"{ "
							+"color : black; "
							+"} "
					+"HR 	{ "
							+"color : #525D76; "
							+"} "
					+"</style>  "
				 +"</head>  "
				 +"<body>  "
				  +"<h1>HTTP Status 404 - "+url+"</h1>  "
				  +"<hr size=\"1\" noshade=\"noshade\" />  "
				  +"<p><b>type</b> Status report</p>  "
				  +"<p><b>message</b> <u>"+url+"</u></p>  "
				  +"<p><b>description</b> <u>The requested resource is not available.</u></p>  "
				  +"<hr size=\"1\" noshade=\"noshade\" />  "
				  +"<h3>XEasy.0.1</h3>   "
				 +"</body> "
				+"</html>";
	}
	public static void show404(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			response.getOutputStream().write((get404Code(request.getRequestURI())).getBytes());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
