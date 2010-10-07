/*
 * Created on 2005-mar-15
 *
 */
package lab3Source;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Controls the snake depending on the arrow-keys pressed and make the snake move forward according to its speed.
 * @author Peter Sunnergren
 */
public class ControllerKey implements Controller {
	private int direction;
	
	/**
	 * Updates the body of the snake at every time step.
	 */
	public void step(ArrayList body) {
		Rectangle last = (Rectangle)body.get(body.size() - 1);
		Rectangle first = (Rectangle)body.get(0);
		if ((0 == last.height) && (0 == last.width)) {
			last.height = first.height;
			last.width = first.width;
		}
		switch (direction) {			
		case KeyEvent.VK_LEFT :
			last.setLocation(first.x - Snake.boxSize, first.y);
			break;
		case KeyEvent.VK_RIGHT:
			last.setLocation(first.x + Snake.boxSize, first.y);
			break;
		case KeyEvent.VK_UP   :
			last.setLocation(first.x, first.y - Snake.boxSize);
			break;
		case KeyEvent.VK_DOWN :
			last.setLocation(first.x, first.y + Snake.boxSize);
			break;
		}
		
		body.add(0, last);
		body.remove(body.size() - 1);
	}
	/**
	 * Gets the current direction.
	 * @return Direction
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Sets the moving direction of the snake and check if it is a legal direction.
	 * @param dir The direction
	 */
	public void setDirection(int dir) {
		if (!((direction == dir) ||
				(KeyEvent.VK_LEFT == dir) && (KeyEvent.VK_RIGHT == direction) ||
				(KeyEvent.VK_RIGHT == dir) && (KeyEvent.VK_LEFT == direction) ||
				(KeyEvent.VK_UP == dir) && (KeyEvent.VK_DOWN == direction) ||
				(KeyEvent.VK_DOWN == dir) && (KeyEvent.VK_UP == direction))) {
			direction = dir;
		}
	}
}
