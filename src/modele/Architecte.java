package modele;


public class Architecte extends Personnage{
    
    
    public Architecte() {
        super("Architecte", 7, Caracteristiques.ARCHITECTE);
    }
    
	public void utiliserPouvoir() {
		Quartier quartier;
		for(int i=0; i<2; i++) {
			quartier = this.getPlateau().getPioche().piocher();
			System.out.println("Vous piochez :" + quartier.getNom() + " (" +quartier.getType() + ") : coût " + quartier.getCout());
			this.getJoueur().ajouterQuartierDansMain(quartier);
		}
	}

	@Override
	public void utiliserPouvoirAvatar() {
		this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
		this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
	}
    
}