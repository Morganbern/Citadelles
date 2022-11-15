package modele;



public class Roi extends Personnage{
    
    
    public Roi() {
        super("Roi", 4, Caracteristiques.ROI);
        // TODO Auto-generated constructor stub
    }
    
    public void utiliserPouvoir() {
        System.out.println("Je prends la couronne");
        if(super.getJoueur()!= null)
            super.getJoueur().setPossedeCouronne(true);
    }
    
    public void percevoirRessourcesSpecifiques() { 
        Quartier[] cite;
        if(super.getJoueur()!=null) {
            cite = super.getJoueur().getCite();
            int nbNoble = 0;
            
            for(int i=0; i <super.getJoueur().nbQuartiersDansCite() ; i++) {
                if(cite[i]!= null && cite[i].getType() == "NOBLE")
                    nbNoble++;    
            }
            super.getJoueur().ajouterPieces(nbNoble);
            System.out.println(nbNoble+" ont été ajoutées au trésor du joueur");    
        }
    
    }

	@Override
	void utiliserPouvoirAvatar() {
		if(super.getJoueur()!= null)
            super.getJoueur().setPossedeCouronne(true);
	}
    
    

}