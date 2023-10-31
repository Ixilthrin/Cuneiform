package app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainCanvas extends JPanel {
	private Scene _scene;
	private Point _start = null;
	private Point _end = null;
	private BufferedImage _selectedImage;
	private BufferedImage _backBuffer;
	private BufferedImage _initialBackBuffer;
	private ImageObject _selectedFreeImage;
	private Point _pastePoint;
	private int _pasteWidth;
	private int _pasteHeight;
	private Point _mousePosition;
    public MainCanvas(Scene scene) {
    	super();
        setScene(scene);
    	var input = new CanvasListener(this);
    	addMouseListener(input);
    	addMouseMotionListener(input);
        addKeyListener(input);
        addMouseWheelListener(input);
        
        setOpaque(false);
    }
    
    public void setScene(Scene scene) {
        _scene = scene;	
    }
    
    public void mousePressed(Point p) {
    	_end = null;
    	if (_selectedFreeImage != null || _selectedImage != null) {
    		setPastePoint(p);
    		return;
    	}
    	var selectedFreeImage = _scene.findImageAtPoint(p);
    	if (selectedFreeImage != null) {
    		_selectedFreeImage = selectedFreeImage;
    		_scene.removeFreeImage(_selectedFreeImage);
    	} else {
        	_start = p;
    	}
    	repaint();
    }
    
    public void mouseReleased(Point p) {
    	if (_selectedFreeImage != null) {
    		return;
    	}
    	
    	if (_selectedImage != null) {
    		setPastePoint(p);
    		return;
    	}
    	
    	_end = p;
    	if (_backBuffer != null && _start != null && _end != null) {
        	if (_end.x - _start.x > 0 && _end.y - _start.y > 0) {
            	_pasteWidth = _end.x - _start.x;
            	_pasteHeight = _end.y - _start.y;
    		    _selectedImage = _backBuffer.getSubimage(_start.x, _start.y, _pasteWidth, _pasteHeight);
        	}
    	}
    	repaint();
    	_start = null;
    	_end = null;
    }
    
    public void setPastePoint(Point p) {
    	if (_selectedImage != null || _selectedFreeImage != null)
    	    _pastePoint = p;
    }
    
    public void setMousePosition(Point p) {
    	_mousePosition = p;
    	repaint();
    }
    
    public void paintComponent(Graphics mainGraphics) {
    	super.paintComponent(mainGraphics);
        _backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
    	var backBufferGraphics = _backBuffer.getGraphics();
		if (_selectedImage != null && _pastePoint != null && _mousePosition != null) {
			var freeImage = new ImageObject(_selectedImage, new Point(_mousePosition.x, _mousePosition.y),
					new Point(_mousePosition.x + _pasteWidth, _mousePosition.y + _pasteHeight));
			_scene.addFreeImage(freeImage);
			_selectedImage = null;
			_pastePoint = null;

			try {
				var image = _backBuffer.getSubimage(0, 300, 400, 260);
				var outputfile = new File("c:/images/output.png");
				ImageIO.write(image, "png", outputfile);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
    	}
		if (_selectedFreeImage != null) {
		    _selectedFreeImage.move(new Point(_mousePosition.x, _mousePosition.y));
			if (_pastePoint != null) { 
			    _scene.addFreeImage(_selectedFreeImage);
			    _selectedFreeImage = null;
		    }  
		}
    	_pastePoint = null;
		_scene.draw(backBufferGraphics);
    	mainGraphics.drawImage(_backBuffer, 0,  0,  800,  600,  null);
    	if (_selectedImage != null) {
    		mainGraphics.drawImage(_selectedImage, _mousePosition.x, _mousePosition.y, _pasteWidth, _pasteHeight, null);
    	}
    	if (_selectedFreeImage != null) {
    		_selectedFreeImage.draw(mainGraphics);
    	}
    	drawSelectionBox(mainGraphics);
    	if (_mousePosition != null) {
    		var rgb = _backBuffer.getRGB(_mousePosition.x, _mousePosition.y); //always returns TYPE_INT_ARGB
        	var alpha = String.valueOf((rgb >> 24) & 0xFF);
        	var red =   String.valueOf((rgb >> 16) & 0xFF);
        	var green = String.valueOf((rgb >>  8) & 0xFF);
        	var blue =  String.valueOf(rgb & 0xFF);
    		var colorMessage = "alpha: " + alpha + ", red = " + red + ", green = " + green + ", blue = " + blue;
    		mainGraphics.drawString(colorMessage, 500, 540);
    	}
    }
    
    private void drawSelectionBox(Graphics g) {
    	if (_start != null && _end == null && _selectedImage == null && _selectedFreeImage == null) {
        	var previousColor = g.getColor();
        	g.setColor(Color.LIGHT_GRAY);
    		g.drawRect(_start.x, _start.y, _mousePosition.x - _start.x, _mousePosition.y - _start.y);
        	g.setColor(previousColor);
    	}
    }
}
