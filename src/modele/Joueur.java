package modele;

import java.util.ArrayList;

public class Joueur {
	private String nom;
	private int tresor;
	private Quartier[] cite;
	private int nbQuartier;
	private ArrayList<Quartier> main;
	private boolean possedeCouronne;
	
	public Joueur(String nom) {
		super();
		setNom(nom);
		this.tresor = 0;
		this.nbQuartier = 0;
		this.possedeCouronne = false;
		this.cite = new Quartier[8];
		this.main = new ArrayList<Quartier>();
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int nbPieces() {
		return tresor;
	}
	public Quartier[] getCite() {
		return cite;
	}
	public int nbQuartiersDansCite() {
		return nbQuartier;
	}
	public int nbQuartiersDansMain() {
		return main.size();
	}
	public ArrayList<Quartier> getMain() {
		return main;
	}
	public boolean getPossedeCouronne() {
		return possedeCouronne;
	}
	public void setPossedeCouronne(boolean possedeCouronne) {
		this.possedeCouronne = possedeCouronne;
	}
	
	
}
