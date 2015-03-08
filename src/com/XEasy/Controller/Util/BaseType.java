package com.XEasy.Controller.Util;

public class BaseType
{
	public static boolean isBaseType(String NeedType)
	{
		String [] strings = new String[]
		{
				StaticString.Int,
				StaticString.IntArray,
				StaticString.Boolean,
				StaticString.BooleanArray,
				StaticString.Byte,
				StaticString.ByteArray,
				StaticString.Char,
				StaticString.CharArray,
				StaticString.Double,
				StaticString.DoubleArray,
				StaticString.Float,
				StaticString.FloatArray,
				StaticString.Long,
				StaticString.LongArray,
				StaticString.Short,
				StaticString.ShortArray,
				StaticString.String,
				StaticString.StringArray
		};
		return (GetBaseTypeValue(NeedType,strings) == null)?false:true;
		
	}
	
	public static String GetBaseTypeValue(String needtype,String ...strings)
	{
		if (needtype == null || needtype.isEmpty())
		{
			return null;
		}
		if (strings == null)
		{
			return null;
		}
		for (String string : strings)
		{
			if (needtype.equals(string))
			{
				return string;
			}
		}
		return null;
	}
}
