/*
 * Created on 2005-mar-15
 *
 */
package lab3Source;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JApplet;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * The main class in the game,initializes the snake and the game panel and starts the game.
 * @author Peter Sunnergren
 */

public class GameApplet extends JApplet implements ActionListener, KeyListener {
    private Snake snake;	
	
    /**
     * Sets up and starts the game.
     */
    public void init() {
	this.setSize(450, 450);
		
	makeMenu();
	add(new GamePanel(this));
		
	snake = new Snake(6, 200, 200, KeyEvent.VK_LEFT);
	new Thread(snake).start();
		
	addKeyListener(this);		
	setFocusable(true);
	requestFocus();
    }
	
    /**
     * Creates the menu at the top of the applet
     */
    private void makeMenu () {
	JMenu chooseMenu;
	JMenuItem originalItem; 
	JMenuItem threedItem;
	JMenuItem abcItem;
	JMenuItem shadowItem;
	    
	JMenu controllerMenu;
	JMenuItem playerItem;
	JMenuItem demoItem;
	    
	JMenu helpMenu;
	JMenu aboutMenu;
	JMenuItem aboutItem;
	    
	JPopupMenu.setDefaultLightWeightPopupEnabled(false);

	JMenuBar myMenuBar = new JMenuBar();
        this.setJMenuBar(myMenuBar);

        chooseMenu = new JMenu("Graphics");
        myMenuBar.add(chooseMenu);

        originalItem = new JMenuItem("Original");
        originalItem.addActionListener(this);
        chooseMenu.add(originalItem);

        threedItem = new JMenuItem("3D");
        threedItem.addActionListener(this);
        chooseMenu.add(threedItem);

        abcItem = new JMenuItem("ABC");
        abcItem.addActionListener(this);
        chooseMenu.add(abcItem);

        shadowItem = new JMenuItem("Shadow");
        shadowItem.addActionListener(this);
        chooseMenu.add(shadowItem);

        controllerMenu = new JMenu("Controller");
        myMenuBar.add(controllerMenu);

        playerItem = new JMenuItem("Keyboard player");
        playerItem.addActionListener(this);
        controllerMenu.add(playerItem);

        demoItem = new JMenuItem("Demo mode");
        demoItem.addActionListener(this);
        controllerMenu.add(demoItem);
        
        helpMenu = new JMenu("Help");
        myMenuBar.add(helpMenu);

        aboutMenu = new JMenu("About this");
        aboutMenu.addActionListener(this);
        helpMenu.add(aboutMenu);

        aboutItem = new JMenuItem("This is a nice little farm");
        aboutMenu.add(aboutItem);
    }
	
    /**
     * Method to get the snake object.
     */
    public Snake getSnake() {
	return snake;
    }
	
    /**
     * Passes the pressed key on to the snake.
     */
    public void keyPressed(KeyEvent evt) {
	snake.turn(evt.getKeyCode());
    }

    /**
     * Implements the actions that should be taking when an item in the menu is chosen.
     * @param event An event that originated in the item.
     */
    public void actionPerformed(ActionEvent event) {
        if ("Original" == event.getActionCommand()) {
            snake.setOriginalGraphics();
        } else if ("3D" == event.getActionCommand()) {
	    snake.set3dGraphics();
        } else if ("ABC"== event.getActionCommand()) {
	    snake.setABCGraphics();
        } else if ("Shadow"== event.getActionCommand()) {
	    snake.setShadowGraphics();
        } else if ("Keyboard player"== event.getActionCommand()) {
	    snake.setKeyControll();
        } else if ("Demo mode"== event.getActionCommand()) {
	    snake.setDemoControll();
        }	else {
            System.out.print("Missad meny");
        }
    	System.out.println(event.getActionCommand());
    }
	
	
    /**
     * Only exists to fill the KeyListener interface
     */
    public void keyTyped(KeyEvent evt) {
    }

    /**
     * Only exists to fill the KeyListener interface
     */
    public void keyReleased(KeyEvent evt) {
	
    }
}
