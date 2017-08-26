package cn.lib.manager.domain;

import java.util.Date;

public class Announcement {
	private int a_id;
	private Date a_time;
	private String a_content;

	public Announcement() {
		super();
	}

	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public Date getA_time() {
		return a_time;
	}

	public void setA_time(Date a_time) {
		this.a_time = a_time;
	}

	public String getA_content() {
		return a_content;
	}

	public void setA_content(String a_content) {
		this.a_content = a_content;
	}
 
}
