package lab3Source;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;

abstract class AbstractGraphics implements Renderer {
    protected Font font = new Font("Helvetica", Font.BOLD, 20);
    protected Color snakeColor, bonusColor = Color.red;

    public void putBackground() {
	java.awt.Graphics g = GamePanel.getBuffer();
	g.setColor(Color.lightGray);
	g.fillRect(0, 0,
		   GamePanel.getDimension().height,
		   GamePanel.getDimension().width);
    }

    public void outside() {
	java.awt.Graphics g = GamePanel.getBuffer();
	g.setFont(font);
	g.setColor(Color.red);
	g.drawString("You are outside",
		     GamePanel.getDimension().width / 4,
		     GamePanel.getDimension().height / 2);
    }

    public void setSnakeColor(Color color) {
	snakeColor = color;
    }
}
