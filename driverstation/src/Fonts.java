import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

/**
 * 
 */

/**
 * @author joelmanning
 *
 */
public class Fonts {
	private static Font regular;
	private static Font bold;
	private static Font mono;
	
	static {
		try {
			regular = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Ubuntu-Regular.ttf"));
			bold = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Ubuntu-Bold.ttf"));
			mono = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/UbuntuMono.ttf"));
		} catch(FontFormatException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static Font regular(float size){
		return regular.deriveFont(size);
	}
	public static Font bold(float size){
		return bold.deriveFont(size);
	}
	public static Font mono(float size){
		return mono.deriveFont(size);
	}
}
