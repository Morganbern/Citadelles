package Multijoueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import controleur.Interaction;

public class Client {
	private static final String SERVER_IP =  "localhost";//"172.16.80.177";
	private static final int SERVER_PORT = 9090;
	private static BufferedReader in;
	private static PrintWriter out;
	
	public static void main(String [] args) throws IOException{
		Socket socket = new Socket(SERVER_IP, SERVER_PORT);
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		Scanner console = new Scanner(System.in);
		String msg2Send = "";
		int Int2Send;
		Boolean Bool2Send;
		String msg2Receive;
		
		while(true) {
			
			msg2Receive = in.readLine();
			if (!msg2Receive.equals("") && !msg2Receive.contains("ToAll: ") && !msg2Receive.contains("Msg: ")) {
				
				// On vérifie le format int ou String grâce à un préfix
				if(msg2Receive.contains("Int: ")) {
					msg2Receive.replace("Int", "[Serveur]");
					System.out.println(msg2Receive);
					int oneTime = 1;
					while(!msg2Receive.equals("OK")) {
						if (oneTime != 1) {
							System.out.println(msg2Receive);
						}
						Int2Send = Interaction.lireUnEntier();
						out.println(Int2Send);
						out.flush();
						System.out.println("");
						msg2Receive = in.readLine();
						oneTime +=1;
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else if(msg2Receive.contains("Str: ")) {
					msg2Receive.replace("Str", "[Serveur]");
					System.out.println(msg2Receive);
					msg2Send = Interaction.lireUneChaine();
					out.println(msg2Send);
					out.flush();
				}else if(msg2Receive.contains("Bool: ")) {
					msg2Receive.replace("Bool", "[Serveur]");
					System.out.println(msg2Receive);
						Bool2Send = Interaction.lireOuiOuNon();
						out.println(Bool2Send);
						out.flush();
	
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