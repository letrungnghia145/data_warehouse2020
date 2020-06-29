package utils.generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Configuration;

public class TableGenerator {
	public static boolean generate(Configuration config, Connection connection) throws SQLException {
		String[] fieldsName = config.getColumn_list().trim().split(",");
		String[] datatype = config.getColumn_datatype().split(",");
		String[] fieldsWithUnique = config.getColumn_unique().split(",");
		return generate(null, fieldsName, datatype, fieldsWithUnique, connection);
	}

	public static boolean generate(String tablename, String[] fieldsName, String[] datatype, String[] fieldsWithUnique,
			Connection connection) throws SQLException {
//		String[] fieldsName = config.getColumn_list().trim().split(",");
//		String[] datatype = config.getColumn_datatype().split(",");
//		String[] fieldsWithUnique = config.getColumn_unique().split(",");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("CREATE TABLE IF NOT EXISTS " + tablename + " (\n");

		for (int i = 0; i < fieldsName.length; i++) {
			stringBuilder.append("\t`" + fieldsName[i] + "`\t" + datatype[i] + ",\n");
		}

		stringBuilder.append("PRIMARY KEY (`" + fieldsName[0] + "`)" + createUniqueContrains(fieldsWithUnique)
				+ ")  ENGINE=InnoDB;");
		String sql = stringBuilder.toString();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		int result = preparedStatement.executeUpdate();
		if (result == 0)
			return true;
		return false;
	}

	private static StringBuilder createUniqueContrains(String[] fields) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String field : fields) {
			stringBuilder.append(",\nUNIQUE INDEX `" + field.toUpperCase() + "_UNIQUE` (`" + field + "` ASC) VISIBLE");
		}
		return stringBuilder;
	}
}
