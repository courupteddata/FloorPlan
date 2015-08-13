package main;

public class Main {

	public static void main(String[] args) {
		Frame test = new Frame();
		
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			test.updateGraphicPanel();
		}

	}

}
