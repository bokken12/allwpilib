import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * 
 */

/**
 * @author joelmanning
 *
 */
public class TextButton extends DashboardButton {
	
	private String text;
	private Font font;
	private Color textColor;
	private int textX, textY;
	private boolean centerTrigger;
	
	public TextButton(int width, int height, Color color, boolean strict, String text, Font font, Color textColor){
		super(width, height, color, strict);
		this.text = text;
		this.font = font;
		this.textColor = textColor;
		centerTrigger = true;
	}
	
	/* (non-Javadoc)
	 * @see DashboardButton#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(centerTrigger){
			FontMetrics metrics = g.getFontMetrics(font);
			textX = (getWidth() - metrics.stringWidth(text))/2;
			textY = ((getHeight() - metrics.getHeight())/2) + metrics.getAscent();
			centerTrigger = false;
		}
		g.setFont(font);
		g.setColor(textColor);
		g.drawString(text, textX, textY);
	}
}
