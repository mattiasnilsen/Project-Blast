package projectblast.model.explosive;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Destructible;
import projectblast.model.Movable;
import projectblast.model.MovableEntity;
import projectblast.model.Position;
import projectblast.model.Movable.Direction;
import projectblast.model.hero.Hero;

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
