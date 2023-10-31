package app;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public class ImageObject {
    private Image _image;
    private Point _start;
    private Point _end;
    public ImageObject(Image i, Point s, Point e) {
    	_image = i;
    	_start = s;
    	_end = e;
    }
    public Point getStart() {
    	return _start;
    }
    public Point getEnd() {
    	return _end;
    }
	
	public boolean contains(Point p) {
		if (p.x > getStart().x 
		    && p.x < getEnd().x
		    && p.y > getStart().y
		    && p.y < getEnd().y) {
			return true;
		}
	    return false;	
	}
    
    public void move(Point p) {
    	int endX = p.x + _end.x - _start.x;
    	int endY = p.y + _end.y - _start.y;
    	_start = p;
    	_end = new Point(endX, endY);
    }
    
    public void draw(Graphics g) {
    	g.drawImage(_image, _start.x, _start.y, _end.x - _start.x, _end.y - _start.y, null);
    }
}
