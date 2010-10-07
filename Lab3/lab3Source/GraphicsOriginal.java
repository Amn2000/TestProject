/*
 * Created on 2005-mar-15
 *
*/
package lab3Source;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.AbstractList;
import java.util.Iterator;
/**
 * Base graphics to show the snake on the screen.
 * @author Peter Sunnergren
 */
public class GraphicsOriginal extends AbstractGraphics implements Renderer {
	
    /**
     * Puts the snake on the screen
     * @param body The snake body
     */
    public void putBody(AbstractList body) {	
	Rectangle r;
	java.awt.Graphics g = GamePanel.getBuffer();
	g.setColor(snakeColor);
	Iterator iterator = body.iterator();
	while (iterator.hasNext()) {
	    r = (Rectangle)iterator.next();
	    g.fillRect(r.x, r.y, r.height, r.width);
	}
    }
		
    /**
     * Puts the bonus on the screen
     * @param bonuses A list of the bonuses
     */
    public void putBonus(AbstractList bonuses) {
	Rectangle r;
	java.awt.Graphics g = GamePanel.getBuffer();
	Iterator iterator = bonuses.iterator();
	g.setColor(bonusColor);
	while (iterator.hasNext()) {
	    r = (Rectangle)iterator.next();
	    g.fillRect(r.x, r.y, r.height, r.width);
	}
    }
}
