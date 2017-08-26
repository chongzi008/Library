package cn.lib.manager.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import cn.lib.manager.domain.Announcement;
import cn.lib.manager.domain.PageBean;
import cn.lib.utils.JdbcUtils;
import cn.lib.utils.PageUtils;

public class AnnouncementImpl {
private QueryRunner qr=JdbcUtils.getQuerrRunner();
	public Announcement showAnnouncement() { 
		
		String sql="select * from announcement" +
				" where a_time=(SELECT MAX(a_time) FROM announcement) ";
		try {
			return qr.query(sql, new BeanHandler<Announcement>(Announcement.class));
		} catch (SQLException e) {
			
			throw new RuntimeException();
		}
		
	}
	public void addAnnounce(String content) {
		
		String sql="insert into announcement(a_id,a_time,a_content)values(?,?,?)";
				
		try {
			 qr.update(sql,0,new Date(),content);
		} catch (SQLException e) {
			
			throw new RuntimeException();
		}
	}
	public PageBean findAllByPages(int currentPage, int pageSize) {
		
		return PageUtils.findByAlls(currentPage, pageSize, "announcement", Announcement.class);
	}
	public void update(String content, int aid) {
	
		try {
			String sql = "update announcement set a_time=?,a_content=? where a_id=?";
			Object[] params = {new Date(),content,aid};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	public void delete(int aid) {
		try {
			String sql = "delete from announcement  where a_id=?";
			Object[] params = {aid};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException();
		}	
	}
	public Announcement findById(int a_id) {
		try {
			String sql = "select * from announcement  where a_id=?";
			Object[] params = {a_id};
			return qr.query(sql, new BeanHandler<Announcement>(Announcement.class), params);
		} catch (SQLException e) {
			throw new RuntimeException();
		}	
	}

}
