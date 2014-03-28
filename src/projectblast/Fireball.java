package projectblast;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class Fireball extends Explosive {
	
	
	public Fireball(int x, int y, Image sprite, Animation[] animations, Direction direction, Hero owner) {
		super(x, y, sprite, animations, owner);
		this.move(direction);
		
	}

	@Override
	public void destroy() {
		explode();
		//remove object
	}

}
