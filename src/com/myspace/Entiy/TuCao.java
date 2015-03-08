package com.myspace.Entiy;

public class TuCao {

	private String logname;
	private String message;
	private String time;
	
	public TuCao() {
		super();
	}
	public TuCao(String logname, String message, String time) {
		super();
		this.logname = logname;
		this.message = message;
		this.time = time;
	}
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
