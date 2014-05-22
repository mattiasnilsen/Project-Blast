package projectblast.model.entity;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.attribute.Position;

/**
 * @author Axel Savén Östebo
 *
 */
public abstract class Block extends Entity{
	
	public Block(Position position){
		super(position,  new Rectangle(position.getX() + 1, position.getY() + 1, 30, 30));
	}

}
