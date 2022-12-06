package modele;

import controleur.Interaction;

public class Marchande extends Personnage{
    
    
    public Marchande() {
        super("Marchande", 6, Caracteristiques.MARCHANDE);
    }
    
    public void percevoirRessourcesSpecifiques() {
    	if (this.getJoueur() != null && !this.getAssassine()) {
	    	int nbBatCom= 0;
	    	for(int i=0; i< this.getJoueur().nbQuartiersDansCite(); i++) {
	    		if(this.getJoueur().getCite()[i].getType().equals("COMMERCANT"))
	    			nbBatCom++;
	    	}
	    	this.getJoueur().ajouterPieces(nbBatCom);
	    	if(!this.getJoueur().getIsBot())
	    		Interaction.Send2Joueur(getJoueur(), "Msg: Vous avez " + nbBatCom + " batiment(s) commercants. Vous recevez donc "+ nbBatCom +" piece(s) d'or.");
    	}
    }

	public void utiliserPouvoir() {
		Interaction.Send2Joueur(getJoueur(), "Int: Vous utiliser votre pouvoir. Vous recevez donc 1 piece d'or.");
		this.getJoueur().ajouterPieces(1);
	}

	@Override
	public void utiliserPouvoirAvatar() {
		this.getJoueur().ajouterPieces(1);
	} 
    
}