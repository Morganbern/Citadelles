package modele;

import java.util.Random;

import controleur.Interaction;

public class Condottiere extends Personnage{
    
    
    public Condottiere() {
        super("Condottiere", 8, Caracteristiques.CONDOTTIERE);
    }
    
    public void percevoirRessourcesSpecifiques() {
    	if (this.getJoueur() != null && !this.getAssassine()) {
    		int nbBatMil= 0;
        	for(int i=0; i< this.getJoueur().nbQuartiersDansCite(); i++) {
        		if(this.getJoueur().getCite()[i].getType().equals("MILITAIRE"))
        			nbBatMil++;
        	}
        	this.getJoueur().ajouterPieces(nbBatMil);
    		if(!this.getJoueur().getIsBot())
    			Interaction.Send2Joueur(getJoueur(), "Msg: Vous avez " + nbBatMil + " batiment(s) commercants. Vous recevez donc "+ nbBatMil +" piece(s) d'or.");
    	}
    }
    
	@Override
	public void utiliserPouvoir() {
		Interaction.Send2Joueur(getJoueur(),"Bool: Voulez-vous utiliser votre pouvoir de destruction ? ");
		if(Interaction.lireOuiOuNon(getJoueur())){
	    	int choixJ,choixQ = 0;
	    	do {
	    		Interaction.Send2Joueur(getJoueur(),"Msg: Voici la liste des joueurs et le contenu de leur cité :");
		    	for(int i=0; i<this.getPlateau().getNombreJoueurs(); i++) {
		    		afficherContenuJoueur(i);
		    	}
		    	Interaction.Send2Joueur( getJoueur(), "Msg: Pour information, vous avez "+ this.getJoueur().nbPieces() + " pièce(s) d’or dans votre trésor");
		    	Interaction.Send2Joueur( getJoueur(), "Int: Quel joueur choisissez-vous ? (0 pour ne rien faire) ");
	    		choixJ = Interaction.lireUnEntier(0,this.getPlateau().getNombreJoueurs()+1);
	    		if(choixJ != 0) {
		    		if(this.getPlateau().getJoueur(choixJ-1).nbQuartiersDansCite() == 8) {
		    			Interaction.Send2Joueur(getJoueur(), "Msg: Vous ne pouvez pas selectionner un joueur dont la cité est déja complète.");
		    			choixJ = -1;
		    		}else if(choixJ+1 == this.indiceEveque() && !this.getPlateau().getJoueur(this.indiceEveque()).getPersonnage().getAssassine()) {
		    			Interaction.Send2Joueur(getJoueur(), "Msg: Vous ne pouvez pas choisir l'éveque si il n'est pas assasiné.");
		    			choixJ = -1;
		    		}else{
		    			Interaction.Send2Joueur(getJoueur(), "Int: Quel quartier choisissez-vous chez " + this.getPlateau().getJoueur(choixJ-1).getNom() + " ? ");
			    		choixQ = Interaction.lireUnEntier(1,this.getPlateau().getJoueur(choixJ-1).nbQuartiersDansCite()+1);
			    		if(this.getPlateau().getJoueur(choixJ-1).getCite()[choixQ-1].getCout()-1 > this.getJoueur().nbPieces()) {
			    			Interaction.Send2Joueur(getJoueur(), "Int: Votre trésor n'est pas suffisant");
				    		choixQ = -1;
			    		}
		    		}
	    		}
	    	}while(choixJ == -1 || choixQ == -1);
	    	if(choixJ != 0) {
	    		if(!this.getPlateau().getJoueur(choixJ-1).isQuartierDansSaCite("Donjon")) {
	    			Interaction.Send2Joueur(getJoueur(), "Msg: => On retire le quartier " + this.getPlateau().getJoueur(choixJ-1).getCite()[choixQ-1].getNom() + " à " + this.getPlateau().getJoueur(choixJ-1).getNom());
					this.getJoueur().retirerPieces(this.getPlateau().getJoueur(choixJ-1).getCite()[choixQ-1].getCout()-1);
					this.getPlateau().getJoueur(choixJ-1).retirerQuartierDansCite(this.getPlateau().getJoueur(choixJ-1).getCite()[choixQ-1].getNom());
					Interaction.Send2Joueur(getJoueur(),"Pour information, vous avez "+ this.getJoueur().nbPieces() + " pièce(s) d’or dans votre trésor");
	    		}else {
	    			Interaction.Send2Joueur(getJoueur(),"Cela n'a aucun effet sur le joueur puisqu'il possède le quartier Donjon dans sa cité");
	    		}
	    	}
		}
	}
	
