package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import placeables.Room;
import utils.MouseMoving;

public class Floor extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static Point mousePosition;
	
	ArrayList<Room> rooms;
	
	public Floor()
	{
		this.setBackground(Color.WHITE);
		this.rooms = new ArrayList<Room>();
		this.addActions();
	}
	
	public Floor(ArrayList<Room> rooms)
	{
		this.setBackground(Color.WHITE);
		this.rooms = rooms;
		this.addActions();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		for(Room room: rooms)
		{
			if(room != null)
			{
				room.unfocusCorners();
				Point tmpPoint = this.getMousePosition();
				if(tmpPoint != null)
				{
					room.focusOnCorner(tmpPoint);
					mousePosition = getMousePosition();
				}
				
				room.draw(g2D);
			}
		}
	}
	
	private void addActions()
	{
		addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent me)
			{		
			super.mouseClicked(me);
			
			for(Room room : rooms)
			{
				if(room.contains(me.getPoint()))
				{
					room.resize(room.width * 1.2, room.height*1.2);
					updatePanel();
					System.out.println(room.getCenterX() + " " + me.getPoint().toString());
				}
				if(room.onCorner(me.getPoint()))
				{
					System.out.println(room.getCenterX() + " " + me.getPoint().toString() + " Corner");
				}
			}
			}
			
			public void mouseMoved(MouseEvent me)
			{
				super.mouseMoved(me);
				
			}
			
			public void mousePressed(MouseEvent me)
			{
				super.mousePressed(me);
				
				for(Room room : rooms)
				{
					if(room.onCorner(me.getPoint()))
					{
						setMouseDown();
						startThread(room.getFocusedCornerID(), room);
						mousePosition = getMousePosition();
					}
				}
			}
			
			public void mouseReleased(MouseEvent me)
			{
				super.mouseReleased(me);
				
				setMouseUp();
			}
			
			public void mouseDragged(MouseEvent me)
			{
				super.mouseDragged(me);
							
			}
			
		});
	}
	
	public void updatePanel()
	{
		this.repaint();
	}
	
	public void addRoom(Room room)
	{
		rooms.add(room);
	}
	
	public void removeRoom(Room room)
	{
		rooms.remove(room);
	}
	
	//Start thread helper methods an starters
	//--------------------------------------------------
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
	
	private static void startThread(int focusedCorner, Room room)
	{
		System.out.println("test");
		if(safetyCheck())
		{
			isRunning = true;
			
			new Thread()
			{
				public void run()
				{
					while(mouseDown)
					{
						System.out.println(focusedCorner + " " + mousePosition.toString());
						room.resize(room.getWidth() + mousePosition.x , room.getHeight() + mousePosition.y);
						
						
					}
					isRunning = false;
				}
							
			}.start();
		}
	}
	
	private static synchronized void setMouseDown()
	{
		mouseDown = true;
	}
	
	private static synchronized void setMouseUp()
	{
		mouseDown = false;
	}
	
	//End thread helper methods an starters
	//--------------------------------------------------
	
	

}
