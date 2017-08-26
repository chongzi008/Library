package cn.lib.manager.domain;

/**
 * 这是图书的实体类
 * 
 * @author ChongZi007
 * 
 */
public class Book {
	private int b_id;// 对应主键 图书编号
	private String b_name;// 图书名字
	private BookType type;// 图书类型
	private String b_author;// 图书作者
	private String b_pubname;// 图书出版社名字
	private double b_price;// 图书价格
	private int b_surplus;// 图书剩余数
	private int b_sum;// 图书总数
	private String b_image;// 图书图片
	private String b_site;// 图书位置

	public Book() {
		super();
	}

	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

	public BookType getType() {
		return type;
	}

	public void setType(BookType type) {
		this.type = type;
	}

	public String getB_author() {
		return b_author;
	}

	public void setB_author(String b_author) {
		this.b_author = b_author;
	}

	public String getB_pubname() {
		return b_pubname;
	}

	public void setB_pubname(String b_pubname) {
		this.b_pubname = b_pubname;
	}

	public double getB_price() {
		return b_price;
	}

	public void setB_price(double b_price) {
		this.b_price = b_price;
	}

	public int getB_surplus() {
		return b_surplus;
	}

	public void setB_surplus(int b_surplus) {
		this.b_surplus = b_surplus;
	}

	public int getB_sum() {
		return b_sum;
	}

	public void setB_sum(int b_sum) {
		this.b_sum = b_sum;
	}

	public String getB_image() {
		return b_image;
	}

	public void setB_image(String b_image) {
		this.b_image = b_image;
	}

	public String getB_site() {
		return b_site;
	}

	public void setB_site(String b_site) {
		this.b_site = b_site;
	}

}
