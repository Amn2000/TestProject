/*
 * Created on 2005-mar-16
 *
 */
package lab3Source;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Font;

/**
 * A graphics to the game with some "3D" feel to it.
 * @author Peter Sunnergren
 */
public class Graphics3D {
    /**
     * Fills the base of the game in a gradient paint from white of base color.
     * @param c Base color
     */
    public void printBase(Color c) {
	Graphics2D g = (Graphics2D)GamePanel.getBuffer();
	Dimension d = GamePanel.getDimension();
	Rectangle2D e = new Rectangle2D.Double(0, 0, d.height, d.width);
	GradientPaint gp = new GradientPaint(0, 0, Color.white, d.height,
					     d.width, c, false);
	g.setPaint(gp);
	g.fill(e);
    }
	
    /**
     * Draws a circle on the screen. Used to make the body of the snake.
     * @param b The bounding box of the circle.
     * @param c The color that is faded towards white.
     */
    public void printBox(Box b, Color c) {
	Graphics2D g2 = (Graphics2D)GamePanel.getBuffer();
	Ellipse2D e = new Ellipse2D.Double(b.x1, b.y1, b.x2 - b.x1,
					   b.y2 - b.y1);
	GradientPaint gp = new GradientPaint(b.x1, b.y1, Color.white,
					     b.x2, b.y2, c, false);
	g2.setPaint(gp);
	g2.fill(e);
    }
	
    /**
     * Draws a bonus rectangle on the screen.
     * @param b Bounding box
     * @param c The color of the rectangle.
     */
    public void printBonus(Box b, Color c) {
	Graphics2D g2 = (Graphics2D)GamePanel.getBuffer();
	Rectangle2D e = new Rectangle2D.Double(b.x1, b.y1, b.x2 - b.x1,
					       b.y2 - b.y1);
	GradientPaint gp = new GradientPaint(b.x1, b.y1, c,
					     b.x2, b.y2, Color.white, false);
	g2.setPaint(gp);
	g2.fill(e);
    }
}
