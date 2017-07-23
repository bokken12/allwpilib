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
public class RoundedRect extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1285888705615844382L;
	
	private int arcDiameter;
	
	public RoundedRect(int arcDiameter){
		this.arcDiameter = arcDiameter;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(getBackground());
		g.fillRoundRect(0, 0, getWidth(), getHeight(), arcDiameter, arcDiameter);
	}
}
