package cn.lib.manager.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.lib.manager.domain.Borrow;
import cn.lib.manager.domain.Fine;
import cn.lib.manager.domain.PageBean;
import cn.lib.utils.JdbcUtils;
import cn.lib.utils.PageUtils;

/**
 * 罚款的Dao层
 * 
 * @author ChongZi007
 * 
 */
public class FineImpl {

	public PageBean findByAllPages(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return PageUtils.findByAlls(currentPage, pageSize, "fine", Fine.class);
	}

	public PageBean findItemPages(int currentPage, int pageSize, String item) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(String f_id) {

		String sql = "delete from fine where f_id=?";
		try {
			JdbcUtils.getQuerrRunner().update(sql, Integer.valueOf(f_id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static PageBean findByAllItem(int currentPage, int pageSize,
			String item, String tableName, Class<Fine> beanName) {

		try {
			// 第一步设置好pagebean的当前页码 跟页面总大小
			PageBean pagebean = new PageBean();
			pagebean.setCurrentPage(currentPage);
			pagebean.setPageSize(pageSize);

			// 获取总数
			// 查询四次
			// 查询title得到结果
			String sql = "SELECT * FROM " + tableName + " WHERE " + "f_id"
					+ " LIKE ? ;";
			List<Fine> listTitle = JdbcUtils.getQuerrRunner().query(sql,
					new BeanListHandler<Fine>(beanName), "%" + item + "%");

			// 查询contentName得到结果
			sql = "SELECT * FROM " + tableName + " WHERE " + "r_id"
					+ " LIKE ? ;";
			List<Fine> listContent = JdbcUtils.getQuerrRunner().query(sql,
					new BeanListHandler<Fine>(beanName), "%" + item + "%");

			// 查询authorName得到结果
			sql = "SELECT * FROM " + tableName + " WHERE " + "b_id"
					+ " LIKE ? ;";
			List<Fine> listAuthor = JdbcUtils.getQuerrRunner().query(sql,
					new BeanListHandler<Fine>(beanName), "%" + item + "%");

			// 得到四次查询的全部结果
			// 我们利用Map的属性来实现
			Map<Integer, Fine> itemMap = new LinkedHashMap<Integer, Fine>();
			itemCharge(itemMap, listTitle);
			itemCharge(itemMap, listContent);
			itemCharge(itemMap, listAuthor);
			int totalRecord = itemMap.size();//
			// 设置总页数
			pagebean.setTotalRecord(totalRecord);
			List<Fine> beanList = new ArrayList<Fine>();
			// 下面就是分页显示回去的结果 而我现在全部的结果就在itemMap里面
			// 我现在要根据方法内部传来的页面值来返回一定数目的bean对象存在list里面让其带回去如何实现？
			// 这里直接就把map的东东存进list里面吧 两个都是有序的 就不用担心了
			// 不为空我们才遍历
			if (itemMap.size() != 0) {
				for (Fine Fine : itemMap.values()) {
					beanList.add(Fine);
				}

			}
			// 此时的pagebeanlist是装了所有的符合条件的新闻对象
			// 为了实现分页的效果我们每次就从这里取10条就好
			List<Fine> PagebeanList = new ArrayList<Fine>();
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

		for (Object Fine : listTitle) {
			Fine item = (Fine) Fine;
			itemMap.put(item.getF_id(), item);

		}

	}

	public void add(Fine fine) {
		String sql = "insert into fine(f_id,r_id,b_id,f_num,f_date,f_fnum)values(?,?,?,?,?,?)";
		try {
			JdbcUtils.getQuerrRunner().update(sql, fine.getF_id(),
					fine.getR_id(), fine.getB_id(), fine.getF_num(),
					fine.getF_date(), null);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(Fine fine) {
		String sql = "update fine set f_date=?,f_fnum=? where f_id=?;";
		try {
			JdbcUtils.getQuerrRunner().update(sql,new Date(),fine.getF_fnum(),fine.getF_id());
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getBookNum(int r_id, int b_id) {
		
		String sql = "select * from borrow where r_id=? and b_id=?";
		try {
			 List<Borrow> list = JdbcUtils.getQuerrRunner().query(sql, new BeanListHandler<Borrow>(Borrow.class),r_id,b_id);
					return list.size();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	};

}
