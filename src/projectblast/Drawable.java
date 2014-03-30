package projectblast;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * @author Mattias Nilsen
 * Common interface for every class that needs to be drawn on the screen.
 */
public interface Drawable {
    /**
     * Draw this object at its position.
     * @param g The graphics object used to draw.
     */
	public void draw(Graphics g);
	/**
	 * Draw this object at its position with the supplied color.
	 * @param g The graphics object used to draw.
	 * @param teamColor The color to user.
	 */
	public void draw(Graphics g, Color teamColor);
}
