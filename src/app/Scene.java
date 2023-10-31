package app;
import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Scene {
	private Page _sourcePage;
	private Page _targetPage;
	private LinkedList<ImageObject> _freeImages = new LinkedList<ImageObject>();
	
	public Scene() {
		_sourcePage = new Page(new Point(0, 0), new Point(400, 260));
		var image = ImageReader.ReadImageFromFile("c:/images/first.png");
		//var image = ImageReader.ReadImageFromFile("c:/images/output_modified.png");
		_sourcePage.addImage(new ImageObject(image, new Point(0, 0), new Point(400, 260)));
		
		var target = new BufferedImage(400, 260, BufferedImage.TYPE_INT_ARGB);
		var targetGraphics = target.getGraphics();
		targetGraphics.drawLine(0,  0,  200,  200);
		
		_targetPage = new Page(new Point(0, 300), new Point(400, 560));
		_targetPage.setBackground(null);
		_targetPage.addImage(new ImageObject(target, new Point(0, 300), new Point(400, 560)));
	}
	
    public void draw(Graphics g) {
    	_sourcePage.draw(g);
    	_targetPage.draw(g);
    	
    	var it = _freeImages.iterator();
    	while (it.hasNext()) {
    		it.next().draw(g);
    	}
    }
    
    public void addFreeImage(ImageObject image) {
    	_freeImages.addLast(image);
    }
    
    public ImageObject findImageAtPoint(Point p) {
    	var it = _freeImages.descendingIterator();
    	while (it.hasNext()) {
    		var image = it.next();
    		if (image.contains(p))
    			return image;
    	}
    	return null;
    }
    
    public void removeFreeImage(ImageObject image) {
    	_freeImages.remove(image);
    }
}
