package Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Student;

public class TestFileExcel {
	static final int STT = 0;
	static final int MSSV = 1;
	static final int HOLOT = 2;
	static final int TEN = 3;
	static final int NGAYSINH = 4;
	static final int MALOP = 5;
	static final int LOP = 6;
	static final int SDT = 7;
	static final int EMAIL = 8;
	static final int QUEQUAN = 9;
	static final int GHICHU = 10;

	public static Map<String, Student> readData(File[] files) throws IOException {
		Map<String, Student> students = new HashMap<String, Student>();
		for (File file : files) {
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rows = sheet.rowIterator();
//				Iterator<Row> rows = getRowsFromExel(path);
			while (rows.hasNext()) {
				Row row = rows.next();
				if (row.getRowNum() == 0) {
					continue;
				}
				Iterator<Cell> cells = row.cellIterator();
				Student student = new Student();
				while (cells.hasNext()) {
					Cell cell = cells.next();
//						int num = students.size() + 1;
					setProps(cell, student);
				}
				boolean isStandardlize = standardizedData(student, students);
				if (!isStandardlize) {
					continue;
				}
				students.put(student.getId(), student);
			}
			workbook.close();
			inputStream.close();
		}
		return students;
	}

//		private static Iterator<Row> getRowsFromExel(File file) throws IOException {
//			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//			Workbook workbook = new XSSFWorkbook(inputStream);
//			Sheet sheet = workbook.getSheetAt(0);
	//
//			Iterator<Row> rows = sheet.rowIterator();
//			return rows;
//		}

	private static void setProps(Cell cell, Student student) {
		int propsIndex = cell.getColumnIndex();
		Object value = getValue(cell);
		switch (propsIndex) {
		case STT:
			student.setNum(Integer.parseInt(String.valueOf(value)));
//				student.setNum(stt);
			break;
		case MSSV:
			student.setId(String.valueOf(value));
			break;
		case HOLOT:
			student.setLastname(String.valueOf(value));
			break;
		case TEN:
			student.setFirstname(String.valueOf(value));
			break;
		case NGAYSINH:
//				student.setDob(dob);
			break;
		case MALOP:
			student.setClass_id(String.valueOf(value));
			break;
		case LOP:
			student.setClass_name(String.valueOf(value));
			break;
		case SDT:
			student.setPhone(String.valueOf(value));
			break;
		case EMAIL:
			student.setEmail(String.valueOf(value));
			break;
		case QUEQUAN:
			student.setHome_town(String.valueOf(value));
			break;
		case GHICHU:
//				student.setNote((String)value);
			break;

		default:
			break;
		}
	}

	private static Object getValue(Cell cell) {
		CellType cellType = cell.getCellType();
		Object value = new Object();
		switch (cellType) {
		case BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case NUMERIC:
			value = new BigDecimal(cell.getNumericCellValue()).intValue();
			break;
		case STRING:
			value = cell.getStringCellValue();
			break;
		case FORMULA:
			Workbook workbook = cell.getSheet().getWorkbook();
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			value = new BigDecimal(evaluator.evaluate(cell).getNumberValue()).intValue();
			break;
		case BLANK:
		case ERROR:
		case _NONE:
			value = 1;
			break;
		default:
			break;
		}
		return value;
	}

	private static boolean standardizedData(Student student, Map<String, Student> students) {
		if (!students.containsKey(student.getId())) {
			students.put(student.getId(), student);
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		File dir = new File("/data");
		File[] files = dir.listFiles();
		Map<String, Student> data = readData(files);
		for (Student student : data.values()) {
			System.out.println(student);
		}
		data.put("17125070", new Student());
		data.put("73455426", new Student());
		System.out.println(data.size());
	}
}