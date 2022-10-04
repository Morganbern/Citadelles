package test;
import modele.Quartier;

public class TestQuartier {
	public static void main(String[] args){
		TestQuartier testQuartier = new TestQuartier();
		testQuartier.test1();	
		//testQuartier.test2();
		//testQuartier.test3();
		//testQuartier.test4();
		//testQuartier.test5();
		//testQuartier.test6();
		//testQuartier.test7();
	}

	public void test1(){
		System.out.println("TEST DU CONSTRUCTEUR VIDE");
		Quartier quartier = new Quartier();
		Test.test(quartier.getNom().equals(""),"test du nom du quartier");
		Test.test(quartier.getType().equals(""),"test du type du quartier");
		Test.test(quartier.getCout() == 0,"test du cout du quartier");
		Test.test(quartier.getCaracteristiques().equals(""),"test des caracteristiques du quartier");
	}

	public void test2(){
		System.out.println("TEST POUR UN TEMPLE (RELIGIEUX");
		Quartier quartier = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Test.test(quartier.getNom().equals("temple"),"test du nom du quartier");
		Test.test(quartier.getType().equals("RELIGIEUX"),"test du type du quartier");
		Test.test(quartier.getCout() == 1,"test du cout du quartier");
		Test.test(quartier.getCaracteristiques().equals(""),"test des caracteristiques du quartier");
	}

	public void test3(){
		System.out.println("TEST POUR UNE PRISON (MILITAIRE)");
		Quartier quartier = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Test.test(quartier.getNom().equals("prison"),"test du nom du quartier");
		Test.test(quartier.getType().equals("MILITAIRE"),"test du type du quartier");
		Test.test(quartier.getCout() == 2,"test du cout du quartier");
		Test.test(quartier.getCaracteristiques().equals(""),"test des caracteristiques du quartier");
	}
	public void test4(){
		System.out.println("TEST POUR UN PALAIS (NOBLE)");
		Quartier quartier = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		Test.test(quartier.getNom().equals("palais"),"test du nom du quartier");
		Test.test(quartier.getType().equals("NOBLE"),"test du type du quartier");
		Test.test(quartier.getCout() == 5,"test du cout du quartier");
		Test.test(quartier.getCaracteristiques().equals(""),"test des caracteristiques du quartier");
	}
	public void test5(){
		System.out.println("TEST POUR UN MARCHE (COMMERCANT)");
		Quartier quartier = new Quartier("marché",Quartier.TYPE_QUARTIERS[3],2);
		Test.test(quartier.getNom().equals("marché"),"test du nom du quartier");
		Test.test(quartier.getType().equals("COMMERCANT"),"test du type du quartier");
		Test.test(quartier.getCout() == 2,"test du cout du quartier");
		Test.test(quartier.getCaracteristiques().equals(""),"test des caracteristiques du quartier");
	}

	public void test6(){
		String caracteristiques = "Le Donjon ne peut être affecté par les pouvoirs des personnages de rang 8";
		System.out.println("TEST POUR LA MERVEILLE DONJON");
		Quartier quartier = new Quartier("Donjon",Quartier.TYPE_QUARTIERS[4],3,caracteristiques);
		Test.test(quartier.getNom().equals("Donjon"),"test du nom de la merveille");
		Test.test(quartier.getType().equals("MERVEILLE"),"test du type de la merveille");
		Test.test(quartier.getCout() == 3,"test du cout de la merveille");
		Test.test(quartier.getCaracteristiques().equals(caracteristiques),"test des caracteristiques de la merveille");
	}

	public void test7(){
		System.out.println("TEST DES ACCESSEURS EN ECRITURE");
		Quartier quartier = new Quartier();
		quartier.setNom("Basilique");
		Test.test(quartier.getNom().equals("Basilique"),"test du changement de nom du quartier");
		quartier.setCout(7);
		Test.test(quartier.getCout() == 0,"test d'un mauvais changement de cout");
		quartier.setCout(-1);
		Test.test(quartier.getCout() == 0,"test d'un deuxieme mauvais changement de cout");
		quartier.setCout(4);
		Test.test(quartier.getCout() == 4,"test d'un bon changement de cout");
		quartier.setType("merveille");
		Test.test(quartier.getType().equals(""),"test d'un mauvais changement du type");
		quartier.setType("MERVEILLE");
		Test.test(quartier.getType().equals("MERVEILLE"),"test d'un bon changement du type");
		quartier.setCaracteristiques("A la fin de la partie...");
		Test.test(quartier.getCaracteristiques().equals("A la fin de la partie..."),
			"test du changement des caracteristiques");
	}
}
