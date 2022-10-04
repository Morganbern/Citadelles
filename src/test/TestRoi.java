package test;

import modele.Caracteristiques;
import modele.Joueur;
import modele.Quartier;
import modele.Roi;

public class TestRoi {

	public static void main(String[] args) {
		TestRoi testRoi= new TestRoi();
		testRoi.test1();
		//testRoi.test2();	
		//testRoi.test3();
		//testRoi.test4();
		//testRoi.test5();
		//testRoi.test6();
		//testRoi.test7();
		//testRoi.test8();
		//testRoi.test9();
		//testRoi.test10();
	}
	
	public void test1(){
		System.out.println("TEST DU CONSTRUCTEUR");
		Roi roi = new Roi();
		Test.test(roi.getNom().equals("Roi"),"test du nom du personnage Roi");
		Test.test(roi.getRang()== 4,"test du rang du personnage Roi");
		Test.test(roi.getCaracteristiques().equals(Caracteristiques.ROI),
				"test des caractéristiques du personnage Roi");
		Test.test(roi.getJoueur()==null, "test de l'initialisation de la variable \"joueur\"");
		Test.test(roi.getAssassine()==false, "test de l'initialisation de la variable \"assassine\"");
		Test.test(roi.getVole()==false, "test de l'initialisation de la variable \"vole\"");
	}
	public void test2(){
		System.out.println("TEST DE L'ATTRIBUTION D'UN JOUEUR");
		Joueur joueur = new Joueur("Billy");
		Roi roi = new Roi();
		roi.setJoueur(joueur);
		Test.test(roi.getJoueur() == joueur,"test de l'attribution de la variable \"joueur\"");
		Test.test(roi.getJoueur().getNom().equals("Billy"),"test du nom de joueur attribué");
	}
	public void test3(){
		System.out.println("TEST DE L'ASSASSINAT DU PERSONNAGE");
		Roi roi = new Roi();
		roi.setAssassine();
		Test.test(roi.getAssassine() == true,"test de l'assassinat");
	}
	public void test4(){
		System.out.println("TEST DU VOL DU PERSONNAGE");
		Roi roi = new Roi();
		roi.setVole();
		Test.test(roi.getVole() == true,"test du vol");
	}
	public void test5(){
		System.out.println("TEST DE LA PERCEPTION DE PIECES D'OR");
		Joueur joueur = new Joueur("Billy");
		Roi roi = new Roi();
		roi.ajouterPieces();
		Test.test(roi.getJoueur() == null,"test alors que le joueur n'est pas attribué");
		roi.setJoueur(joueur);
		roi.ajouterPieces();
		Test.test(roi.getJoueur().nbPieces() == 2,"vé&rification du nombre de pièces d'or");
	}
	public void test6(){
		System.out.println("TEST DE L'AJOUT DE QUARTIER DANS LA MAIN DU JOUEUR");
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		Joueur joueur = new Joueur("Billy");
		Roi roi = new Roi();
		roi.ajouterQuartier(quartier1);
		Test.test(roi.getJoueur() == null,"test alors que le joueur n'est pas attribué");
		roi.setJoueur(joueur);
		roi.ajouterQuartier(quartier1);
		roi.ajouterQuartier(quartier2);
		roi.ajouterQuartier(quartier3);
		Test.test(roi.getJoueur().nbQuartiersDansMain() == 3,"test du nombre de quartiers après ajout");
	}
	public void test7(){
		System.out.println("TEST DE LA CONSTRUCTION D'UN QUARTIER DANS LA CITE DU JOUEUR");
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		Joueur joueur = new Joueur("Billy");
		Roi roi = new Roi();
		roi.construire(quartier1);
		Test.test(roi.getJoueur() == null,
				"test alors que le joueur n'est pas attribué");
		roi.setJoueur(joueur);
		roi.construire(quartier1);
		roi.construire(quartier2);
		roi.construire(quartier3);
		Test.test(roi.getJoueur().nbQuartiersDansCite() == 3,
				"test du nombre de quartiers après construction");
		Test.test(roi.getJoueur().quartierPresentDansCite("prison"),
				"test de la présence de la prison dans la cité");
	}
	public void test8(){
		System.out.println("TEST DE LA PERCEPTION DE RESSOURCES SPECIFIQUES");
		Joueur joueur = new Joueur("Billy");
		Roi roi = new Roi();
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		roi.percevoirRessourcesSpecifiques();
		Test.test(roi.getJoueur() == null,
			"test alors que le joueur n'est pas attribué");
		roi.setJoueur(joueur);
		roi.ajouterPieces();
		Test.test(roi.getJoueur().nbPieces() == 2,
			"test du nombre de pièces d'or avant perception");
		roi.percevoirRessourcesSpecifiques();
		Test.test(roi.getJoueur().nbPieces() == 2,
			"test alors qu'il n'y a pas de quartiers nobles");
		roi.construire(quartier1);
		roi.construire(quartier2);
		roi.construire(quartier3);		
		roi.percevoirRessourcesSpecifiques();
		Test.test(roi.getJoueur().nbPieces() == 3,
			"test du nombre de pièces d'or après perception de ressources spécifiques avec 1 quartier noble");
	}
	public void test9(){
		System.out.println("TEST DE L'UTILISATION DU POUVOIR DU ROI");
		Joueur joueur = new Joueur("Billy");
		Roi roi = new Roi();
		roi.utiliserPouvoir();
		Test.test(roi.getJoueur() == null,
				"test alors que le joueur n'est pas attribué");
		roi.setJoueur(joueur);
		Test.test(roi.getJoueur().getPossedeCouronne() == false, "test avant utilisation");
		roi.utiliserPouvoir();
		Test.test(roi.getJoueur().getPossedeCouronne() == true, "test après utilisation");
	}
	public void test10(){
		System.out.println("TEST DE LA REINITIALISATION");
		Joueur joueur = new Joueur("Billy");
		Roi roi = new Roi();
		roi.setJoueur(joueur);
		roi.setAssassine();
		roi.setVole();
		roi.reinitialiser();
		Test.test(roi.getJoueur() == null, "test du joueur non attribué");
		Test.test(roi.getAssassine() == false, "test de l'assassinat du personnage");
		Test.test(roi.getVole() == false, "test du vol du personnage");
	}
}
