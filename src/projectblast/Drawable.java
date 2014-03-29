/**
 * 
 */
package projectblast;

import org.newdawn.slick.Color;

import org.newdawn.slick.Graphics;

/**
 * @author Mattias Nilsen
 *
 */
public interface Drawable {
	public void draw(Graphics g);
	public void draw(Graphics g, Color teamColor);
}
