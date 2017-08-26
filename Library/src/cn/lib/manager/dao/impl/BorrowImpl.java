package cn.lib.manager.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import cn.lib.manager.domain.Book;
import cn.lib.manager.domain.Borrow;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.domain.Reader;
import cn.lib.utils.JdbcUtils;

public class BorrowImpl {
	private QueryRunner qr = JdbcUtils.getQuerrRunner();
     
	
	public void update(Borrow borrow){
		String sql="update borrow set lend_date=?,back_date=?,isback=?where bor_id=?";
		try {
			qr.update(sql,borrow.getLend_date(),borrow.getBack_date(),
					borrow.getIsback(),borrow.getBor_id() );
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public Date findDate(int r_id,int b_id){
		String sql="SELECT lend_date FROM borrow WHERE r_id=? AND b_id=?;";
		try {
			List<Borrow> list = qr.query(sql, new BeanListHandler<Borrow>(Borrow.class),r_id,b_id);
			if(list.size()==0){
				return null;
			}else{
				return list.get(0).getLend_date();
			}
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	/**
	 * 查询所有借阅的记录回显到页面上
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageBean findBorrowsByPages(int currentPage, int pageSize) {

		try {
			PageBean pagebean = new PageBean();
			pagebean.setCurrentPage(currentPage);
			pagebean.setPageSize(pageSize);
			// 这里返回总记录数目
			String sql = "select count(*) from borrow";
			Number num = (Number) qr.query(sql, new ScalarHandler());
			int totalRecord = num.intValue();
			pagebean.setTotalRecord(totalRecord);
			// 得到的pagebean里面的pagebeanlist里面的borrow类型的变量还没有读者对象跟书本对象
			// 我们先用maplisthandler查询得到borrow表的所有数据
			// 第二步我们根据list里面的每一个map的对象的id来得到reader和book对象 设置进borrow对象中

			// 得到原始beanlist 里面没有两个需要的对象
			sql = "select * from borrow ORDER BY r_id limit ?,?";
			List<Borrow> PagebeanList = (List<Borrow>) qr.query(sql, new BeanListHandler<Borrow>(Borrow.class),
					new Object[] { Integer.valueOf((currentPage - 1) * pageSize), Integer.valueOf(pageSize) });
			// 得到与原始beanlist对应的maplist里面有我们需要用的id
			List<Map<String, Object>> mapList = (List) qr.query(sql, new MapListHandler(),
					new Object[] { Integer.valueOf((currentPage - 1) * pageSize), Integer.valueOf(pageSize) });
			// 遍历maplist根据id得到两个对象
			for (int i = 0; i < mapList.size(); i++) {
				// 得到id
				Integer rid = (Integer) ((Map) (mapList.get(i))).get("r_id");
				Integer bid = (Integer) ((Map) (mapList.get(i))).get("b_id");
				// 根据id得到对象
				Reader reader = findByRid(rid);
				Book book = findByBid(bid);
				// 为原始bean对象设置两个对象
				//得到原始bean对象
				Borrow borrow = PagebeanList.get(i);
				setReader(reader,borrow);
				setBook(book,borrow);
			}
            
			pagebean.setPageBeanList(PagebeanList);
		
			return pagebean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	public PageBean findById(int currentPage, int pageSize,int r_id) {

		try {
			PageBean pagebean = new PageBean();
			pagebean.setCurrentPage(currentPage);
			pagebean.setPageSize(pageSize);
			// 这里返回总记录数目
			String sql = "select count(*) from borrow where r_id=?";
			Number num = (Number) qr.query(sql, new ScalarHandler(),r_id);
			int totalRecord = num.intValue();
			pagebean.setTotalRecord(totalRecord);
			// 得到的pagebean里面的pagebeanlist里面的borrow类型的变量还没有读者对象跟书本对象
			// 我们先用maplisthandler查询得到borrow表的所有数据
			// 第二步我们根据list里面的每一个map的对象的id来得到reader和book对象 设置进borrow对象中

			// 得到原始beanlist 里面没有两个需要的对象
			sql = "select * from borrow where r_id=? ORDER BY bor_id limit ?,?";
			List<Borrow> PagebeanList = (List<Borrow>) qr.query(sql, new BeanListHandler<Borrow>(Borrow.class),
					new Object[] { r_id,Integer.valueOf((currentPage - 1) * pageSize), Integer.valueOf(pageSize) });
			// 得到与原始beanlist对应的maplist里面有我们需要用的id
			List<Map<String, Object>> mapList = (List) qr.query(sql, new MapListHandler(),
					new Object[] { r_id,Integer.valueOf((currentPage - 1) * pageSize), Integer.valueOf(pageSize) });
			// 遍历maplist根据id得到两个对象
			for (int i = 0; i < mapList.size(); i++) {
				// 得到id
				Integer rid = (Integer) ((Map) (mapList.get(i))).get("r_id");
				Integer bid = (Integer) ((Map) (mapList.get(i))).get("b_id");
				// 根据id得到对象
				Reader reader = findByRid(rid);
				Book book = findByBid(bid);
				// 为原始bean对象设置两个对象
				//得到原始bean对象
				Borrow borrow = PagebeanList.get(i);
				setReader(reader,borrow);
				setBook(book,borrow);
			}
            
			pagebean.setPageBeanList(PagebeanList);
		
			return pagebean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	/**
	 * 根据条件查询
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
			
			// 这里返回总记录数目
			String sql = "select * from borrow where r_username LIKE ? ;";
		     List<Borrow> borrows = qr.query(sql, new BeanListHandler<Borrow>(Borrow.class),"%" + item + "%");
			int totalRecord = borrows.size();
			pagebean.setTotalRecord(totalRecord);
			
			// 得到的pagebean里面的pagebeanlist里面的borrow类型的变量还没有读者对象跟书本对象
			// 我们先用maplisthandler查询得到borrow表的所有数据
			// 第二步我们根据list里面的每一个map的对象的id来得到reader和book对象 设置进borrow对象中

			// 得到原始beanlist 里面没有两个需要的对象
			String items="%" + item + "%";
			sql = "select * from borrow where r_username LIKE '"+ items +"' limit ?,?";
			List<Borrow> PagebeanList = (List<Borrow>)JdbcUtils.getQuerrRunner().query(sql, new BeanListHandler<Borrow>(Borrow.class),
					Integer.valueOf((currentPage - 1) * pageSize), Integer.valueOf(pageSize));
			// 得到与原始beanlist对应的maplist里面有我们需要用的id
			List<Map<String, Object>> mapList = (List)JdbcUtils.getQuerrRunner().query(sql, new MapListHandler(),
					new Object[] { Integer.valueOf((currentPage - 1) * pageSize), Integer.valueOf(pageSize) });
			// 遍历maplist根据id得到两个对象
			for (int i = 0; i < mapList.size(); i++) {
				// 得到id
				Integer rid = (Integer) ((Map) (mapList.get(i))).get("r_id");
				Integer bid = (Integer) ((Map) (mapList.get(i))).get("b_id");
				// 根据id得到对象
				Reader reader = findByRid(rid);
				Book book = findByBid(bid);
				// 为原始bean对象设置两个对象
				//得到原始bean对象
				Borrow borrow = PagebeanList.get(i);
				setReader(reader,borrow);
				setBook(book,borrow);
			}
            
			pagebean.setPageBeanList(PagebeanList);
		
			return pagebean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
          
	
	
	
	
	/**
	 * 为borrow对象设置book对象属性
	 * 
	 * @param book
	 */
	private void setBook(Book book,Borrow borrow) {
       //得到对应下表的borrow对象
		borrow.setBook(book);
	}

	/**
	 * 为borrow对象设置reader属性
	 * 
	 * @param reader
	 */
	private void setReader(Reader reader,Borrow borrow) {
                 borrow.setReader(reader);
	}

	/**
	 * 根据书籍id返回书籍对象
	 * 
	 * @return
	 */
	private Book findByBid(Integer bid) {

		try {
			String sql = "select * from book where b_id=?";
			return qr.query(sql, new BeanHandler<Book>(Book.class), bid + "");
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	/**
	 * 根据读者id返回读者对象
	 * 
	 * @return
	 */
	private Reader findByRid(Integer rid) {
		try {
			String sql = "select * from reader where r_id=?";
			return qr.query(sql, new BeanHandler<Reader>(Reader.class), rid + "");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
    /**
     * 查询所有超期未还书籍读者
     * @param currentPage
     * @param pageSize
     * @param item
     * @return
     */
	public PageBean findOverDatePages(int currentPage, int pageSize) {
		try {
			PageBean pagebean = new PageBean();
			pagebean.setCurrentPage(currentPage);
			pagebean.setPageSize(pageSize);
			
			// 这里返回总记录数目
			String sql = "SELECT * FROM borrow where bor_id in(select bor_id from borrow where  datediff(NOW(),lend_date)>30 && isback='否')";
		     List<Borrow> borrows = qr.query(sql, new BeanListHandler<Borrow>(Borrow.class));
			int totalRecord = borrows.size();
			pagebean.setTotalRecord(totalRecord);
			
			// 得到的pagebean里面的pagebeanlist里面的borrow类型的变量还没有读者对象跟书本对象
			// 我们先用maplisthandler查询得到borrow表的所有数据
			// 第二步我们根据list里面的每一个map的对象的id来得到reader和book对象 设置进borrow对象中

			// 得到原始beanlist 里面没有两个需要的对象
			
			sql = "SELECT * FROM borrow where bor_id in(select bor_id from borrow where  datediff(NOW(),lend_date)>30 && isback='否') limit ?,?";
			List<Borrow> PagebeanList = (List<Borrow>)JdbcUtils.getQuerrRunner().query(sql, new BeanListHandler<Borrow>(Borrow.class),
					Integer.valueOf((currentPage - 1) * pageSize), Integer.valueOf(pageSize));
			// 得到与原始beanlist对应的maplist里面有我们需要用的id
			List<Map<String, Object>> mapList = (List)JdbcUtils.getQuerrRunner().query(sql, new MapListHandler(),
					new Object[] { Integer.valueOf((currentPage - 1) * pageSize), Integer.valueOf(pageSize) });
			// 遍历maplist根据id得到两个对象
			for (int i = 0; i < mapList.size(); i++) {
				// 得到id
				Integer rid = (Integer) ((Map) (mapList.get(i))).get("r_id");
				Integer bid = (Integer) ((Map) (mapList.get(i))).get("b_id");
				// 根据id得到对象
				Reader reader = findByRid(rid);
				Book book = findByBid(bid);
				// 为原始bean对象设置两个对象
				//得到原始bean对象
				Borrow borrow = PagebeanList.get(i);
				setReader(reader,borrow);
				setBook(book,borrow);
			}
            
			pagebean.setPageBeanList(PagebeanList);
		
			return pagebean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void addBorrow(int r_id, int b_id) {
		
		try {
			
			String sql="select * from reader where r_id=?";
			Reader reader = qr.query(sql, new BeanHandler<Reader>(Reader.class),r_id);
			sql="insert into borrow(bor_id,r_id,b_id,r_username,lend_date,back_date,isback)values(?,?,?,?,?,?,?)";
			qr.update(sql,0,r_id,b_id,reader.getR_username(),new Date(),null,"否");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
			
		}
		
	}

	
	
	
}
