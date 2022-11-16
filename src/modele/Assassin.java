package modele;
import java.util.Random;

import controleur.Interaction;


public class Assassin extends Personnage{
    
    
    public Assassin() {
        super("Assassin", 1, Caracteristiques.ASSASSIN);
    }
    
    private int indiceAssassin() {
    	for(int i=0; i<this.getPlateau().getNombrePersonnages(); i++) {
    		if(this.getPlateau().getPersonnage(i).getNom().equals(new String("Assassin")))
    			return i;
    	}
    	return -1;
    }
    
    public void utiliserPouvoir() {
		System.out.println("Quel personnage voulez-vous assassiner ?");
    	for(int i=0; i<this.getPlateau().getNombrePersonnages(); i++) {
    		System.out.println((i+1) + " " + this.getPlateau().getPersonnage(i).getNom());
    	}
    	int choix;
    	do {
    		System.out.print("Votre choix : ");
    		choix = Interaction.lireUnEntier(1,this.getPlateau().getNombrePersonnages()+1);
    		if(choix == this.indiceAssassin()+1) {
    			System.out.println("Vous ne pouvez pas vous assassiner.");
    			choix = -1;
    		}
    			
    	}
    	while(choix == -1);
    	this.getPlateau().getPersonnage(choix-1).setAssassine();
    }

	@Override
	public void utiliserPouvoirAvatar() {
		Random generateur = new Random();
		int choix;
    	do {
    		choix = generateur.nextInt(this.getPlateau().getNombrePersonnages())+1;
    		if(choix == this.indiceAssassin()+1) {
    			choix = -1;
    		}
    			
    	}
    	while(choix == -1);
    	this.getPlateau().getPersonnage(choix-1).setAssassine();
	} 
    
}