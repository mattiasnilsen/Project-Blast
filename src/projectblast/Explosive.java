package projectblast;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public abstract class Explosive extends MovableEntity implements Destructible {
	private Hero owner;
	
	
	public Explosive(int x, int y, Image sprite, int speed, Direction direction, Animation[] animations, Hero owner) {
		super(x, y, sprite, speed, direction, animations);
		this.owner = owner;
		
	}
	
	
	
	
	
	public void explode(){
		
	}

}
