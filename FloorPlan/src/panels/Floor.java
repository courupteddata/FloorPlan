package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import placeables.Room;

public class Floor extends JPanel{

	private static final long serialVersionUID = 1L;
	
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
				//g2D.draw((Shape) room);
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
				
				for(Room room : rooms)
				{
					//room.unfocusCorners();
					if(room != null)
					{
						room.focusOnCorner(me.getPoint());
					}
				}
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
	
	

}
