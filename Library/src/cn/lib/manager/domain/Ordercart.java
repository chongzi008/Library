package cn.lib.manager.domain;

import java.util.Date;

public class Ordercart {
 
	private int r_id;//读者编号
	private int b_id;//图书编号
	private int o_num;//预约数量
	private Date o_date;//预约时间
	public Ordercart() {
		super();
	}
	
	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public int getO_num() {
		return o_num;
	}

	public void setO_num(int o_num) {
		this.o_num = o_num;
	}

	public Date getO_date() {
		return o_date;
	}

	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}

	public Ordercart(int r_id, int b_id, int o_num, Date o_date) {
		super();
		this.r_id = r_id;
		this.b_id = b_id;
		this.o_num = o_num;
		this.o_date = o_date;
	}
	@Override
	public String toString() {
		return "Ordercart [r_id=" + r_id + ", b_id=" + b_id + ", o_num=" + o_num + ", o_date=" + o_date + "]";
	}
	
	
}
