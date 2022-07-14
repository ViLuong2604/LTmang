package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Server.Server;

public class Client {
	Socket socket;
	BufferedReader netIn, userIn;
	PrintWriter netOut;
	String request, respon;

	public Client() {
		try {
			socket = new Socket("127.0.0.1", Server.PORT);
			netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			userIn = new BufferedReader(new InputStreamReader(System.in));
			netOut = new PrintWriter(socket.getOutputStream());
			conn();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void conn() {
		try {
			String request, respone, username, password;
			boolean success = false;
			// login
			while (true) {
				System.out.println(netIn.readLine());
				username = userIn.readLine().trim();
				netOut.println(username);
				netOut.flush();
				netOut.flush();
				if (username.toUpperCase().equalsIgnoreCase("QUIT")) {
					netOut.println("QUIT");
					netOut.flush();
					netIn.close();
					netOut.close();
					break;
				}
				respone = netIn.readLine();
				if (respone.startsWith("ERROR")) {
					System.out.println(respone);
					continue;
				}
				System.out.println(respone);
				password = userIn.readLine();
				netOut.println(password);
				netOut.flush();
				if (password.toUpperCase().equalsIgnoreCase("QUIT")) {
					netOut.println("QUIT");
					netOut.flush();
					netIn.close();
					netOut.close();
					break;
				}
				String lastRe = netIn.readLine();
				if (lastRe.startsWith("ERROR")) {
					System.out.println(respone);
					continue;
				} else {
					System.out.println(lastRe);
					success = true;
					break;
				}
			}
			while (true && success) {
				request = userIn.readLine();
				netOut.println(request);
				netOut.flush();
				if (request.equalsIgnoreCase("QUIT")) {
					netIn.close();
					netOut.close();
					userIn.close();
					break;
				}
				respone = netIn.readLine();
				System.out.println(respone);
				while (!(respone = netIn.readLine()).equals(" ")) {
					System.out.println(respone);
				}
			}
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
