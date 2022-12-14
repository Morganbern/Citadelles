package modele;

import java.util.ArrayList;
import java.util.Random;

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
    	
    	Personnage perso = this.joueur.getPersonnage();
    	if(perso.getNom().equals("Architecte")) {
    		Interaction.Send2Joueur(getJoueur(), "Bool: Voulez-vous construire un ou des Quartiers ?");
		}else {
			Interaction.Send2Joueur(getJoueur(), "Bool: Voulez-vous construire un Quartier");
		}
 
		if(Interaction.lireOuiOuNon(getJoueur())) {
			
			ArrayList<Quartier> Main = perso.getJoueur().getMain();
			
			int QuartierACstruire = 0;
			int NbQuartierACstruire = 1;
			
			if(perso.getNom().equals("Architecte")) {
				Interaction.Send2Joueur(getJoueur(),"Int: Combien de quartier souhaitez-vous construire? (Jusqu'?? 3)");
				NbQuartierACstruire = Interaction.lireUnEntier(1,4);
			}
			for (int i=0; i<NbQuartierACstruire;i++) {
				if(Main.size() != 0) {
					boolean boucle = true;
					// Afficher la main
					int index=1;
					for(Quartier quartier : Main) {
						Interaction.Send2Joueur(getJoueur(), "Msg: " + index + ". Nom: " + quartier.getNom() + ", Type: " + quartier.getType() + ", Co??t: " + quartier.getCout() );
						index ++;
					}
					while(boucle){
						Interaction.Send2Joueur(getJoueur(), "Int: Quel quartier voulez-vous construire ?");
						QuartierACstruire = Interaction.lireUnEntier(1,Main.size()+1);
						if( (!this.joueur.isQuartierDansSaCite("Carri??re") && this.joueur.isQuartierDansSaCite(Main.get(QuartierACstruire-1).getNom()))) {
							Interaction.Send2Joueur(getJoueur(), "Msg: Vous ne pouvez pas construire 2 fois le m??me Quartier");
						}else {
							if(this.getJoueur().nbPieces() < Main.get(QuartierACstruire-1).getCout()) {
								Interaction.Send2Joueur(getJoueur(),"Msg: Vous ne pouvez pas construire ce Quartier, vous n'avez pas assez de pi??ces.");
							}else {
								if(this.getJoueur().nbQuartiersDansCite() == 8) {
									Interaction.Send2Joueur(getJoueur(),"Msg: Vous ne pouvez pas construire ce Quartier, votre cit?? est compl??te.");
								}else {
									this.joueur.ajouterQuartierDansCite(Main.get(QuartierACstruire-1));
									if(!this.joueur.joueurAChantier()) this.joueur.retirerPieces(perso.getJoueur().getMain().get(QuartierACstruire-1).getCout());
									this.joueur.retirerQuartierChoisieDansMain(Main.get(QuartierACstruire-1));
									boucle = false;
								}
							}
						}
						
						if(boucle) {
							Interaction.Send2Joueur(getJoueur(),"Bool: Souhaitez-vous toujours construire un quartier ?");
							boucle = Interaction.lireOuiOuNon(getJoueur());
						}
					}
					boucle = true;
				} else {
					Interaction.Send2Joueur(getJoueur(),"Msg: Vous n'avez plus de cartes dans votre main, vous ne pouvez donc pas construire de Quartier");
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
				if (quartier != null && quartier.getCout() <= perso.getJoueur().nbPieces()) 
					ListeDeQuartierAchetable.add(quartier);
			}
			if(ListeDeQuartierAchetable.size() > 0) {
				int QuartierACstruire = generateur.nextInt(ListeDeQuartierAchetable.size());
				this.joueur.ajouterQuartierDansCite(ListeDeQuartierAchetable.get(QuartierACstruire));
				this.joueur.retirerQuartierChoisieDansMain(ListeDeQuartierAchetable.get(QuartierACstruire));
				this.joueur.retirerPieces(ListeDeQuartierAchetable.get(QuartierACstruire).getCout());
			}
		}
    }
    
    public void percevoirRessourcesSpecifiques() {
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