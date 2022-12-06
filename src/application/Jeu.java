package application;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Multijoueur.Client;
import Multijoueur.Serveur;
import controleur.Interaction;
import modele.Caracteristiques;
import modele.Joueur;
import modele.Personnage;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;

public class Jeu {
	private PlateauDeJeu plateau;
	private int numeroConfiguration;
	private Random generateur;
	private static Boolean isMultijoueur;
	
	public Jeu(PlateauDeJeu plateau) {
		this.plateau = plateau;
	}
	
	public void jouer(){
		System.out.println("Bienvenue sur Citadelle !!");
		int choix = 0;
		String newLine = System.getProperty("line.separator");
			//afficher menu
		while(choix != 4) {
			System.out.println("Menu :" + newLine + 
							"1. Jouer" + newLine +
							"2. Multijoueur" + newLine +
							"3. Règle " + newLine + 
							"4. Quitter"
							);
			
			System.out.println("Que souhaiter vous faire ?");
			choix = Interaction.lireUnEntier(1, 5);
			
			if (choix == 1) {
				setIsMultijoueur(false);
				jouerPartie();
				System.out.println(newLine + newLine);
			}else if(choix==2) {
				
				System.out.println("Souhaitez-vous :" + newLine +
						"1. Héberger une partie" + newLine +
						"2. Rejoindre une partie" + newLine +
						"3. Revenir au menu principal"
						);
				int ClientorServ = Interaction.lireUnEntier(1, 4);
				if (ClientorServ==1) {
					setIsMultijoueur(true);
					try {
						Serveur.main(null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jouerPartie();
				}else if(ClientorServ ==2) {
					try {
						Client.main(null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					jouer();
				}
			
				
			}else if(choix==3) {
				afficherLesRegles();
			}
		}
		System.out.println("A bientôt sur Citadelle");
		System.exit(0);
	}
	
	private void afficherLesRegles() {
		System.out.println("Affichage des règles du Jeu :\n");
		System.out.print(Caracteristiques.REGLES);
	}
	
	private void jouerPartie() {
		//System.out.println("Jouer Partie");
		initialisation();
		do{
			tourDeJeu();
			gestionCouronne();
			reinitialisationPersonnages();
			System.out.println("Fin du tour\n\n");
		}while(!partieFinie());
		calculDesPoints();
		
	}
	
	private void initialisation() {
		Pioche pioche = new Pioche();
		pioche = Configuration.nouvellePioche();
		if(getIsMultijoueur()) {
			plateau = Configuration.configurationMultijoueur(pioche);
		}else {
			plateau = Configuration.configurationDeBase(pioche);
		}
		for(int i=0; i<plateau.getNombreJoueurs(); i++){
			plateau.getJoueur(i).ajouterPieces(2);
			for(int j=0; j<4; j++) 
				plateau.getJoueur(i).ajouterQuartierDansMain(pioche.piocher());
		}
		generateur = new Random();
		plateau.getJoueur(generateur.nextInt(plateau.getNombreJoueurs())).setPossedeCouronne(true);
	}
	
	private void gestionCouronne() {
		if(idJoueurRoi() != -1) {
			this.plateau.getJoueur(idJoueurRoi()).setPossedeCouronne(true);
		}
	}
	
	private int idJoueurRoi() {
		int joueurRoi = 0;
		while(joueurRoi < this.plateau.getNombreJoueurs()){
			if(plateau.getJoueur(joueurRoi).getPersonnage().getNom().equals(new String("Roi")))
				return joueurRoi;
			joueurRoi++;
		}
		return -1;
	}
	
	private int idJoueurVoleur() {
		int joueurVoleur = 0;
		while(joueurVoleur < this.plateau.getNombreJoueurs()){
			if(plateau.getJoueur(joueurVoleur).getPersonnage().getNom().equals(new String("Voleur")))
				return joueurVoleur;
			joueurVoleur++;
		}
		return -1;
	}
	
	private void reinitialisationPersonnages() {
		for(int i=0; i<this.plateau.getNombreJoueurs(); i++) {
			this.plateau.getJoueur(i).getPersonnage().reinitialiser();
		}
	}
	
	private boolean partieFinie() {
		for(int i=0; i<plateau.getNombreJoueurs(); i++){
			if(this.plateau.getJoueur(i).nbQuartiersDansCite() == 8) {
				return true;
			}
		}
		return false;
	}
	
	private void tourDeJeu() {
		choixPersonnages();
		ArrayList<Personnage> listeDePersonnage = new ArrayList<Personnage>();
		
		// Récupération de Personnages qui n'ont pas de joueur
		/*
		for(int i=0;i<plateau.getNombrePersonnages();i++) {
			if(plateau.getPersonnage(i).)
			listeDePersonnage.add(plateau.getPersonnage(i));
		}
		*/
		
		for(int i=0; i<plateau.getNombreJoueurs();i++) {
			listeDePersonnage.add(plateau.getJoueur(i).getPersonnage());
		}
		
		
		listeDePersonnage.sort((o1, o2) -> Integer.toString(o1.getRang()).compareTo(Integer.toString(o2.getRang())));

		for (Personnage perso : listeDePersonnage) {
			if (perso.getJoueur() != null && !perso.getAssassine()){
				if(perso.getVole()) {
					if(!perso.getJoueur().getIsBot())System.out.println("Vous avez été volé !!");
					int idVoleur = idJoueurVoleur();
					int nbPiecesAVoler = perso.getJoueur().nbPieces();
					perso.getJoueur().retirerPieces(nbPiecesAVoler);
		    		this.plateau.getJoueur(idVoleur).ajouterPieces(nbPiecesAVoler);
				}
				percevoirRessource(perso);
				perso.percevoirRessourcesSpecifiques();
	
				if(!perso.getJoueur().getIsBot()) {
					System.out.println("Vous disposez de " + perso.getJoueur().nbPieces() + " pièce(s)");
					System.out.println("Voulez-vous utiliser votre pouvoir ? oui/o, ou non/n");
					boolean choix = Interaction.lireOuiOuNon();
					if(choix) perso.utiliserPouvoir();
					
				}else {
					generateur = new Random();
					if(generateur.nextBoolean()) perso.utiliserPouvoirAvatar();
				}
				perso.construire();
			}
			updateTermineePremier();
		}
	}
		
	
	private void choixPersonnages() {
		
		ArrayList<Personnage> listeDePersonnage = new ArrayList<Personnage>();
		Personnage[] CarteEcarteVisible = new Personnage[2];
		Personnage[] CarteEcarteCache = new Personnage[2];
		int NcarteEcarte;
		for(int i=0;i<plateau.getNombrePersonnages();i++) {
			listeDePersonnage.add(plateau.getPersonnage(i));
		}
		// ecarter deux cartes faces visibles
		generateur = new Random();
		
		for (int i=0; i<2; i++) {
			NcarteEcarte = generateur.nextInt(listeDePersonnage.size());
			System.out.println("Le Personnage " + listeDePersonnage.get(NcarteEcarte).getNom()+ " est écarté face visible");
			CarteEcarteVisible[i] = listeDePersonnage.get(NcarteEcarte);
			listeDePersonnage.remove(NcarteEcarte);
		}
		
		// ecarter une carte faces cachées
			NcarteEcarte = generateur.nextInt(listeDePersonnage.size());
			System.out.println("Un personnage est ́ecarté face cachée");
			CarteEcarteCache[0] = listeDePersonnage.get(NcarteEcarte);
			listeDePersonnage.remove(NcarteEcarte);
		
		// Recuperer la liste des joueurs dans une variables
		ArrayList<Joueur> listeDeJoueur = new ArrayList<Joueur>();
		for(int i=0; i<plateau.getNombreJoueurs();i++) {
			listeDeJoueur.add(plateau.getJoueur(i));
		}
		
		int JoueurAvecCouronne = 0;
		
		while(!plateau.getJoueur(JoueurAvecCouronne).getPossedeCouronne()) JoueurAvecCouronne++;

		Joueur JoueurCouronne = plateau.getJoueur(JoueurAvecCouronne);
		System.out.println("Le joueur " + JoueurCouronne.getNom() + " à la couronne ! ");
		// La couronne choisi son personnage
		if(JoueurCouronne.getIsBot()){ //couronne = bot
			int RndmInt = generateur.nextInt(0,listeDePersonnage.size());
			listeDePersonnage.get(RndmInt).setJoueur(JoueurCouronne);
			listeDePersonnage.remove(RndmInt);
		}else { //couronne = humain
			for (int index= 0; index<listeDePersonnage.size();index++) {
				System.out.println((index+1) + " " + listeDePersonnage.get(index).getNom());
			}
			
			System.out.println("Quel personnage choississez vous ? Veuillez rentrer le numéro assossié");
			int choixPersonnage = Interaction.lireUnEntier(1,listeDePersonnage.size()+1);
			listeDePersonnage.get(choixPersonnage-1).setJoueur(JoueurCouronne);
			listeDePersonnage.remove(choixPersonnage-1);
		}
		
		
		int IndexJoueur = JoueurAvecCouronne+1 ;

		for(int i=0; i<listeDeJoueur.size()-1;i++) {
			
			if (IndexJoueur>=listeDeJoueur.size()) {
				IndexJoueur = 0;
			}
			
			// Ligne pour tester
			//System.out.println("Index joueur : " + IndexJoueur + " Taille de la liste : " + listeDeJoueur.size());
			
			Joueur joueur = listeDeJoueur.get(IndexJoueur);
			if(joueur.getIsBot()){
				int RndmInt = generateur.nextInt(0,listeDePersonnage.size());
				listeDePersonnage.get(RndmInt).setJoueur(joueur);
				listeDePersonnage.remove(RndmInt);
				
			}else {
				for (int index= 0; index<listeDePersonnage.size();index++) {
					System.out.println((index+1) + " " + listeDePersonnage.get(index).getNom());
				}
				System.out.println("Quel personnage choississez vous ? Veuillez rentrer le numéro assossié");
				int choixPersonnage = Interaction.lireUnEntier(1, listeDePersonnage.size()+1);
				listeDePersonnage.get(choixPersonnage-1).setJoueur(joueur);
				listeDePersonnage.remove(choixPersonnage-1);
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
		
		generateur = new Random();
		int choix  = (!perso.getJoueur().getIsBot())? Interaction.lireUnEntier(1, 3): generateur.nextInt(1,3);
		
			
			if(choix ==1) {
				perso.ajouterPieces();
			}else {
				ArrayList<Quartier> cartes = new ArrayList<Quartier>();
				if(!perso.getJoueur().getIsBot()) { // Joueur humain
					for(int i=1; i<=2; i++) {
						cartes.add(this.plateau.getPioche().piocher());
						System.out.println(i + ". "+ cartes.get(i-1).getNom() + " (" +cartes.get(i-1).getType() + ") : coût " + cartes.get(i-1).getCout());
					}
					System.out.println("Quelle carte souhaitez-vous garder ?");
					choix = Interaction.lireUnEntier(1,3);
					
				}else { //Bot
					for(int i=1; i<=2; i++) {
						cartes.add(this.plateau.getPioche().piocher());
					}
					choix = generateur.nextInt(1,3);
				}
				perso.ajouterQuartier(cartes.get(choix-1));
				this.plateau.getPioche().ajouter(cartes.get((choix == 1)? 1:0));
				
			}
	}
	
	private void calculDesPoints() {
		ArrayList<Integer> scoreJoueurs = new ArrayList<Integer>();
		ArrayList<String> nomJoueurs = new ArrayList<String>();
		int scoreTotal;
		ArrayList<String> categorieQuartier = new ArrayList<String>();
		for(int i=0;i<plateau.getNombreJoueurs();i++) {
			scoreTotal = 0;
			categorieQuartier.clear();
			nomJoueurs.add(plateau.getJoueur(i).getNom());
			for(int j=0;j<plateau.getJoueur(i).nbQuartiersDansCite();j++) {
				scoreTotal += plateau.getJoueur(i).getCite()[j].getCout(); //additionner le prix de tout les quartiers de la cite de chaque joueur
				scoreTotal += plateau.getJoueur(i).getCite()[j].calculDesPointsMerveilles(plateau.getJoueur(i));
				if(!categorieQuartier.contains(plateau.getJoueur(i).getCite()[j].getType())) //Test si le type de quartier est deja present ou non
					categorieQuartier.add(plateau.getJoueur(i).getCite()[j].getType());
			}
			if(categorieQuartier.size() == 5) //Si la taille est egal a 5 alors ils contient dans sa cite tout les quartiers differents
				scoreTotal += 3;
			if(plateau.getJoueur(i).nbQuartiersDansCite() == 8) {
				if(plateau.getJoueur(i).isTermineePremier())
					scoreTotal += 4;
				else
					scoreTotal += 2;
			}
			scoreJoueurs.add(scoreTotal);
		}
	    int max;
	    int indice;
	    System.out.println("---- AFFICHAGE DES SCORES ----");
		for(int i=0;i<plateau.getNombreJoueurs();i++) {
		    max = 0;
		    indice = 0;
		    for(int j=0; j<scoreJoueurs.size(); j++){ //Trouver le meilleur score et l'indice correspondant
		        if(scoreJoueurs.get(j) >= max){
		            max = scoreJoueurs.get(j);
		            indice = j;
		        }
		    }
		    System.out.println(i+1 + " -> " + nomJoueurs.get(indice) + " avec " + scoreJoueurs.get(indice) + "points.");
		    scoreJoueurs.remove(indice);
		    nomJoueurs.remove(indice);
		}
	}

	
	private void updateTermineePremier() {
		boolean countTerminee = false;
		for(int i=0; i<plateau.getNombreJoueurs();i++) {
			if(plateau.getJoueur(i).isTermineePremier())
				countTerminee = true;
		}
		if(!countTerminee) {
			for(int i=0; i<plateau.getNombreJoueurs();i++) {
				if(plateau.getJoueur(i).nbQuartiersDansCite() == 8) {
					plateau.getJoueur(i).setTermineePremier(true);
					return;
				}
			}
		}
	}
	
	public static void setIsMultijoueur(Boolean isMultijoueur) {
		Jeu.isMultijoueur = isMultijoueur;
	}
	
	public static Boolean getIsMultijoueur() {
		return isMultijoueur;
	}
	
	
}
