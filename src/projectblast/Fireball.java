package projectblast;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class Fireball extends Explosive {
	
	
	public Fireball(int x, int y, Image sprite, int speed, Animation[] animations, Direction direction, Hero owner) {
		super(x, y, sprite, speed, direction, animations, owner);
	}

	@Override
	public void destroy() {
		explode();
		//remove object
	}

}
