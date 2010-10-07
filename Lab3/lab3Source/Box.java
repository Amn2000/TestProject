/*
 * Created on 2005-mar-16
 */
package lab3Source;

/**
 * A representation of the bounding box of an element in the snakes body.
 * @author Peter Sunnergren
 */
public class Box {
	int x1;
	int y1;
	int x2;
	int y2;
	/**
	 * @param x1 X-coord upper left corner
	 * @param y1 Y-coord upper left corner
	 * @param x2 X-coord lower right corner
	 * @param y2 Y-coord lower right corner
	 */
	public Box(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
}
