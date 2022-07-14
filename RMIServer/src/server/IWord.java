package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IWord extends Remote {
	String getWellComeMessage() throws RemoteException;

	boolean checkUsername(String username) throws RemoteException;

	String login(String username, String password) throws RemoteException;

	String uploadFile(String id, String filename, byte[] data) throws RemoteException;

	String addText(String id, String line) throws RemoteException;

	int getNums(String id) throws RemoteException;

	int getNum(String id) throws RemoteException;

	int getNumList(String id) throws RemoteException;

	String[] getWords(String id) throws RemoteException;

	int[] getListNumber(String id) throws RemoteException;
}
