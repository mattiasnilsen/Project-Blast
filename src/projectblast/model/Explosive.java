package projectblast.model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public abstract class Explosive extends MovableEntity implements Destructible {
	private Hero owner;
	
	
	public Explosive(int x, int y, Image sprite, int speed, Direction direction, Animation[] animations, Hero owner) {
		super(x, y, sprite, speed, direction, new Rectangle(x,y,28,28), animations);
		this.owner = owner;
		
	}
	
	
	public Hero getOwner(){
		return owner;
	}
	
	
	public void explode(){
		
	}

}
