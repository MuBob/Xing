package services;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.DataSourceManager;

import entitys.Staff;

public class LoginImp {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());
	public List<Staff> getAll(String sql, String id) {
		// TODO Auto-generated method stub
		List<Staff> list = null;
		try {
			list = runner.query(sql,
					new BeanListHandler<Staff>(Staff.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

}
