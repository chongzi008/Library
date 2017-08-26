package cn.lib.manager.domain;

/**
 * 这是管理员的实体类
 * 
 * @author ChongZi007
 * 
 */
public class Manager {
	private int m_id;// 管理员id
	private String m_username;// 管理员名字
	private String m_password;// 管理员密码

	public Manager() {
		super();
	}

	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public String getM_username() {
		return m_username;
	}

	public void setM_username(String m_username) {
		this.m_username = m_username;
	}

	public String getM_password() {
		return m_password;
	}

	public void setM_password(String m_password) {
		this.m_password = m_password;
	}

}
