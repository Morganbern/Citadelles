package modele;
import controleur.Interaction;


public class Eveque extends Personnage{
    
    
    public Eveque() {
        super("Eveque", 2, Caracteristiques.ASSASSIN);
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
    		if(choix == this.indiceVoleur()+1 || this.getPlateau().getPersonnage(choix).getRang() == 1) {
    			System.out.println("Vous ne pouvez voler ce joueur.");
    			choix = -1;
    		}
    			
    	}
    	while(choix > this.getPlateau().getNombrePersonnages() || choix < 1);
    	this.getPlateau().getPersonnage(choix-1).setVole();
    } 
    
}