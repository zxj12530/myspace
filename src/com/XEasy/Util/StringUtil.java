package com.XEasy.Util;

/**
 * 
 * @author StringUtil �ַ����Ĺ�����
 * 
 */
public class StringUtil
{
	/**
	 * �жϴ��������ַ����Ƿ�Ϊ���ַ���
	 * 
	 * @param string
	 *            Ҫ�жϵ��ַ���
	 * @return true ���ַ���Ϊ���ַ���
	 * @return false ���ַ������ǿ��ַ���
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
	 * �жϴ��ݹ������ַ��������У��Ƿ��п��ַ���
	 * 
	 * @param strings
	 *            ���ݹ������ַ�������
	 * @return true ���ݹ������ַ��������У�������һ���ǿ��ַ���
	 * @return false ���ݹ������ַ��������У�û�п��ַ���
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
	 * �жϴ��ݹ������ַ��������У��Ƿ�ȫ���ǿ��ַ���
	 * 
	 * @param strings
	 *            ���ݹ������ַ�������
	 * @return true ���ݹ������ַ��������У�ȫ�����ǿ��ַ���
	 * @return false ���ݹ������ַ��������У�������һ�����ǿ��ַ���
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
	 * �ж��ַ����Ƿ��ֻ�����
	 * 
	 * @param phoneNum
	 *            ����Ĳ���������һ���ֻ�����ʱ�����ô˷���
	 * @return ���ƥ����ȷ��return true , else return else
	 */
	// ��������������ֻ����룬����ֻ������������ƥ��
	public static boolean isPhoneNumber(String phoneNum)
	{
		// �ֻ�����ƥ����
		return RegexTool.BaseRegex(phoneNum, "1[358]\\d{9}");
	}

	/**
	 * �ж��ַ����Ƿ���������ʽ
	 * @param email
	 *            ����Ĳ���������һ�������ַʱ�����ô˷���
	 * @return ���ƥ����ȷ��return true , else return false
	 */
	// ������������������ַ����������������ƥ��
	public static boolean isEmail(String email)
	{
		return RegexTool.BaseRegex(email, "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
	}
	
	/**
	 * ƥ�������ַ���������ʽ
	 * @param ChineseWord
	 * @return ��ȷ return true else return false
	 */
	public static boolean isChinese(String ChineseWord)
	{
		return RegexTool.BaseRegex(ChineseWord, "[\u4e00-\u9fa5]");
	}
	
	/**
	 * �ж��ַ����Ƿ�URL����
	 * @param url	Ҫ�жϵ������ַ���
	 * @return		
	 */
	public static boolean isURL(String url)
	{
		// ����ƥ����
		return RegexTool.BaseRegex(url, "[a-zA-z]+://[^\\s]*");
	}
	/**
	 * �жϴ������ַ����Ƿ���һ��IP��ַ
	 * @param ip	Ҫ�жϵ��ַ���
	 * @return		true=��IP��ַ
	 */
	public static boolean isIP(String ip)
	{
		// ����ƥ����
		return RegexTool.BaseRegex(ip, "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
	}
	
	
}
class RegexTool
{
	/**
	 * @param string	Ҫƥ����ַ���
	 * @param regex		Ҫƥ��Ĺ���
	 * @return			���Ҫƥ����ַ���ΪNULL �򷵻�false 
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