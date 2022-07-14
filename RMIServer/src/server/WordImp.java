package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class WordImp extends UnicastRemoteObject implements IWord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String, OneConnect> map = new HashMap<>();
	ArrayList<User> users = new ArrayList<>();

	protected WordImp() throws RemoteException {
		super();
		users.add(new User("user1", "@bc"));
		users.add(new User("user2", "@bc"));
	}

	@Override
	public boolean checkUsername(String username) throws RemoteException {
		for (User user : users) {
			if (user.getUsername().equals(username))
				return true;
		}
		return false;
	}

	@Override
	public String getWellComeMessage() throws RemoteException {

		return "wellcome to word proccessing";
	}

	@Override
	public String login(String username, String password) throws RemoteException {
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				map.put(username, new OneConnect());
				return user.getUsername();
				
			}
				
		}
		return null;
	}

	@Override
	public String uploadFile(String id, String filename, byte[] data) throws RemoteException {
		
		return map.get(id).uploadFile(id, filename, data);
	}

	@Override
	public String addText(String id, String line) throws RemoteException {
		
		return map.get(id).addText(id, line);
	}

	@Override
	public int getNums(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNum(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumList(String id) throws RemoteException {
		//
		return 0;
	}

	@Override
	public String[] getWords(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getListNumber(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
