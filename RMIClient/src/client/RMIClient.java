package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.IWord;

public class RMIClient {
	BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
	IWord word;
	String username;
	String id;

	public RMIClient() throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(1234);
		word = (IWord) registry.lookup("word");
		System.out.println(word.getWellComeMessage());
		conn();
	}

	private void conn() throws RemoteException {

		System.out.println(word.getWellComeMessage());
		String line;
		loop: while (true) {
			try {
				line = scanner.readLine();
				String[] command = line.split(" ");
				if (command.length == 0) {
					System.out.println("Cau lenh khong duoc rong");
					continue;
				}
				String requset = command[0].toUpperCase();

				switch (requset) {
				case "USERNAME": {
					if (command.length > 2) {
						System.out.println("Cau truc cau lenh khong dung");
						continue;
					}
					if (command[1] == null || command[1].equals("")) {
						System.out.println("Hay nhap ten dang nhap	");
						continue;
					}
					boolean checkUsername = word.checkUsername(command[1]);
					if (checkUsername) {
						username = command[1];
						System.out.println("Nguoi dung ton tai");
					} else {
						System.out.println("Nguoi dung khong ton tai");
					}

					break;
				}
				case "PASSWORD": {
					if (command.length > 2) {
						System.out.println("Cau truc cau lenh khong dung");
						continue;
					}
					if (command[1] == null || command[1].equals("")) {
						System.out.println("Hay nhap mat khau");
						continue;
					}
					if (username == null) {
						System.out.println("Hay nhap username truoc");
						continue;
					}
					String id = word.login(username, command[1]);
					if (id != null) {
						this.id = id;
						System.out.println("Dang nhap thanh cong");
					} else {
						System.out.println("Dang nhap thanh cong");
						username = null;
					}
					break;
				}
				case "ADD_FILE": {
					if (id==null) {
						System.out.println("Can dang nhap truoc");
						continue;
					}	
					if (command.length > 2) {
						System.out.println("Cau truc cau lenh khong dung");
						continue;
					}
					if (command[1] == null || command[1].equals("")) {
						System.out.println("Hay file can up");
						continue;
					}					
					File file = new File(command[1]);
					FileInputStream fis= new FileInputStream(file);
					byte[] data=fis.readAllBytes();
					String mess= word.uploadFile(id, file.getName(), data);
					System.out.println(mess);
					break;
				}
				case "ADD_TEXT": {
					if (id==null) {
						System.out.println("Can dang nhap truoc");
						continue;
					}	
					String data="";
					for (int i = 1; i < command.length; i++) {
						data+=command[1];
					}									
					String mess= word.addText(id, data);
					System.out.println(mess);
					break;
				}
				case "GET_NUM": {

					break;
				}
				case "GET_NUMS": {

					break;
				}
				case "GET_NUM_LIST": {

					break;
				}
				case "GET_WORDS": {
					
					break;
				}
				case "QUIT": {
					break loop;
				}
				default:
					System.out.println("Khong tim thay lenh");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}
