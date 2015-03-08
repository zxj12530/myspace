package com.XEasy.Controller.Util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//�ṩһ���ࡣ����ͨ���URI�����Ӧ��Method����
public class URL2Method
{
	private static Map<String, Map<String, Object>> map = new HashMap<String, Map<String,Object>>();
	/**
	 * ͨ��һ��uri��ȡ��һ��map��
	 * ���map�������������
	 * һ���Ƕ�Ӧ�ķ�����
	 * ��һ�������Ӧ��Class��
	 * @param uri	�����Uri
	 * @return		map&lt;method,Class&gt;
	 */
	public static Map<String, Object> getMethod(String uri)
	{
		return map.get(uri);
	}
	/**
	 * ������Ӧ����Ϣ
	 * @param uri		�����Uri
	 * @param method	Uri��Ӧ�ķ���
	 * @param cls		�������ڵ���
	 * @param MethodType		����ʽ
	 */
	@SuppressWarnings("rawtypes")
	public static void put(String uri,Method method,Class cls,String MethodType)
	{
		if (map.get(uri) != null)
		{
			System.err.println("\nWarning:there are two of the same link application:"+uri+" \nMethod:"+method);
		}
		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put(StaticString.Method, method);
		tempMap.put(StaticString.Class, cls);
		tempMap.put(StaticString.MethodType, MethodType);
		map.put(uri, tempMap);
	}
}
