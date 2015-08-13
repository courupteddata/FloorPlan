package placeables;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import utils.ResponsiveCircle;

public class Room extends Rectangle2D.Double
{
	private static final long serialVersionUID = 1L;

	//0-topLeft,1-topRight,2-bottomRight,3-bottomLeft
	private ResponsiveCircle[] corners = new ResponsiveCircle[4];
	
	public Room()
	{
		super();
		
	}
	
	public Room(double x, double y, double w, double h)
	{
		super(x,y,w,h);
		
		double tmpDiameter = Math.max(((this.getWidth() + this.getHeight())/2) * .20, 1);
		
		
		for(int i = 0; i < corners.length; i++)
		{
			Point2D.Double tmp = cornerCoord(i);
			corners[i] = new ResponsiveCircle(tmp.x, tmp.y, tmpDiameter);
		}
		
		
		
	}
	
	public void draw(Graphics2D g2D)
	{
		g2D.draw(this);
		
		for(ResponsiveCircle rC : corners)
		{
			if(rC.isFocused())
			{
			g2D.draw(rC);
			}
			else
			{
				
			}
		}
	}
	
	public void moveTo(double x, double y)
	{
		setRect(x,y, this.width, this.height);
		
		for(ResponsiveCircle rC : corners)
		{
			rC.moveTo(x, y);
		}
	}
	
	public void resize(double w, double h)
	{
		setRect(this.x, this.y, w, h);
		
		double tmpDiameter = Math.max(((this.getWidth() + this.getHeight())/2) * .20, 1);
		
		Point2D.Double tmp;
		
		for(int i = 0; i < corners.length; i++)
		{
			tmp = cornerCoord(i);
			
			corners[i].reFrame(tmp.x, tmp.y, tmpDiameter);
		}
		
	}
	public boolean onCorner(Point2D point)
	{
		for(ResponsiveCircle rC : corners)
		{
			if(rC.contains(point))
			{
				return true;
			}
		}
		return false;
	}
	
	public void focusOnCorner(Point2D point)
	{
		for(ResponsiveCircle rC : corners)
		{
			if(rC.contains(point))
			{
				rC.focus();
			}
		}
	}
	
	public void unfocusCorners()
	{
		for(ResponsiveCircle rC : corners)
		{

				rC.unfocus();
			
		}
	}
	
	//0-topLeft,1-topRight,2-bottomRight,3-bottomLeft
	private Point2D.Double cornerCoord(int corner)
	{
		switch(corner)
		{
		case 0:
			return new Point2D.Double(this.getX(), this.getY());
		case 1:
			return new Point2D.Double(this.getX() + this.getWidth(), this.getY());
		case 2:
			return new Point2D.Double(this.getX() + this.getWidth(), this.getY() + this.getHeight());
		case 3:
			return new Point2D.Double(this.getX(), this.getY() + this.getHeight());
			
		}
		
		return new Point2D.Double();
	}

}
