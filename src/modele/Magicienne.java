package modele;
import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;


public class Magicienne extends Personnage{
    
    
    public Magicienne() {
        super("Magicienne", 3, Caracteristiques.MAGICIENNE);
    }
    
    private int indiceMagicienne() {
    	for(int i=0; i<this.getPlateau().getNombreJoueurs(); i++) {
    		if(this.getPlateau().getJoueur(i).getPersonnage().getNom().equals(new String("Magicienne")))
    			return i;
    	}
    	return -1;
    }
    
    public void utiliserPouvoir() {
    	
    	
		System.out.println("Voulez-vous  ́echanger vos cartes avec celles d’un autre joueur ?(o/n) ");
		boolean reponse = Interaction.lireOuiOuNon();
		if(reponse) {
	    	for(int i=0; i<this.getPlateau().getNombreJoueurs(); i++) {
	    		System.out.println((i+1) + " " + this.getPlateau().getJoueur(i).getPersonnage().getNom() + "- Nb de cartes : " + this.getPlateau().getJoueur(i).nbQuartiersDansMain());
	    	}
	    	int choix;
	    	do {
	    		System.out.println("Votre choix : ");
	    		choix = Interaction.lireUnEntier(1,this.getPlateau().getNombreJoueurs()+1);
	    		if(this.getPlateau().getJoueur(choix-1).getPersonnage().getNom().equals(new String("Magicienne"))) {
	    			System.out.println("Vous ne pouvez pas vous échanger vos cartes.");
	    			choix = -1;
	    		}
	    			
	    	}while(choix == -1);
	    	
	    	ArrayList<Quartier> copieMagicienne = new ArrayList<Quartier>(this.getJoueur().getMain());
	    	ArrayList<Quartier> copiePersonne = new ArrayList<Quartier>(this.getPlateau().getJoueur(choix-1).getMain());
	    	
	    	while(this.getJoueur().nbQuartiersDansMain()>0) {
	    		this.getJoueur().retirerQuartierDansMain();
	    	}
	    	while(this.getPlateau().getJoueur(choix-1).nbQuartiersDansMain()>0) {
	    		this.getPlateau().getJoueur(choix-1).retirerQuartierDansMain();
	    	}
	    	
	    	for(int i=0; i<copieMagicienne.size(); i++) {
	    		this.getPlateau().getJoueur(choix-1).ajouterQuartierDansMain(copieMagicienne.get(i));
	    	}
	    	for(int i=0; i<copiePersonne.size(); i++) { 
	    		this.getJoueur().ajouterQuartierDansMain(copiePersonne.get(i));
	    	}
	    	  	
		}else if(this.getJoueur().nbQuartiersDansMain()==0){
			
		}else {
			System.out.println("Combien de cartes voulez-vous prendre dans la pioche ? ");
			int nbCarteMainMagicienne=this.getJoueur().nbQuartiersDansMain();
			int nbCartes = Interaction.lireUnEntier(0, this.getJoueur().nbQuartiersDansMain()+1);
			if(nbCartes == 0) {
				
			}else if(nbCartes == nbCarteMainMagicienne) {
				
				ArrayList<Quartier> copieMagicienne = new ArrayList<Quartier>(this.getJoueur().getMain());
		    	for(int i=0; i<nbCarteMainMagicienne;i++) {
		    		this.getJoueur().retirerQuartierDansMain();
		    		this.getPlateau().getPioche().ajouter(copieMagicienne.get(i));
		    	}
		    	for(int i=0; i<nbCarteMainMagicienne; i++) {
		    		this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
		    	}		
			}else {
				
				ArrayList<Quartier> copieMagicienne = new ArrayList<Quartier>(this.getJoueur().getMain());
				for(int j=0; j<nbCartes;j++) {
					System.out.println("Voici les cartes de votre main :");
					for(int i=0;i<copieMagicienne.size();i++) {
						System.out.println((i+1)+ " "+ copieMagicienne.get(i).getNom()+" - type: "+copieMagicienne.get(i).getType()+" - "+" - pièces"+copieMagicienne.get(i).getCout() );
					}
					
					System.out.println("Quel est le numero de la carte que vous voulez retirer ?");
					int choix = Interaction.lireUnEntier(1,copieMagicienne.size()+1);
					this.getPlateau().getPioche().ajouter(copieMagicienne.get(choix-1));
					copieMagicienne.remove(choix-1);
				}
				
				for(int i=0;i<nbCartes;i++) //Ajout des cartes defaussés
					copieMagicienne.add(this.getPlateau().getPioche().piocher());
					
				for(int i=0;i<nbCarteMainMagicienne;i++)
					this.getJoueur().retirerQuartierDansMain();
				
				for(int i=0;i<copieMagicienne.size();i++)
					this.getJoueur().ajouterQuartierDansMain(copieMagicienne.get(i));
			}
				
			
			
		}
	    	
    	
    }

