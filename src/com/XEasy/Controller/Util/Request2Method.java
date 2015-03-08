package com.XEasy.Controller.Util;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Request2Method
{
	public static Object GetParameterObject(HttpServletRequest request,String ClassNameStr)
	{
		try
		{
			//解析request里所有的参数名和参数值
			@SuppressWarnings("unchecked")
			Map<String, String[]> parameters = request.getParameterMap();
			//解析一个Bean里面有多少setGet方法、
			Method method[] = Class.forName(ClassNameStr).getDeclaredMethods();
			Object object = Class.forName(ClassNameStr).newInstance();//要返回的对象实例
			for (Method method2:method)
			{
				String methodStr = method2.toString();
				String methodName = (methodStr.substring((methodStr.substring(0,methodStr.indexOf("("))).lastIndexOf(".")+1));
				if (methodName.startsWith("set"))//获取所有的额Set方法。
				{
					//获取方法里面的参数类型
					//方法名全称："+methodName;
					String pramerType = (methodName.substring(methodName.indexOf("(")+1,methodName.indexOf(")")));
					//需要参数类型："+pramerType;
					String afterSet = ((methodName.substring(0,methodName.indexOf("("))).substring((methodName.substring(0,methodName.indexOf("("))).indexOf("set") + 3));
					//Set之后:"+afterSet;
					String tolow = (afterSet.replace(afterSet.substring(0, 1), afterSet.substring(0, 1).toLowerCase()));
					//首字母降小写："+tolow;
					if (parameters != null && (parameters.get(afterSet)!=null || parameters.get(tolow)!=null))//如果Name=Name
					{
						String pars[] = (parameters.get(afterSet) == null)?parameters.get(tolow):parameters.get(afterSet);
						Object parObject[] = null;
						
						if (pars.length == 1)//如果不是数组
						{
							parObject = PageTurn.StringGetObj(pramerType, pars);
							
						}else //如果传入的是数组
						{
							parObject = PageTurn.StringGetObjs(pramerType, pars);
						}
						
						if (parObject != null)//如果要注入的参数不是空的话，就是可以注入，则执行这个方法，否则就跳过
						{
							method2.invoke(object, parObject);
						}
					}
				}
			}
			return object;
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
