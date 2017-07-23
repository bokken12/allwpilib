import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * 
 */

/**
 * @author joelmanning
 *
 */
public class DashboardButton extends JComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6001745025891801442L;

	private boolean state;
	private Color color;

	public DashboardButton(int width, int height, Color color, boolean strict){
		Dimension size = new Dimension(width, height);
	    setPreferredSize(size);
	    if(strict){
	    	setMinimumSize(size);
	    	setMaximumSize(size);
	    }
		this.color = color;
		state = false;
	}
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		if(state){
			g.fillRect(0, 0, getWidth(), getHeight());
		} else {
			g.drawRect(0, 0, getWidth(), getHeight());
		}
	}
}
