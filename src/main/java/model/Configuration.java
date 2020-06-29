package model;

public class Configuration {
	private int id_config;
	private String hostname;
	private int port;
	private String username;
	private String password;
	private String remote_dir;
	private String local_dir;
	private String column_list;
	private String columnToWarehouse;
	private String column_datatype;
	private String column_unique;
	private String delimiter;
	private String status;

	public Configuration(int id_config, String hostname, int port, String username, String password, String remote_dir,
			String local_dir, String column_list, String columnToWarehouse, String column_datatype,
			String column_unique, String delimiter, String status) {
		super();
		this.id_config = id_config;
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
		this.remote_dir = remote_dir;
		this.local_dir = local_dir;
		this.column_list = column_list;
		this.columnToWarehouse = columnToWarehouse;
		this.column_datatype = column_datatype;
		this.column_unique = column_unique;
		this.delimiter = delimiter;
		this.status = status;
	}

	public Configuration() {
		super();
	}

	public int getId_config() {
		return id_config;
	}

	public void setId_config(int id_config) {
		this.id_config = id_config;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemote_dir() {
		return remote_dir;
	}

	public void setRemote_dir(String remote_dir) {
		this.remote_dir = remote_dir;
	}

	public String getLocal_dir() {
		return local_dir;
	}

	public void setLocal_dir(String local_dir) {
		this.local_dir = local_dir;
	}

	public String getColumn_list() {
		return column_list;
	}

	public void setColumn_list(String column_list) {
		this.column_list = column_list;
	}

	public String getColumnToWarehouse() {
		return columnToWarehouse;
	}

	public void setColumnToWarehouse(String columnToWarehouse) {
		this.columnToWarehouse = columnToWarehouse;
	}

	public String getColumn_datatype() {
		return column_datatype;
	}

	public void setColumn_datatype(String column_datatype) {
		this.column_datatype = column_datatype;
	}

	public String getColumn_unique() {
		return column_unique;
	}

	public void setColumn_unique(String column_unique) {
		this.column_unique = column_unique;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Configuration [id_config=" + id_config + ", hostname=" + hostname + ", port=" + port + ", username="
				+ username + ", password=" + password + ", remote_dir=" + remote_dir + ", local_dir=" + local_dir
				+ ", column_list=" + column_list + ", columnToWarehouse=" + columnToWarehouse + ", column_datatype="
				+ column_datatype + ", column_unique=" + column_unique + ", delimiter=" + delimiter + ", status="
				+ status + "]";
	}
}
