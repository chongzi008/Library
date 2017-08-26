package cn.lib.manager.dao;

import java.util.List;

import cn.lib.manager.domain.Reader;

public interface ReaderDao {
	/**
	 * 添加读者
	 * @param book
	 */
	void add(Reader reader);
     /**
      * 删除读者
      * @param b_id
      */
	void delete(int r_id);
	/**
	 * 修改读者信息
	 * @param book
	 */
	void update(Reader reader);
	/**
	 * 查询所有读者
	 * @return
	 */
	List<Reader>findAll();
}
