package connecttion.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import contants.Strategy;
import utils.enviroment.Enviroment;

public class DatabaseConnector {

	public static Connection getConnection(Enviroment enviroment, Strategy strategy) throws ClassNotFoundException, SQLException {
		String driver = enviroment.getStringProp("driver");
		String url = enviroment.getStringProp(strategy.name().toLowerCase());
		String user = enviroment.getStringProp("username");
		String password = enviroment.getStringProp("password");
		return getConnection(driver, url, user, password);
	}

	public static Connection getConnection(String driver, String url, String user, String password)
			throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}
