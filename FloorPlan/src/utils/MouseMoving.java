package utils;

public class MouseMoving 
{
	private static volatile boolean mouseDown = false;
	private static volatile boolean isRunning = false;
	
	private static synchronized boolean safetyCheck()
	{
		if(isRunning)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static void startThread()
	{
		if(safetyCheck())
		{
			isRunning = true;
			
			new Thread()
			{
				public void run()
				{
					while(mouseDown)
					{
						System.out.println("test");
					}
				}
							
			}.start();
		}
	}
	
	public static void setMouseDown()
	{
		mouseDown = true;
	}
	
	public static void setMouseUp()
	{
		mouseDown = false;
	}

}
