package test;

import application.Application;

public class TestApplication {

	public static void main(String[] args) {
		TestApplication test= new TestApplication();
		test.test1();
	}
	
	public void test1(){
		Application app = new Application();
		app.main();
	}
}
