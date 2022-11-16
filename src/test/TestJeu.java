package test;

import application.Configuration;
import application.Jeu;
import controleur.Interaction;
import modele.Pioche;
import modele.PlateauDeJeu;

public class TestJeu {
	
	public static void main(String[] args){
		TestJeu test = new TestJeu();
		test.test1();
		//test.test2();
}	
	
	public void test1() {
		System.out.println("Test tour de jeu");
		Pioche pioche = Configuration.nouvellePioche();
		PlateauDeJeu plateau = Configuration.configurationDeBase(pioche);
		Jeu jeu = new Jeu(plateau);
		jeu.tourDeJeu();
		
	}
	
	
}