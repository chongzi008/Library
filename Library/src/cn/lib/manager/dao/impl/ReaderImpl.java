package cn.lib.manager.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.lib.manager.dao.ReaderDao;
import cn.lib.manager.domain.Book;
import cn.lib.manager.domain.Reader;
import cn.lib.manager.domain.Manager;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.domain.Reader;
import cn.lib.utils.JdbcUtils;
import cn.lib.utils.PageUtils;

/**
 * 读者持久化层
 * 
 * @author ChongZi007
 * 
 */
public class ReaderImpl implements ReaderDao {

	private QueryRunner qr = JdbcUtils.getQuerrRunner();

	/**
	 * 添加读者
	 */
	@Override
	public void add(Reader reader) {
		try {
			String sql = "insert into reader values(?,?,?,?,?,?,?)";
			Object[] params = { reader.getR_id(), reader.getR_username(),
					reader.getR_password(), reader.getR_sex(), 10, 0, 0 };
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void delete(int r_id) {
		try {
			String sql = "delete from reader where r_id=?";
			qr.update(sql, r_id);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void update(Reader reader) {
		try {
			String sql = "update reader set r_username=?,r_password=?,r_sex=?where r_id=?";

			Object[] params = { reader.getR_username(), reader.getR_password(),
					reader.getR_sex(), reader.getR_id() };
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void updateSurPlus(Reader reader) {
		try {
			String sql = "update reader set cur_num=? where r_id=?";

			Object[] params = { reader.getCur_num(),reader.getR_id()
					};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	@Override
	public List<Reader> findAll() {

		try {
			String sql = "select * from reader";
			return qr.query(sql, new BeanListHandler<Reader>(Reader.class));
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public PageBean findByAllPages(int currentPage, int pageSize) {
		return PageUtils.findByAlls(currentPage, pageSize, "reader",
				Reader.class);
	}

	public PageBean findItemPages(int currentPage, int pageSize, String item) {
		return PageUtils.findItem(currentPage, pageSize, item, "reader",
				"r_username", Reader.class);
	}

	public static PageBean findByAllItem(int currentPage, int pageSize,
			String item, String tableName, Class<Reader> beanName) {

		try {
			// 第一步设置好pagebean的当前页码 跟页面总大小
			PageBean pagebean = new PageBean();
			pagebean.setCurrentPage(currentPage);
			pagebean.setPageSize(pageSize);

			// 获取总数
			// 查询四次
			// 查询title得到结果
			String sql = "SELECT * FROM " + tableName + " WHERE " + "r_id"
					+ " LIKE ? ;";
			List<Reader> listTitle = JdbcUtils.getQuerrRunner().query(sql,
					new BeanListHandler<Reader>(beanName), "%" + item + "%");

			// 查询contentName得到结果
			sql = "SELECT * FROM " + tableName + " WHERE " + "r_username"
					+ " LIKE ? ;";
			List<Reader> listContent = JdbcUtils.getQuerrRunner().query(sql,
					new BeanListHandler<Reader>(beanName), "%" + item + "%");

			// 得到四次查询的全部结果
			// 我们利用Map的属性来实现
			Map<Integer, Reader> itemMap = new LinkedHashMap<Integer, Reader>();
			itemCharge(itemMap, listTitle);
			itemCharge(itemMap, listContent);
			int totalRecord = itemMap.size();//
			// 设置总页数
			pagebean.setTotalRecord(totalRecord);
			List<Reader> beanList = new ArrayList<Reader>();
			// 下面就是分页显示回去的结果 而我现在全部的结果就在itemMap里面
			// 我现在要根据方法内部传来的页面值来返回一定数目的bean对象存在list里面让其带回去如何实现？
			// 这里直接就把map的东东存进list里面吧 两个都是有序的 就不用担心了
			// 不为空我们才遍历
			if (itemMap.size() != 0) {
				for (Reader Reader : itemMap.values()) {
					beanList.add(Reader);
				}

			}
			// 此时的pagebeanlist是装了所有的符合条件的新闻对象
			// 为了实现分页的效果我们每次就从这里取10条就好
			List<Reader> PagebeanList = new ArrayList<Reader>();
			int startIndex = (currentPage - 1) * pageSize;// 根据当前页码算出来的开始索引
			int endIndex = 0;
			if (beanList.size() < 10) {
				// 长度小于10我们就直接让结束索引为其大小
				endIndex = beanList.size();
			} else if (beanList.size() > 10) {
				// 长度大于10
				endIndex = startIndex + 10;

			}
			if (beanList.size() != 0) {
				// 确保有值的时候我们才遍历

				for (int i = startIndex; i < endIndex; i++) {
					// 取值我们一次取10个 但是即使是超过10个数量的 仍然也会出现索引越界的问题 这里我们需要处理一下
					if (i >= beanList.size()) {

					} else {
						PagebeanList.add(beanList.get(i));
					}
				}
			}

			pagebean.setPageBeanList(PagebeanList);

			return pagebean;

		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public static void itemCharge(Map itemMap, List listTitle) {

		for (Object Reader : listTitle) {
			Reader item = (Reader) Reader;
			itemMap.put(item.getR_id(), item);

		}

	}

	/**
	 * 根据id得到读者
	 * 
	 * @param reader
	 * @return
	 */
	public Reader find(Reader reader) {

		try {
			String sql = "select * from reader where r_id =?";
			return qr.query(sql, new BeanHandler<Reader>(Reader.class),
					reader.getR_id());
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}
	
	
	public Reader findById(int r_id) {

		try {
			String sql = "select * from reader where r_id =?";
			return qr.query(sql, new BeanHandler<Reader>(Reader.class),
					r_id);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}
}
