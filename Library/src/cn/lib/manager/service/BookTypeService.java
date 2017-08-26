package cn.lib.manager.service;

import cn.lib.manager.dao.impl.BookTypeImpl;
import cn.lib.manager.domain.BookType;
import cn.lib.manager.domain.Fine;
import cn.lib.manager.domain.PageBean;

/**
 * 管理书籍业务逻辑类
 * @author ChongZi007
 *
 */
public class BookTypeService {
 private BookTypeImpl bookTypeImpl=new BookTypeImpl();
	public PageBean findAllByPages(int currentPage, int pageSize) {
		
		return bookTypeImpl.findAllByPages( currentPage,  pageSize);
	}
	public void delete(String t_id) {
		
		bookTypeImpl.delete(t_id);
	}
	public void update(BookType bookType) {
		bookTypeImpl.update(bookType);
	}
	public void add(BookType bookType) {
		
		 bookTypeImpl.add(bookType );
	}

}
