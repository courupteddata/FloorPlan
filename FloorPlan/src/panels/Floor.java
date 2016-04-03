package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import placeables.Room;
import utils.Threads;

public class Floor extends JPanel
{

    private static final long serialVersionUID = 1L;

    //private static Point mousePosition;
    
    Threads utilThread = new Threads();

    ArrayList<Room> rooms;

    public Floor() {
	this.setBackground(Color.WHITE);
	this.rooms = new ArrayList<Room>();
	this.addActions();
    }

    public Floor(ArrayList<Room> rooms) {
	this.setBackground(Color.WHITE);
	this.rooms = rooms;
	this.addActions();
    }

    @Override
    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	Graphics2D g2D = (Graphics2D) g;
	for (Room room : rooms) {
	    if (room != null) {
		room.unfocusCorners();
		Point tmpPoint = this.getMousePosition();
		if (tmpPoint != null) {
		    room.focusOnCorner(tmpPoint);
		    utilThread.setMousePosition(tmpPoint);
		}

		room.draw(g2D);
	    }
	}
    }

    private void addActions()
    {
	addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent me)
	    {
		super.mouseClicked(me);
		System.out.println(me.getPoint().toString() + " Point");
	    }

	    @Override
	    public void mouseMoved(MouseEvent me)
	    {
		super.mouseMoved(me);

	    }

	    @Override
	    public void mousePressed(MouseEvent me)
	    {
		super.mousePressed(me);

		utilThread.setMousePosition(getMousePosition());
		for (Room room : rooms) {
		    if (room.onCorner(me.getPoint())) {
			utilThread.setMouseDown();
			utilThread.startRoomThread(room.getFocusedCornerID(), room);
		    }
		    else if (room.contains(me.getPoint())) {
			utilThread.setMouseDown();
			utilThread.startRoomThread(-1, room);
			
		    }
		}
	    }

	    @Override
	    public void mouseReleased(MouseEvent me)
	    {
		super.mouseReleased(me);

		utilThread.setMouseUp();
	    }

	    @Override
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
	rooms.add(0, room);

    }

    public void removeRoom(Room room)
    {
	rooms.remove(room);
    }
}
