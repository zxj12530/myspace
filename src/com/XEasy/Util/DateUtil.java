package com.XEasy.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ����ʱ��Ĺ�����
 * @author xcode.xiao
 *
 */
public class DateUtil
{
	private static Date date = null;
	private static SimpleDateFormat simpleDateFormat = null;
	/**
	 * ��ȡ��ǰ���
	 * @return
	 */
	public static String getYear()
	{
		return format("yyyy");
	}
	/**
	 * ��ȡ��ǰ�·�
	 * @return
	 */
	public static String getMouth()
	{
		return format("MM");
	}
	/**
	 * ��ȡ��ǰ���ڱ��µĵڼ���
	 * @return
	 */
	public static String getDay()
	{
		return format("dd");
	}
	/**
	 * ��ȡ��ǰСʱ��
	 * @return
	 */
	public static String getHour()
	{
		return format("HH");
	}
	/**
	 * ��ȡ��ǰʱ�������
	 * @return
	 */
	public static String getMinute()
	{
		return format("mm");
	}
	/**
	 * ��ȡ��ǰ����
	 * @return
	 */
	public static String getSecend()
	{
		return format("ss");
	}
	
	/**
	 * ��ȡ��ǰ���ڣ���-��-�� ʱ:��:�룩
	 * @return 2014-08-26 23:57:52 �������͵�
	 */
	public static String getTodayDateTime()
	{
		return format("yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * ��ȡ��ǰ���ڣ���-��-�գ�
	 * @return 2014-08-26�������͵�
	 */
	public static String getTodayDate()
	{
		return format("yyyy-MM-dd");
	}
	/**
	 * ��ȡ��ǰʱ�䣨ʱ:��:�룩
	 * @return 23:57:52 �������͵�
	 */
	public static String getTodayTime()
	{
		return format("HH:mm:ss");
	}
	/**
	 * ����ת���ľ�̬����
	 * @param	G  Era ��־��  Text  AD  
	 * @param	y  ��  Year  1996; 96  
	 * @param	M  ���е��·�  Month  July; Jul; 07  
	 * @param	w  ���е�����  Number  27  
	 * @param	W  �·��е�����  Number  2  
	 * @param	D  ���е�����  Number  189  
	 * @param	d  �·��е�����  Number  10  
	 * @param	F  �·��е�����  Number  2  
	 * @param	E  �����е�����  Text  Tuesday; Tue  
	 * @param	a  Am/pm ���  Text  PM  
	 * @param	H  һ���е�Сʱ��0-23��  Number  0  
	 * @param	k  һ���е�Сʱ��1-24��  Number  24  
	 * @param	K  am/pm �е�Сʱ��0-11��  Number  0  
	 * @param	h  am/pm �е�Сʱ��1-12��  Number  12  
	 * @param	m  Сʱ�еķ�����  Number  30  
	 * @param	s  �����е�����  Number  55  
	 * @param	S  ������  Number  978  
	 * @param	z  ʱ��  General time zone  Pacific Standard Time; PST; GMT-08:00  
	 * @param	Z  ʱ��  RFC 822 time zone  -0800
	 * @param Ҫת���Ĺ���
	 * @return
	 */
	public static String format(String formatString)
	{
		date = new Date();
		simpleDateFormat = new SimpleDateFormat(formatString);
		return simpleDateFormat.format(date);
	}
	
	public static String format(String formatString,Date date)
	{
		simpleDateFormat = new SimpleDateFormat(formatString);
		return simpleDateFormat.format(date);
	}
	
}
