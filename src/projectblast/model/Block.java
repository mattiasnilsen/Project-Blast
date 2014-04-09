package projectblast.model;

import org.newdawn.slick.geom.Rectangle;

/**
 * @author Axel Savén Östebo
 *
 */
public abstract class Block extends Entity{
	
	public Block(Position position){
		super(position,  new Rectangle(position.getX() + 1, position.getY() + 1, 30, 30));
	}

}
