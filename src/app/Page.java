package app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Vector;

public class Page {
	private Point _start;
	private Point _end;
	private Vector<ImageObject> _images;
	private Color _background = Color.WHITE;
    public Page(Point start, Point end) {
    	_start = start;
    	_end = end;
    	_images = new Vector<ImageObject>();
    }
    
    public void addImage(ImageObject i) {
    	_images.add(i);
    }
    
    public void setBackground(Color c) {
    	_background = c;
    }
    
    public void draw(Graphics g) {
    	var previousColor = g.getColor();
    	if (_background != null) {
        	g.setColor(_background);
            //g.fillRect(_start.x, _start.y, _end.x - _start.x, _end.y - _start.y);
    	}
    	g.setColor(Color.BLACK);
        g.drawRect(_start.x, _start.y, _end.x - _start.x, _end.y - _start.y);
        g.setColor(previousColor);
    	for (var image : _images) {
    		image.draw(g);
    	}
    }
}
