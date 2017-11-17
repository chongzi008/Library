package test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.lib.manager.dao.impl.ManagerImpl;
import cn.lib.manager.dao.impl.ReaderImpl;
import cn.lib.manager.domain.Book;
import cn.lib.manager.domain.BookType;
import cn.lib.manager.domain.Borrow;
import cn.lib.manager.domain.Manager;
import cn.lib.manager.domain.PageBean;
import cn.lib.manager.domain.Reader;
import cn.lib.manager.service.ManagerService;
import cn.lib.manager.service.ReaderService;
import cn.lib.utils.JdbcUtils;

public class test1 {
	private QueryRunner qr = JdbcUtils.getQuerrRunner();
	private ManagerService manage = new ManagerService();
	private ManagerImpl mimpl = new ManagerImpl();

	@Test
	public void test1() {
		Reader reader=new Reader();
		reader.setR_id(20141401);
		
		Reader reader2 = new ReaderService().find(reader);
		System.out.println("++++++"+reader2.getR_password());
		
		
		System.out.println("这是我的修改++++++++++++++++++++++");

	}

	
}
