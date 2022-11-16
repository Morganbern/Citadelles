package modele;


public class Architecte extends Personnage{
    
    
    public Architecte() {
        super("Architecte", 7, Caracteristiques.ARCHITECTE);
    }
    
	public void utiliserPouvoir() {
		this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
		this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
	}

	@Override
	public void utiliserPouvoirAvatar() {
		this.utiliserPouvoir();
	}
    
}