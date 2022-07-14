package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class WordServer {
	
	public static void main(String[] args) throws RemoteException {
		Registry registry= LocateRegistry.createRegistry(1234);
		IWord word= new WordImp();
		registry.rebind("word", word);
	}
}
