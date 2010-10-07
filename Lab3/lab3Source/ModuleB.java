/*
 * Created on 2005-mar-17
 *
 */
package lab3Source;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.AbstractList;

/**
 * Draws a very nice looking snake.
 * @author Peter Sunnergren
 */
public class ModuleB {
	private int size = 5;
	
	/**
	 * Draws the snake
	 * @param body The body of the snake
	 */
	public void drawSnake(AbstractList body) {
		Rectangle r;
		for (int i = 0; i < body.size(); i++) {
			r = (Rectangle)body.get(i);
			GamePanel.getBuffer().setColor(Color.green);
			if (0 == i) {
				//first
				Rectangle second = (Rectangle)body.get(1);
				if (r.x < second.x) { //<-
					GamePanel.getBuffer().fillOval(r.x - size, r.y, r.width + size - 1, r.height);
					GamePanel.getBuffer().fillRect(r.x + r.width/2 +1, r.y, r.width / 2 + Snake.borderSize, r.height);
					GamePanel.getBuffer().setColor(Color.red);
					GamePanel.getBuffer().fillOval(r.x + r.width/2 - size, r.y + r.height/2 - 2*size, size, 2*size);
					GamePanel.getBuffer().fillOval(r.x + r.width/2 - size, r.y + r.height/2, size, 2*size);
				} else if (r.x > second.x) { //->
					GamePanel.getBuffer().fillOval(r.x, r.y, r.width + size - 1, r.height);
					GamePanel.getBuffer().fillRect(r.x - Snake.borderSize , r.y, r.width / 2 + Snake.borderSize, r.height);
					GamePanel.getBuffer().setColor(Color.red);
					GamePanel.getBuffer().fillOval(r.x + r.width/2, r.y + r.height/2 - 2*size, size, 2*size);
					GamePanel.getBuffer().fillOval(r.x + r.width/2, r.y + r.height/2, size, 2*size);
				} else if (r.y < second.y) { //^
					GamePanel.getBuffer().fillOval(r.x, r.y - size, r.width, r.height + size - 1);
					GamePanel.getBuffer().fillRect(r.x, r.y + r.width/2 + 1, r.width, r.height / 2 + Snake.borderSize);
					GamePanel.getBuffer().setColor(Color.red);
					GamePanel.getBuffer().fillOval(r.x + r.width/2 - 2*size, r.y + r.height/2 - size, 2*size, size);
					GamePanel.getBuffer().fillOval(r.x + r.width/2, r.y + r.height/2 - size, 2*size, size);
				} else if (r.y > second.y) { //v
					GamePanel.getBuffer().fillOval(r.x, r.y, r.width, r.height + size - 1);
					GamePanel.getBuffer().fillRect(r.x, r.y - Snake.borderSize, r.width, r.height / 2 + Snake.borderSize);
					GamePanel.getBuffer().setColor(Color.red);
					GamePanel.getBuffer().fillOval(r.x + r.width/2 - 2*size, r.y + r.height/2, 2*size, size);
					GamePanel.getBuffer().fillOval(r.x + r.width/2, r.y + r.height/2, 2*size, size);				
				}
			} else if ((i+1) == body.size() ) {
				//last
				Rectangle second = (Rectangle)body.get(body.size()-2);
				Polygon p = new Polygon();
				if (r.x < second.x) { //->
					p.addPoint(r.x + r.width + Snake.borderSize, r.y);
					p.addPoint(r.x + r.width + Snake.borderSize, r.y + r.height);
					p.addPoint(r.x, r.y + r.height / 2);
					GamePanel.getBuffer().fillPolygon(p);
				} else if (r.x > second.x) { //<-
					p.addPoint(r.x - Snake.borderSize, r.y);
					p.addPoint(r.x - Snake.borderSize, r.y + r.height);
					p.addPoint(r.x + r.width, r.y + r.height / 2);
					GamePanel.getBuffer().fillPolygon(p);
				} else if (r.y < second.y) { //v
					p.addPoint(r.x, r.y + r.height + Snake.borderSize);
					p.addPoint(r.x + r.width, r.y + r.height + Snake.borderSize);
					p.addPoint(r.x + r.width / 2, r.y);
					GamePanel.getBuffer().fillPolygon(p);
				} else if (r.y > second.y) { //^
					p.addPoint(r.x, r.y - Snake.borderSize);
					p.addPoint(r.x + r.width, r.y - Snake.borderSize);
					p.addPoint(r.x + r.width / 2, r.y + r.height);
					GamePanel.getBuffer().fillPolygon(p);		
				}
			} else {
				Rectangle before = (Rectangle)body.get(i-1);
				Rectangle after = (Rectangle)body.get(i+1);
				if ((before.x < r.x) && (r.x < after.x) ||
						(before.x > r.x) && (r.x > after.x)) { // --> or <--
					GamePanel.getBuffer().fillRect(r.x, r.y, r.width + Snake.borderSize, r.height);
				} else if ((before.y < r.y) && (r.y < after.y) ||
						(before.y > r.y) && (r.y > after.y)) { // ^^ or vv
					GamePanel.getBuffer().fillRect(r.x, r.y, r.width, r.height + Snake.borderSize);
				} else if ((before.y < r.y) && (r.x < after.x)) { // ^-
					GamePanel.getBuffer().fillArc(r.x, r.y - r.height, 2*(r.width + Snake.borderSize), 2*r.height, 180, 90);
				} else if ((before.x > r.x) && (r.y < after.y)) { // ^|>
					GamePanel.getBuffer().fillArc(r.x, r.y, 2*(r.width + Snake.borderSize), 2*(r.height + Snake.borderSize), 90, 90);
				} else if ((before.y > r.y) && (r.x > after.x)) { // -v
					GamePanel.getBuffer().fillArc(r.x - r.width, r.y, 2*r.width, 2*(r.height + Snake.borderSize), 0, 90);
				} else if ((before.x < r.x) && (r.y > after.y)) { // <|v
					GamePanel.getBuffer().fillArc(r.x - r.width , r.y - r.height, 2*r.width, 2*r.height, 270, 90);
				} else if ((before.y > r.y) && (r.x < after.x)) { // v-
					GamePanel.getBuffer().fillArc(r.x, r.y, 2*(r.width + Snake.borderSize), 2*(r.height + Snake.borderSize), 90, 90);
				} else if ((before.x > r.x) && (r.y > after.y)) { // v|>
					GamePanel.getBuffer().fillArc(r.x, r.y - r.height, 2*(r.width + Snake.borderSize), 2*r.height, 180, 90);
				} else if ((before.y < r.y) && (r.x > after.x)) { // -^
					GamePanel.getBuffer().fillArc(r.x - r.width , r.y - r.height, 2*r.width, 2*r.height, 270, 90);
				} else if ((before.x < r.x) && (r.y < after.y)) { // <|^
					GamePanel.getBuffer().fillArc(r.x - r.width, r.y, 2*r.width, 2*(r.height + Snake.borderSize), 0, 90);
				}
				else {
					GamePanel.getBuffer().fillRect(r.x, r.y, r.width, r.height);
				}
			}
		}
	}	
}

