import java.awt.*;
import javax.swing.*;

public class DisplayCanvas extends JPanel {

	private Color color; // stores color used to draw fractal
	private int level; // stores current level of fractal
	
	private final int WIDTH = 400; // defines width of JPanel
	private final int HEIGHT = 400; // defines height of JPanel
	
	
	public DisplayCanvas (int currentPoint) {
		setPreferredSize( new Dimension( WIDTH, HEIGHT ) );
	}
}
