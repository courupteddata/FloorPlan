package utils;

import java.awt.geom.Ellipse2D;

public class ResponsiveCircle extends Ellipse2D.Double
{
	private static final long serialVersionUID = 1L;
	
	private double diameter;
	private double radius;
	
	private boolean focused;
	
	public ResponsiveCircle()
	{
		super();
	}
	
	public ResponsiveCircle(double x, double y, double diameter)
	{
		super(x - (diameter/2), y - (diameter/2), diameter, diameter);
		this.diameter = diameter;
		radius = diameter/2.0;
	}
	
	public void moveTo(double x, double y)
	{
		this.setFrame(x - radius, y - radius, diameter, diameter);
	}
	
	public void resize(double diameter)
	{
		this.diameter = diameter;
		this.radius = diameter/2.0;

		this.setFrame(this.x, this.y, diameter, diameter);
	}
	
	public void reFrame(double x, double y, double diameter)
	{
		this.diameter = diameter;
		this.radius = diameter/2.0;
		
		this.setFrame(x - radius, y - radius, diameter, diameter);
	}
	
	
	public double getRadius()
	{
		return radius;
	}
	
	public double getDiameter()
	{
		return diameter;
	}
	
	public boolean isFocused()
	{
		return focused;
	}
	
	public void focus()
	{
		focused = true;
	}
	
	public void unfocus()
	{
		focused = false;
	}
	
	
	

}
