package application;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;
import modele.Joueur;
import modele.Personnage;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Joueur;

public class Jeu {
	private PlateauDeJeu plateau;
	private int numeroConfiguration;
	private Random generateur;
	
	public Jeu(PlateauDeJeu plateau) {
		this.plateau = plateau;
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
			for(int j=0; j<4; j++) {plateau.getJoueur(i).ajouterQuartierDansMain(pioche.piocher());}
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
		choixPersonnages();
		ArrayList<Personnage> listeDePersonnage = new ArrayList<Personnage>();
		
		for(int i=0;i<plateau.getNombrePersonnages();i++) {
			listeDePersonnage.add(plateau.getPersonnage(i));
		}
		
		listeDePersonnage.sort((o1, o2) -> Integer.toString(o1.getRang()).compareTo(Integer.toString(o2.getRang())));

		for (Personnage perso : listeDePersonnage) {
			if (!perso.getAssassine()){
				if(perso.getVole()) {
//					idJoueurVoleur();
//					int nbPiecesAVoler = this.getPlateau().getPersonnage(choix-1).getJoueur().nbPieces();
//		    		this.getJoueur().ajouterPieces(nbPiecesAVoler);
//		    		this.getPlateau().getPersonnage(choix-1).getJoueur().retirerPieces(nbPiecesAVoler);
				}
				percevoirRessource(perso);
				perso.percevoirRessourcesSpecifiques();
				System.out.println("Voulez-vous utiliser votre pouvoir ?");
				
					
				
				
				
				
				
			}
		}
		
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
		
		// ecarter deux cartes faces cachées
		for (int i=0; i<1; i++) {
			NcarteEcarte = generateur.nextInt(listeDePersonnage.size());
			System.out.println("Un personnage est  ́ecarté face cachée");
			CarteEcarteCache[i] = listeDePersonnage.get(NcarteEcarte);
			listeDePersonnage.remove(NcarteEcarte);
		}
		
		// Recuperer la liste des joueurs dans une variables
		ArrayList<Joueur> listeDeJoueur = new ArrayList<Joueur>();
		for(int i=0; i<plateau.getNombreJoueurs();i++) {
			listeDeJoueur.add(plateau.getJoueur(i));
		}
		
		int JoueurAvecCouronne = 0;
		
		while(!plateau.getJoueur(JoueurAvecCouronne).getPossedeCouronne()){
			JoueurAvecCouronne++;
		}

		Joueur JoueurCouronne = plateau.getJoueur(JoueurAvecCouronne);
		System.out.println("Le joueur" + JoueurCouronne + " à la couronne ! ");
		
		if(JoueurCouronne.getIsBot()){
			int RndmInt = generateur.nextInt(0,listeDePersonnage.size());
			listeDePersonnage.get(RndmInt).setJoueur(JoueurCouronne);
			listeDePersonnage.remove(RndmInt);
		}else {
			for (int index= 0; index<listeDePersonnage.size();index++) {
				System.out.println((index+1) + " " + listeDePersonnage.get(index));
			}
			System.out.println("Qu'elle personnage choississez vous ? Veuillez rentrer le numéro assossié");
			int choixPersonnage = Interaction.lireUnEntier();
			listeDePersonnage.get(choixPersonnage).setJoueur(JoueurCouronne);
			listeDePersonnage.remove(choixPersonnage);
		}
		
		
		int IndexJoueur = JoueurAvecCouronne ;

		for(int i=0; i<listeDeJoueur.size()-1;i++) {
			
			if (IndexJoueur>listeDeJoueur.size()) {
				IndexJoueur = 0;
			}
			
			Joueur joueur = listeDeJoueur.get(IndexJoueur);
			if(joueur.getIsBot()){
				int RndmInt = generateur.nextInt(0,listeDePersonnage.size());
				listeDePersonnage.get(RndmInt).setJoueur(joueur);
				listeDePersonnage.remove(RndmInt);
				
			}else {
				for (int index= 0; index<listeDePersonnage.size();index++) {
					System.out.println((index+1) + " " + listeDePersonnage.get(index));
				}
				System.out.println("Qu'elle personnage choississez vous ? Veuillez rentrer le numéro assossié");
				int choixPersonnage = Interaction.lireUnEntier();
				listeDePersonnage.get(choixPersonnage).setJoueur(joueur);
				listeDePersonnage.remove(choixPersonnage);
			}
			IndexJoueur++;
		}
	}
	
	private void percevoirRessource(Personnage perso) {
		
		if(!perso.getJoueur().getIsBot()) {
			System.out.println("Souhaitez-vous prendre : ");
			System.out.println("1. Deux pièces d'or ");
			System.out.println("2. Prendre deux cartes de la pioche, une carte sera ensuite défaussée");
		}
		
		if(!perso.getJoueur().getIsBot()) {
			
		}
			int choix = Interaction.lireUnEntier(1, 2);
			if(choix ==1) {
				perso.ajouterPieces();
			}else {
				ArrayList<Quartier> cartes = new ArrayList<Quartier>();
				for(int i=1; i<2; i++) {
					cartes.add(this.plateau.getPioche().piocher());
					System.out.println(i + ". "+ cartes.get(i-1));
				}
				System.out.println("Qu'elles carte souhaité vous garder ?");
				choix = Interaction.lireUnEntier(1,2);
				perso.ajouterQuartier(cartes.get(choix));
				this.plateau.getPioche().ajouter(cartes.get((choix == 1)? 2:1));
			}
	}
	
	private void calculDesPoints() {
		
	}

}
