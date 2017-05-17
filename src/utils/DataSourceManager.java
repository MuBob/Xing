package utils;


import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;


public class DataSourceManager {
	private static DataSource ds;
	static {
		InputStream inputStream = DataSourceManager.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
		Properties properties=new Properties();
		try {
			properties.load(inputStream);
				ds=BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取数据�?
	 * @return
	 */
	public static DataSource getSource(){
		return ds;
	}
}
