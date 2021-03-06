package utils.loader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connecttion.database.DatabaseConnector;
import connecttion.database.DatabaseUtils;
import contants.Status;
import contants.Strategy;
import model.Log;

public class LogsLoader {
	public static List<Log> loadAllLogsWithStatus(Status status, int idConfig)
			throws ClassNotFoundException, SQLException {
		List<Log> logs = new ArrayList<Log>();
		Connection connection = DatabaseConnector.getConnection(Strategy.DB_CONTROL);
		String sql = "SELECT * FROM log WHERE status = ? and id_config= ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, status.name());
		preparedStatement.setInt(1, idConfig);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Log log = new Log();
			log.setId_config(rs.getInt("id_config"));
			log.setId_log(rs.getInt("id_log"));
			log.setSource_dir(rs.getString("source_dir"));
			log.setSource_name(rs.getString("source_name"));
			log.setTime_insert(rs.getDate("time_insert"));
			log.setStatus(rs.getString("status"));
			logs.add(log);
		}
		DatabaseUtils.closeConnectionQuietly(connection);
		return logs;
	}

	public static Log loadLogWithStatus(Status status, int idConfig) throws ClassNotFoundException, SQLException {
		Connection connection = DatabaseConnector.getConnection(Strategy.DB_CONTROL);
		String sql = "SELECT * FROM log WHERE status = ? and id_config = ? LIMIT 1";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, status.name());
		preparedStatement.setInt(2, idConfig);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			Log log = new Log();
			log.setId_config(rs.getInt("id_config"));
			log.setId_log(rs.getInt("id_log"));
			log.setSource_dir(rs.getString("source_dir"));
			log.setSource_name(rs.getString("source_name"));
			log.setTime_insert(rs.getDate("time_insert"));
			log.setStatus(rs.getString("status"));
			DatabaseUtils.closeConnectionQuietly(connection);
			return log;
		}
		DatabaseUtils.closeConnectionQuietly(connection);
		return null;
	}
}
