package utils;

import java.awt.Point;
import java.awt.geom.Point2D;

import main.Main;
import placeables.Room;

public class Threads
{
    
    private volatile boolean mouseDown = false;
    private volatile boolean isRunning = false;
    private Point mousePosition;
    
    
    public Threads()
    {
	
    }
    
    
    public synchronized boolean safetyCheck()
    {
	if (isRunning) {
	    return false;
	}
	else {
	    return true;
	}
    }
    
    public void setMousePosition(Point point)
    {
	this.mousePosition = point;
    }
    

    
    public void startRoomThread(int focusedCorner, Room room)
    {
	System.out.println("test");
	if (safetyCheck()) {
	    isRunning = true;

	    new Thread() {
		Point2D tmpPoint;
		double xChange;
		double yChange;

		@Override
		public void run()
		{
		    xChange = mousePosition.x - room.getX();
		    yChange = mousePosition.y - room.getY();
		    
		    while (mouseDown) {
			

			if (Main.isReady()) {
			    if (focusedCorner != -1) {
				tmpPoint = room.cornerCoord(focusedCorner);

				switch (focusedCorner) {
				case 0:
				    room.moveTo(mousePosition.x,
					    mousePosition.y);
				    room.addSizeOffSet(
					    -(mousePosition.x - tmpPoint.getX()),
					    -(mousePosition.y - tmpPoint.getY()));
				    break;
				case 1:
				    room.moveTo(room.getX(), mousePosition.y);
				    room.addSizeOffSet(
					    (mousePosition.x - tmpPoint.getX()),
					    -(mousePosition.y - tmpPoint.getY()));
				    break;
				case 2:
				    room.addSizeOffSet(
					    (mousePosition.x - tmpPoint.getX()),
					    (mousePosition.y - tmpPoint.getY()));
				    break;
				case 3:
				    room.moveTo(mousePosition.x, room.getY());
				    room.addSizeOffSet(
					    -(mousePosition.x - tmpPoint.getX()),
					    (mousePosition.y - tmpPoint.getY()));
				    break;

				}
			    }
			    else {

				room.moveTo(mousePosition.x - xChange,
					mousePosition.y - yChange);
			    }
			}

		    }
		    isRunning = false;
		}

	    }.start();
	}
    }

    public synchronized void setMouseDown()
    {
	mouseDown = true;
    }

    public synchronized void setMouseUp()
    {
	mouseDown = false;
    }


}
