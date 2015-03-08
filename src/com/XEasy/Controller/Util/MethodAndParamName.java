package com.XEasy.Controller.Util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MethodAndParamName
{
	/**
	 * �����˷��� �뷽���ڲ�����������������
	 */
	private static Map<Method, Map<Class<?>, String>> ParamType_name = new HashMap<Method, Map<Class<?>,String>>();
	/**
	 * ���淽��method�뷽������������ͣ����������Ĺ�ϵ
	 * @param method		����ķ���
	 * @param Type_name		���������ͣ����������Ĺ�ϵ
	 */
	public static void put(Method method,Map<Class<?>, String> Type_name)
	{
		ParamType_name.put(method, Type_name);
	}
	/**
	 * ͨ������method��ȡ��������������ͣ����������Ĺ�ϵ
	 * @param method	����
	 * @return			���������ͣ����������Ĺ�ϵ
	 */
	public static Map<Class<?>, String> GetTpye_Name(Method method)
	{
		return ParamType_name.get(method);
	}
}
