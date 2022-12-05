package modele;

import java.util.ArrayList;
import java.util.Random;

public class Pioche {
	private ArrayList<Quartier> liste;
	
	public Pioche() {
		super();
		this.liste = new ArrayList<Quartier>();
	}

	public Quartier piocher(){
		if(!liste.isEmpty()) {
			Quartier q = liste.get(0);
			liste.remove(0);
			return q;
		}
		return null;
	}
	
	public void ajouter(Quartier nouveau) {
		liste.add(nouveau);
	}
	
	public int nombreElements() {
		return liste.size();
	}
	
	public void melanger() {
		Random generateur = new Random();
		int i,j,k;
		Quartier temp;
		for(i=0; i<this.nombreElements();i++) {
			j = generateur.nextInt(this.nombreElements());
			k = generateur.nextInt(this.nombreElements());
			temp = liste.get(j);
			liste.set(j, liste.get(k));
			liste.set(k, temp);
		}
	}
}
