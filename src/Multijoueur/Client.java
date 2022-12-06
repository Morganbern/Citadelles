package Multijoueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private static final String SERVER_IP = "172.16.80.177";
	private static final int SERVER_PORT = 9090;
	private static BufferedReader in;
	private static PrintWriter out;
	
	public static void main(String [] args) throws IOException {
		Socket socket = new Socket(SERVER_IP, SERVER_PORT);
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		Scanner console = new Scanner(System.in);
		String msg2Send;
		String msg2Receive;
		
		while(true) {
			
			msg2Receive = in.readLine();
			if (msg2Receive!="" && msg2Receive.contains("ToAll: ")) {
				System.out.println("[Server] : " + msg2Receive);
				while(!in.readLine().equals("OK")) {
					msg2Send = console.nextLine();
					out.println(msg2Send);
					out.flush();
				}
			}

				if(msg2Send.equals("quitter")) {
					break;
				}

			}
		}
		
		console.close();
		in.close();
		socket.close();
		System.exit(0);
	}
}
