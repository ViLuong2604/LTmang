package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class OneConnect extends Thread {
	Socket socket;
	BufferedReader netIn;
	PrintWriter netOut;
	String request;
	Dao dao;
    String command,params;
	public OneConnect(Socket socket) {
		try {
			this.socket = socket;
			netIn = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			netOut = new PrintWriter(this.socket.getOutputStream());
			dao = new Dao();
			System.out.println("Server khoi dong");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
//		String username, password;
//		User user = null;
		try {
			String username, password;
			User user = null;
			// login
			 while (true) {
				netOut.println("Nhap username: ");
				netOut.flush();
				username = netIn.readLine();
				if (username.toUpperCase().equalsIgnoreCase("QUIT")) {
					netIn.close();
					netOut.close();
					break;
				}
				if (dao.findUsername(username.trim())) {
					netOut.println("Nhap password: ");
					netOut.flush();
					password = netIn.readLine();
					if (password.toUpperCase().equalsIgnoreCase("QUIT")) {
						netIn.close();
						netOut.close();
						break;
					}
					user = dao.login(username, password);
					if (user != null) {
						netOut.println("Login thanh cong!");
						netOut.flush();
						break;
					} else {
						netOut.println("ERROR: password khong chinh xac.. Nhap lai");
						netOut.flush();
						continue ;
					}
				} else {
					netOut.println("ERROR: " + username + " not exist in system!");
					netOut.flush();
					continue ;
				}
			}

//			}
		
		while (true && user != null) {
			request = netIn.readLine();
			if (request.equalsIgnoreCase("QUIT")) {
				netIn.close();
				netOut.close();
				break;
			}
			reqAnal(request);
			switch (command) {
			case "TEST":
				netOut.println("da vao day " + params);
				netOut.flush();
				continue;

			default:
				break;
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void reqAnal (String input) {
		StringTokenizer st = new StringTokenizer(input);
		command = st.nextToken();
		switch (command) {
		case "TEST":
			params = st.nextToken();
			break;

		default:
			break;
		}
	}
}
