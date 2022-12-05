package Multijoueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import controleur.Interaction;

public class Serveur {
	private static final int PORT = 9090;
	
	private static ArrayList<Socket> clients = new ArrayList<>();
	private static ArrayList<BufferedReader> ins = new ArrayList<>();
	public static ArrayList<PrintWriter> outs = new ArrayList<>();
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		System.out.println("Veuillez entrer le nombre de joueur attendu? (maximum 3)");
		int NbDeJoueurAttendu = Interaction.lireUnEntier(0, 4);
		ServerSocket listener = new ServerSocket (PORT);
		
		while(clients.size()<NbDeJoueurAttendu) {
			System.out.println("[SERVER] Waiting for client connection...");
			Socket client = listener.accept();
			System.out.println("[SERVER] Un joueur à rejoint la salle!");
			clients.add(client);		
			outs.add(new PrintWriter(client.getOutputStream(), true));
			ins.add(new BufferedReader(new InputStreamReader(client.getInputStream())));
			System.out.println("En attente de " + (NbDeJoueurAttendu - clients.size()) + " Joueur(s)");
		}
		String str = ins.get(0).readLine();
		System.out.println("client 1 : " + str);
		
		String str2 = ins.get(1).readLine();
		System.out.println("client 2 : " + str2);
		
		outToAll("yes");
		
		
//		out = new PrintWriter(clients.get(0).getOutputStream(), true);
//		out.println("test");
//		
//		while(true) {
//			
//		}
		//listener.close();
		
//		ServerSocket listener = new ServerSocket (PORT);
//		Socket client = listener.accept();
//		System.out.println("client connected");
//		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//		
//		String str = ins.get(0).readLine();
//		System.out.println("client : " + str);

//		
//		out = new PrintWriter(client.getOutputStream());
//		out.println("yes");
//		out.flush();
//		
		
		
		
	}
	
	
	
	public static void outToAll(String msg) throws IOException {
		for (PrintWriter out : outs ) {
			out.println(msg);
			out.flush();
		}
	}
	
}
