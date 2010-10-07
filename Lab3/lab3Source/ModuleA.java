/*
 * Created on 2005-mar-17
 *
 */
package lab3Source;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

/**
 * A background module that make the game more fun.
 * @author Peter Sunnergren
 */

public class ModuleA implements Runnable{
	private BufferedImage image;
	private Graphics2D buffer;
	private Color color1 = Color.white;
	private Color color2 = Color.black;
	private boolean running;
	
	/**
	 * Sets up the buffering and draws the first set of slices.
	 */
	public ModuleA () { 
		image = new BufferedImage(GamePanel.getDimension().width, GamePanel.getDimension().height, BufferedImage.TYPE_INT_RGB);
		buffer = (Graphics2D)image.getGraphics();
		drawSlices(0);
	}
	
	private void drawSlices(int o) {
		boolean white = true;
		double offset = Math.PI/32*o;
		Polygon polygon = new Polygon();
		for (int i = 0; i < 16; i++) {
		if (white) {
				buffer.setColor(color1);
				white = false;
			} else {
				buffer.setColor(color2);
				white = true;
			}
			polygon.addPoint(GamePanel.getDimension().width / 2, GamePanel.getDimension().height / 2);
			polygon.addPoint(GamePanel.getDimension().width /2 + (int)Math.round(Math.sin(i*Math.PI/8+offset)*GamePanel.getDimension().width),
					GamePanel.getDimension().width/2 + (int)Math.round(Math.cos(i*Math.PI/8+offset)*GamePanel.getDimension().width));
			polygon.addPoint(GamePanel.getDimension().width /2 + (int)Math.round(Math.sin((i+1)*Math.PI/8+offset)*GamePanel.getDimension().width),
					GamePanel.getDimension().width /2 + (int)Math.round(Math.cos((i+1)*Math.PI/8+offset)*GamePanel.getDimension().width));
			buffer.fill(polygon);
			polygon.reset();		
		}
	}
	
	/**
	 * Rotates the slices a little each time step.
	 */
	public void run() {
		int i = 1;
		running = true;
		while (running) {
			drawSlices(i);
			i++;
			GamePanel.rePaint();
		    try { 
				Thread.sleep(70);
			} catch( InterruptedException e ) {
				System.out.println("Twister stopped");
			}
		}
	}
	
	/**
	 * Draws the current slices in to the screen.
	 */
	public void drawBackground() {
		if (running) {
			GamePanel.getBuffer().drawImage(image, 0, 0, null);
		}
	}
	
	/**
	 * If the snake goes outside this exits the program.
	 */
	public void outSide () {
		System.exit(0);
	}
}
