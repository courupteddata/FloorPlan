package placeables;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import utils.ResponsiveCircle;

public class Room extends Rectangle2D.Double
{
    private static final long serialVersionUID = 1L;

    // 0-topLeft,1-topRight,2-bottomRight,3-bottomLeft
    private ResponsiveCircle[] corners = new ResponsiveCircle[4];

    public Room() {
	super();

    }

    public Room(double x, double y, double w, double h) {
	super(x, y, w, h);

	double tmpDiameter = Math.max(
		((this.getWidth() + this.getHeight()) / 2) * .20, 1);

	for (int i = 0; i < corners.length; i++) {
	    Point2D.Double tmp = cornerCoord(i);
	    corners[i] = new ResponsiveCircle(tmp.x, tmp.y, tmpDiameter);
	}

    }

    public void draw(Graphics2D g2D)
    {
	g2D.draw(this);

	for (ResponsiveCircle rC : corners) {
	    if (rC.isFocused()) {
		g2D.draw(rC);
	    }
	}
    }

    public void moveTo(double x, double y)
    {
	setRect(x, y, this.width, this.height);

	Point2D.Double tmp;

	for (int i = 0; i < corners.length; i++) {
	    tmp = cornerCoord(i);

	    corners[i].moveTo(tmp.x, tmp.y);
	}
    }

    public void resize(double w, double h)
    {
	setRect(this.x, this.y, w, h);

	double tmpDiameter = Math.max(
		((this.getWidth() + this.getHeight()) / 2) * .20, 1);

	Point2D.Double tmp;

	for (int i = 0; i < corners.length; i++) {
	    tmp = cornerCoord(i);

	    corners[i].reFrame(tmp.x, tmp.y, tmpDiameter);
	}

    }

    public void addSizeOffSet(double w, double h)
    {
	setRect(this.x, this.y, this.width + w, this.height + h);

	double tmpDiameter = Math.max(
		((this.getWidth() + this.getHeight()) / 2) * .10, 1);

	Point2D.Double tmp;

	for (int i = 0; i < corners.length; i++) {
	    tmp = cornerCoord(i);

	    corners[i].reFrame(tmp.x, tmp.y, tmpDiameter);
	}
    }

    public boolean onCorner(Point2D point)
    {
	for (ResponsiveCircle rC : corners) {
	    if (rC.contains(point)) {
		return true;
	    }
	}
	return false;
    }

    public void focusOnCorner(Point2D point)
    {
	for (ResponsiveCircle rC : corners) {
	    if (rC.contains(point)) {
		rC.focus();
	    }
	}
    }

    public Point2D.Double getFocusedCornerCoord()
    {
	for (ResponsiveCircle rC : corners) {
	    if (rC.isFocused()) {
		return new Point2D.Double(rC.getCenterX(), rC.getCenterY());
	    }
	}
	return new Point2D.Double(0, 0);
    }

    public int getFocusedCornerID()
    {
	for (int i = 0; i < corners.length; i++) {
	    if (corners[i].isFocused()) {
		return i;
	    }
	}
	return -1;
    }

    public void unfocusCorners()
    {
	for (ResponsiveCircle rC : corners) {

	    rC.unfocus();

	}
    }

    // 0-topLeft,1-topRight,2-bottomRight,3-bottomLeft
    public Point2D.Double cornerCoord(int corner)
    {
	switch (corner) {
	case 0:
	    return new Point2D.Double(this.getX(), this.getY());
	case 1:
	    return new Point2D.Double(this.getX() + this.getWidth(),
		    this.getY());
	case 2:
	    return new Point2D.Double(this.getX() + this.getWidth(),
		    this.getY() + this.getHeight());
	case 3:
	    return new Point2D.Double(this.getX(), this.getY()
		    + this.getHeight());

	}

	return new Point2D.Double();
    }

}
