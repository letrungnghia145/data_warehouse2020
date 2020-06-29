package etl.extract;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import connecttion.database.DatabaseConnector;
import contants.ActionType;
import contants.Status;
import contants.Strategy;
import model.Configuration;
import model.Log;
import model.Student;
import utils.generator.TableGenerator;
import utils.loader.ConfigsLoader;
import utils.loader.LogsLoader;
import utils.logger.Logger;
import utils.reader.Reader;
import utils.reader.XLSXReader;

public class ExtractClass {
	public static void loadLocalToStaging(Configuration configuration)
			throws ClassNotFoundException, SQLException, IOException {
		Log log = LogsLoader.loadLogWithStatus(Status.EXTRACT_READY, configuration.getId_config());
		Connection connection = DatabaseConnector.getConnection(Strategy.DB_STAGING);
		boolean isGennerated = TableGenerator.gennerate(configuration, connection);
		if (isGennerated) {
			Reader reader = new XLSXReader();
			File file = new File(log.getSource_dir() + File.separator + log.getSource_name());
			List<Student> students = reader.readData(file);
			for (Student student : students) {
				insertToStaging(student, connection);
//				if (isInserted) {
				Logger.updateCurrentAction(ActionType.EXTRACT, log.getId_log());
				Logger.updateStatusLog(Status.TRANSFORM_READY, log.getId_log());
//					return true;
//				}
			}
		}
//		return false;
	}

	private static void insertToStaging(Student student, Connection connection) throws SQLException {
		try {
			String sql = "INSERT INTO staging VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, student.getNum());
			preparedStatement.setString(2, student.getId());
			preparedStatement.setString(3, student.getLastname());
			preparedStatement.setString(4, student.getFirstname());
			preparedStatement.setDate(5, student.getDob());
			preparedStatement.setString(6, student.getClass_id());
			preparedStatement.setString(7, student.getClass_name());
			preparedStatement.setString(8, student.getPhone());
			preparedStatement.setString(9, student.getEmail());
			preparedStatement.setString(10, student.getHome_town());
			preparedStatement.setString(11, student.getNote());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
//			return false;
		}
//		return true;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		List<Configuration> configs = ConfigsLoader.loadAllConfigs();
		for (Configuration config : configs) {
//			System.out.println(loadLocalToStaging(config));
			loadLocalToStaging(config);
		}
	}
}
