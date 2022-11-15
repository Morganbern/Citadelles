package test;

import application.Configuration;
import modele.Pioche;
import modele.PlateauDeJeu;

public class TestConfiguration {
	
	public static void main(String[] args){
		TestConfiguration test = new TestConfiguration();
		//test.test1();
		test.test2();
		//test.test3();
		//test.test4();
	}	
	
	public void test1() {
		System.out.println("Test Nouvelle Pioche");
		Configuration.nouvellePioche();
		Pioche pioche = Configuration.nouvellePioche();
		int nbCartes = pioche.nombreElements();
		
		System.out.println("Test nombre de cartes");
		System.out.println("Il y a "+nbCartes + " cartes dans la pioche" );
		
		System.out.println("Test valeurs des cartes");
		String CartePioche;
		int eglise=0;
		int forteresse = 0;
		int hotelDeVille = 0;
		int temple = 0;
		int cathedrale = 0;
		int monastere = 0;
		int TourDeGuet = 0;
		int prison = 0;
		int caserne = 0;
		int echoppe = 0;
		int comptoir= 0;
		int port = 0;
		int palais = 0;
		int chateau = 0;
		int marche =0;
		int manoir = 0;
		int taverne= 0;

		
		for (int carte=0; carte<nbCartes; carte++) {
			CartePioche = pioche.piocher().getNom();
			if (CartePioche.equals("église")){
				eglise ++;
			}else if(CartePioche.equals("forteresse")){
				forteresse ++;
			}else if(CartePioche.equals("hôtel de ville")) {
				hotelDeVille ++;
			}else if(CartePioche.equals("temple")) {
				temple ++;
			}else if(CartePioche.equals("cathédrale")) {
				cathedrale ++;
			}else if(CartePioche.equals("monastère")) {
				monastere ++;
			}else if(CartePioche.equals("tour de guet")) {
				TourDeGuet ++;
			}else if(CartePioche.equals("prison")) {
				prison ++;
			}else if(CartePioche.equals("caserne")) {
				caserne ++;
			}else if(CartePioche.equals("échoppe")) {
				echoppe ++;
			}else if(CartePioche.equals("comptoir")) {
				comptoir ++;
			}else if(CartePioche.equals("port")) {
				port ++;
			}else if(CartePioche.equals("palais")) {
				palais ++;
			}else if(CartePioche.equals("château")) {
				chateau ++;
			}else if(CartePioche.equals("marché")) {
				marche ++;
			}else if(CartePioche.equals("manoir")) {
				manoir ++;
			}else if(CartePioche.equals("taverne")) {
				taverne ++;
			}
		
		}
		
		System.out.println("eglise : " + eglise);
		System.out.println("forteresse : " + forteresse);
		System.out.println("hotelDeVille : " + hotelDeVille);
		System.out.println("temple : " + temple);
		System.out.println("cathedrale : " + cathedrale);
		System.out.println("monastere : " + monastere);
		System.out.println("TourDeGuet : " + TourDeGuet);
		System.out.println("prison : " + prison);
		System.out.println("caserne : " + caserne);
		System.out.println("echoppe : " + echoppe);
		System.out.println("comptoir : " + comptoir);
		System.out.println("port : " + port);
		System.out.println("palais : " + palais);
		System.out.println("chateau : " + chateau);
		System.out.println("marche : " + marche);
		System.out.println("manoir : " + manoir);
		System.out.println("taverne : " + taverne);
		
	}
	
	public void test2() {
		
		Pioche pioche = new Pioche();
		PlateauDeJeu plateau = Configuration.configurationDeBase(pioche);
		
		int nbCartes = pioche.nombreElements();
		Test.test(nbCartes==14,"Test du Nb de carte");
		
		for (int Cartes=0; Cartes < nbCartes; Cartes++) {
			System.out.println(pioche.piocher().getNom());
		}
		 
		int nbJoueur = plateau.getNombreJoueurs();
		 Test.test(nbJoueur==4,"Test du Nb de joueur");
			
	}
	
}
