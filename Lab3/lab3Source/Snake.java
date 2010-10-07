/*
 * Created on 2005-mar-15
 *
 */
package lab3Source;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.AbstractList;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is what make the game tick. It updates the position according to the controller.
 * @author Peter Sunnergren
 */

public class Snake implements Runnable {
    /**
     * Use this lock to avoid concurrent modification of the
     * ArrayLists body and bonus.
     */
    public final static ReentrantLock lock = new ReentrantLock();

    /**
     * The size of an element in the snakes body
     */
    public static int boxSize = 25;
    /**
     * The distance between to elements in the snakes body
     */
    public static int borderSize = 2;

    private int speed = 200;
    private boolean outside = false;
    private ArrayList body;
    private ArrayList bonus;
    private Renderer renderer;
    private Controller controller;
    /**
     * Initializes the snake.
     * @param startSize
     * @param startX
     * @param startY
     * @param startDir
     */	
    public Snake(int startSize, int startX, int startY, int startDir) {
	setOriginalGraphics();
	renderer.setSnakeColor(Color.blue);
	body = new ArrayList();
	bonus = new ArrayList();
	setDemoControll();
	controller.setDirection(startDir);
	for (int i = 0; i < startSize; i++) {
	    body.add(new Rectangle(startX + boxSize*i, startY,
				   boxSize - borderSize,
				   boxSize - borderSize));
	}
    }
	
    /**
     * Overloads run() in the Runnable interface.
     * Updates the position of the snake and check whether it is outside or takes a bonus.
     * Also randomizes bonuses randomly in time.
     */
    public void run() {
	while (true) {		
	    Snake.lock.lock();
	    controller.step(body);
	    for (int i = 0; i < body.size(); i++) {
		Rectangle r = (Rectangle)body.get(i);
		if (r.x < 0 || r.y < 0 ||
		    r.x > GamePanel.getDimension().width ||
		    r.y > GamePanel.getDimension().height)
		    outside = true;
		for (int k = 0; k < bonus.size(); k++) {
		    if (r.intersects((Rectangle)bonus.get(k))) {
			bonus.remove(k);
			body.add(new Rectangle());
			continue;
		    }
		}
	    }
	    Snake.lock.unlock();
	    Snake.lock.lock();	    
	    if (0 == (int)Math.round(Math.random()*10)) {
		bonus.add(new Rectangle((int)Math.round(Math.random()*(400 - boxSize)),
					(int)Math.round(Math.random()*(400 - boxSize)),
					boxSize, boxSize));
	    }
	    Snake.lock.unlock();
	    GamePanel.rePaint();
			
	    try { 
		Thread.sleep(speed);
	    } catch( InterruptedException e ) {
		System.out.println("Snake stopped");
	    }
	}
    }

    /**
     * Uses the current graphics to draw the background, snake and bonuses on the screen
     */
    public void paint() {
	renderer.putBackground();
	renderer.putBody(body);
	if (outside) {
	    renderer.outside();
	    outside = false;
	}
	renderer.putBonus(bonus);
    }

    /**
     * Turns the snake to a direction.
     * @param dir Direction
     */
    public void turn(int dir) {
	controller.setDirection(dir);
    }

    /**
     * @return The current bonuses
     */
    public AbstractList getBonus() {
	return bonus;
    }
	
    /**
     * Sets that the snake should be controlled by the keyboard.
     */
    public void setKeyControll() {
	int dir;
	if (controller == null)
	    dir = KeyEvent.VK_LEFT;
	else
	    dir = controller.getDirection();

	// YOUR CODE HERE
	// create the new controller, set the old direction to the new
	// controller
	 controller = new ControllerKey();
	 controller.setDirection(dir);
 
    }
	
    /**
     * Set the snake in demo mode.
     *
     */
    public void setDemoControll() {
	int dir;
	if (controller == null)
	    dir = KeyEvent.VK_LEFT;
	else
	    dir = controller.getDirection();
	controller = new ControllerDemo(this);
	controller.setDirection(dir);
    }
	
    /**
     * Sets that the original graphics are used to draw the snake.
     */
    public void setOriginalGraphics() {
	renderer = new GraphicsOriginal();
	renderer.setSnakeColor(Color.blue);
    }
	
    /**
     * Sets that the "3D" graphics are used.
     * This method should be implemented during the labs
     */
    public void set3dGraphics() {
    	
	// YOUR CODE HERE
	// create the new renderer
     renderer =new Graphics3DAdapter(new Graphics3D());
     renderer.setSnakeColor(Color.green);
	// initialize the colour of the snake
	// otherwise it is null and you'll get a NullPointerException
    }
	
    /**
     * Sets that the package graphics are used.
     * This method should be implemented during the labs
     */
    public void setABCGraphics() {
	// YOUR CODE HERE
	// create the new renderer
    	renderer=new ABCFacade();
        renderer.setSnakeColor(Color.green);
	// initialize the colour of the snake
	// otherwise it is null and you'll get a NullPointerException
    }
	
    /**
     * Sets that the graphics with shadows are used.
     * This method should be implemented during the labs
     */
    public void setShadowGraphics(){
	// YOUR CODE HERE
	// create the new renderer
     renderer= new Decorator(renderer);	
    renderer.setSnakeColor(Color.green);
    	
	// initialize the colour of the snake
	// otherwise it is null and you'll get a NullPointerException
    }
	
}
