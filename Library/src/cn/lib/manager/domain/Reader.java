package cn.lib.manager.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 这是读者实体类
 * @author ChongZi007
 *
 */
public class Reader {
	private int r_id;// 读者编号
	private String r_username;// 读者名字
	private String r_password;// 读者密码
	private String r_sex;// 读者性别
	private int r_num;// 读者可借书数
	private int cur_num;// 读者已借书数
	private int overdate_num;// 读者过期未还书数

	public Reader() {
		super();
	}

	public Reader(int r_id, String r_username, String r_password, String r_sex, int r_num, int cur_num,
			int overdate_num) {
		super();
		this.r_id = r_id;
		this.r_username = r_username;
		this.r_password = r_password;
		this.r_sex = r_sex;
		this.r_num = r_num;
		this.cur_num = cur_num;
		this.overdate_num = overdate_num;
	}

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public String getR_username() {
		return r_username;
	}

	public void setR_username(String r_username) {
		this.r_username = r_username;
	}

	public String getR_password() {
		return r_password;
	}

	public void setR_password(String r_password) {
		this.r_password = r_password;
	}

	public String getR_sex() {
		return r_sex;
	}

	public void setR_sex(String r_sex) {
		this.r_sex = r_sex;
	}

	public int getR_num() {
		return r_num;
	}

	public void setR_num(int r_num) {
		this.r_num = r_num;
	}

	public int getCur_num() {
		return cur_num;
	}

	public void setCur_num(int cur_num) {
		this.cur_num = cur_num;
	}

	public int getOverdate_num() {
		return overdate_num;
	}

	public void setOverdate_num(int overdate_num) {
		this.overdate_num = overdate_num;
	}

	@Override
	public String toString() {
		return "reader [r_id=" + r_id + ", r_username=" + r_username + ", r_password=" + r_password + ", r_sex=" + r_sex
				+ ", r_num=" + r_num + ", cur_num=" + cur_num + ", overdate_num=" + overdate_num + "]";
	}

}
