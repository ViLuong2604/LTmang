package Client;

import Server.Server;

public class MainRun {
	public static void main(String[] args) {
     Thread t1 = new Thread(new Runnable() {
		public void run() {
			Server sv = new Server();
			
		}
	});
     Thread t2 = new Thread(new Runnable() {
 		public void run() {
 			Client sv = new Client();
 			
 		}
 	});
     t1.start();
     t2.start();
	}
}
