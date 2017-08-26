package cn.lib.manager.domain;

import java.util.Date;

/**
 * 这是借阅的实体类
 * 
 * @author ChongZi007
 * 
 */
public class Borrow {
	private int bor_id;// 借阅标号
	private Reader reader;// 读者
	private Book book;// 书
	private String r_username;
	private Date lend_date;// 借书日期
	private Date back_date;// 还书日期
	private String isback;// 是否归还

	public Borrow() {
		super();
	}



	public int getBor_id() {
		return bor_id;
	}



	public void setBor_id(int bor_id) {
		this.bor_id = bor_id;
	}



	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getLend_date() {
		return lend_date;
	}

	public void setLend_date(Date lend_date) {
		this.lend_date = lend_date;
	}

	public Date getBack_date() {
		return back_date;
	}

	public void setBack_date(Date back_date) {
		this.back_date = back_date;
	}

	public String getIsback() {
		return isback;
	}

	public void setIsback(String isback) {
		this.isback = isback;
	}

	public String getR_username() {
		return r_username;
	}

	public void setR_username(String r_username) {
		this.r_username = r_username;
	}

}
