package com.XEasy.Util;

/**
 * @author MyObj �Զ���Ĳ���������
 */
public class ObjectUtil
{
	/**
	 * �ж϶����Ƿ�ΪNULL
	 * @param object Ҫ�жϵĶ���
	 * @return true���˶���Ϊ�ն���
	 * @return false������Ϊ�ն���
	 */
	public static boolean isNull(Object object)
	{
		if (object == null)
		{
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * �ж�һ�������������Ƿ�������һ�������ǿն���
	 * @param objects	�ɴ��������������������Ĳ��� 
	 * @return	true	����Ĳ����У�������һ���ǿն���
	 * @return	false	����Ĳ����У�û�пն���
	 */
	public static boolean hasNull(Object...objects)
	{
		if (objects == null)
		{
			return true;
		}
		for (Object obj : objects)
		{
			if (obj == null)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �жϴ���Ĳ����У��Ƿ�ȫ�ǿն���
	 * @param objects	�ɴ��������������������Ĳ��� 
	 * @return true		����Ĳ����У�ȫ���ǿն���
	 * @return false	����Ĳ����У�������һ�����ǿն���
	 */
	public static boolean allNull(Object...objects)
	{
		if (objects == null)
		{
			return true;
		}
		for (Object object : objects)
		{
			if (object != null)
			{
				return false;
			}
		}
		return true;
	}
	
}
