package cn.lib.manager.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.lib.manager.domain.BookType;
import cn.lib.manager.domain.PageBean;
import cn.lib.utils.JdbcUtils;
import cn.lib.utils.PageUtils;

/**
 * 书籍分类dao层
 * 
 * @author ChongZi007
 * 
 */
public class BookTypeImpl {
	private QueryRunner qr = JdbcUtils.getQuerrRunner();

	public PageBean findAllByPages(int currentPage, int pageSize) {

		return PageUtils.findByAlls(currentPage, pageSize, "booktype",
				BookType.class);
	}

	public void delete(String t_id) {

		String sql = "delete from booktype where t_id=?";
		try {
			JdbcUtils.getQuerrRunner().update(sql, Integer.valueOf(t_id));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(BookType bookType) {
		String sql = "update booktype set t_name=? where t_id=?";
		try {
			JdbcUtils.getQuerrRunner().update(sql,bookType.getT_name(),bookType.getT_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	public void add(BookType bookType) {
		try {

			String sql = "insert into booktype (t_id,t_name) values(?,?)";
			Object[] params = { bookType.getT_id(), bookType.getT_name() };
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
