package modele;

public abstract class Personnage {
	private String nom;
	private int rang;
	private String caracteristiques;
	private Joueur joueur;
	private boolean assassine;
	private boolean vole;
	//private PlateauDeJeu plateau;
	
	
	public String getNom() {
		return nom;
	}
	
	public Personnage(String nom, int rang, String caracteristiques) {
		super();
		this.nom = nom;
		this.rang = rang;
		this.caracteristiques = caracteristiques;
		this.joueur = null;
		this.vole = false;
		this.assassine = false;
	}

	public int getRang() {
		return rang;
	}

	public String getCaracteristiques() {
		return caracteristiques;
	}

	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur j) {
		this.joueur = j;
	}
	
	public boolean getAssassine() {
		return assassine;
	}
	public void setAssassine(boolean assassine) {
		this.assassine = assassine;
	}
	
	public boolean getVole() {
		return vole;
	}
	public void setVole(boolean vole) {
		this.vole = vole;
	}
	
	public void ajouterPieces() { 
		if (this.joueur == null && this.assassine == true)
			joueur.ajouterPieces(2); 
	}
	
	public void ajouterQuartier(Quartier nouveau) { 
		if(this.joueur == null && this.assassine == true)
			this.joueur.ajouterQuartierDansMain(nouveau);
	}
	
	public void construire(Quartier nouveau) { 
		if(this.joueur == null && this.assassine == true)
			this.joueur.ajouterQuartierDansCite(nouveau);
		}
	
	public void percevoirRessourcesSpecifiques() { 
		if(this.joueur == null && this.assassine == true)
			System.out.println("aucune ressource spécifique");
	}
	
	abstract void utiliserPouvoir();
	
	public void reinitialiser() {
		this.joueur.reinitialiser();
		this.vole = false;
		this.assassine = false;
	}
	
	
	
}