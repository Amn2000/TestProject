/*
 * Created on 2005-mar-16
 *
 */
package lab3Source;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Controls the snake running in a demo mode.
 * @author Peter Sunnergren
 */

public class ControllerDemo implements Controller {
    private int direction;

    private Snake snake;
    public ControllerDemo (Snake s) {
	 snake = s;
    }
	
    /**
     * Before updating the body this method check if the snake are going outside the frame, if it can take any bonus and if it is going to collide with itself and change the direction accordingly.
     */
    public void step(ArrayList body) {
	int oldDir = direction;
	Rectangle last = (Rectangle)body.get(body.size() - 1);
	Rectangle first = (Rectangle)body.get(0);
		
	if ((0 == last.height) && (0 == last.width)) {
	    last.height = first.height;
	    last.width = first.width;
	}
		
	//make the snake avoid going outside the frame
	switch (direction) {			
	case KeyEvent.VK_LEFT:
	    if ((first.x - Snake.boxSize) <= 0) {
		direction = KeyEvent.VK_UP;
		oldDir = KeyEvent.VK_DOWN;
	    }
	    break;
	case KeyEvent.VK_RIGHT:
	    if ((first.x + 2*Snake.boxSize) >= GamePanel.getDimension().width)
		direction = KeyEvent.VK_DOWN;
	    oldDir =KeyEvent.VK_UP;
	    break;
	case KeyEvent.VK_UP:
	    if ((first.y - Snake.boxSize) <= 0) {
		direction = KeyEvent.VK_RIGHT;
		oldDir = KeyEvent.VK_LEFT; 
	    }
	    break;
	case KeyEvent.VK_DOWN :
	    if ((first.y + 2*Snake.boxSize) >= GamePanel.getDimension().height) {
		direction = KeyEvent.VK_LEFT;
		oldDir = KeyEvent.VK_RIGHT;
	    }
	    break;
	}
		
	//Make the snake turn to take bonuses
	Rectangle left = new Rectangle(0, first.y, first.x, Snake.boxSize);
	Rectangle right = new Rectangle(first.x + Snake.boxSize, first.y, GamePanel.getDimension().width, Snake.boxSize);
	Rectangle up = new Rectangle(first.x, 0, Snake.boxSize, first.y);
	Rectangle down = new Rectangle(first.x, first.y + Snake.boxSize, Snake.boxSize, GamePanel.getDimension().height);

	for (int k = 0; k < snake.getBonus().size(); k++) {
	    if (left.intersects((Rectangle)snake.getBonus().get(k))) {
		if ((direction == KeyEvent.VK_UP) || (direction ==  KeyEvent.VK_DOWN)) {
		    direction = KeyEvent.VK_LEFT;
		    continue;
		} 
	    }
	    if (right.intersects((Rectangle)snake.getBonus().get(k))) {
		if ((direction == KeyEvent.VK_UP) || (direction ==  KeyEvent.VK_DOWN)) {
		    direction = KeyEvent.VK_RIGHT;
		    continue;
		} 
	    }
	    if (up.intersects((Rectangle)snake.getBonus().get(k))) {
		if ((direction == KeyEvent.VK_LEFT) || (direction ==  KeyEvent.VK_RIGHT)) {
		    direction = KeyEvent.VK_UP;
		    continue;
		} 
	    }
	    if (down.intersects((Rectangle)snake.getBonus().get(k))) {
		if ((direction == KeyEvent.VK_LEFT) || (direction ==  KeyEvent.VK_RIGHT)) {
		    direction = KeyEvent.VK_DOWN;
		    continue;
		} 
	    }
	}
		
	//Makes the snake not collide with itself
	Rectangle newFirst = new Rectangle(0, 0, Snake.boxSize, Snake.boxSize);
	switch (direction) {			
	case KeyEvent.VK_LEFT:
	    newFirst.setLocation(first.x - Snake.boxSize, first.y);
	    break;
	case KeyEvent.VK_RIGHT:
	    newFirst.setLocation(first.x + Snake.boxSize, first.y);
	    break;
	case KeyEvent.VK_UP:
	    newFirst.setLocation(first.x, first.y - Snake.boxSize);
	    break;
	case KeyEvent.VK_DOWN:
	    newFirst.setLocation(first.x, first.y + Snake.boxSize);
	    break;
	}
	for (int i = 0; i < body.size(); i++) {
	    if (newFirst.intersects((Rectangle)body.get(i))) {
		direction = oldDir;
	    }	
	}
		
	switch (direction) {			
	case KeyEvent.VK_LEFT:
	    last.setLocation(first.x - Snake.boxSize, first.y);
	    break;
	case KeyEvent.VK_RIGHT:
	    last.setLocation(first.x + Snake.boxSize, first.y);
	    break;
	case KeyEvent.VK_UP:
	    last.setLocation(first.x, first.y - Snake.boxSize);
	    break;
	case KeyEvent.VK_DOWN:
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
     * Sets the moving direction of the snake.
     * @param dir
     */
    public void setDirection(int dir) {
	direction = dir;
    }	
}

