package Multijoueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import controleur.Interaction;
import modele.Joueur;

public class Serveur {
	private static final int PORT = 9090;
	
	private static ArrayList<Socket> clients = new ArrayList<>();
	private static ArrayList<BufferedReader> ins = new ArrayList<>();
	public  static ArrayList<PrintWriter> outs = new ArrayList<>();
	private static int NbPersonneConnecte;
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		
		System.out.println("Veuillez entrer le nombre de joueur attendu? (maximum 3)");
		Joueur serv = new Joueur("Serv"); 
		int NbDeJoueurAttendu = Interaction.lireUnEntier(0, 4, serv);
//		Enumeration e = NetworkInterface.getNetworkInterfaces();
//		while(e.hasMoreElements())
//		{
//		    NetworkInterface n = (NetworkInterface) e.nextElement();
//		    Enumeration ee = n.getInetAddresses();
//		    while (ee.hasMoreElements())
//		    {
//		        InetAddress i = (InetAddress) ee.nextElement();
//		        System.out.println(i.+ " " +i.getHostAddress());
//		    }
//		}
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
		
		setNbPersonneConnecte(clients.size());
		
	}
	
	
	public static void setNbPersonneConnecte(int nbPersonneConnecte) {
		NbPersonneConnecte = nbPersonneConnecte;
	}
	
	public static int getNbPersonneConnecte() {
		return NbPersonneConnecte;
	}
	
	public static ArrayList<BufferedReader> getIns() {
		return ins;
	}
	
	public static ArrayList<PrintWriter> getOuts() {
		return outs;
	}
	
	
	
}
