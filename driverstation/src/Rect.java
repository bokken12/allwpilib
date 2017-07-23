import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * 
 */

/**
 * @author joelmanning
 *
 */
public class Rect extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2764644324624080829L;
	
	private Color color;
	
	public Rect(int x, int y, int width, int height, Color color){
		setBounds(x, y, width, height);
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
	}
}
