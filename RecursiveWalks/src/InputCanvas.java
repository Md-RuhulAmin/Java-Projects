import java.awt.*;
import java.util.*;
import javax.swing.*;

public class InputCanvas extends JPanel implements EventListener{


	private double[] s;
	private double[] t;
	private int x1, x2, y1,y2;

	public void setPoints(Point[] points ) {
		x1 = points[0].x;
		y1 = points[0].y;
		x2 = points[points.length-1].x;
		y2 = points[points.length-1].y;
		s = new double[points.length];
		t = new double[points.length];
		s[0] = t[0] = 0;
		double d = (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1);
		for (int i = 1; i < points.length; i++) {
			s[i] = ( (x2-x1)*(points[i].x-x1) + (y2-y1)*(points[i].y-y1)) / d;
			t[i] = ( (x2-x1)*(points[i].y-y1) - (y2-y1)*(points[i].x-x1)) / d;
		}
		repaint();
	}




	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawLine(200, 0, 200, 0);
	}


	public void setPointCount(int NumberOfPoints) {


	}

	public void install(int[] coordinates) {
		// TODO Auto-generated method stub
		
	}


}
