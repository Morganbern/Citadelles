package Multijoueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private static final String SERVER_IP = "172.16.80.177";
	private static final int SERVER_PORT = 9090;
	private static BufferedReader in;
	private static PrintWriter out;
	
	public static void main(String [] args) throws IOException {
		Socket socket = new Socket(SERVER_IP, SERVER_PORT);
		
		out = new PrintWriter(socket.getOutputStream(), true);
		out.println("is it working");
		out.flush();
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		String str = in.readLine();
		System.out.println("server: " + str);
		

		in.close();
		socket.close();
		System.exit(0);
	}
}
