package test;

import modele.Quartier;
import modele.Joueur;

public class TestJoueur {
	public static void main(String[] args){
		TestJoueur testJoueur = new TestJoueur();
		testJoueur.test1();
		//testJoueur.test2();	
		//testJoueur.test3();
		//testJoueur.test4();
		//testJoueur.test5();	
	}	
	
	public void test1(){
		System.out.println("TEST DU CONSTRUCTEUR");
		Joueur joueur = new Joueur("Billy");
		Test.test(joueur.getNom().equals("Billy"),"test du nom du joueur");
		Test.test(joueur.nbPieces() == 0,"test du trésor initial du joueur");
		Test.test(joueur.nbQuartiersDansCite()== 0,
				"test de nombre de quartiers dans la cite");
		Test.test(joueur.nbQuartiersDansMain()== 0,
				"test du nombre de quartiers dans la main du joueur");
	}
	
	public void test2() {
		System.out.println("TEST DU TRESOR DU JOUEUR");
		Joueur joueur = new Joueur("Billy");
		joueur.ajouterPieces(2);
		joueur.ajouterPieces(1);
		Test.test(joueur.nbPieces() == 3,"test de l'ajout d'un nombre de pièces");
		joueur.ajouterPieces(-2);
		Test.test(joueur.nbPieces() == 3,"test d'ajout d'un nombre de pièces négatif");
		joueur.retirerPieces(-1);
		Test.test(joueur.nbPieces() == 3,"test d'un retrait d'un nombre négatif de pièces");
		joueur.retirerPieces(4);
		Test.test(joueur.nbPieces() == 3,"test de retrait d'un trop grand nombre de pièces");
		joueur.retirerPieces(2);
		joueur.retirerPieces(1);
		Test.test(joueur.nbPieces() == 0,"test de retrait d'un nombre de pièces");
	}
	
	public void test3() {
		System.out.println("TEST DE LA CITE DU JOUEUR");
		Joueur joueur = new Joueur("Billy");
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		joueur.ajouterQuartierDansCite(quartier1);
		joueur.ajouterQuartierDansCite(quartier2);
		joueur.ajouterQuartierDansCite(quartier3);
		Test.test(joueur.nbQuartiersDansCite() == 3,"test de l'ajout de trois quartiers");
		Test.test(joueur.quartierPresentDansCite("temple"), "test de présence d'un quartier");
		Quartier retour = joueur.retirerQuartierDansCite("prison");
		Test.test(joueur.nbQuartiersDansCite() == 2 && retour==quartier2, 
				"test de retrait d'un quartier");
		Test.test(!joueur.quartierPresentDansCite("marché"), "test de non présence d'un quartier");
	}
	
	public void test4() {
		System.out.println("TEST DE LA MAIN DU JOUEUR");
		Joueur joueur = new Joueur("Billy");
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		
		joueur.ajouterQuartierDansMain(quartier1);
		joueur.ajouterQuartierDansMain(quartier2);
		joueur.ajouterQuartierDansMain(quartier3);
		Test.test(joueur.nbQuartiersDansMain() == 3,"test de l'ajout de trois quartiers");
		Quartier retour = joueur.retirerQuartierDansMain();
		Test.test(joueur.nbQuartiersDansMain() == 2 && 
				(retour==quartier1 || retour==quartier2 || retour==quartier3), 
				"test de retrait d'un quartier");		
	}
	
	public void test5() {
		System.out.println("TEST DE LA REINITIALISATION DU JOUEUR");
		Joueur joueur = new Joueur("Billy");
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		joueur.ajouterQuartierDansMain(quartier1);
		joueur.ajouterQuartierDansMain(quartier2);
		joueur.ajouterQuartierDansCite(quartier3);
		joueur.reinitialiser();
		joueur.ajouterPieces(2);
		joueur.reinitialiser();
		Test.test(joueur.nbQuartiersDansMain()==0 && joueur.nbQuartiersDansCite()==0
				&& joueur.nbPieces()==0,"test de la réinitialisation");		
	}
}