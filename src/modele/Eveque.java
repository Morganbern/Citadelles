package modele;

import controleur.Interaction;

public class Eveque extends Personnage{
    
    
    public Eveque() {
        super("Eveque", 5, Caracteristiques.EVEQUE);
    }
    
    public void percevoirRessourcesSpecifiques() {
    	if (this.getJoueur() != null && !this.getAssassine()) {
	    	int nbBatRel= 0;
	    	for(int i=0; i< this.getJoueur().nbQuartiersDansCite(); i++) {
	    		if(this.getJoueur().getCite()[i].getType().equals("RELIGIEUX"))
	    			nbBatRel++;
	    	}
	    	this.getJoueur().ajouterPieces(nbBatRel);
	    	if(!this.getJoueur().getIsBot())
	    		Interaction.Send2Joueur(getJoueur(), "Msg: Vous avez " + nbBatRel + " batiment(s) religieux. Vous recevez donc "+ nbBatRel +" piece(s) d'or.");
    	}
    }

	public void utiliserPouvoir() {
	}

	public void utiliserPouvoirAvatar() {
	} 
    
}