package utils.generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Configuration;
import utils.loader.ConfigsLoader;

public class TableGenerator {
	public static void gennerate(Configuration config, Connection connection) throws SQLException {
		String[] fieldsName = config.getColumn_list().trim().split(",");
		String[] datatype = config.getColumn_datatype().split(",");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("CREATE TABLE IF NOT EXISTS `db_staging`.`staging`(\n");
		
		for (int i = 0; i < fieldsName.length; i++) {
			stringBuilder.append("\t`"+fieldsName[i]+"`\t"+datatype[i]+",\n");
		}
		
		stringBuilder.append("PRIMARY KEY (`"+fieldsName[0]+"`), UNIQUE INDEX `MSSV_UNIQUE` (`MSSV` ASC) VISIBLE,\r\n" + 
				"UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE,\r\n" + 
				"UNIQUE INDEX `DIENTHOAI_UNIQUE` (`DIENTHOAI` ASC) VISIBLE)  ENGINE=InnoDB;");
		String sql = null;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

//		CREATE TABLE IF NOT EXISTS `db_staging`.`staging`(
//				`STT` INT NOT NULL,
//				`MSSV` TEXT NULL,
//				`HOLOT` TEXT NULL,
//				`TEN` TEXT NULL,
//				`NGAYSINH` DATETIME NULL,
//				`MALOP` TEXT NULL,
//				`TELOP` TEXT NULL,
//				`DIENTHOAI` TEXT NULL,
//				`EMAIL` TEXT NULL,
//				`QUEQUAN` TEXT NULL,
//				`GHICHU` TEXT NULL,
//				PRIMARY KEY (`STT`)) ENGINE=InnoDB;

//		CREATE TABLE `db_staging`.`new_table` (
//				  `STT` INT NOT NULL,
//				  `MSSV` VARCHAR(45) NOT NULL,
//				  `HOLOT` VARCHAR(45) NULL,
//				  `TEN` VARCHAR(45) NULL,
//				  `NGAYSINH` DATETIME NULL,
//				  `MALOP` VARCHAR(45) NULL,
//				  `TENLOP` VARCHAR(45) NULL,
//				  `DIENTHOAI` VARCHAR(45) NULL,
//				  `EMAIL` VARCHAR(45) NOT NULL,
//				  `QUEQUAN` VARCHAR(45) NULL,
//				  `GHICHU` VARCHAR(45) NULL,
//				  PRIMARY KEY (`STT`),
//				  UNIQUE INDEX `MSSV_UNIQUE` (`MSSV` ASC) VISIBLE,
//				  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE,
//				  UNIQUE INDEX `DIENTHOAI_UNIQUE` (`DIENTHOAI` ASC) VISIBLE);
	}
	private static void createUniqueContrains() {
		
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		List<Configuration> configs = ConfigsLoader.loadAllConfigs();
		for (Configuration config : configs) {
			String[] fieldsName = config.getColumn_list().trim().split(",");
			String[] datatype = config.getColumn_datatype().split(",");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("CREATE TABLE IF NOT* EXISTS `db_staging`.`staging`(\n");
			
			for (int i = 0; i < fieldsName.length; i++) {
				stringBuilder.append("\t`"+fieldsName[i]+"`\t"+datatype[i]+",\n");
			}
			
			stringBuilder.append("PRIMARY KEY (`"+fieldsName[0]+"`), UNIQUE INDEX `MSSV_UNIQUE` (`MSSV` ASC) VISIBLE,\r\n" + 
					"UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE,\r\n" + 
					"UNIQUE INDEX `DIENTHOAI_UNIQUE` (`DIENTHOAI` ASC) VISIBLE)  ENGINE=InnoDB;");
			String sql = stringBuilder.toString();
			System.out.println(sql);
			int c = 0;
			System.out.println(c = stringBuilder.indexOf("*"));
			stringBuilder.deleteCharAt(c);
			System.out.println(stringBuilder.toString());
		}
	}
}
