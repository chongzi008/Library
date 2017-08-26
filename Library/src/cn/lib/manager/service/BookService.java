package cn.lib.manager.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import cn.lib.exception.BookException;
import cn.lib.manager.dao.impl.BookImpl;
import cn.lib.manager.domain.Book;
import cn.lib.manager.domain.PageBean;

public class BookService {
	private BookImpl bookImpl = new BookImpl();

	/**
	 * 添加功能
	 * 
	 * @param state
	 * 
	 * @param reader
	 * @throws BookException
	 */
	public void add(Book book) throws BookException {

		this.bookImpl.add(book);

	}

	/**
	 * 删除功能
	 * 
	 * @param r_id
	 */
	public void delete(int b_id) {
		this.bookImpl.delete(b_id);
	}

	/**
	 * 修改功能
	 * 
	 * @param b_state
	 * @param b_state
	 * 
	 * @param reader
	 * @throws BookException
	 */
	public void update(Book book, String b_state) throws BookException {
		// 这里对用户输入的数据进行校验

		this.bookImpl.update(book);
	}

	/**
	 * 查询全部书本
	 * 
	 * @return
	 */
	public List<Book> findAll() {

		return this.bookImpl.findAll();
	}

	/**
	 * 查询所有
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageBean findAllByPages(int currentPage, int pageSize) {
		return bookImpl.findByAllPages(currentPage, pageSize);
	}

	/**
	 * 根据条件查询返回书本
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param item
	 * @return
	 */
	public PageBean findItem(int currentPage, int pageSize, String item) {
		return bookImpl.findItemPages(currentPage, pageSize, item);
	}

	/**
	 * 通过id查询响应书籍
	 * 
	 * @param bid
	 * @return
	 */
	public Book findById(int bid) {
		return bookImpl.findById(bid);
	}

	/**
	 * 查询A类书籍
	 * 
	 * @return
	 */
	public PageBean<Book> sortByA(int currentPage, int pageSize,int tid) {
		return bookImpl.sortByA(currentPage, pageSize,tid);
	}

	public PageBean findByType(int currentPage, int pageSize, String item) {
		return bookImpl.findByType( currentPage,  pageSize,  item);
	}

}
