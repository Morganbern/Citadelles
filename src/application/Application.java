package application;

import modele.PlateauDeJeu;

public class Application {
	private static Jeu jeu;
	
	public void main() {
		jeu = new Jeu(new PlateauDeJeu());
		jeu.jouer();
	}
}
