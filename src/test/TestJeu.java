package test;

import application.Jeu;

public class TestJeu {
	
	public static void main(String[] args){
		TestJeu test = new TestJeu();
		test.test1();
		test.test2();
	}	

	public void test1() {
		System.out.println("Test Jouer");
		Jeu jeu = new Jeu();
		jeu.jouer();
	}
}