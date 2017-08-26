package cn.lib.manager.domain;

import java.util.Date;

public class Fine {
	private int f_id;//罚款编号
	private int r_id;//读者编号
	private int b_id;//图书编号
	private double f_num;//罚款金额
	private Date f_date;//缴费日期
	private int f_fnum;//实际罚款金额

	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
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


	public double getF_num() {
		return f_num;
	}

	public void setF_num(double f_num) {
		this.f_num = f_num;
	}

	public Date getF_date() {
		return f_date;
	}

	public void setF_date(Date f_date) {
		this.f_date = f_date;
	}

	public int getF_fnum() {
		return f_fnum;
	}

	public void setF_fnum(int f_fnum) {
		this.f_fnum = f_fnum;
	}

}
