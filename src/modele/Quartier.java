package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import controleur.Interaction;

public class Quartier {
	public static final String[] TYPE_QUARTIERS = {"RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};
	private String nom;
	private String type;
	private int coutConstruction;
	private String caracteristiques;
	
	public Quartier(String nom, String type, int cout, String caracteristiques) {
		super();
		setNom(nom);
		setType(type);
		setCout(cout);
		setCaracteristiques(caracteristiques);
	}
	
	public Quartier(String nom, String type, int cout) {
		super();
		setNom(nom);
		setType(type);
		setCout(cout);
		setCaracteristiques("");
	}
	
	public Quartier(String nom, String type) {
		super();
		setNom(nom);
		setType(type);
		setCout(0);
		setCaracteristiques("");
	}
	public Quartier(String nom) {
		super();
		setNom(nom);
		setType("");
		setCout(0);
		setCaracteristiques("");
	}
	
	public Quartier() {
		super();
		setNom("");
		setType("");
		setCout(0);
		setCaracteristiques("");
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = Arrays.asList(TYPE_QUARTIERS).contains(type) ? type  : "";
	}
	
	public int getCout() {
		return coutConstruction;
	}
	public void setCout(int cout) {
		this.coutConstruction = (cout >= 1 && cout <= 6) ? cout : 0;
	}
	
	public String getCaracteristiques() {
		return caracteristiques;
	}
	public void setCaracteristiques(String caracteristiques) {
		this.caracteristiques = caracteristiques;
	}
	
	public int calculDesPointsMerveilles(Joueur joueur) {
		switch (this.nom){
		case "Capitole": {
			ArrayList<String> tabTypeQuartier = new ArrayList<String>();
			for(int i=0; i<joueur.nbQuartiersDansCite();i++) {
				tabTypeQuartier.add(joueur.getCite()[i].type);
			}
			for (int i=0; i<5; i++) {
				if(Collections.frequency(tabTypeQuartier, TYPE_QUARTIERS[i]) >= 3)
					return 3;
			}
			return 0;
		}
		case "Cours des Miracles": {
			if(!joueur.getIsBot()) {
				System.out.println("Choisissez le type de quartier que vous voulez associez à 'Cours des Miracles'");
				for (int i=0; i<5; i++) {
					System.out.println((i+1) + ": " + TYPE_QUARTIERS[i]);
				}
				int choixType = Interaction.lireUnEntier(1, 6);
				this.type = TYPE_QUARTIERS[choixType];
			}
		}
		case "Statue Equestre": {
			if(joueur.getPossedeCouronne()) {
				return 5;
			}
		}
		default:
			return 0;
		}
	}
	
}
