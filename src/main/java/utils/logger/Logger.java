package utils.logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connecttion.database.DatabaseConnector;
import contants.Status;
import contants.Strategy;

public class Logger {
	public static void updateStatusLog(Status status, int idLog) throws ClassNotFoundException, SQLException {
		Connection connection = DatabaseConnector.getConnection(Strategy.DB_CONTROL);
		String sql = "UPDATE `db_control`.`log` SET `status` = ? WHERE (`id_log` = ?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, status.name());
		statement.setInt(2, idLog);
		statement.executeUpdate();
	};
}
