package Test;

import java.sql.SQLException;
import java.util.List;

import com.chilkatsoft.CkScp;
import com.chilkatsoft.CkSsh;

import connection.server.ServerUtils;
import model.Configuration;
import utils.loader.ConfigsLoader;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		List<Configuration> configurations = ConfigsLoader.loadAllConfigs();
		for (Configuration config : configurations) {
			String hostname = config.getHostname();
			int port = config.getPort();
			String username = config.getUsername();
			String password = config.getPassword();
//			String remote_dir = config.getRemote_dir();
			String remote_dir = config.getRemote_dir()+"/sinhvien_chieu_nhom11.xlsx";
//			String local_dir = config.getLocal_dir();
			String local_dir = "/data/sinhvien_chieu_nhom11.xlsx";
//			String[] fileNameExtensions = {"txt", "xlsx"};

			CkSsh ssh = ServerUtils.connectToServer(hostname, port, username, password);
			CkScp scp = ServerUtils.createScpTunnel(ssh);
			
			if (scp != null) {
				System.out.println("Ok");
				System.out.println(remote_dir);
				System.out.println(scp.DownloadFile(remote_dir, local_dir));
//				boolean isSuccess = true;
//				for (String ext : fileNameExtensions) {
//					specifyFileType(ext, scp);
//					scp.SyncTreeDownload(remote_dir, local_dir, 2, false);
//				}
				ServerUtils.disconnectServerQuietly(ssh);
//				return true;
				return;
			}
			System.out.println("error");
//			return false;
		}
	}
}
