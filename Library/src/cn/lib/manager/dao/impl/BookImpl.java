package cn.lib.manager.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.lib.exception.BookException;
import cn.lib.manager.dao.BookDao;
import cn.lib.manager.domain.Book;
import cn.lib.manager.domain.BookType;
import cn.lib.manager.domain.PageBean;
import cn.lib.utils.JdbcUtils;

public class BookImpl implements BookDao {
	private QueryRunner qr = JdbcUtils.getQuerrRunner();

	/**
	 * 添加书本
	 * @throws BookException 
	 */
	@Override
	public void add(Book book) throws BookException {
		try {

			String sql = "insert into book(b_id,b_name,b_author,t_id,b_pubname,b_price,b_surplus,"
					+ "b_sum,b_image,b_site)values(?,?,?,?,?,?,?,?,?,?)";
			Object[] params = { book.getB_id(), book.getB_name(),
					book.getB_author(), book.getType().getT_id(),
					book.getB_pubname(), book.getB_price(),
					book.getB_surplus(), book.getB_sum(), book.getB_image(),
					book.getB_site() };
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new BookException("请输入正确的书籍类型id");
		}
	}

	/**
	 * 通过id删除书本
	 */
	@Override
	public void delete(int b_id) {

		try {
			String sql = "delete from book where b_id=?";
			qr.update(sql, b_id);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	/**
	 * 修改书本信息
	 */
	@Override
	public void update(Book book) {

		try {
			String sql = "update book set b_surplus=?,b_sum=?,b_site=? where b_id=?";
			Object[] params = { book.getB_surplus(), book.getB_sum(),
					book.getB_site(), book.getB_id() };
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 得到所有书本
	 */

	@Override
	public List<Book> findAll() {

		try {
			String sql = "select * from book";
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	/**
	 * 通过id得到书本
	 * 
	 * @param b_id
	 * @return
	 */
	public Book find(int b_id) {

		try {
			String sql = "select * from book where b_id=?";
			return qr.query(sql, new BeanHandler<Book>(Book.class), b_id);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	/**
	 * 分页得到所有书本
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageBean findByAllPages(int currentPage, int pageSize) {
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

	/**
	 * 根绝条件查询书本信息
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param item
	 * @return
	 */

	public PageBean findItemPages(int currentPage, int pageSize, String item) {
		try {

			PageBean pagebean = new PageBean();
			pagebean.setCurrentPage(currentPage);
			pagebean.setPageSize(pageSize);

			String sql = "SELECT * FROM " + "book" + " WHERE " + "b_name"
					+ " LIKE ? ;";
			List<Book> list = JdbcUtils.getQuerrRunner().query(sql,
					new BeanListHandler<Book>(Book.class), "%" + item + "%");
			int totalRecord = list.size();

			// 设置总页数
			pagebean.setTotalRecord(totalRecord);

			sql = "SELECT * FROM " + "book" + " WHERE " + "b_name"
					+ " LIKE ? limit ?,?;";
			List<Map<String, Object>> queryMap = JdbcUtils.getQuerrRunner()
					.query(sql,
							new MapListHandler(),
							new Object[] {
									"%" + item + "%",
									Integer.valueOf((currentPage - 1)
											* pageSize),
									Integer.valueOf(pageSize) });
			// 同样的手法
			List<Book> PagebeanList = new ArrayList<Book>();
			// list为原始对象集合 我们先遍历它得到每一个原始对象
			// 使用beanutils 先把每一个map转换成对象
			if (queryMap != null) {
				for (Map<String, Object> map : queryMap) {
					Book book = new Book();
					try {
						BeanUtils.populate(book, map);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}// 这里得到没有类型对象的book对象
					int t_id = (Integer) map.get("t_id");// 得到id
					// 根据id查询得到一个类型对象
					sql = "select * from booktype where t_id=?";
					BookType bookType = qr.query(sql,
							new BeanHandler<BookType>(BookType.class), t_id);
					// 把对象存进bean对象中
					book.setType(bookType);
					// 最后把对象存进pagebeanlist中 设置进pagebean中
					PagebeanList.add(book);

				}
			}

			pagebean.setPageBeanList(PagebeanList);

			return pagebean;

		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	/**
	 * 根绝条件查询书本信息
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param item
	 * @return
	 */
	public PageBean findItemPage(int currentPage, int pageSize, String item) {
		try {

			PageBean pagebean = new PageBean();
			pagebean.setCurrentPage(currentPage);
			pagebean.setPageSize(pageSize);

			String sql = "SELECT * FROM " + "book" + " WHERE " + "b_name"
					+ " LIKE ? ";
			List<Book> namelist = JdbcUtils.getQuerrRunner().query(sql,
					new BeanListHandler<Book>(Book.class), "%" + item + "%");

			sql = "SELECT * FROM " + "book" + " WHERE " + "b_author"
					+ " LIKE ? ";
			List<Book> authorlist = JdbcUtils.getQuerrRunner().query(sql,
					new BeanListHandler<Book>(Book.class), "%" + item + "%");

			// 得到四次查询的全部结果
			// 我们利用Map的属性来实现
			Map<Integer, Book> itemMap = new LinkedHashMap<Integer, Book>();
			itemCharge(itemMap, namelist);
			itemCharge(itemMap, authorlist);
			int totalRecord = itemMap.size();
			// 设置总页数
			pagebean.setTotalRecord(totalRecord);

			List<Book> beanList = new ArrayList<Book>();
			// 不为空我们才遍历
			if (itemMap.size() != 0) {
				for (Book bookItem : itemMap.values()) {
					beanList.add(bookItem);
				}

			}

			// 此时的pagebeanlist是装了所有的符合条件的新闻对象
			// 为了实现分页的效果我们每次就从这里取10条就好
			List<Book> PagebeanList = new ArrayList<Book>();
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

	/**
	 * 通过id查询书本
	 * 
	 * @param bid
	 * @return
	 */
	public Book findById(int bid) {

		try {
			String sql = "SELECT * FROM book WHERE b_id=?";
			QueryRunner runner = JdbcUtils.getQuerrRunner();
			Map<String, Object> map = runner.query(sql, new MapHandler(), bid);
			Book book = new Book();
			BeanUtils.populate(book, map);
			sql = "select * from booktype where t_id=?";
			BookType bookType = qr.query(sql, new BeanHandler<BookType>(
					BookType.class), (Integer) map.get("t_id"));
			book.setType(bookType);
			return book;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static void itemCharge(Map itemMap, List listTitle) {

		for (Object bookitem : listTitle) {
			Book item = (Book) bookitem;
			itemMap.put(item.getB_id(), item);

		}

	}

	public PageBean<Book> sortByA(int currentPage, int pageSize, int tid) {

		try {
			// 这次查书本有点麻烦了
			PageBean pagebean = new PageBean();
			pagebean.setCurrentPage(currentPage);
			pagebean.setPageSize(pageSize);

			String sql = "select count(*) from " + "book where t_id=?;";
			QueryRunner qr = JdbcUtils.getQuerrRunner();
			Number num = (Number) qr.query(sql, new ScalarHandler(), tid);
			int totalRecord = num.intValue();
			pagebean.setTotalRecord(totalRecord);
			// 上面步骤设置记录的总数量
			// 下面就是返回真正的数据 我们还是老思路先取到所有的book 在分别读取得到id 拿到name 存进对象
			// 在存进book对象

			sql = "select * from " + "book" + " where t_id=? limit ?,?";
			List<Map<String, Object>> list = qr.query(
					sql,
					new MapListHandler(),
					new Object[] { tid,
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

	public PageBean findByType(int currentPage, int pageSize, String item) {
		try {

			PageBean pagebean = new PageBean();
			pagebean.setCurrentPage(currentPage);
			pagebean.setPageSize(pageSize);

			String sql = "SELECT * FROM " + "booktype" + " WHERE " + "t_name"
					+ " LIKE ? ;";
			BookType type = JdbcUtils.getQuerrRunner()
					.query(sql, new BeanHandler<BookType>(BookType.class),
							"%" + item + "%");

			if (type != null) {

				sql = "SELECT * FROM " + "book" + " WHERE " + "t_id =?";

				List<Book> list = JdbcUtils.getQuerrRunner().query(sql,
						new BeanListHandler<Book>(Book.class), type.getT_id());
				int totalRecord = list.size();

				// 设置总页数
				pagebean.setTotalRecord(totalRecord);

				sql = "SELECT * FROM " + "book" + " WHERE t_id=? limit ?,?";

				List<Map<String, Object>> queryMap = JdbcUtils.getQuerrRunner()
						.query(sql,
								new MapListHandler(),
								new Object[] {
										type.getT_id(),
										Integer.valueOf((currentPage - 1)
												* pageSize),
										Integer.valueOf(pageSize) });
				// 同样的手法
				List<Book> PagebeanList = new ArrayList<Book>();
				// list为原始对象集合 我们先遍历它得到每一个原始对象
				// 使用beanutils 先把每一个map转换成对象
				if (queryMap != null) {
					for (Map<String, Object> map : queryMap) {
						Book book = new Book();
						try {
							BeanUtils.populate(book, map);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}// 这里得到没有类型对象的book对象
						int t_id = (Integer) map.get("t_id");// 得到id
						// 根据id查询得到一个类型对象
						sql = "select * from booktype where t_id=?";
						BookType bookType = qr
								.query(sql, new BeanHandler<BookType>(
										BookType.class), t_id);
						// 把对象存进bean对象中
						book.setType(bookType);
						// 最后把对象存进pagebeanlist中 设置进pagebean中
						PagebeanList.add(book);

					}
				}

				pagebean.setPageBeanList(PagebeanList);
			}

			System.out.println(pagebean.getPageBeanList() + "++++++++++");
			return pagebean;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

}
