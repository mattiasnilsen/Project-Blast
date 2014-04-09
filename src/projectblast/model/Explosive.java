package projectblast.model;

import org.newdawn.slick.geom.Rectangle;

public abstract class Explosive extends MovableEntity implements Destructible {
	private Hero owner;
	
	public Explosive(Position position,  int speed, Direction direction, Hero owner) {
		super(position,  speed, direction, new Rectangle(position.getX(), position.getY(), 28, 28));
		this.owner = owner;
	
	}
	
	public Hero getOwner(){
		return owner;
	}
	
	
	public Explosion explode(){
		
		return new Explosion(new Position(getX(), getY()));
		
	}

}
