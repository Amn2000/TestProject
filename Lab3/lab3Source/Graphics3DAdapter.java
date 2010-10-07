package lab3Source;

import java.util.AbstractList;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Iterator;

// YOUR CODE HERE
// extends? implements?
class Graphics3DAdapter extends AbstractGraphics {

	 private Graphics3D _Graph3D;
    public Graphics3DAdapter(Graphics3D input)
    {
    	_Graph3D=input;
    	
    }
    // YOUR CODE HERE
    // overwrite some of the inherited/implemented methods

	@Override
	public void putBackground() {
		// TODO Auto-generated method stub
		_Graph3D.printBase(Color.magenta);
	}
	@Override
	public void putBody(AbstractList body) {
		// TODO Auto-generated method stub
		
		Iterator iterator = body.iterator();
		while (iterator.hasNext()) {
			 Rectangle tmpRectangle = (Rectangle)iterator.next();
		 
		_Graph3D.printBox(new Box(tmpRectangle.x, tmpRectangle.y, tmpRectangle.x+tmpRectangle.width, tmpRectangle.y+tmpRectangle.height), snakeColor);
		}
	}
	@Override
	public void putBonus(AbstractList bonus) {
		// TODO Auto-generated method stub
		Iterator iterator = bonus.iterator();
		while (iterator.hasNext()) {
		    Rectangle tmpRectangle = (Rectangle)iterator.next();
		_Graph3D.printBonus(new Box(tmpRectangle.x, tmpRectangle.y, tmpRectangle.x+tmpRectangle.width, tmpRectangle.y+tmpRectangle.height),Color.cyan);
		
	}
	}


}
