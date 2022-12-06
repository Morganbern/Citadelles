package modele;
import java.util.Random;

import controleur.Interaction;


public class Voleur extends Personnage{
    
    
    public Voleur() {
        super("Voleur", 2, Caracteristiques.VOLEUR);
    }
    
    private int indiceVoleur() {
    	for(int i=0; i<this.getPlateau().getNombreJoueurs(); i++) {
    		if(this.getPlateau().getJoueur(i).getPersonnage().getNom().equals(new String("Voleur")))
    			return i;
    	}
    	return -1;
    }
    
    public void utiliserPouvoir() {
		System.out.println("Quel personnage voulez-vous voler ?");
    	for(int i=0; i<this.getPlateau().getNombreJoueurs(); i++) {
    		System.out.println((i+1) + " " + this.getPlateau().getJoueur(i).getPersonnage().getNom());
    	}
    	int choix;
    	do {
    		System.out.print("Votre choix : ");
    		choix = Interaction.lireUnEntier(1,this.getPlateau().getNombreJoueurs()+1);
    		if(choix-1 == this.indiceVoleur() || this.getPlateau().getJoueur(choix-1).getPersonnage().getRang() == 1 || this.getPlateau().getJoueur(choix-1).getPersonnage().getAssassine()) {
    			System.out.println("Vous ne pouvez voler ce joueur.");
    			choix = -1;
    		}
    			
    	}
    	while(choix == -1);
    	if(this.getPlateau().getJoueur(choix-1) != null) {
    		this.getPlateau().getJoueur(choix-1).getPersonnage().setVole();
    	}
    }

	@Override
	public void utiliserPouvoirAvatar() {
		Random generateur = new Random();
    	int choix;
    	do {
    		choix = generateur.nextInt(this.getPlateau().getNombrePersonnages()); // quel personnage voler ?
    		if(choix == this.indiceVoleur() || this.getPlateau().getJoueur(choix).getPersonnage().getRang() == 1 || this.getPlateau().getJoueur(choix).getPersonnage().getAssassine()) {
    			choix = -1;
    		}
    			
    	}
    	while(choix == -1);
    	if(this.getPlateau().getJoueur(choix) != null) {
    		this.getPlateau().getJoueur(choix).getPersonnage().setVole();
    	}
	} 
    
}