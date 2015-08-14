package main;

public class Main
{

    // Start some bad code that is for testing------------
    public static volatile boolean ready = false;

    public static synchronized boolean isReady()
    {
	return ready;
    }

    // End some bad code that is for testing--------------

    public static void main(String[] args)
    {
	Frame test = new Frame();

	while (true) {
	    ready = false;
	    try {
		Thread.sleep(50);
	    }
	    catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    ready = true;
	    test.updateGraphicPanel();

	}

    }

}
