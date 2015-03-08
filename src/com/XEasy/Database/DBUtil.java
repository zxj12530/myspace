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
 *	操作数据库的工具类
 */
public class DBUtil extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private DBUtil()
	{
	}
	/**
	 * 获取链接数据库的链接
	 * 必须在web.xml中定义
	 * @param &lt;context-param&gt;
	 * @param 		&lt;description&gt;数据库的驱动类类名&lt;/description&gt;
	 * @param 		&lt;param-name&gt;classname&lt;/param-name&gt;
	 * @param 		&lt;param-value&gt;com.mysql.jdbc.Driver&lt;/param-value&gt;
	 * @param &lt;/context-param&gt;
	 * @param &lt;context-param&gt;
	 * @param 		&lt;description&gt;链接数据库的URL&lt;/description&gt;
	 * @param 		&lt;param-name&gt;url&lt;/param-name&gt;
	 * @param 		&lt;param-value&gt;jdbc:mysql://127.0.0.1:3306/xcode&lt;/param-value&gt;
	 * @param &lt;/context-param&gt;
	 * @param &lt;context-param&gt;
	 * @param 		&lt;description&gt;数据库的用户名&lt;/description&gt;
	 * @param 		&lt;param-name&gt;username&lt;/param-name&gt;
	 * @param 		&lt;param-value&gt;root&lt;/param-value&gt;
	 * @param &lt;/context-param&gt;
	 * @param &lt;context-param&gt;
	 * @param 		&lt;description&gt;数据库密码&lt;/description&gt;
	 * @param 		&lt;param-name&gt;password&lt;/param-name&gt;
	 * @param 		&lt;param-value&gt;root&lt;/param-value&gt;
	 * @param &lt;/context-param&gt;
	 * @return	返回数据库的链接的Connection
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
	 * 执行SQL语句的静态类
	 * @param connection	连接数据库的Connection
	 * @param sql			需要执行的SQL语句
	 * @return				此SQL语句是否执行成功
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
	 * 执行SQL语句返回的获取数据集
	 * @param connection		连接数据库的Connection
	 * @param sql				执行查询功能的SQL语句
	 * @return					查询到的结果集
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
	 * 执行查询SQL语句并返回结果集
	 * @param connection		连接数据库的Connection
	 * @param sql				执行查询语句的SQL语句
	 * @param data				SQL语句中问号所对应的字符串数组
	 * @return					查询后的结果集
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
	 * 执行SQL语句
	 * @param connection		连接数据库的Connection
	 * @param sql				执行查询语句的SQL语句
	 * @param data				SQL语句中问号所对应的字符串数组
	 * @return					是否SQL语句执行成功
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
	 * 关闭数据库链接
	 * @param connections	传入一个，或多个数据库链接
	 */
	public static void Close(Connection ...connections)
	{
		if (connections != null && connections.length >0)//判断不为空
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
