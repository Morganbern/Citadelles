package modele;
import java.util.ArrayList;

import controleur.Interaction;


public class Magicienne extends Personnage{
    
    
    public Magicienne() {
        super("Magicienne", 3, Caracteristiques.MAGICIENNE);
    }
    
    private int indiceMagicienne() {
    	for(int i=0; i<this.getPlateau().getNombrePersonnages(); i++) {
    		if(this.getPlateau().getPersonnage(i).getNom().equals(new String("Magicienne")))
    			return i;
    	}
    	return -1;
    }
    
    public void utiliserPouvoir() {
    	
    	
		System.out.println("Voulez-vous  ́echanger vos cartes avec celles d’un autre joueur ?(o/n) ");
		boolean reponse = Interaction.lireOuiOuNon();
		if(reponse ==true) {
	    	for(int i=0; i<this.getPlateau().getNombrePersonnages(); i++) {
	    		System.out.println((i+1) + " " + this.getPlateau().getPersonnage(i).getNom() + "- Nb de cartes : " + this.getPlateau().getJoueur(i).nbQuartiersDansMain());
	    	}
	    	int choix;
	    	do {
	    		System.out.println("Votre choix : ");
	    		choix = Interaction.lireUnEntier(1,this.getPlateau().getNombrePersonnages()+1);
	    		if(choix == this.indiceMagicienne()+1) {
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
					for(int i=0;i<nbCarteMainMagicienne;i++) {
						System.out.println((i+1)+ " "+ this.getJoueur().getMain().get(i).getNom()+" - type: "+this.getJoueur().getMain().get(i).getType()+" - "+" - pièces"+this.getJoueur().getMain().get(i).getCout() );
					}
					
					System.out.println("Quel est le numero de la carte que vous voulez retirer ?");
					int choix = Interaction.lireUnEntier(1,this.getJoueur().nbQuartiersDansMain()+1);
					this.getPlateau().getPioche().ajouter(copieMagicienne.get(choix-1));
					copieMagicienne.remove(choix-1);
				}
				for(int i=0;i<nbCartes;i++) {
					copieMagicienne.add(this.getPlateau().getPioche().piocher());
				}
				
				for(int i=0; i<nbCarteMainMagicienne;i++) {
					this.getJoueur().retirerQuartierDansMain();
				}
				
				for(int i=0; i<copieMagicienne.size();i++) {
					this.getJoueur().ajouterQuartierDansMain(copieMagicienne.get(i));
				}
				
			}
				
			
			
		}
	    	
    	
    } 
    
}