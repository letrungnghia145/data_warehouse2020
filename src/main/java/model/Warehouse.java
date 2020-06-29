package model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import utils.loader.ConfigsLoader;

public class Warehouse {
	private String id;
	private String fullname;
	private Date birthday;
	private String classid;
	private String phone;

	public Warehouse(String id, String fullname, Date birthday, String classid, String phone) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.birthday = birthday;
		this.classid = classid;
		this.phone = phone;
	}

	public Warehouse() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", fullname=" + fullname + ", birthday=" + birthday + ", classid=" + classid
				+ ", phone=" + phone + "]";
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		List<Configuration> configs = ConfigsLoader.loadAllConfigs();
		for (Configuration configuration : configs) {
			System.out.println(configuration.getColumnToWarehouse());
		}
	}
}
