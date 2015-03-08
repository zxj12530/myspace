package com.myspace.Entiy;

import java.util.Date;

public class User {

	private String log_email;
	private String log_password;
	private int secret_no;
	private String log_ip;
	private Date create_time;
	
	//是否在线
	private String is_Online;
	
	public String getIs_Online() {
		return is_Online;
	}
	public void setIs_Online(String is_Online) {
		this.is_Online = is_Online;
	}
	public String getLog_email() {
		return log_email;
	}
	public void setLog_email(String log_email) {
		this.log_email = log_email;
	}
	public String getLog_password() {
		return log_password;
	}
	public void setLog_password(String log_password) {
		this.log_password = log_password;
	}
	public int getSecret_no() {
		return secret_no;
	}
	public void setSecret_no(int secret_no) {
		this.secret_no = secret_no;
	}
	public String getLog_ip() {
		return log_ip;
	}
	public void setLog_ip(String log_ip) {
		this.log_ip = log_ip;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
