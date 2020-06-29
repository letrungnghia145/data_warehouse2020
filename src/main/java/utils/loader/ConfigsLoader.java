package utils.loader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connecttion.database.DatabaseConnector;
import connecttion.database.DatabaseUtils;
import contants.Strategy;
import model.Configuration;

public class ConfigsLoader {
	public static List<Configuration> loadAllConfigs() throws ClassNotFoundException, SQLException {
		List<Configuration> configurations = new ArrayList<Configuration>();
		Connection connection = DatabaseConnector.getConnection(Strategy.DB_CONTROL);
		String sql = "SELECT * FROM configuration";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Configuration configuration = new Configuration();
			configuration.setColumn_list(rs.getString("column_list"));
			configuration.setColumnToWarehouse(rs.getString("columnToWarehouse"));
			configuration.setColumn_datatype(rs.getString("column_datatype"));
			configuration.setColumn_unique(rs.getString("column_unique"));
			configuration.setDelimiter(rs.getString("delimeter"));
			configuration.setHostname(rs.getString("hostname"));
			configuration.setId_config(rs.getInt("id_config"));
			configuration.setLocal_dir(rs.getString("local_dir"));
			configuration.setPassword(rs.getString("password"));
			configuration.setPort(rs.getInt("port"));
			configuration.setRemote_dir(rs.getString("remote_dir"));
			configuration.setStatus(rs.getString("status"));
			configuration.setUsername(rs.getString("username"));
			configurations.add(configuration);
		}
		DatabaseUtils.closeConnectionQuietly(connection);
		return configurations;
	}

	public static Configuration getConfig(int configId) throws ClassNotFoundException, SQLException {
		Connection connection = DatabaseConnector.getConnection(Strategy.DB_CONTROL);
		String sql = "SELECT * FROM configuration";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		Configuration configuration = null;
		while (rs.next()) {
			configuration = new Configuration();
			configuration.setColumn_list(rs.getString("column_list"));
			configuration.setColumnToWarehouse(rs.getString("columnToWarehouse"));
			configuration.setColumn_datatype(rs.getString("column_datatype"));
			configuration.setColumn_unique(rs.getString("column_unique"));
			configuration.setDelimiter(rs.getString("delimeter"));
			configuration.setHostname(rs.getString("hostname"));
			configuration.setId_config(rs.getInt("id_config"));
			configuration.setLocal_dir(rs.getString("local_dir"));
			configuration.setPassword(rs.getString("password"));
			configuration.setPort(rs.getInt("port"));
			configuration.setRemote_dir(rs.getString("remote_dir"));
			configuration.setStatus(rs.getString("status"));
			configuration.setUsername(rs.getString("username"));
			return configuration;
		}
		DatabaseUtils.closeConnectionQuietly(connection);
		return configuration;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Configuration config = ConfigsLoader.getConfig(1);
		System.out.println(config);
	}
}
