package com.XEasy.Util;

/**
 * @author MyObj 对对象的操作工具类
 */
public class ObjectUtil
{
	/**
	 * 判断对象是否为NULL
	 * @param object 要判断的对象
	 * @return true：此对象为空对象
	 * @return false：对象不为空对象
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
	 * 判断一个对象数组里是否至少有一个对象是空对象
	 * @param objects	可传入任意数量，任意对象的参数 
	 * @return	true	传入的参数中，至少有一个是空对象
	 * @return	false	传入的参数中，没有空对象
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
	 * 判断传入的参数中，是否全是空对象
	 * @param objects	可传入任意数量，任意对象的参数 
	 * @return true		传入的参数中，全部是空对象
	 * @return false	传入的参数中，至少有一个不是空对象
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
