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
	
	
	public static int lireUnEntier(){
		int Intmsg = 0;
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
		
		return Intmsg;
	}

	public static int lireUnEntier(Joueur joueur){
		int Intmsg = 0;
		if(Jeu.getIsMultijoueur() && joueur.getIn()!=null) {
			BufferedReader in = joueur.getIn();	
			String msg = "400";
			try {
				msg = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Intmsg = Integer.parseInt(msg);
			Send2Joueur(joueur, "OK");
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
	
	public static int lireUnEntier(int borneMin, int borneMax) {
		int Intmsg = 0;
		boolean continu = false;
			do {
				try {
					Intmsg = sc.nextInt();
					continu = (Intmsg>=borneMin && Intmsg<=borneMax);	
					if(!continu) {
						System.out.print("Veuillez rentrer un entier entre " + borneMin + " et " + borneMax +" exclue : ");
					}
				} catch (InputMismatchException e) {
					System.out.print("Veuillez rentrer un entier entre " + borneMin + " et " + borneMax +" exclue : ");
					sc.next(); // passe l'entier pour �viter de boucler
				}
			} while(!continu);
		return Intmsg;
	}

	
	// renvoie un entier lu au clavier compris dans l'intervalle
	//     [borneMin, borneMax[
	public static int lireUnEntier(int borneMin, int borneMax, Joueur joueur){
		int Intmsg = -1;
		if(Jeu.getIsMultijoueur() && joueur.getIn()!=null) {
			
			BufferedReader in = joueur.getIn();	
			String msg= "400";
			int oneTime = 1;
			while(!(borneMin<=Intmsg && Intmsg<= borneMax-1)) {
				if (oneTime !=1) {
					Send2Joueur(joueur, "Veuillez rentrer un entier entre " + borneMin + " et " + borneMax +" exclue : ");
				}
				try {
					msg = in.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intmsg = Integer.parseInt(msg);
				oneTime +=1;
				
			}
			Send2Joueur(joueur, "OK");
			
		}else {
			boolean continu = false;
			do {
				try {
					Intmsg = sc.nextInt();
					continu = (Intmsg>=borneMin && Intmsg<=borneMax);	
					if(!continu) {
						System.out.print("Veuillez rentrer un entier entre " + borneMin + " et " + borneMax +" exclue : ");
					}
				} catch (InputMismatchException e) {
					System.out.print("Veuillez rentrer un entier entre " + borneMin + " et " + borneMax +" exclue : ");
					sc.next(); // passe l'entier pour �viter de boucler
				}
			} while(!continu);
		}
		
		
		
		
		return Intmsg;
	}
	
	
	public static boolean lireOuiOuNon() {
		boolean b= true;
		String texte;
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
	

	// lit les r�ponses "oui", "non", "o" ou "n" et renvoie un bool�en
	public static boolean lireOuiOuNon(Joueur joueur){
		boolean b= true;
			if(Jeu.getIsMultijoueur() && joueur.getIn()!=null) {
				
				BufferedReader in = joueur.getIn();
				String msg = "true";
				try {
					msg = in.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(msg.equals("true")) {
					b = true;
				}else {
					b = false;
				}
				
			}else {
				
				String texte;
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
				
			}


		return b;
	}
	
	
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

	// renvoie une cha�ne de caract�re lue au clavier:
	public static String lireUneChaine(Joueur joueur){
		String retour = "";
		
		if(Jeu.getIsMultijoueur() && joueur.getIn()!=null) {
			BufferedReader in = joueur.getIn();
			try {
				retour = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	
		}else {
			
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
			
		}
		
		return retour;
	}
	
	public static  void Send2Joueur(Joueur joueur, String  msg) {
		PrintWriter out;
		if (Jeu.getIsMultijoueur()  && joueur.getIn()!=null) {
			out = joueur.getOut();
			if(out!=null) {
				out.println(msg);
				out.flush();
			}else {
				msg = SuppPrefixMsg(msg);
				System.out.print(msg);
			}
			
		}else {
			msg = SuppPrefixMsg(msg);
			System.out.print(msg);
		}
	}
	
	public static String getMsgJoueur(Joueur joueur) {
		String msg = "";
		BufferedReader in = joueur.getIn();
		
		try {
			msg = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(msg.equals("")) {
			try {
				msg = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Send2Joueur(joueur, "OK");
		
		return msg;
	}

	public static void outToAll(String msg){
		ArrayList<PrintWriter> outs = Serveur.getOuts();
		for (PrintWriter out : outs ) {
			out.println("ToAll: " + msg);
			out.flush();
		}
	}
	
	public static String SuppPrefixMsg(String msg) {
		if(msg.contains("Int: ")) {
			msg.replace("Int", "");
		}else if(msg.contains("Str: ")) {
			msg.replace("Str", "");
		}else if(msg.contains("Bool: ")) {
			msg.replace("Bool", "");
		}else if(msg.contains("Msg: ")) {
			msg.replace("Msg", "");
		}
		return msg;
	}
	
}
