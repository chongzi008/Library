package cn.lib.manager.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import cn.lib.exception.FineException;
import cn.lib.manager.dao.impl.BookImpl;
import cn.lib.manager.dao.impl.BorrowImpl;
import cn.lib.manager.dao.impl.FineImpl;
import cn.lib.manager.dao.impl.ReaderImpl;
import cn.lib.manager.domain.Book;
import cn.lib.manager.domain.Fine;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.domain.Reader;

/**
 * 罚款的业务逻辑层
 * 
 * @author ChongZi007
 * 
 */
public class FineService {
	private FineImpl fineImpl = new FineImpl();

	public PageBean findAllByPages(int currentPage, int pageSize) {
		return fineImpl.findByAllPages(currentPage, pageSize);
	}

	public PageBean findItem(int currentPage, int pageSize, String item) {
		return fineImpl.findByAllItem(currentPage, pageSize, item, "fine",
				Fine.class);
	}

	public void delete(String f_id) {

		fineImpl.delete(f_id);
	}

	public boolean add(Fine fine, String fineType) throws FineException {
		
		
		
		int num = this.fineImpl.getBookNum(fine.getR_id(), fine.getB_id());
		BookImpl bookImpl = new BookImpl();
		Book book = bookImpl.find(fine.getB_id());
		ReaderImpl readerImpl=new ReaderImpl();
		Reader reader = readerImpl.findById(fine.getR_id());
		Date lendDate = new BorrowImpl().findDate(fine.getR_id(),
				fine.getB_id());
		if(lendDate==null){
			throw new FineException("操作失败，检查输入的读者编号或者书籍编号是否正确");
			
		}else{
			if (fineType.equals("overdate")) {
				// 超期处理

				long sub = (new Date().getTime() - lendDate.getTime())
						/ (24 * 60 * 60 * 1000);
				if (sub <= 30) {
	                return false;
				} else {
					long a = 1;
					double finePrice = BigDecimal.valueOf(book.getB_price())
							.multiply(new BigDecimal(0.01))
							.multiply(new BigDecimal(sub + a-30)).doubleValue();
					fine.setF_num(finePrice);
					book.setB_surplus(book.getB_surplus()+1);
					if(reader.getCur_num()>=1){
					reader.setCur_num(reader.getCur_num()-1);}
					bookImpl.update(book);
					readerImpl.updateSurPlus(reader);
					this.fineImpl.add(fine);
					return true;
				}

			} else if (fineType.equals("loss")) {
				double finePrice = BigDecimal.valueOf(book.getB_price())
						.multiply(new BigDecimal(num)).multiply(new BigDecimal(3))
						.doubleValue();
				fine.setF_num(finePrice);
				
				
				if (book.getB_sum() > 0) {
					book.setB_sum(book.getB_sum() - 1);
					bookImpl.update(book);
				}

				this.fineImpl.add(fine);
				return true;
			}
			return true;

			
		}
		
		
	}

	public void update(Fine fine) {
		this.fineImpl.update(fine);

	}

}
