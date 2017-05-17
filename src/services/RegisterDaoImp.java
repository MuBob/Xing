package services;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import utils.DataSourceManager;

/**
 * @author WangJinming
 *
 */
public class RegisterDaoImp {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());
	public void Savedata(String number,String password){
		String sql = "insert into login(number,password) values(?,?)";
		try {
			runner.update(sql,number,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
