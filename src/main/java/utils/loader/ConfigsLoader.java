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
}
