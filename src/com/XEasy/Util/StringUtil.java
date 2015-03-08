package com.XEasy.Util;

/**
 * 
 * @author StringUtil 字符串的工具类
 * 
 */
public class StringUtil
{
	/**
	 * 判断传过来的字符串是否为空字符串
	 * 
	 * @param string
	 *            要判断的字符串
	 * @return true 此字符串为空字符串
	 * @return false 此字符串不是空字符串
	 */
	public static boolean isEmpty(String string)
	{
		if (string == null)
		{
			return true;
		}
		return string.isEmpty();
	}

	/**
	 * 判断传递过来的字符串数组中，是否含有空字符串
	 * 
	 * @param strings
	 *            传递过来的字符串数组
	 * @return true 传递过来的字符串数组中，至少有一个是空字符串
	 * @return false 传递过来的字符串数组中，没有空字符串
	 */
	public static boolean hasEmpty(String... strings)
	{
		if (strings == null)
		{
			return true;
		}
		if (ObjectUtil.hasNull((Object[])strings))
		{
			return true;
		}
		for (String string : strings)
		{
			if (string.isEmpty())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断传递过来的字符串数组中，是否全部是空字符串
	 * 
	 * @param strings
	 *            传递过来的字符串数组
	 * @return true 传递过来的字符串数组中，全部都是空字符串
	 * @return false 传递过来的字符串数组中，至少有一个不是空字符串
	 */
	public static boolean allEmpty(String... strings)
	{
		if (strings == null)
		{
			return true;
		}
		if (ObjectUtil.allNull((Object[])strings))
		{
			return true;
		}
		for (String string : strings)
		{
			if (string != null && !string.isEmpty())
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否手机号码
	 * 
	 * @param phoneNum
	 *            传入的参数仅仅是一个手机号码时，调用此方法
	 * @return 如果匹配正确，return true , else return else
	 */
	// 如果传进来的是手机号码，则对手机号码进行正则匹配
	public static boolean isPhoneNumber(String phoneNum)
	{
		// 手机号码匹配结果
		return RegexTool.BaseRegex(phoneNum, "1[358]\\d{9}");
	}

	/**
	 * 判断字符串是否符合邮箱格式
	 * @param email
	 *            传入的参数仅仅是一个邮箱地址时，调用此方法
	 * @return 如果匹配正确，return true , else return false
	 */
	// 如果传进来的是邮箱地址，则对邮箱进行正则匹配
	public static boolean isEmail(String email)
	{
		return RegexTool.BaseRegex(email, "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
	}
	
	/**
	 * 匹配中文字符的正则表达式
	 * @param ChineseWord
	 * @return 正确 return true else return false
	 */
	public static boolean isChinese(String ChineseWord)
	{
		return RegexTool.BaseRegex(ChineseWord, "[\u4e00-\u9fa5]");
	}
	
	/**
	 * 判断字符串是否URL链接
	 * @param url	要判断的链接字符串
	 * @return		
	 */
	public static boolean isURL(String url)
	{
		// 中文匹配结果
		return RegexTool.BaseRegex(url, "[a-zA-z]+://[^\\s]*");
	}
	/**
	 * 判断传来的字符串是否是一个IP地址
	 * @param ip	要判断的字符串
	 * @return		true=是IP地址
	 */
	public static boolean isIP(String ip)
	{
		// 中文匹配结果
		return RegexTool.BaseRegex(ip, "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
	}
	
	
}
class RegexTool
{
	/**
	 * @param string	要匹配的字符串
	 * @param regex		要匹配的规则
	 * @return			如果要匹配的字符串为NULL 则返回false 
	 */
	public static boolean BaseRegex(String string,String regex)
	{
		if (StringUtil.isEmpty(string))
		{
			return false;
		}
		return string.matches(regex);
	}
}