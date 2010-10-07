package lab3Source;

import java.util.AbstractList;
import java.util.Iterator;
import java.awt.Color;
import java.awt.Rectangle;

// YOUR CODE HERE
// extends? implements?
class Decorator  extends AbstractGraphics  {
    private Renderer decorated;

    public Decorator(Renderer r) {
	decorated = r;
    }

    private void decorateRectangle(Rectangle r) {
	java.awt.Graphics g = GamePanel.getBuffer();
	g.drawLine(r.x + 2, r.y - 2, r.x + r.width + 2, r.y - 2);
	g.drawLine(r.x + r.width + 2, r.y - 2,
		   r.x + r.width + 2, r.y - 2 + r.height);
    }

	@Override
	public void putBody(AbstractList body) {
		// TODO Auto-generated method stub
		
		decorated.putBody(body);
		Iterator iterator = body.iterator();
		while (iterator.hasNext()) 
		{
		 Rectangle tmpRectangle = (Rectangle)iterator.next();
		 decorateRectangle(tmpRectangle);
		}
		
	}

	@Override
	public void putBonus(AbstractList bonus) {
		// TODO Auto-generated method stub
		decorated.putBonus(bonus);
		Iterator iterator = bonus.iterator();
		while (iterator.hasNext()) 
		{
		 Rectangle tmpRectangle = (Rectangle)iterator.next();
		 decorateRectangle(tmpRectangle);
		}
		
	}


	@Override
	public void putBackground() {
		// TODO Auto-generated method stub
		decorated.putBackground();
		
	}

	

    // YOUR CODE HERE
    // overwrite some of the inherited/implemented methods
	
	
}
