package modele;

import java.util.ArrayList;
import java.util.Random;

public class Joueur {
	private String nom;
	private int tresor;
	private Quartier[] cite;
	private int nbQuartier;
	private ArrayList<Quartier> main;
	private boolean possedeCouronne;
	protected Personnage monPersonnage;
	
	public Joueur(String nom) {
		super();
		setNom(nom);
		this.tresor = 0;
		this.nbQuartier = 0;
		this.possedeCouronne = false;
		this.cite = new Quartier[8];
		this.main = new ArrayList<Quartier>();
		this.monPersonnage = null;
	}
	
	public Personnage getPersonnage() {
		return monPersonnage;
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
	
	public void ajouterPieces(int nbPieces) {
		this.tresor += (nbPieces > 0) ? nbPieces : 0;
	}
	
	public void retirerPieces(int nbPieces) {
		if(nbPieces < 0)
			nbPieces = 0;
		this.tresor -= (nbPieces >= this.tresor) ? 0 : nbPieces;
	}
	
	public void ajouterQuartierDansCite(Quartier quartier) {
		if(this.nbQuartier >= 7) // Maximum 7 car 0 - 7 -> 8 quartiers
			System.out.println("La cite est complète, elle ne peux plus acceuillir de nouveaux quartiers");
		else {
			this.cite[this.nbQuartier] = quartier;
			this.nbQuartier++;
		}
	}
	
	public int positionQuartierInCite(String nomQuartier) {
		for(int i=0; i<=this.nbQuartier; i++) {
			if( this.cite[i]!=null && this.cite[i].getNom().equals(nomQuartier)) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean quartierPresentDansCite(String nomQuartier) {
		return positionQuartierInCite(nomQuartier) == -1 ? false : true;
	}
	
	
	public Quartier retirerQuartierDansCite(String nomQuartier) {
		if(this.nbQuartier == 0)
			System.out.println("La cite est vide, aucun quartier ne peut y etre retiré");
		else {
			int indice = positionQuartierInCite(nomQuartier);
			if(indice != -1){
				Quartier quartierARetirer = this.cite[indice];
				for(int i=indice; i<=this.nbQuartier-1; i++) {
					cite[i] = cite[i+1];
				}
				this.nbQuartier--;
				return quartierARetirer;
			}
		}
		return null;
	}
	
	public void ajouterQuartierDansMain(Quartier quartier) { 
		this.main.add(quartier);
	}
	
	public Quartier retirerQuartierDansMain() { 
		Random generateur = new Random();
		int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
		return (this.main.size() <=0) ?null : this.main.remove(numeroHasard);
	}
	
	public void reinitialiser() { 
		this.tresor = 0;
		this.main.clear();
		this.nbQuartier = 0;
		for(int i=0; i<8; i++) {
			this.cite[i] = null;
		}
	}
	
	
	
}
