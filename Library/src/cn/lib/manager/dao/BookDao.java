package cn.lib.manager.dao;

import java.util.List;

import cn.lib.exception.BookException;
import cn.lib.manager.domain.Book;

public interface BookDao {
	/**
	 * 添加图书
	 * @param book
	 * @throws BookException 
	 */
	void add(Book book) throws BookException;
     /**
      * 删除书本
      * @param b_id
      */
	void delete(int b_id);
	/**
	 * 修改图书
	 * @param book
	 */
	void update(Book book);
	/**
	 * 查询所有图书
	 * @return
	 */
	List<Book>findAll();
	/**
	 * 根据id查询书本
	 * @param b_id
	 * @return
	 */

}
