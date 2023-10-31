package app;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class Run 
{
	public static void main(String[] args)
	{
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showWindow();
            }
        });
			
	}
    
    private static void showWindow() {
        System.out.println("Window on event dispatch thread: "+
                SwingUtilities.isEventDispatchThread());
        var f = new MainFrame("Cuneiform 1.0", new Scene());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);
        f.setVisible(true);
    }
}