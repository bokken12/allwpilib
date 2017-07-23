import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author joelmanning
 *
 */
public class DriverStation extends JFrame {
	
	public static final int SPACING = 10;
	public static Font AWESOME;
	public static Font REGULAR;
	public static Font BOLD;
	public static Font MONO;
	
	public static DriverStation DS;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AWESOME = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/FontAwesome.ttf")).deriveFont(16f);
			REGULAR = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Ubuntu-Regular.ttf"));
			BOLD = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Ubuntu-Bold.ttf"));
			MONO = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/UbuntuMono.ttf"));
		} catch(IOException e){
			System.err.println("Could not find font files.");
			System.exit(0);
		} catch(FontFormatException e) {
			System.err.println("Font files improperly formatted.");
			System.exit(0);
		}
		DS = new DriverStation();
	}
	
	public DriverStation(){
		//Left Tab
		JPanel leftTab = new JPanel();
		leftTab.setLayout(new BoxLayout(leftTab, BoxLayout.PAGE_AXIS));
		Dimension leftTabSize = new Dimension(36, 144);
		leftTab.setPreferredSize(leftTabSize);
		leftTab.setMinimumSize(leftTabSize);
		leftTab.setMaximumSize(leftTabSize);
		leftTab.setBackground(Colors.WIDGET_BACKGROUND);
			TextButton leftTabOperator = new TextButton(36, 36, Colors.WIDGET_BACKGROUND_SELECTED, true, "\uf0e4", AWESOME, Colors.WIDGET_FOREGROUND);
			TextButton leftTabNetwork = new TextButton(36, 36, Colors.WIDGET_BACKGROUND_SELECTED, true, "\uf0f1", AWESOME, Colors.WIDGET_FOREGROUND);
			TextButton leftTabSettings = new TextButton(36, 36, Colors.WIDGET_BACKGROUND_SELECTED, true, "\uf0ad", AWESOME, Colors.WIDGET_FOREGROUND);
			TextButton leftTabJoysticks = new TextButton(36, 36, Colors.WIDGET_BACKGROUND_SELECTED, true, "\uf287", AWESOME, Colors.WIDGET_FOREGROUND);
			leftTab.add(leftTabOperator);
			leftTab.add(leftTabNetwork);
			leftTab.add(leftTabSettings);
			leftTab.add(leftTabJoysticks);
		//Left Cards
		RoundedRect leftCard = new RoundedRect(10);
		leftCard.setLayout(new CardLayout());
			//Operator Card
			JPanel leftCardOperator = new JPanel();
			leftCardOperator.setLayout(new BoxLayout(leftCardOperator, BoxLayout.LINE_AXIS));
			JPanel controls = new JPanel();
			Dimension controlsSize = new Dimension(160, 187);
			controls.setPreferredSize(controlsSize);
			controls.setMinimumSize(controlsSize);
			controls.setMaximumSize(controlsSize);
			controls.setLayout(new BoxLayout(controls, BoxLayout.PAGE_AXIS));
		//Main Panel
		JPanel all = new JPanel();
		all.setLayout(new BoxLayout(all, BoxLayout.LINE_AXIS));
		all.setBackground(Colors.WINDOW_BACKGROUND);
		//all.setBounds(0, 0, 800, 200);
		all.add(Box.createRigidArea(new Dimension(SPACING, 0)));
		all.add(leftTab);
		all.add(Box.createRigidArea(new Dimension(SPACING, 0)));
		//Main Window
		setMinimumSize(new Dimension(800, 227));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(all);
		setVisible(true);
	}
	
}
