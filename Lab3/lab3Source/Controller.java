/*
 * Created on 2005-mar-15
 */
package lab3Source;
import java.util.ArrayList;

/**
 * An interface to object that can control the game.
 * @author Peter Sunnergren
 */


public interface Controller {
    /**
     * Updates the body of the snake at a time step
     * @param body The body of the snake
     */
    public void step(ArrayList body); 
		
    /**
     * Gets the current direction.
     * @return Direction
     */
    public int getDirection();
	
    /**
     * Sets the moving direction of the snake.
     * @param dir
     */
    public void setDirection(int dir);
}
