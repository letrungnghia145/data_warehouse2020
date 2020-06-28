package connecttion.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtils {
	public static void closeConnectionQuietly(Connection connection) throws SQLException {
		connection.close();
	}

	public static void rollBack(Connection connection) throws SQLException {
		connection.rollback();
	}
}
