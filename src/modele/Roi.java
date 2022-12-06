package modele;

import controleur.Interaction;

public class Roi extends Personnage{
    
    
    public Roi() {
        super("Roi", 4, Caracteristiques.ROI);
        // TODO Auto-generated constructor stub
    }
    
    public void utiliserPouvoir() {
    	Interaction.outToAll(getJoueur().getNom()+ "prend la couronne"); 
        if(super.getJoueur()!= null)
            super.getJoueur().setPossedeCouronne(true);
    }
    
    public void percevoirRessourcesSpecifiques() { 
    	if (this.getJoueur() != null && !this.getAssassine()) {
	        Quartier[] cite;
	        if(super.getJoueur()!=null) {
	            cite = super.getJoueur().getCite();
	            int nbNoble = 0;
	            
	            for(int i=0; i <super.getJoueur().nbQuartiersDansCite() ; i++) {
	                if(cite[i]!= null && cite[i].getType() == "NOBLE")
	                    nbNoble++;    
	            }
	            super.getJoueur().ajouterPieces(nbNoble);
	            if(!this.getJoueur().getIsBot())
	            	Interaction.Send2Joueur(getJoueur(), "Msg: "+nbNoble+" ont été ajoutées au trésor du joueur");;    
	        }
    	}    
    }

	@Override
	public void utiliserPouvoirAvatar() {
		if(super.getJoueur()!= null)
            super.getJoueur().setPossedeCouronne(true);
	}
    
    

}