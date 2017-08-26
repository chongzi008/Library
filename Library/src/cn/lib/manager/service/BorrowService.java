package cn.lib.manager.service;

import java.util.Date;

import cn.lib.exception.FineException;
import cn.lib.manager.dao.impl.BookImpl;
import cn.lib.manager.dao.impl.BorrowImpl;
import cn.lib.manager.dao.impl.ReaderImpl;
import cn.lib.manager.domain.Book;
import cn.lib.manager.domain.Borrow;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.domain.Reader;

public class BorrowService {
	private BorrowImpl borrowImpl = new BorrowImpl();

	public PageBean findBorrows(int currentPage, int pageSize) {
		return borrowImpl.findBorrowsByPages(currentPage, pageSize);
	}

	public PageBean findItem(int currentPage, int pageSize, String item) {

		return borrowImpl.findItemPages(currentPage, pageSize, item);
	}

	public PageBean findOverdateBorrows(int currentPage, int pageSize) {

		return borrowImpl.findOverDatePages(currentPage, pageSize);
	}

	public boolean update(Borrow borrow, int r_id, int b_id) {

		// 前提是不是超期的人才可以在这个地方还书
		Date date = borrowImpl.findDate(r_id, b_id);
		long sub = (new Date().getTime() - date.getTime())
				/ (24 * 60 * 60 * 1000);
		if (sub > 30) {
			return false;
		} else {

			ReaderImpl readerImpl = new ReaderImpl();
			Reader reader = readerImpl.findById(r_id);
			// 得到书
			BookImpl bookImpl = new BookImpl();
			Book book = bookImpl.find(b_id);
			book.setB_surplus(book.getB_surplus() + 1);
			reader.setCur_num(reader.getCur_num() - 1);
			bookImpl.update(book);
			readerImpl.updateSurPlus(reader);

			this.borrowImpl.update(borrow);
			return true;
		}
	}

	public PageBean findById(int currentPage, int pageSize, int r_id) {
		return this.borrowImpl.findById(currentPage, pageSize, r_id);
	}

	public boolean addBorrow(int r_id, int b_id) throws FineException{
		boolean flag=true;
		//这里要对图书的库存 做出处理  能借书的前提是b_surplus大于等于一
		 //借了之后响应的图书的b_surplus也要减少一
		//不仅是图书的要变化  读者也要发生变化  读者的可借数书-1  读者的已借书数+1 
		//得到读者
		ReaderImpl readerImpl = new ReaderImpl();
		Reader reader = readerImpl.findById(r_id); 
		//得到书
		BookImpl bookImpl = new BookImpl();
		Book book = bookImpl.find(b_id);
		if(reader==null||book==null){
		   throw new FineException("请仔细检查有没有输错读者编号或书籍编号");
		}else{
			if(book.getB_surplus()>=1 &&reader.getCur_num()<reader.getR_num()){
				book.setB_surplus(book.getB_surplus()-1);
				reader.setCur_num(reader.getCur_num()+1);
				bookImpl.update(book);
				readerImpl.updateSurPlus(reader);
				this.borrowImpl.addBorrow(r_id,b_id);
				flag=true;
				return flag;
			}else{
				flag=false;
				return flag;
			}
					
		}
	 
		
		
		
	}

	public void updateNo(Borrow borrow, int r_id, int b_id) {
		ReaderImpl readerImpl = new ReaderImpl();
		Reader reader = readerImpl.findById(r_id);
		// 得到书
		BookImpl bookImpl = new BookImpl();
		Book book = bookImpl.find(b_id);
		book.setB_surplus(book.getB_surplus() - 1);
		reader.setCur_num(reader.getCur_num() + 1);
		bookImpl.update(book);
		readerImpl.updateSurPlus(reader);

		this.borrowImpl.update(borrow);

	}
}
