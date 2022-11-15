package application;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;
import modele.Joueur;
import modele.Personnage;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Joueur;

public class Jeu {
	private PlateauDeJeu plateau;
	private int numeroConfiguration;
	private Random generateur;
	
	public Jeu() {
		
	}
	
	public void jouer() {
		System.out.println("Bienvenue sur Citadelle !!");
		String choix;
		do{
			//afficher menu
			String newLine = System.getProperty("line.separator");
			System.out.println("Menu :" + newLine + 
								"Jouer" + newLine +
								"Règle" + newLine + 
								"Quitter");
			System.out.println("Que souhaiter vous faire ?");
			choix = Interaction.lireUneChaine();
		}while(!(choix.equals("Jouer") || choix.equals("Règle") || choix.equals("Quitter")));
		if (choix.equals("Jouer")) {
			jouerPartie();
		}else if(choix.equals("Règle")) {
			afficherLesRegles();
		}else {
			System.out.println("A bientôt sur Citadelle");
			//System.exit(0);
		}
		
	}
	
	private void afficherLesRegles() {
		System.out.println("Affichage des règles du Jeu");
	}
	
	private void jouerPartie() {
		//System.out.println("Jouer Partie");
		initialisation();
		do{
			tourDeJeu();
			gestionCouronne();
			reinitialisationPersonnages();
		}while(!partieFinie());	
	}
	
	private void initialisation() {
		Pioche pioche = new Pioche();
		pioche = Configuration.nouvellePioche();
		plateau = Configuration.configurationDeBase(pioche);
		for(int i=0; i<plateau.getNombreJoueurs(); i++){
			plateau.getJoueur(i).ajouterPieces(2);
			plateau.getJoueur(i).ajouterQuartierDansMain(pioche.piocher());
		}
		generateur = new Random();
		plateau.getJoueur(generateur.nextInt(plateau.getNombreJoueurs())).setPossedeCouronne(true);
	}
	
	private void gestionCouronne() {
		
	}
	
	private void reinitialisationPersonnages() {
		
	}
	
	private boolean partieFinie() {
		return true;
	}
	
	private void tourDeJeu() {
		
	}
	
	private void choixPersonnages() {
		
		ArrayList<Personnage> listeDePersonnage = new ArrayList<Personnage>();
		Personnage[] CarteEcarteVisible = new Personnage[1];
		Personnage[] CarteEcarteCache = new Personnage[1];
		int NcarteEcarte;
		for(int i=0;i<plateau.getNombrePersonnages();i++) {
			listeDePersonnage.add(plateau.getPersonnage(i));
		}
		// ecarter deux cartes faces visibles
		generateur = new Random();
		
		for (int i=0; i<1; i++) {
			NcarteEcarte = generateur.nextInt(listeDePersonnage.size());
			System.out.println("Le Personnage " + listeDePersonnage.get(NcarteEcarte)+ "est écarté face visible");
			CarteEcarteVisible[i] = listeDePersonnage.get(NcarteEcarte);
			listeDePersonnage.remove(NcarteEcarte);
		}
		
		for (int i=0; i<1; i++) {
			NcarteEcarte = generateur.nextInt(listeDePersonnage.size());
			System.out.println("Un personnage est  ́ecarté face cachée");
			CarteEcarteCache[i] = listeDePersonnage.get(NcarteEcarte);
			listeDePersonnage.remove(NcarteEcarte);
		}
		int i = 0;
		while(!plateau.getJoueur(i).getPossedeCouronne()){
			i++;
		}
		System.out.println("Le joueur" + "" );
		
		
		
		
		
		
		
	}
	
	private void percevoirRessource() {
		
	}
	
	private void calculDesPoints() {
		
	}
	
}
