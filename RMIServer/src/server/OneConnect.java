package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.rmi.RemoteException;

public class OneConnect {
	private File file;

	public String uploadFile(String id, String filename, byte[] data) throws RemoteException {
		file = new File(filename);

		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.close();
			return "Upload thanh cong";
		} catch (IOException e) {
			return "Upload that bai";
		}
	}

	public String addText(String id, String line) throws RemoteException {
		if (file == null) {
			return "File chua upload";
		}
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.seek(raf.length());
			raf.writeUTF(line);
			raf.close();
			
		} catch (FileNotFoundException e) {
			return "File khong the ghi";
		} catch (IOException e) {
			return "File khong the ghi";
		}
		return null;
	}

	public int getNums(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNum(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumList(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public String[] getWords(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] getListNumber(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
