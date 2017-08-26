package cn.lib.manager.service;

import java.util.List;

import cn.lib.manager.dao.impl.ManagerImpl;
import cn.lib.manager.domain.Manager;
import cn.lib.manager.domain.PageBean;

public class ManagerService {
	ManagerImpl mimpl = new ManagerImpl();

	/**
	 * 查询所有管理人员
	 * 
	 * @return
	 */
	public PageBean findAllByPages(int currentPage, int pageSize) {
		return mimpl.findByAllPages(currentPage, pageSize);
	}

	public PageBean findItem(int currentPage, int pageSize, String item) {
		return mimpl.findItemPages(currentPage, pageSize, item);
	}

	/**
	 * 添加管理员
	 * 
	 * @param manager
	 */
	public void add(Manager manager) {

		this.mimpl.add(manager);
	}

	/**
	 * 删除管理员
	 * 
	 * @param manager
	 */
	public void delete(Manager manager) {

		this.mimpl.delete(manager);
	}

	/**
	 * 修改管理员信息
	 * 
	 * @param manager
	 */
	public void update(Manager manager) {

		this.mimpl.update(manager);
	}

	public Manager find(Manager manager) {
		
		return this.mimpl.find( manager);
	}

}
