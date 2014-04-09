package projectblast.model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class Bomb extends Explosive {

	public Bomb(Position position, Image sprite, int speed, Direction direction,
			Animation[] animations, Hero owner) {
		super(position, sprite, speed, direction, animations, owner);
		setName("Bomb");
	}

	@Override
	public Explosion destroy() {
		return null;
		
	}

	
	
	


}
