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
			//����request�����еĲ������Ͳ���ֵ
			@SuppressWarnings("unchecked")
			Map<String, String[]> parameters = request.getParameterMap();
			//����һ��Bean�����ж���setGet������
			Method method[] = Class.forName(ClassNameStr).getDeclaredMethods();
			Object object = Class.forName(ClassNameStr).newInstance();//Ҫ���صĶ���ʵ��
			for (Method method2:method)
			{
				String methodStr = method2.toString();
				String methodName = (methodStr.substring((methodStr.substring(0,methodStr.indexOf("("))).lastIndexOf(".")+1));
				if (methodName.startsWith("set"))//��ȡ���еĶ�Set������
				{
					//��ȡ��������Ĳ�������
					//������ȫ�ƣ�"+methodName;
					String pramerType = (methodName.substring(methodName.indexOf("(")+1,methodName.indexOf(")")));
					//��Ҫ�������ͣ�"+pramerType;
					String afterSet = ((methodName.substring(0,methodName.indexOf("("))).substring((methodName.substring(0,methodName.indexOf("("))).indexOf("set") + 3));
					//Set֮��:"+afterSet;
					String tolow = (afterSet.replace(afterSet.substring(0, 1), afterSet.substring(0, 1).toLowerCase()));
					//����ĸ��Сд��"+tolow;
					if (parameters != null && (parameters.get(afterSet)!=null || parameters.get(tolow)!=null))//���Name=Name
					{
						String pars[] = (parameters.get(afterSet) == null)?parameters.get(tolow):parameters.get(afterSet);
						Object parObject[] = null;
						
						if (pars.length == 1)//�����������
						{
							parObject = PageTurn.StringGetObj(pramerType, pars);
							
						}else //��������������
						{
							parObject = PageTurn.StringGetObjs(pramerType, pars);
						}
						
						if (parObject != null)//���Ҫע��Ĳ������ǿյĻ������ǿ���ע�룬��ִ��������������������
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
