package model;

import java.util.Date;

public class Log {
	private int id_log;
	private int id_config;
	private String source;
	private Date time_insert;
	private int row_count;
	private String current_action;
	private String status;

	public Log(int id_log, int id_config, String source, Date time_insert, int row_count, String current_action,
			String status) {
		super();
		this.id_log = id_log;
		this.id_config = id_config;
		this.source = source;
		this.time_insert = time_insert;
		this.row_count = row_count;
		this.current_action = current_action;
		this.status = status;
	}

	public Log() {
		super();
	}

	public int getId_log() {
		return id_log;
	}

	public void setId_log(int id_log) {
		this.id_log = id_log;
	}

	public int getId_config() {
		return id_config;
	}

	public void setId_config(int id_config) {
		this.id_config = id_config;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getTime_insert() {
		return time_insert;
	}

	public void setTime_insert(Date time_insert) {
		this.time_insert = time_insert;
	}

	public int getRow_count() {
		return row_count;
	}

	public void setRow_count(int row_count) {
		this.row_count = row_count;
	}

	public String getCurrent_action() {
		return current_action;
	}

	public void setCurrent_action(String current_action) {
		this.current_action = current_action;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Log [id_log=" + id_log + ", id_config=" + id_config + ", source=" + source + ", time_insert="
				+ time_insert + ", row_count=" + row_count + ", current_action=" + current_action + ", status=" + status
				+ "]";
	}
}
