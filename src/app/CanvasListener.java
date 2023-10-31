package app;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Vector;

public class CanvasListener implements IAreaListener {
	private MainCanvas _mainCanvas;
	private Vector<IAreaListener> _listeners;
	public CanvasListener(MainCanvas frame) {
		_mainCanvas = frame;
		_listeners = new Vector<IAreaListener>();
	}
	
	public boolean contains(Point p) {
		return true;
	}
	
	public void addListener(IAreaListener listener) {
		_listeners.add(listener);
	}
	
	public void removeListener(IAreaListener listener) {
		_listeners.remove(listener);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("\"Click\"  x: " + e.getX() + "  y: " + e.getY());
		//_mainCanvas.setPastePoint(e.getPoint());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//_mainCanvas.mouseReleased(null);
		System.out.println("\"Pressed\"  x: " + e.getX() + "  y: " + e.getY());
        _mainCanvas.mousePressed(e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("\"Released\"  x: " + e.getX() + "  y: " + e.getY());
        _mainCanvas.mouseReleased(e.getPoint());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("\"Entered\"  x: " + e.getX() + "  y: " + e.getY());

	}

	@Override
	public void mouseExited(MouseEvent e) {
		//System.out.println("\"Exited\"  x: " + e.getX() + "  y: " + e.getY());

	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("KeyTyped: " + e.getKeyCode());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("KeyPressed: " + e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("KeyReleased: " + e.getKeyCode());
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		//System.out.println("Mouse wheel rotation: " + e.getWheelRotation());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//System.out.println("Mouse dragged");
		//_mainCanvas.mouseReleased(e.getPoint());	
		_mainCanvas.setMousePosition(e.getPoint());
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
        //System.out.println("Mouse moved");		
		_mainCanvas.setMousePosition(e.getPoint());
	}

}
