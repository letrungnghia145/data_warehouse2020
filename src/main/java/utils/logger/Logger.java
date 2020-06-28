package utils.logger;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connecttion.database.DatabaseConnector;
import connecttion.database.DatabaseUtils;
import contants.ActionType;
import contants.Status;
import contants.Strategy;
import model.Configuration;
import model.Log;

public class Logger {
	public static void updateStatusLog(Status status, int idLog) throws ClassNotFoundException, SQLException {
		Connection connection = DatabaseConnector.getConnection(Strategy.DB_CONTROL);
		String sql = "UPDATE `db_control`.`log` SET `status` = ? WHERE (`id_log` = ?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, status.name());
		statement.setInt(2, idLog);
		statement.executeUpdate();
	};

	public static void updateCurrentAction(ActionType action, int idLog) throws ClassNotFoundException, SQLException {
		Connection connection = DatabaseConnector.getConnection(Strategy.DB_CONTROL);
		String sql = "UPDATE `db_control`.`log` SET `current_action` = ? WHERE (`id_log` = ?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, action.name());
		statement.setInt(2, idLog);
		statement.executeUpdate();
	}

	public static void downloadStatusLogging(Configuration configuration) throws ClassNotFoundException, SQLException {
		File local_directory = new File(configuration.getLocal_dir());
		File[] files = local_directory.listFiles();
		for (int i = 0; i < files.length; i++) {
			Log log = new Log();
			log.setId_log(i + 1);
			log.setId_config(configuration.getId_config());
			log.setSource_dir(configuration.getLocal_dir());
			log.setSource_name(files[i].getName());
			log.setTime_insert(new Date(System.currentTimeMillis()));
			log.setCurrent_action(ActionType.DOWNLOAD.name());
			log.setStatus(Status.ER.name());
			insertLog(log);
		}
	}

	private static boolean insertLog(Log log) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			connection = DatabaseConnector.getConnection(Strategy.DB_CONTROL);
			String sql = "INSERT INTO log VALUES(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, log.getId_log());
			preparedStatement.setInt(2, log.getId_config());
			preparedStatement.setString(3, log.getSource_dir());
			preparedStatement.setString(4, log.getSource_name());
			preparedStatement.setDate(5, log.getTime_insert());
			preparedStatement.setString(6, log.getCurrent_action());
			preparedStatement.setString(7, log.getStatus());
			preparedStatement.executeUpdate();
			DatabaseUtils.closeConnectionQuietly(connection);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			DatabaseUtils.closeConnectionQuietly(connection);
		}
	}

	public static void main(String[] args) {
		System.out.println(new Date(System.currentTimeMillis()));
	}
}
