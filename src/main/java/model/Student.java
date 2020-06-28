package model;

import java.sql.Date;

public class Student {
	private int num;
	private String id;
	private String lastname;
	private String firstname;
	private Date dob;
	private String class_id;
	private String class_name;
	private String phone;
	private String email;
	private String home_town;
	private String note;

	public Student(int num, String id, String lastname, String firstname, Date dob, String class_id, String class_name,
			String phone, String email, String home_town, String note) {
		super();
		this.num = num;
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.dob = dob;
		this.class_id = class_id;
		this.class_name = class_name;
		this.phone = phone;
		this.email = email;
		this.home_town = home_town;
		this.note = note;
	}

	public Student() {
		super();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHome_town() {
		return home_town;
	}

	public void setHome_town(String home_town) {
		this.home_town = home_town;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + ", dob="
				+ dob + ", class_id=" + class_id + ", class_name=" + class_name + ", phone=" + phone + ", email="
				+ email + ", home_town=" + home_town + ", note=" + note + "]";
	}
}
