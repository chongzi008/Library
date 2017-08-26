package cn.lib.Login.service;

import cn.lib.Login.dao.loginDao;
import cn.lib.manager.domain.PageBean;

public class loginService {

	public PageBean getBook(int currentPage, int pageSize) {
		return new loginDao().getBook(currentPage,pageSize);
	}

}
