package test;

import modele.Caracteristiques;
import modele.Joueur;
import modele.Quartier;
import modele.Eveque;

public class TestEveque {

	public static void main(String[] args) {
		TestEveque test= new TestEveque();
		test.test1();
		//test.test2();	
	}
	
	public void test1(){
		System.out.println("TEST DU CONSTRUCTEUR");
		Eveque eveque = new Eveque();
		Test.test(eveque.getNom().equals("Eveque"),"test du nom du personnage");
		Test.test(eveque.getRang()== 5,"test du rang du personnage");
		Test.test(eveque.getCaracteristiques().equals(Caracteristiques.EVEQUE),
				"test des caractéristiques du personnage");
		Test.test(eveque.getJoueur()==null, "test de l'initialisation de la variable \"joueur\"");
		Test.test(eveque.getAssassine()==false, "test de l'initialisation de la variable \"assassine\"");
		Test.test(eveque.getVole()==false, "test de l'initialisation de la variable \"vole\"");
	}
	public void test2(){
		System.out.println("TEST DE LA PERCEPTION DE RESSOURCES SPECIFIQUES");
		Joueur joueur = new Joueur("Billy");
		Eveque eveque = new Eveque();
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("église",Quartier.TYPE_QUARTIERS[0],2);
		eveque.setJoueur(joueur);
		eveque.ajouterPieces();
		Test.test(eveque.getJoueur().nbPieces() == 2,
			"test du nombre de pièces d'or avant perception");
		eveque.construire(quartier1);
		eveque.construire(quartier2);
		eveque.construire(quartier3);		
		eveque.percevoirRessourcesSpecifiques();
		Test.test(eveque.getJoueur().nbPieces() == 4,
			"test du nombre de pièces d'or après perception de ressources spécifiques avec 2 quartiers religieux");
	}
}