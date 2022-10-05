package modele;



public class Roi extends Personnage{
	
	
	public Roi(String nom, int rang, String caracteristiques) {
		super("Roi", 4, Caracteristiques.ROI);
		// TODO Auto-generated constructor stub
	}
	
	public void utiliserPouvoir() {
		System.out.println("Je prends la couronne");
		super.getJoueur().setPossedeCouronne(true);
	}
	
	public void percevoirRessourcesSpecifiques() { 
		Quartier[] cite = super.getJoueur().getCite();
		int nbNoble = 0;
		for(int i=0; i <=7 ; i++) {
			if(cite[i].getType() == "NOBLE")
				nbNoble++;	
		}
		super.getJoueur().ajouterPieces(nbNoble);
		System.out.println(nbNoble+" ont été ajoutées au trésor du joueur");
		
		
	}
	
	

}
