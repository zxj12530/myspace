package com.myspace.manage;

import java.sql.Connection;
import java.util.TimerTask;
import com.XEasy.Database.DBUtil;
import com.XEasy.Util.DateUtil;
import com.myspace.Entiy.LoginVO;

/**
 * 保存登录数据异步线程
 * @author lenovo
 *
 */
public class ThreadSaveLog extends TimerTask{
	private String classname;
	private String url;
	private String username;
	private String password;
	private Connection con;
	public ThreadSaveLog(String classname,String url,String username,String password){
		this.classname = classname;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	@Override
	public void run() {
		try {
			System.out.println(DateUtil.getTodayDateTime()+",ThreadSaveLog执行...");
			con = DBUtil.getConnection( classname, url, username, password);
			if(con == null){
				System.out.println("error for getDBConnection ");
			}
			int count = 0;
			for(LoginVO vo :CacheManager.loginInfo){
					String sql = "insert into m_log_info values (0,?,?,?,?,?);";
					boolean ok = DBUtil.execute(con, sql, new String[]{vo.getLog_ip(),vo.getLog_city(),vo.getLog_time(),String.valueOf(vo.getLog_count()),vo.getLog_url()});
					if(ok){
						count ++;
					}
			}
			System.out.println("insert login info count ="+count);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}finally{
			DBUtil.Close(con);
			CacheManager.loginInfo.clear();
		}
	}
}
