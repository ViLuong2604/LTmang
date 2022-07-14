package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static int PORT =6969;
ServerSocket server;
Socket socket;
public Server() {
	try {
		server = new ServerSocket(PORT);
		socket = server.accept();
		OneConnect one = new OneConnect(socket);
		one.start();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
