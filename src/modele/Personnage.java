package modele;

import java.util.ArrayList;
import java.util.Random;

import application.Jeu;
import controleur.Interaction;

public abstract class Personnage{
    private String nom;
    private int rang;
    private String caracteristiques;
    private Joueur joueur;
    private boolean assassine;
    private boolean vole;
    private PlateauDeJeu plateau;
    
    
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
        this.joueur.monPersonnage = this;
    }
    
    public boolean getAssassine() {
        return assassine;
    }
    
    public void setAssassine() {
        this.assassine = true;
    }
    
    public boolean getVole() {
        return vole;
    }
    public void setVole() {
        this.vole = true;
    }
    
    public void ajouterPieces() { 
        if (this.joueur != null && !this.assassine)
            joueur.ajouterPieces(2); 
    }
    
    public void ajouterQuartier(Quartier nouveau) { 
    	if (this.joueur != null && !this.assassine)
            this.joueur.ajouterQuartierDansMain(nouveau);
    }
    
    public void construire() { 
    	if (this.joueur == null || this.assassine) return;
    	if (!this.joueur.getIsBot()) {
    		PersoConstruitQuartier();
    	}else {
    		BotConstruitQuartier();
    	}    	
    }
    
    private void PersoConstruitQuartier() {
    	
    	Random generateur = new Random();
    	Personnage perso = this.joueur.getPersonnage();
    	
    	if(perso.getNom().equals("Architecte")) {
			System.out.println("Voulez-vous construire un ou des Quartiers ?");
		}else {
			System.out.println("Voulez-vous construire un Quartier");
		}
 
		if(Interaction.lireOuiOuNon()) {
			
			ArrayList<Quartier> Main = perso.getJoueur().getMain();
			
			// Afficher la main
			int index=1;
			for(Quartier quartier : Main) {
				System.out.println(index + ". Nom: " + quartier.getNom() + ", Type: " + quartier.getType() + ", Coût: " + quartier.getCout() );
				index ++;
			}
			
			int QuartierACstruire = 0;
			int NbQuartierACstruire = 1;
			
			if(perso.getNom().equals("Architecte")) {
				System.out.println("Combien de quartier souhaitez-vous construire? (Jusqu'à 3)");
				NbQuartierACstruire = generateur.nextInt(4);
			}
			
			boolean choix=false; 
			for (int i=0; i<NbQuartierACstruire;i++) {
				System.out.println("Quel quartier voulez-vous construire ?");
				QuartierACstruire = Interaction.lireUnEntier(1,index);
				while(this.joueur.getMain().get(QuartierACstruire-1).getCout() > this.joueur.nbPieces() ||
						!(this.joueur.isQuartierDansSaCite("Carrière")) ||
						!(this.joueur.isQuartierDansSaCite(Main.get(QuartierACstruire-1).getNom())) ||
						!choix
						){
					System.out.println("Souhaitez-vous toujours construire un quartier ?");
					choix = generateur.nextBoolean();
					if (choix) {
						System.out.println("Quel quartier voulez-vous construire ?");
						QuartierACstruire = Interaction.lireUnEntier(1,index);
					}
				}
				
				if(1<=QuartierACstruire && QuartierACstruire<=index) {
					this.joueur.ajouterQuartierDansCite(Main.get(QuartierACstruire-1));
					this.joueur.retirerQuartierChoisieDansMain(perso.getJoueur().getMain().get(QuartierACstruire-1));
					if(!this.joueur.joueurAChantier()) this.joueur.retirerPieces(perso.getJoueur().getMain().get(QuartierACstruire-1).getCout());
				}
			}
			
			
		}
    }
    
    public void BotConstruitQuartier() {
    	Random generateur = new Random();
    	Personnage perso = this.joueur.getPersonnage();
    	ArrayList<Quartier> ListeDeQuartierAchetable = new ArrayList<Quartier>();
    	
    	
		if(generateur.nextBoolean()) {
			for(Quartier quartier : perso.getJoueur().getMain()) {
				if (quartier.getCout() < perso.getJoueur().nbPieces()) ListeDeQuartierAchetable.add(quartier);
			}
			int QuartierACstruire = generateur.nextInt(ListeDeQuartierAchetable.size());
			this.joueur.ajouterQuartierDansCite(this.joueur.getMain().get(QuartierACstruire));
			this.joueur.retirerQuartierChoisieDansMain(this.joueur.getMain().get(QuartierACstruire));
			this.joueur.retirerPieces(perso.getJoueur().getMain().get(QuartierACstruire-1).getCout());
		}
    }
    
    public void percevoirRessourcesSpecifiques() { 
    	if (this.joueur != null && !this.assassine)
            System.out.println("aucune ressource spécifique");
    }
    
    
    public PlateauDeJeu getPlateau() {
		return plateau;
	}

	public void setPlateau(PlateauDeJeu plateau) {
		this.plateau = plateau;
	}

	public abstract void utiliserPouvoir();
	
	
	public abstract void utiliserPouvoirAvatar();
    
    public void reinitialiser() {
        if(this.joueur != null)
        	this.joueur.monPersonnage = null;
        this.joueur = null;
        this.vole = false;
        this.assassine = false;
    }
    
    
    
}