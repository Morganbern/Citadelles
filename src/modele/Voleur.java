package modele;
import java.util.Random;

import controleur.Interaction;


public class Voleur extends Personnage{
    
    
    public Voleur() {
        super("Voleur", 2, Caracteristiques.VOLEUR);
    }
    
    private int indiceVoleur() {
    	for(int i=0; i<this.getPlateau().getNombrePersonnages(); i++) {
    		if(this.getPlateau().getPersonnage(i).getNom().equals(new String("Voleur")))
    			return i;
    	}
    	return -1;
    }
    
    public void utiliserPouvoir() {
		System.out.println("Quel personnage voulez-vous voler ?");
    	for(int i=0; i<this.getPlateau().getNombrePersonnages(); i++) {
    		System.out.println((i+1) + " " + this.getPlateau().getPersonnage(i).getNom());
    	}
    	int choix;
    	do {
    		System.out.print("Votre choix : ");
    		choix = Interaction.lireUnEntier(1,this.getPlateau().getNombrePersonnages()+1);
    		if(choix == this.indiceVoleur()+1 || this.getPlateau().getPersonnage(choix-1).getRang() == 1 || this.getPlateau().getPersonnage(choix-1).getAssassine()) {
    			System.out.println("Vous ne pouvez voler ce joueur.");
    			choix = -1;
    		}
    			
    	}
    	while(choix == -1);
    	if(this.getPlateau().getPersonnage(choix-1).getJoueur() != null) {
    		this.getPlateau().getPersonnage(choix-1).setVole();
    	}
    }

	@Override
	public void utiliserPouvoirAvatar() {
		Random generateur = new Random();
    	int choix;
    	do {
    		choix = generateur.nextInt(this.getPlateau().getNombrePersonnages())+1; // quel personnage voler ?
    		if(choix == this.indiceVoleur()+1 || this.getPlateau().getPersonnage(choix-1).getRang() == 1 || this.getPlateau().getPersonnage(choix-1).getAssassine()) {
    			choix = -1;
    		}
    			
    	}
    	while(choix == -1);
    	if(this.getPlateau().getPersonnage(choix-1).getJoueur() != null) {
    		this.getPlateau().getPersonnage(choix-1).setVole();
    		int nbPiecesAVoler = this.getPlateau().getPersonnage(choix-1).getJoueur().nbPieces();
    		this.getJoueur().ajouterPieces(nbPiecesAVoler);
    		this.getPlateau().getPersonnage(choix-1).getJoueur().retirerPieces(nbPiecesAVoler);
    	}
	} 
    
}