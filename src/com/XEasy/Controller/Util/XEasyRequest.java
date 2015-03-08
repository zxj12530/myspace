package com.XEasy.Controller.Util;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.XEasy.Controller.CenterController;


public class XEasyRequest
{
	public static HttpServletRequest GetMyRequest(final HttpServletRequest request,final FilterChain chain)
	{
		return new HttpServletRequest()
		{
			
			@Override
			public void setCharacterEncoding(String encode)throws UnsupportedEncodingException
			{
				request.setCharacterEncoding(encode);
			}
			
			@Override
			public void setAttribute(String key, Object value)
			{
				request.setAttribute(key, value);
			}
			
			@Override
			public void removeAttribute(String attributeName)
			{
				request.removeAttribute(attributeName);
			}
			
			@Override
			public boolean isSecure()
			{
				return request.isSecure();
			}
			
			@Override
			public int getServerPort()
			{
				return request.getServerPort();
			}
			
			@Override
			public String getServerName()
			{
				return request.getServerName();
			}
			
			@Override
			public String getScheme()
			{
				return request.getScheme();
			}
			//ǿ�иı�request��ת������
			@Override
			public RequestDispatcher getRequestDispatcher(final String uri)
			{
				return new RequestDispatcher()
				{
					@Override
					public void include(ServletRequest request, ServletResponse response)
							throws ServletException, IOException
					{
						request.getRequestDispatcher(uri).include(request, response);
					}
					@Override
					public void forward(ServletRequest request, ServletResponse response)
							throws ServletException, IOException
					{
						CenterController.Control(uri, (HttpServletRequest)request, (HttpServletResponse)response, chain);
					}
				};
			}
			
			@Override
			public int getRemotePort()
			{
				return request.getRemotePort();
			}
			
			@Override
			public String getRemoteHost()
			{
				return request.getRemoteHost();
			}
			
			@Override
			public String getRemoteAddr()
			{
				return request.getRemoteAddr();
			}
			
			@Override
			public String getRealPath(String arg0)
			{
				return request.getRealPath(arg0);
			}
			
			@Override
			public BufferedReader getReader() throws IOException
			{
				return request.getReader();
			}
			
			@Override
			public String getProtocol()
			{
				return request.getProtocol();
			}
			
			@Override
			public String[] getParameterValues(String arg0)
			{
				return request.getParameterValues(arg0);
			}
			
			@Override
			public Enumeration getParameterNames()
			{
				return request.getParameterNames();
			}
			
			@Override
			public Map getParameterMap()
			{
				return request.getParameterMap();
			}
			
			@Override
			public String getParameter(String arg0)
			{
				return request.getParameter(arg0);
			}
			
			@Override
			public Enumeration getLocales()
			{
				return request.getLocales();
			}
			
			@Override
			public Locale getLocale()
			{
				return request.getLocale();
			}
			
			@Override
			public int getLocalPort()
			{
				return request.getLocalPort();
			}
			
			@Override
			public String getLocalName()
			{
				return request.getLocalName();
			}
			
			@Override
			public String getLocalAddr()
			{
				return request.getLocalAddr();
			}
			
			@Override
			public ServletInputStream getInputStream() throws IOException
			{
				return request.getInputStream();
			}
			
			@Override
			public String getContentType()
			{
				return request.getContentType();
			}
			
			@Override
			public int getContentLength()
			{
				return request.getContentLength();
			}
			
			@Override
			public String getCharacterEncoding()
			{
				return request.getCharacterEncoding();
			}
			
			@Override
			public Enumeration getAttributeNames()
			{
				return request.getAttributeNames();
			}
			
			@Override
			public Object getAttribute(String arg0)
			{
				return request.getAttribute(arg0);
			}
			
			@Override
			public boolean isUserInRole(String arg0)
			{
				return request.isUserInRole(arg0);
			}
			
			@Override
			public boolean isRequestedSessionIdValid()
			{
				return request.isRequestedSessionIdValid();
			}
			
			@Override
			public boolean isRequestedSessionIdFromUrl()
			{
				return request.isRequestedSessionIdFromUrl();
			}
			
			@Override
			public boolean isRequestedSessionIdFromURL()
			{
				return request.isRequestedSessionIdFromURL();
			}
			
			@Override
			public boolean isRequestedSessionIdFromCookie()
			{
				return request.isRequestedSessionIdFromCookie();
			}
			
			@Override
			public Principal getUserPrincipal()
			{
				return request.getUserPrincipal();
			}
			
			@Override
			public HttpSession getSession(boolean arg0)
			{
				return request.getSession(arg0);
			}
			
			@Override
			public HttpSession getSession()
			{
				return request.getSession();
			}
			
			@Override
			public String getServletPath()
			{
				return request.getServletPath();
			}
			
			@Override
			public String getRequestedSessionId()
			{
				return request.getRequestedSessionId();
			}
			
			@Override
			public StringBuffer getRequestURL()
			{
				return request.getRequestURL();
			}
			
			@Override
			public String getRequestURI()
			{
				return request.getRequestURI();
			}
			
			@Override
			public String getRemoteUser()
			{
				return request.getRemoteUser();
			}
			
			@Override
			public String getQueryString()
			{
				return request.getQueryString();
			}
			
			@Override
			public String getPathTranslated()
			{
				return request.getPathTranslated();
			}
			
			@Override
			public String getPathInfo()
			{
				return request.getPathInfo();
			}
			
			@Override
			public String getMethod()
			{
				return request.getMethod();
			}
			
			@Override
			public int getIntHeader(String arg0)
			{
				return request.getIntHeader(arg0);
			}
			
			@Override
			public Enumeration getHeaders(String arg0)
			{
				return request.getHeaders(arg0);
			}
			
			@Override
			public Enumeration getHeaderNames()
			{
				return request.getHeaderNames();
			}
			
			@Override
			public String getHeader(String arg0)
			{
				return request.getHeader(arg0);
			}
			
			@Override
			public long getDateHeader(String arg0)
			{
				return request.getDateHeader(arg0);
			}
			
			@Override
			public Cookie[] getCookies()
			{
				return request.getCookies();
			}
			
			@Override
			public String getContextPath()
			{
				return request.getContextPath();
			}
			
			@Override
			public String getAuthType()
			{
				return request.getAuthType();
			}

			@Override
			public AsyncContext getAsyncContext() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public DispatcherType getDispatcherType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ServletContext getServletContext() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isAsyncStarted() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isAsyncSupported() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public AsyncContext startAsync() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public AsyncContext startAsync(ServletRequest arg0,
					ServletResponse arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean authenticate(HttpServletResponse arg0)
					throws IOException, ServletException {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Part getPart(String arg0) throws IOException,
					IllegalStateException, ServletException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Collection<Part> getParts() throws IOException,
					IllegalStateException, ServletException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void login(String arg0, String arg1) throws ServletException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void logout() throws ServletException {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
