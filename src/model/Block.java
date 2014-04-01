package model;

import org.newdawn.slick.Image;

/**
 * @author Axel Savén Östebo
 *
 */
public abstract class Block extends Entity{
	
	public Block(int x, int y, Image sprite){
		super(x,y,sprite);
	}

}
