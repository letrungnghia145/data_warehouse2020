package connection.server;

import com.chilkatsoft.CkGlobal;
import com.chilkatsoft.CkScp;
import com.chilkatsoft.CkSsh;

public class ServerUtils {
	public static void disconnectServerQuietly(CkSsh ssh) {
		try {
			ssh.Disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static CkScp createScpTunnel(CkSsh ssh) {
		CkScp scp = new CkScp();
		if (ssh != null) {
			scp.UseSsh(ssh);
			return scp;
		}
		return null;
	}

	public static CkSsh connectToServer(String hostname, int port, String username, String password) {
		loadChilkat();
		CkSsh ssh = new CkSsh();
		ssh.put_IdleTimeoutMs(6000);
		ssh.Connect(hostname, port);
		if (!ssh.AuthenticatePw(username, password)) {
			System.out.println(ssh.get_AuthFailReason());
			return null;
		}
		return ssh;
	}

	private static void loadChilkat() {
		try {
			System.loadLibrary("chilkat");
			CkGlobal ckGlobal = new CkGlobal();
			ckGlobal.UnlockBundle("key");
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
	}
}
