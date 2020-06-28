package downloader;

import java.sql.SQLException;
import java.util.List;

import com.chilkatsoft.CkScp;
import com.chilkatsoft.CkSsh;

import connection.server.ServerUtils;
import model.Configuration;
import utils.loader.ConfigsLoader;

public class ScpDownload {
	public static boolean download(Configuration config) {
		String hostname = config.getHostname();
		int port = config.getPort();
		String username = config.getUsername();
		String password = config.getPassword();
		String remote_dir = config.getRemote_dir();
//		String local_dir = config.getLocal_dir();
		String local_dir = "/data";
		String[] fileNameExtensions = {"txt", "xlsx"};

		CkSsh ssh = ServerUtils.connectToServer(hostname, port, username, password);
		CkScp scp = ServerUtils.createScpTunnel(ssh);
		
		if (scp != null) {
//			boolean isSuccess = true;
			for (String ext : fileNameExtensions) {
				specifyFileType(ext, scp);
				scp.SyncTreeDownload(remote_dir, local_dir, 2, false);
			}
			ServerUtils.disconnectServerQuietly(ssh);
			return true;
		}
		return false;

	}
	private static void specifyFileType(String ext, CkScp scp) {
		scp.put_SyncMustMatch("sinhvien*."+ext);
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		List<Configuration> configurations = ConfigsLoader.loadAllConfigs();
		for (Configuration config : configurations) {
			System.out.println(download(config));
		}
	}

}
