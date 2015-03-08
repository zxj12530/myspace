package com.XEasy.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
/**
 * @author xiaolei
 *	�������ݿ�Ĺ�����
 */
public class DBUtil extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private DBUtil()
	{
	}
	/**
	 * ��ȡ�������ݿ������
	 * ������web.xml�ж���
	 * @param &lt;context-param&gt;
	 * @param 		&lt;description&gt;���ݿ������������&lt;/description&gt;
	 * @param 		&lt;param-name&gt;classname&lt;/param-name&gt;
	 * @param 		&lt;param-value&gt;com.mysql.jdbc.Driver&lt;/param-value&gt;
	 * @param &lt;/context-param&gt;
	 * @param &lt;context-param&gt;
	 * @param 		&lt;description&gt;�������ݿ��URL&lt;/description&gt;
	 * @param 		&lt;param-name&gt;url&lt;/param-name&gt;
	 * @param 		&lt;param-value&gt;jdbc:mysql://127.0.0.1:3306/xcode&lt;/param-value&gt;
	 * @param &lt;/context-param&gt;
	 * @param &lt;context-param&gt;
	 * @param 		&lt;description&gt;���ݿ���û���&lt;/description&gt;
	 * @param 		&lt;param-name&gt;username&lt;/param-name&gt;
	 * @param 		&lt;param-value&gt;root&lt;/param-value&gt;
	 * @param &lt;/context-param&gt;
	 * @param &lt;context-param&gt;
	 * @param 		&lt;description&gt;���ݿ�����&lt;/description&gt;
	 * @param 		&lt;param-name&gt;password&lt;/param-name&gt;
	 * @param 		&lt;param-value&gt;root&lt;/param-value&gt;
	 * @param &lt;/context-param&gt;
	 * @return	�������ݿ�����ӵ�Connection
	 */
	public static Connection getConnection(HttpServletRequest request)
	{
		ServletContext servletContext = request.getSession().getServletContext();
			
		String classname = servletContext.getInitParameter("classname");
		
		String url = servletContext.getInitParameter("url");
		
		String username = servletContext.getInitParameter("username");
		
		String password = servletContext.getInitParameter("password");
		
		Connection connection = null;
		
		try
		{
			Class.forName(classname);
			
			connection = DriverManager.getConnection(url, username, password);
		
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public static Connection getConnection(String classname,String url,String username,String password)
	{
			
		Connection connection = null;
		
		try
		{
			Class.forName(classname);
			
			connection = DriverManager.getConnection(url, username, password);
		
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return connection;
	}
	
	/**
	 * ִ��SQL���ľ�̬��
	 * @param connection	�������ݿ��Connection
	 * @param sql			��Ҫִ�е�SQL���
	 * @return				��SQL����Ƿ�ִ�гɹ�
	 */
	public static boolean executeSQL(Connection connection,String sql)
	{
		try
		{
			java.sql.Statement statement = connection.createStatement();
			statement.execute(sql);
			return true;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ִ��SQL��䷵�صĻ�ȡ���ݼ�
	 * @param connection		�������ݿ��Connection
	 * @param sql				ִ�в�ѯ���ܵ�SQL���
	 * @return					��ѯ���Ľ����
	 */
	public static ResultSet executeQury(Connection connection,String sql)
	{
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			return preparedStatement.executeQuery();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ִ�в�ѯSQL��䲢���ؽ����
	 * @param connection		�������ݿ��Connection
	 * @param sql				ִ�в�ѯ����SQL���
	 * @param data				SQL������ʺ�����Ӧ���ַ�������
	 * @return					��ѯ��Ľ����
	 */
	public static ResultSet executeQury(Connection connection,String sql,String[] data)
	{
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			if (data != null && data.length > 0)
			{
				int num = 0;
				for (String string : data)
				{
					preparedStatement.setString(++num, string);
				}
			}
			return preparedStatement.executeQuery();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ִ��SQL���
	 * @param connection		�������ݿ��Connection
	 * @param sql				ִ�в�ѯ����SQL���
	 * @param data				SQL������ʺ�����Ӧ���ַ�������
	 * @return					�Ƿ�SQL���ִ�гɹ�
	 */
	public static boolean execute(Connection connection,String sql,String[] data)
	{
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			if (data != null && data.length > 0)
			{
				int num = 0;
				for (String string : data)
				{
					preparedStatement.setString(++num, string);
				}
			}
			preparedStatement.execute();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * �ر����ݿ�����
	 * @param connections	����һ�����������ݿ�����
	 */
	public static void Close(Connection ...connections)
	{
		if (connections != null && connections.length >0)//�жϲ�Ϊ��
		{
			for (Connection connection : connections)
			{
				if (connection != null)
				{
					try
					{
						connection.close();
					} catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
