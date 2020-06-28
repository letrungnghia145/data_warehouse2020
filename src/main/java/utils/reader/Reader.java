package utils.reader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Student;

public interface Reader {
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
	public List<Student> readData(File[] files) throws IOException;
}