    private int indiceEveque() {
    	for(int i=0; i<this.getPlateau().getNombreJoueurs(); i++) {
    		if(this.getPlateau().getJoueur(i).getPersonnage().getNom().equals(new String("Eveque")))
    			return i;
    	}
    	return -1;
    }

	private void afficherContenuJoueur(int i) {
		Interaction.Send2Joueur(getJoueur(),"Msg: "+ (i+1) + " | " + this.getPlateau().getJoueur(i).getPersonnage().getNom() + " (" + this.getPlateau().getJoueur(i).getNom() + ") : ");
		Quartier temp;
		for(int j=0; j < this.getPlateau().getJoueur(i).nbQuartiersDansCite(); j++) {
			temp = this.getPlateau().getJoueur(i).getCite()[j];
			Interaction.Send2Joueur(getJoueur(), "Msg: "+(j+1)+ " " + temp.getNom() + " (coût " + String.valueOf(temp.getCout()-1) + "), ");
		}
		if(this.getPlateau().getJoueur(i).nbQuartiersDansCite() == 8) {
			Interaction.Send2Joueur(getJoueur(), "Msg: (cité complète) ");
		}
		Interaction.Send2Joueur(getJoueur(), "\n");
	}
	
	public void utiliserPouvoirAvatar() {
		Random generateur = new Random();
		if(generateur.nextInt(2) == 1){ //avatar veut utiliser son pouvoir ?
	    	int choixJ,choixQ = 0;
	    	do {
	    		choixJ = generateur.nextInt(this.getPlateau().getNombreJoueurs()+1); // choix du joueur aléatoire (0 = personne)
	    		if(choixJ != 0) {
		    		if(this.getPlateau().getJoueur(choixJ-1).nbQuartiersDansCite() == 8) { // la cite du joueur choisi est complete
		    			choixJ = -1;
		    		}else if(choixJ == this.indiceEveque()+1 && !this.getPlateau().getJoueur(this.indiceEveque()).getPersonnage().getAssassine()) { // pas possible de choisir un eveque vivant
		    			choixJ = -1;
		    		}else{
		    			if(this.getPlateau().getJoueur(choixJ-1).nbQuartiersDansCite() != 0){
			    			choixQ = generateur.nextInt(this.getPlateau().getJoueur(choixJ-1).nbQuartiersDansCite()); // choix du quartier
				    		if(this.getPlateau().getJoueur(choixJ-1).getCite()[choixQ].getCout()-1 > this.getJoueur().nbPieces()) { //tresor insuffisant
					    		choixQ = -1;
				    		}
		    			}else {
		    				choixJ = 0;
		    			}
		    		}
	    		}
	    	}while(choixJ == -1 || choixQ == -1);
	    	if(choixJ != 0 && !this.getPlateau().getJoueur(choixJ-1).isQuartierDansSaCite("Donjon")) {
	    		Interaction.outToAll("=> On retire le quartier " + this.getPlateau().getJoueur(choixJ-1).getCite()[choixQ].getNom() + " à " + this.getPlateau().getJoueur(choixJ-1).getNom());
				this.getJoueur().retirerPieces(this.getPlateau().getJoueur(choixJ-1).getCite()[choixQ].getCout()-1);
				this.getPlateau().getJoueur(choixJ-1).retirerQuartierDansCite(this.getPlateau().getJoueur(choixJ-1).getCite()[choixQ].getNom());
	    	}
		}
	}
    
}