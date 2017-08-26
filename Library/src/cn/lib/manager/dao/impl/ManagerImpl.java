package cn.lib.manager.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import cn.lib.manager.dao.ManagerDao;
import cn.lib.manager.domain.Manager;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.domain.Reader;
import cn.lib.utils.JdbcUtils;
import cn.lib.utils.PageUtils;

public class ManagerImpl implements ManagerDao {
	private QueryRunner qr = JdbcUtils.getQuerrRunner();

	/**
	 * 查询所有管理人员分页显示
	 */
	@Override
	public List<Manager> findAll() {

		try {
			String sql = "select * from manager";
			return qr.query(sql, new BeanListHandler<Manager>(Manager.class));
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	/**
	 * 通过id查询响应的管理员
	 */
	@Override
	public Manager findByMid(int m_id) {
		try {
			String sql = "select * from manager where m_id=?";
			return qr.query(sql, new BeanHandler<Manager>(Manager.class), m_id);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	/**
	 * 修改响应管理员
	 */
	@Override
	public void update(Manager manager) {

		try {
			String sql = "update manager set m_username=?,m_password=? where m_id=?";
			qr.update(sql, manager.getM_username(), manager.getM_password(), manager.getM_id());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 删除管理员
	 */
	@Override
	public void delete(Manager manager) {

		try {
			String sql = "delete from manager where m_id=?";
			qr.update(sql, manager.getM_id());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 添加管理员
	 */
	@Override
	public void add(Manager manager) {

		try {
			String sql = "insert into manager values(?,?,?)";
			qr.update(sql, manager.getM_id(), manager.getM_username(), manager.getM_password());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

  /**
   * 查询所有管理员
   * @param currentPage
   * @param pageSize
   * @return
   */
	
	public PageBean findByAllPages(int currentPage, int pageSize) {
		return PageUtils.findByAlls(currentPage, pageSize, "manager", Manager.class);
	}

	/**
	 * 按条件查询管理员
	 * @param currentPage
	 * @param pageSize
	 * @param item
	 * @return
	 */
	public PageBean findItemPages(int currentPage, int pageSize, String item) {
		return PageUtils.findItem(currentPage, pageSize, item, "manager", "m_username", Manager.class);
	}

	public Manager find(Manager manager) {
		
		 	try {
			String sql = "select * from manager where m_id =?";
			return qr.query(sql, new BeanHandler<Manager>(Manager.class),manager.getM_id());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	

}
