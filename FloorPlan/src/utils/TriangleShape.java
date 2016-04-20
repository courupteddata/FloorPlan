package utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class TriangleShape implements Shape{
	
	Line2D.Double lineOne, lineTwo, base;
	double x, y, height, angle;
	
	//Based off of the top point of the triangle, will draw with the base on the right side
	public TriangleShape(double x, double y, double height, double angle)
	{
		this.x = x;
		this.y = y;
		this.height = height;
		this.angle = angle;
		
		double halfAngle = (angle/2.0);
		
		double halfBaseLength = Math.tan(Math.toRadians(halfAngle)) * height;
		
		
		
		lineTwo = new Line2D.Double(x, y, x + height, y - halfBaseLength);
		lineOne = new Line2D.Double(x, y, x + height, y + halfBaseLength);
		base = new Line2D.Double(lineTwo.x2, lineTwo.y2, lineOne.x2, lineOne.y2);
		
		
		
	}
	
	public void draw(Graphics2D g2D)
	{
	    Color tmpColor = g2D.getColor();
	    g2D.setColor(Color.green);
	    g2D.draw(lineTwo);
	    g2D.setColor(Color.MAGENTA);
	    g2D.draw(lineOne);
	    g2D.setColor(Color.PINK);
	    g2D.draw(base);
	    
	    g2D.setColor(tmpColor);
	}

	@Override
	public boolean contains(Point2D arg0)
	{
	    // TODO Auto-generated method stub
	    return false;
	}

	@Override
	public boolean contains(Rectangle2D arg0)
	{
	    // TODO Auto-generated method stub
	    return false;
	}

	@Override
	public boolean contains(double arg0, double arg1)
	{
	    // TODO Auto-generated method stub
	    return false;
	}

	@Override
	public boolean contains(double arg0, double arg1, double arg2,
		double arg3)
	{
	    // TODO Auto-generated method stub
	    return false;
	}

	@Override
	public Rectangle getBounds()
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public Rectangle2D getBounds2D()
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform arg0)
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform arg0, double arg1)
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public boolean intersects(Rectangle2D arg0)
	{
	    // TODO Auto-generated method stub
	    return false;
	}

	@Override
	public boolean intersects(double arg0, double arg1, double arg2,
		double arg3)
	{
	    // TODO Auto-generated method stub
	    return false;
	}

}
