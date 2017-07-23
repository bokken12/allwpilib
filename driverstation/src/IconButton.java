import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

/**
 * 
 */

/**
 * @author joelmanning
 *
 */
public class IconButton extends JComponent implements MouseListener {
	
	private String icon;
	private Font font;
	private Color color, pressColor, iconColor, activeIconColor;
	private boolean active, pressing;

	/**
	 * @param icon
	 * @param font
	 * @param color
	 * @param pressColor
	 * @param iconColor
	 * @param activeIconColor
	 */
	public IconButton(String icon, Font font, Color color, Color pressColor,
			Color iconColor, Color activeIconColor) {
		super();
		this.icon = icon;
		this.font = font;
		this.color = color;
		this.pressColor = pressColor;
		this.iconColor = iconColor;
		this.activeIconColor = activeIconColor;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(pressing ? pressColor : color);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(active ? activeIconColor : iconColor);
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		g.drawString(icon, (getWidth() - metrics.stringWidth(icon))/2, ((getHeight() - metrics.getHeight())/2) + metrics.getAscent());
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		pressing = true;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		pressing = false;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		pressing = false;
	}
	
}
