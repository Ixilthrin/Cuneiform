package app;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
    public MainFrame(String title, Scene scene) {
    	super(title);
    	add(new MainCanvas(scene));
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    }
}
