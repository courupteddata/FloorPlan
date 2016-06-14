package utils;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


//TODO: rename method variables to easier to understand names
public class TriangleShape implements Shape{
		
	int x, y, height;
	double angle;
	
	private Polygon triangle;
	
	//Based off of the top point of the triangle, will draw with the base on the right side
	public TriangleShape(int x, int y, int height, double angle)
	{
		this.x = x;
		this.y = y;
		this.height = height;
		this.angle = angle;
		
		double halfAngle = (angle/2.0);
		
		int  halfBaseLength = (int) (Math.tan(Math.toRadians(halfAngle)) * height);
		
		triangle = new Polygon(new int[] {x, x + height, x + height},new int[] {y, y - halfBaseLength, y + halfBaseLength}, 3);
	
	}
	
	public void draw(Graphics2D g2D)
	{
	    //Color tmpColor = g2D.getColor();
	    //g2D.setColor(Color.ORANGE);
	    
	    g2D.draw(triangle);
	    
	    //g2D.setColor(tmpColor);
	}

	@Override
	public boolean contains(Point2D arg0)
	{
	    return triangle.contains(arg0);
	}

	@Override
	public boolean contains(Rectangle2D arg0)
	{
	    return triangle.contains(arg0);
	}

	@Override
	public boolean contains(double arg0, double arg1)
	{
	    return triangle.contains(arg0, arg1);
	}

	@Override
	public boolean contains(double arg0, double arg1, double arg2,
		double arg3)
	{
	    return triangle.contains(arg0, arg1, arg2, arg3);
	}

	@Override
	public Rectangle getBounds()
	{
	    return triangle.getBounds();
	}

	@Override
	public Rectangle2D getBounds2D()
	{
	    return triangle.getBounds2D();
	}

	@Override
	public PathIterator getPathIterator(AffineTransform arg0)
	{
	    return triangle.getPathIterator(arg0);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform arg0, double arg1)
	{
	    return triangle.getPathIterator(arg0, arg1);
	}

	@Override
	public boolean intersects(Rectangle2D arg0)
	{
	    return triangle.intersects(arg0);
	}

	@Override
	public boolean intersects(double arg0, double arg1, double arg2,
		double arg3)
	{
	    return triangle.intersects(arg0, arg1, arg2, arg3);
	}

}
