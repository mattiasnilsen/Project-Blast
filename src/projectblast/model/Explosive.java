package projectblast.model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public abstract class Explosive extends MovableEntity implements Destructible {
	private Hero owner;
	
	public Explosive(Position position, Image sprite, int speed, Direction direction, Animation[] animations, Hero owner) {
		super(position, sprite, speed, direction, new Rectangle(position.getX(), position.getY(), 28, 28), animations);
		this.owner = owner;
	
	}
	
	public Hero getOwner(){
		return owner;
	}
	
	
	public Explosion explode(){
		
		return new Explosion(new Position(getX(), getY()), null);
		
	}

}