	@Override
	public void utiliserPouvoirAvatar() {
		Random generateur = new Random();
		if(generateur.nextInt(2) == 1){ // Avatar veut echanger ses cartes ?
	    	int choix;
	    	do {
	    		choix = generateur.nextInt(this.getPlateau().getNombreJoueurs()); //choisir avec qui echanger ses cartes
	    		if(this.getPlateau().getJoueur(choix).getPersonnage().getNom().equals(new String("Magicienne"))) { // pas possible d'echanger ses cartes avec soi-meme
	    			choix = -1;
	    		}
	    			
	    	}while(choix == -1);
	    	
	    	ArrayList<Quartier> copieMagicienne = new ArrayList<Quartier>(this.getJoueur().getMain());
	    	ArrayList<Quartier> copiePersonne = new ArrayList<Quartier>(this.getPlateau().getJoueur(choix).getMain());
	    	
	    	while(this.getJoueur().nbQuartiersDansMain()>0) {
	    		this.getJoueur().retirerQuartierDansMain();
	    	}
	    	while(this.getPlateau().getJoueur(choix).nbQuartiersDansMain()>0) {
	    		this.getPlateau().getJoueur(choix).retirerQuartierDansMain();
	    	}
	    	
	    	for(int i=0; i<copieMagicienne.size(); i++) {
	    		this.getPlateau().getJoueur(choix).ajouterQuartierDansMain(copieMagicienne.get(i));
	    	}
	    	for(int i=0; i<copiePersonne.size(); i++) { 
	    		this.getJoueur().ajouterQuartierDansMain(copiePersonne.get(i));
	    	}
	    	  	
		}else if(this.getJoueur().nbQuartiersDansMain()==0){
			
		}else {
			int nbCartes = generateur.nextInt(this.getJoueur().nbQuartiersDansMain()+1); // nombre de cartes a piocher
			int nbCarteMainMagicienne=this.getJoueur().nbQuartiersDansMain();
			if(nbCartes == 0) {
				
			}else if(nbCartes == nbCarteMainMagicienne) {
				
				ArrayList<Quartier> copieMagicienne = new ArrayList<Quartier>(this.getJoueur().getMain());
		    	for(int i=0; i<nbCarteMainMagicienne;i++) {
		    		this.getJoueur().retirerQuartierDansMain();
		    		this.getPlateau().getPioche().ajouter(copieMagicienne.get(i));
		    	}
		    	for(int i=0; i<nbCarteMainMagicienne; i++) {
		    		this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
		    	}		
			}else {
				
				ArrayList<Quartier> copieMagicienne = new ArrayList<Quartier>(this.getJoueur().getMain());
				for(int j=0; j<nbCartes;j++) {
					int choix = generateur.nextInt(copieMagicienne.size()); // Quel indice de carte retirer ?
					this.getPlateau().getPioche().ajouter(copieMagicienne.get(choix));
					copieMagicienne.remove(choix);
				}

				for(int i=0;i<nbCartes;i++) //Ajout des cartes defaussés
					copieMagicienne.add(this.getPlateau().getPioche().piocher());
					
				for(int i=0;i<nbCarteMainMagicienne;i++)
					this.getJoueur().retirerQuartierDansMain();
				
				for(int i=0;i<copieMagicienne.size();i++)
					this.getJoueur().ajouterQuartierDansMain(copieMagicienne.get(i));
				
			}
				
			
			
		}
	} 
    
}