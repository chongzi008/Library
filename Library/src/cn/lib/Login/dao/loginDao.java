package cn.lib.Login.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.lib.manager.domain.Book;
import cn.lib.manager.domain.BookType;
import cn.lib.manager.domain.PageBean;
import cn.lib.utils.JdbcUtils;

public class loginDao {

	public PageBean getBook(int currentPage, int pageSize) {
		try {
			// 这次查书本有点麻烦了
			PageBean pagebean = new PageBean();
			pagebean.setCurrentPage(currentPage);
			pagebean.setPageSize(pageSize);

			String sql = "select count(*) from " + "book" + ";";
			QueryRunner qr = JdbcUtils.getQuerrRunner();
			Number num = (Number) qr.query(sql, new ScalarHandler());
			int totalRecord = num.intValue();
			pagebean.setTotalRecord(totalRecord);
			// 上面步骤设置记录的总数量
			// 下面就是返回真正的数据 我们还是老思路先取到所有的book 在分别读取得到id 拿到name 存进对象
			// 在存进book对象

			sql = "select * from " + "book" + " limit ?,?";
			List<Map<String, Object>> list = qr.query(
					sql,
					new MapListHandler(),
					new Object[] {
							Integer.valueOf((currentPage - 1) * pageSize),
							Integer.valueOf(pageSize) });

			List<Book> PagebeanList = new ArrayList<Book>();
			// list为原始对象集合 我们先遍历它得到每一个原始对象
			// 使用beanutils 先把每一个map转换成对象
			for (Map<String, Object> map : list) {
				Book book = new Book();
				BeanUtils.populate(book, map);// 这里得到没有类型对象的book对象
				int t_id = (Integer) map.get("t_id");// 得到id
				// 根据id查询得到一个类型对象
				sql = "select * from booktype where t_id=?";
				BookType bookType = qr.query(sql, new BeanHandler<BookType>(
						BookType.class), t_id);
				// 把对象存进bean对象中
				book.setType(bookType);
				// 最后把对象存进pagebeanlist中 设置进pagebean中
				PagebeanList.add(book);

			}

			pagebean.setPageBeanList(PagebeanList);

			return pagebean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
