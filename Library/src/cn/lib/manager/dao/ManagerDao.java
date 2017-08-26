package cn.lib.manager.dao;

import java.util.List;

import cn.lib.manager.domain.Manager;

public interface ManagerDao {
	/**
	 * 查询所有的管理员
	 * 
	 * @return
	 */
	List<Manager> findAll();

	/**
	 * 根据m_id来查询对应的管理员
	 * 
	 * @param m_id
	 */
	Manager findByMid(int m_id);

	/**
	 * 修改管理员数据
	 * 
	 * @param manager
	 */

	void update(Manager manager);
	/**
	 * 删除相应的管理员
	 * @param manager
	 */
	void delete(Manager manager);
   
	/**
	 * 添加管理员
	 * @param manager
	 */
	void add(Manager manager);
}
