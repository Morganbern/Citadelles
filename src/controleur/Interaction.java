package controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Multijoueur.Serveur;
import application.Jeu;
import modele.Joueur;

public class Interaction {
	private static Scanner sc = new Scanner(System.in);

	public static int lireUnEntier(Joueur joueur) {
		int Intmsg = 0;
		if(Jeu.getIsMultijoueur()) {
			BufferedReader in = joueur.getIn();
			String msg = "";
			try {
				msg = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try{
				Intmsg = Integer.parseInt(msg);
			}catch(NumberFormatException e) {
				msg="";
			}
			
			
			while(msg.equals("")) {
				
				try {
					msg = in.readLine();		
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Intmsg = Integer.parseInt(msg);	
				} catch (NumberFormatException e) {
				    msg = "";
				}
			}
			
		}else {
	
			boolean continu = true;
			do {
				try {
					Intmsg = sc.nextInt();
					continu = false;
				} catch (InputMismatchException e) {
					System.out.print("Veuillez rentrer un chiffre : ");
					sc.next(); // passe l'entier pour �viter de boucler
				}
			} while(continu);
			
		}
		return Intmsg;
		
	}

	// renvoie un entier lu au clavier compris dans l'intervalle
	//     [borneMin, borneMax[
	public static int lireUnEntier(int borneMin, int borneMax) {
		int i=borneMin-1;
		
		
		
		
		boolean continu = false;
		do {
			try {
				i = sc.nextInt();
				continu = (i>=borneMin && i<borneMax);	
				if(!continu) {
					System.out.print("Veuillez rentrer un entier entre " + borneMin + " et " + borneMax +" exclue : ");
				}
			} catch (InputMismatchException e) {
				System.out.print("Veuillez rentrer un entier entre " + borneMin + " et " + borneMax +" exclue : ");
				sc.next(); // passe l'entier pour �viter de boucler
			}
		} while(!continu);
		return i;
	}

	// lit les r�ponses "oui", "non", "o" ou "n" et renvoie un bool�en
	public static boolean lireOuiOuNon() {
			String texte;
			boolean b= true;
			boolean continu = true;
			do {
				try {
					texte = sc.next();
					if (texte.equals(new String("o")) || texte.equals(new String("oui"))) { 
						b = true;
						continu = false;
						
					}else if(texte.equals(new String("n")) || texte.equals(new String("non"))) {
						b = false;
						continu = false;

					}else {
						System.out.print("Veuillez entrer oui ou o,  ou, n ou non : ");
					}
					
				} catch (InputMismatchException e) {
					System.out.print("Veuillez entrer oui ou o,  ou, n ou non : ");
					sc.next(); // passe l'entier pour �viter de boucler
				}
			} while(continu);
		return b;
	}

	// renvoie une cha�ne de caract�re lue au clavier:
	public static String lireUneChaine() {
		String retour = "";
		boolean continu = true;
		do {
			try {
				retour = sc.next(); 
				continu = false;
	
			} catch (InputMismatchException e) {
				System.out.print("Veuillez entrer votre chaine de caractere");
				sc.next(); // passe l'entier pour �viter de boucler
			}
		} while(continu);
		return retour;
	}
	
	public static  void Send2Joueur(Joueur joueur, String  msg) {
		PrintWriter out;
		if (Jeu.getIsMultijoueur()) {
			out = joueur.getOut();
			if(out!=null) {
				out.print(msg);
				out.flush();
			}else {
				System.out.print(msg);
			}
			
		}else {
			System.out.print(msg);
		}
	}
	
	public static String getMsgJoueur(Joueur joueur) {
		String msg;
		BufferedReader in = joueur.getIn();
		try {
			msg = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	public static void outToAll(String msg) throws IOException {
		ArrayList<PrintWriter> outs = Serveur.getOuts();
		for (PrintWriter out : outs ) {
			out.print("ToAll: " + msg);
			out.flush();
		}
	}
	
	
}
