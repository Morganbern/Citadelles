package modele;


public class Eveque extends Personnage{
    
    
    public Eveque() {
        super("Eveque", 5, Caracteristiques.EVEQUE);
    }
    
    public void percevoirRessourcesSpecifiques() {
    	int nbBatRel= 0;
    	for(int i=0; i< this.getJoueur().nbQuartiersDansCite(); i++) {
    		if(this.getJoueur().getCite()[i].getType().equals("RELIGIEUX"))
    			nbBatRel++;
    	}
    	this.getJoueur().ajouterPieces(nbBatRel);
		System.out.println("Vous avez " + nbBatRel + " batiment(s) religieux. Vous recevez donc "+ nbBatRel +" piece(s) d'or.");
    }

	void utiliserPouvoir() {
	}

	@Override
	void utiliserPouvoirAvatar() {
	} 
    
}