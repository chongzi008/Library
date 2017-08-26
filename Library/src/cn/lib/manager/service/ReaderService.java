package cn.lib.manager.service;

import java.util.List;

import cn.lib.manager.dao.impl.ReaderImpl;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.domain.Reader;

/**
 * 读者业务逻辑层
 * 
 * @author ChongZi007
 * 
 */
public class ReaderService {
	private ReaderImpl readerImpl = new ReaderImpl();

	/**
	 * 添加功能
	 * 
	 * @param reader
	 */
	public void add(Reader reader) {
		this.readerImpl.add(reader);

	}

	/**
	 * 删除功能
	 * 
	 * @param r_id
	 */
	public void delete(int r_id) {
		this.readerImpl.delete(r_id);
	}

	/**
	 * 修改功能
	 * 
	 * @param reader
	 */
	public void update(Reader reader) {

		this.readerImpl.update(reader);
	}
	/**
	 * 根据名字查找读者
	 * @param reader
	 */
	public Reader find(Reader reader) {

		return this.readerImpl.find(reader);
	}

	/**
	 * 查询全部读者
	 * 
	 * @return
	 */
	public List<Reader> findAll() {

		return this.readerImpl.findAll();
	}

	public PageBean findAllByPages(int currentPage, int pageSize) {
		return readerImpl.findByAllPages(currentPage, pageSize);
	}

	public PageBean findItem(int currentPage, int pageSize, String item) {
		return readerImpl.findByAllItem(currentPage, pageSize, item, "reader", Reader.class);
	}

}
