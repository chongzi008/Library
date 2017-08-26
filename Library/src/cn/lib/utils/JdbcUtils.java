package cn.lib.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * 数据库工具类
 * @author ChongZi007
 *
 */
public class JdbcUtils {

	//静态加载数据库连接池对象
	private static DataSource dataSource;
	static {
		dataSource = new ComboPooledDataSource();
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 得到QueryRunner对象
	 * @return
	 */
	public static QueryRunner getQuerrRunner() {
		return new QueryRunner(dataSource);
	}
	
	/**
	 * 查询后关闭依次关闭结果集，statement，以及连接对象
	 * @param con
	 * @param stmt
	 * @param rs
	 */
	public static void closeAll(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close(); 
				rs = null; 
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null && !con.isClosed()) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
