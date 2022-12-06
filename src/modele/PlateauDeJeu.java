package modele;

import controleur.Interaction;

public class PlateauDeJeu {
	private Personnage[] listePersonnages;
	private Joueur[] listeJoueurs;
	private Pioche pioche;
	private int nombrePersonnages;
	private int nombreJoueurs;
	
	public PlateauDeJeu() {
		super();
		this.listeJoueurs = new Joueur[9];
		this.listePersonnages = new Personnage[9];
		this.nombreJoueurs = 0;
		this.nombrePersonnages = 0;
		pioche = new Pioche();
	}

	public void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}

	public Personnage getPersonnage(int i) {
		if(i >= 0 && i < this.nombrePersonnages)
			return listePersonnages[i];
		return null;
	}

	public Joueur getJoueur(int i) {
		if(i >= 0 && i < this.nombreJoueurs)
			return listeJoueurs[i];
		return null;
	}

	public Pioche getPioche() {
		return pioche;
	}

	public int getNombrePersonnages() {
		return nombrePersonnages;
	}

	public int getNombreJoueurs() {
		return nombreJoueurs;
	}
	
	public void ajouterPersonnage(Personnage perso) {
		if(this.nombrePersonnages >= 9)
			Interaction.outToAll("Nombre de personnages maximum atteint");
		else if(perso == null)
			Interaction.outToAll("Personnage non existant");
		else {
			perso.setPlateau(this);
			this.listePersonnages[this.nombrePersonnages++] = perso;
		}
	}
	
	public void ajouterJoueur(Joueur joueur) {
		if(this.nombreJoueurs >= 9)
			Interaction.outToAll("Nombre de joueur maximum atteint");
		else if(joueur == null)
			Interaction.outToAll("Joueur non existant");
		else {
			this.listeJoueurs[this.nombreJoueurs++] = joueur;
		}
	}
	
	
}
