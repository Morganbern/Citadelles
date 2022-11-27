package controleur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction {
	private static Scanner sc = new Scanner(System.in);

	public static int lireUnEntier() {
		int i = 0;
		boolean continu = true;
		do {
			try {
				i = sc.nextInt();
				continu = false;
			} catch (InputMismatchException e) {
				System.out.print("Veuillez rentrer un chiffre : ");
				sc.next(); // passe l'entier pour �viter de boucler
			}
		} while(continu);
		return i;
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


	
}
