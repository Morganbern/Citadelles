package modele;
import java.util.Random;

import controleur.Interaction;


public class Assassin extends Personnage{
    
    
    public Assassin() {
        super("Assassin", 1, Caracteristiques.ASSASSIN);
    }
    
    private int indiceAssassin() {
    	for(int i=0; i<this.getPlateau().getNombreJoueurs(); i++) {
    		if(this.getPlateau().getJoueur(i).getPersonnage().getNom().equals(new String("Assassin")))
    			return i;
    	}
    	return -1;
    }
    
    public void utiliserPouvoir() {
		Interaction.Send2Joueur(getJoueur(),"Msg: Quel personnage voulez-vous assassiner ?");
    	for(int i=0; i<this.getPlateau().getNombreJoueurs(); i++) {
    		System.out.println((i+1) + " " + this.getPlateau().getJoueur(i).getPersonnage().getNom());
    	}
    	int choix;
    	do {
    		Interaction.Send2Joueur(getJoueur(),"Int: Votre choix : ");
    		choix = Interaction.lireUnEntier(1,this.getPlateau().getNombreJoueurs()+1);
    		if(choix == this.indiceAssassin()+1) {
    			Interaction.Send2Joueur(getJoueur(), "Msg: Vous ne pouvez pas vous assassiner.");
    			choix = -1;
    		}
    			
    	}
    	while(choix == -1);
    	this.getPlateau().getJoueur(choix-1).getPersonnage().setAssassine();
    }

	@Override
	public void utiliserPouvoirAvatar() {
		Random generateur = new Random();
    	int choix;
    	do {
    		choix = generateur.nextInt(this.getPlateau().getNombreJoueurs());
    		if(choix == this.indiceAssassin()) {
    			choix = -1;
    		}
    	}
    	while(choix == -1);
    	this.getPlateau().getJoueur(choix).getPersonnage().setAssassine();
	}
    
}