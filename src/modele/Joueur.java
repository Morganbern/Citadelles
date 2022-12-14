package modele;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Joueur {
	private String nom;
	private int tresor;
	private Quartier[] cite;
	private int nbQuartier;
	private ArrayList<Quartier> main;
	private boolean possedeCouronne;
	private boolean IsBot;
	private boolean termineePremier;
	protected Personnage monPersonnage;
	private BufferedReader in;
	private PrintWriter out;
	
	public Joueur(String nom) {
		super();
		setNom(nom);
		this.tresor = 0;
		this.nbQuartier = 0;
		this.possedeCouronne = false;
		this.cite = new Quartier[8];
		this.main = new ArrayList<Quartier>();
		this.monPersonnage = null;
		this.IsBot = false;
		this.in = null;
		this.out = null;
	}
	
	public boolean isTermineePremier() {
		return termineePremier;
	}

	public void setTermineePremier(boolean termineePremier) {
		this.termineePremier = termineePremier;
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
	
	public Boolean getIsBot() {
		return IsBot;
	}
	
	public void setIsBot(boolean IsBot) {
		this.IsBot = IsBot;
	}
	
	public void ajouterPieces(int nbPieces) {
		this.tresor += (nbPieces > 0) ? nbPieces : 0;
	}
	
	public void retirerPieces(int nbPieces) {
		if(nbPieces < 0)
			nbPieces = 0;
		this.tresor -= (nbPieces > this.tresor) ? 0 : nbPieces;
	}
	
	public void ajouterQuartierDansCite(Quartier quartier) {
		if(this.nbQuartier >= 8) // Maximum 7 car 0 - 7 -> 8 quartiers
			Interaction.Send2Joueur(this.monPersonnage.getJoueur(), "Msg: La cite est compl??te, elle ne peux plus acceuillir de nouveaux quartiers");
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
			Interaction.Send2Joueur(this.monPersonnage.getJoueur(), "Msg: La cite est vide, aucun quartier ne peut y etre retir??");
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
	
	public boolean isQuartierDansSaCite(String nomQuartier) {
		for(int j=0;j<nbQuartiersDansCite();j++) {
			if(getCite()[j].getNom().equals(nomQuartier))
				return true;
		}
		return false;
	}
	
	public Quartier retirerQuartierChoisieDansMain(Quartier quartier) {
		int i=0, index=-1;
		for(Quartier quart : main) {
			if (quart == quartier)
				index = i;
			i++;
		}
		return (index != -1) ? main.remove(index) : null;
	}
	
	public boolean joueurAChantier() {
		if(isQuartierDansSaCite("Chantier")) {
			Interaction.Send2Joueur(this.monPersonnage.getJoueur(), "Bool: Souhaitez vous detruire votre chantier pour ne pas payer le cout de construction de votre quartier ?");
			if(Interaction.lireOuiOuNon(this.monPersonnage.getJoueur())) {
				retirerQuartierDansCite("Chantier");
				return true;
			}
		}
		return false;
	}
	
	public void reinitialiser() { 
		this.tresor = 0;
		this.main.clear();
		this.nbQuartier = 0;
		for(int i=0; i<8; i++) {
			this.cite[i] = null;
		}
	}
	
	public BufferedReader getIn() {
		return in;
	}
	
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	
	public PrintWriter getOut() {
		return out;
	}
	
	public void setOut(PrintWriter out) {
		this.out = out;
	}
}
