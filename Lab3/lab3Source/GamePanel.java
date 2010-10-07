/*
 * Created on 2005-mar-15
 *
 */
package lab3Source;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * A panel that holds the game.
 * @author Peter Sunnergren
 */

public class GamePanel extends JPanel {
	private static GameApplet applet;
	private static Image offscreen;
	private static Graphics buffer;
	private static Dimension dimension;

	/**
	 * Constructs the panel and initializes the double buffering
	 * @param a The parent applet to the panel.
	 */ 
	public GamePanel (GameApplet a) {
		dimension = new Dimension(400, 400);
		setMinimumSize(dimension);
		applet = a;
		offscreen = applet.createImage((int)dimension.getWidth(), (int)dimension.getHeight());
		buffer = offscreen.getGraphics();
	}
	/**
	 * Draws the game on screen, does double buffering to make the game faster.
	 */
	public void paint(Graphics g) {
		applet.getSnake().paint();
		g.drawImage(offscreen, 0, 0, applet);
	}
	
	/**
	 * Makes it possible for the graphics packages to use the offscreen buffer graphics.
	 * @return The offscreen graphics
	 */
	static public Graphics getBuffer() {
		return buffer;
	}
	
	/**
	 * Forces the applet and thereby the panel to update the graphics.
	 */
	static public void rePaint() {
		applet.repaint();
	}
	
	/**
	 * Is used to get the dimension of the panel.
	 * @return Size
	 */
	static public Dimension getDimension() {
		return dimension;
	}
}
