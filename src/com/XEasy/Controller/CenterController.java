package com.XEasy.Controller;

import java.io.File;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.XEasy.Annotation.URIMapping;
import com.XEasy.Controller.Util.PackageUtil;
import com.XEasy.Controller.Util.Request2Method;
import com.XEasy.Controller.Util.StaticString;
import com.XEasy.Controller.Util.URL2Method;
import com.XEasy.Controller.Util.XEasyRequest;
import com.XEasy.Controller.Util.errer404;
import com.XEasy.Util.DateUtil;
import com.XEasy.Util.IPUtils;
import com.myspace.Entiy.LoginVO;
import com.myspace.content.Content;
import com.myspace.manage.CacheManager;
import com.myspace.manage.ThreadDeleteUserCache;
import com.myspace.manage.ThreadSaveLog;

public class CenterController implements Filter
{
	@Override
	public void destroy()
	{
	}
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		//1.解析用户访问的链接URI
		String uri = (request.getRequestURI()).substring(request.getContextPath().length());
		Control(uri, request, response, chain);
		saveLoginInfo(uri, request);
		
	}
	
	/**
	 * 中心处理器
	 * 通过解析传进来的字符串，对资源进行分配
	 * @param uri			传进来的字符串
	 * @param request		此次访问需要的Request
	 * @param response		负责响应的Response
	 * @param chain			负责允许通过的守门员
	 */
	public static void Control(String uri,HttpServletRequest request,HttpServletResponse response,FilterChain chain)
	{
//		System.out.println("请求路径："+uri);
		String methodType = request.getMethod().toUpperCase();
//		System.out.println("请求方式："+methodType);
		/**
		 * 判断用户传过来的链接是否被浏览器加上斜杠。
		 * 如果有。则直接拿uri去静态库里去寻找相应的方法，
		 * 如果没有，则自动加上斜杠，再去静态库里面去寻找相应的方法
		 */
		if (uri.equals(""))
		{
			uri = "/";
		}else if (!uri.equals("/"))
		{
			/**
			 * 再判断uri前面是否有斜杠 以及后面是否有斜杠
			 */
			if (!uri.endsWith("/"))
			{
				uri = uri+"/";
			}
			
			if (!uri.startsWith("/"))
			{
				uri = "/"+uri;
			}
		}
		
		//2.通过Uri找到Uri所指向的保存的方法与方法相应的Class
		Map<String, Object> uriInfMap = URL2Method.getMethod(uri);
		//请求的链接："+uri;
		if (uriInfMap != null)//如果找到了URI对应的保存的数据
		{
			String methodtype = (String) uriInfMap.get(StaticString.MethodType);
//			System.out.println("从注释库中获取相应的："+methodtype);
			if (!methodtype.equals(methodType) && !methodtype.equals(StaticString.DEFAULT))//如果用户提交的方式和设置的提交方式不一样。则爆出404异常
			{
				errer404.show404(request, response);
				return ;
			}
			Method method = (Method) uriInfMap.get(StaticString.Method);
			Class<?> clazz = (Class<?>) uriInfMap.get(StaticString.Class);
			try
			{
				Class<?> types[] = method.getParameterTypes();//获取所有参数类型
				Object object[] = new Object[types.length];
				for (int i = 0; i < types.length; i++)
				{
					String string = types[i].getName();
					if (string.equals(StaticString.Response))//方法中，需要Response对象参数
					{
						object[i] = response;
					}else if (string.equals(StaticString.Request)) //方法中，需要Request对象参数
					{
						object[i] = XEasyRequest.GetMyRequest(request,chain);
						
					}else if (string.equals(StaticString.Session)) //方法中，需要Session对象参数
					{
						object[i] = request.getSession();
					}else if (string.equals(StaticString.ServletContext)) //方法中，需要ServletContext对象参数
					{
						object[i] = request.getSession().getServletContext();
					}
//					else if (BaseType.isBaseType(string)) //如果是定义的是基本对象类型
//					{
//						object[i] = null;
//					}
					else //如果需要的是其他对象,则框架会自动认为，是用户传过来的参数被封装成一个对象,然后根据参数名称自动注入
					{
						object[i] = Request2Method.GetParameterObject(request, string);
					}
				}
				/**
				 * 执行注入操作 
				 * 执行注释链接所对应的方法 并 
				 * 有返回值 如果返回字符串，
				 * 则是需要转发 或者 跳转的JSP文件
				 * 如果没有找到对应的JSP文件。
				 * 则是链接
				 */
				Object returnMsg = method.invoke(clazz.newInstance(), object);
				if(returnMsg instanceof String)//如果返回的是字符串，则根据这个字符串内容去找相应的JSP文件
				{
					//返回的字符串是："+returnMsg;
					//1.在返回的字符串后面添加.jsp或者.JSP看看能不能查得到 这个jsp文件
					File file = new File(request.getSession().getServletContext().getRealPath(returnMsg+".jsp"));
					if (!file.exists())
					{
						file = null;
						Control((String)returnMsg, request, response, chain);//迭代
						return;
					}
					request.getRequestDispatcher(returnMsg+".jsp").forward(request, response);
					
					//2.如果没有找到这个JSP文件。则转向注释链接
				}
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}else if (uri.contains(".") && !uri.toUpperCase().contains(".JSP")) //对静态文件进行访问
		{
			try
			{
				chain.doFilter(request, response);
			} catch (Exception e)
			{
				e.printStackTrace();
			} 
		}else//如果没有找到对应的方法，则显示404没有找到异常
		{
			errer404.show404(request, response);
		}
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException
	{
		String packageNames = config.getServletContext().getInitParameter(StaticString.AnnotationPackage);
		
		
		List<Class<?>> list = PackageUtil.getClasses(packageNames);
		
		for (Class<?> cl : list)
		{
			for (Method method : cl.getDeclaredMethods())
			{
				URIMapping annotation = method.getAnnotation(URIMapping.class);
				
				if (annotation!=null)
				{
					/**
					 * 监听请求方式
					 */
					String tempUri = annotation.uri().trim();
					/**
					 * 先判断是不是空字符串或者斜杠
					 */
					if (tempUri.equals(""))
					{
						tempUri = "/";
					}else if (!tempUri.equals("/"))
					{
						/**
						 * 再判断uri前面是否有斜杠 以及后面是否有斜杠
						 */
						if (!tempUri.endsWith("/"))
						{
							tempUri = tempUri+"/";
						}
						
						if (!tempUri.startsWith("/"))
						{
							tempUri = "/"+tempUri;
						}
					}
					URL2Method.put(tempUri, method, cl,annotation.Method().toString());
				}
			}
		}
		
		
		String classname = config.getServletContext().getInitParameter("classname");
		String url = config.getServletContext().getInitParameter("url");
		String username = config.getServletContext().getInitParameter("username");
		String password = config.getServletContext().getInitParameter("password");
	    System.out.println("init......timer......insert login info to db,pre half hour.");
		Timer logtimer = new Timer(); 
		logtimer.schedule(new ThreadSaveLog(classname, url, username, password), 20000,1800*1000);
		
	    System.out.println("init......timer......delete data  from cache,pre 24 hour.");
	    long oneDay = 24 * 60 * 60 * 1000;  
        long initDelay  = getTimeMillis("00:01:00") - System.currentTimeMillis();  
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;
		Timer deletetimer = new Timer(); 
		deletetimer.schedule(new ThreadDeleteUserCache(), initDelay,oneDay);
		
		
	}
	
	 private static long getTimeMillis(String time) {  
	        try {  
	            DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");  
	            DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");  
	            Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);  
	            return curDate.getTime();  
	        } catch (Exception e) {  
	            System.out.println(e);
	        }  
	        return 0;  
	    } 
	 
	 private void saveLoginInfo(String uri,HttpServletRequest request){
		 String endStr = "";
		 if(uri.lastIndexOf(".") < 0){
			 endStr = uri;
		 }else{
			 endStr = uri.substring(uri.lastIndexOf("."),uri.length());
		 }
			try{
				if((Content.unImportStr).indexOf(endStr)==-1){
					
					String ip = IPUtils.getClientIpAddr(request);
					String now = DateUtil.getTodayDateTime();
					System.out.println("IP:"+ip+",time:"+now+",url:"+uri);
					//记录缓存
					LoginVO loginVo = new LoginVO();
					loginVo.setLog_ip(ip);
					loginVo.setLog_time(now);
					loginVo.setLog_url(uri);
					CacheManager.loginInfo.add(loginVo);
				}
			}catch(Exception e){
				System.out.println(e);
			}
	 }
}
