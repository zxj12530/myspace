package com.XEasy.Controller.Util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MethodAndParamName
{
	/**
	 * 保存了方法 与方法内参数的类型与类型名
	 */
	private static Map<Method, Map<Class<?>, String>> ParamType_name = new HashMap<Method, Map<Class<?>,String>>();
	/**
	 * 保存方法method与方法间参数（类型，参数名）的关系
	 * @param method		保存的方法
	 * @param Type_name		参数（类型，参数名）的关系
	 */
	public static void put(Method method,Map<Class<?>, String> Type_name)
	{
		ParamType_name.put(method, Type_name);
	}
	/**
	 * 通过方法method获取方法间参数（类型，参数名）的关系
	 * @param method	方法
	 * @return			参数（类型，参数名）的关系
	 */
	public static Map<Class<?>, String> GetTpye_Name(Method method)
	{
		return ParamType_name.get(method);
	}
}
