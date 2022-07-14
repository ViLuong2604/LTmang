package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
	public static void main(String[] args) throws RemoteException, ClassNotFoundException, NotBoundException {
		new RMIClient();
		
	}
}
