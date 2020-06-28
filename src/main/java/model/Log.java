package model;

import java.sql.Date;

/**
 * @author pyn_x
 *
 */
public class Log {
	private int id_log;
	private int id_config;
	private String source_dir;
	private String source_name;
	private Date time_insert;
	private String current_action;
	private String status;

	public Log(int id_log, int id_config, String source_dir, String source_name, Date time_insert,
			String current_action, String status) {
		super();
		this.id_log = id_log;
		this.id_config = id_config;
		this.source_dir = source_dir;
		this.source_name = source_name;
		this.time_insert = time_insert;
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

	public String getSource_dir() {
		return source_dir;
	}

	public void setSource_dir(String source_dir) {
		this.source_dir = source_dir;
	}

	public String getSource_name() {
		return source_name;
	}

	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}

	public Date getTime_insert() {
		return time_insert;
	}

	public void setTime_insert(Date time_insert) {
		this.time_insert = time_insert;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrent_action() {
		return current_action;
	}

	public void setCurrent_action(String current_action) {
		this.current_action = current_action;
	}

	@Override
	public String toString() {
		return "Log [id_log=" + id_log + ", id_config=" + id_config + ", source_dir=" + source_dir + ", source_name="
				+ source_name + ", time_insert=" + time_insert + ", current_action=" + current_action + ", status="
				+ status + "]";
	}
}