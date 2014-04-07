package projectblast.model;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Axel Savén Östebo
 *
 */
public abstract class Block extends Entity{
	
	public Block(int x, int y, Image sprite){
		super(x,y,sprite,new Rectangle(x+1,y+1,30,30));
	}

}
