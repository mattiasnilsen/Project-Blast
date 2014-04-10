package projectblast.model;

import org.newdawn.slick.geom.Rectangle;

public abstract class Explosive extends MovableEntity implements Destructible {
	private Hero owner;
	private int power;
	
	public Explosive(Position position,  int speed, Direction direction, Hero owner) {
		super(position,  speed, direction, new Rectangle(position.getX(), position.getY(), 28, 28));
		this.owner = owner;
		power = owner.getPower();
	
	}
	
	public Hero getOwner(){
		return owner;
	}
	
	
	public void explode(){
		//TODO Remove this method or make it do something
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

}
