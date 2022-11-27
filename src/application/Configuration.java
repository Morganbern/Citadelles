package application;

import modele.Architecte;
import modele.Assassin;
import modele.Condottiere;
import modele.Eveque;
import modele.Joueur;
import modele.Magicienne;
import modele.Marchande;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Roi;
import modele.Voleur;

import controleur.Interaction;

public class Configuration {

	public static Pioche nouvellePioche() {
		Pioche pioche = new Pioche();
		
		for(int nbCartes=0; nbCartes<2; nbCartes++) {
			pioche.ajouter(new Quartier("cathédrale","RELIGIEUX",5));
			
			pioche.ajouter(new Quartier("forteresse","MILITAIRE",5));
			
			pioche.ajouter(new Quartier("hôtel de ville","COMMERCANT",5));
		}
		
		for (int nbCartes=0; nbCartes<3; nbCartes++) {
			pioche.ajouter(new Quartier("temple","RELIGIEUX",1));
			pioche.ajouter(new Quartier("église","RELIGIEUX",2));
			pioche.ajouter(new Quartier("monastère","RELIGIEUX",3));
			
			pioche.ajouter(new Quartier("tour de guet","MILITAIRE",1));
			pioche.ajouter(new Quartier("prison","MILITAIRE",2));
			pioche.ajouter(new Quartier("caserne","MILITAIRE",3));
			
			pioche.ajouter(new Quartier("échoppe","COMMERCANT",2));
			pioche.ajouter(new Quartier("comptoir","COMMERCANT",3));
			pioche.ajouter(new Quartier("port","COMMERCANT",4));
			
			pioche.ajouter(new Quartier("palais","NOBLE",5));
		}
		
		for (int nbCartes=0; nbCartes<4; nbCartes++) {
			pioche.ajouter(new Quartier("château","NOBLE",4));
			
			pioche.ajouter(new Quartier("marché","COMMERCANT",3));
		}
		
		for (int nbCartes=0; nbCartes<5; nbCartes++) {
			pioche.ajouter(new Quartier("manoir","NOBLE",3));
			pioche.ajouter(new Quartier("taverne","NOBLE",1));
		}
		
		
		pioche.melanger();
		return pioche;
	}
	
	public static PlateauDeJeu configurationDeBase(Pioche p) {
		PlateauDeJeu plateau = new PlateauDeJeu();
		plateau.ajouterPersonnage(new Architecte());
		plateau.ajouterPersonnage(new Assassin());
		plateau.ajouterPersonnage(new Condottiere());
		plateau.ajouterPersonnage(new Eveque());
		plateau.ajouterPersonnage(new Magicienne());
		plateau.ajouterPersonnage(new Marchande());
		plateau.ajouterPersonnage(new Voleur());
		plateau.ajouterPersonnage(new Roi());
		
		// Connaître nombre réel joueur
		
		System.out.println("Entrer le nombre de joueur réel parmis les 4 joueurs");
		int nbJoueur = Interaction.lireUnEntier(0,5);
		
		for(int Joueur=0; Joueur< nbJoueur; Joueur ++) {
			System.out.println("Nom du Joueur n°" + (Joueur+1) + "?" );
			String NomJoueur= Interaction.lireUneChaine();
			plateau.ajouterJoueur(new Joueur(NomJoueur));
		}
		
		// Ajouter les bots
		for(int bot=nbJoueur+1; bot<5; bot++) {
			plateau.ajouterJoueur(new Joueur("Joueur"+bot));
			plateau.getJoueur(bot-1).setIsBot(true);
		}
		
		
		String[] MerveilleName = {"Capitole",
									"Carrière",
									"Chantier",
									"Cours des Miracles",
									"Donjon",
									"Ecole de Magie",
									"Ecuries",
									"Grande Muraille",
									"Hospice",
									"Manufacture",
									"Necropole",
									"Parc",
									"Statue Equestre",
									"Tripot"
									};
		
		String type = "MERVEILLE";
		int[] MerveilleCost = {5, 5, 3, 2, 3, 6, 2, 6, 4, 5, 5, 6, 3, 6};
		for(int nbMerveille=0; nbMerveille <14; nbMerveille++) {
			p.ajouter(new Quartier(MerveilleName[nbMerveille], type, MerveilleCost[nbMerveille] ));

		}
		p.melanger();
		return plateau;
	}
	
}
