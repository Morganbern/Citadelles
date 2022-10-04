package modele;

import java.util.Arrays;

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
	
}
