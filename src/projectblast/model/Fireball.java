package projectblast.model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
/**
 * 
 * @author A.Freudenthaler
 *
 */
public class Fireball extends Explosive {
	
	
	public Fireball(int x, int y, Image sprite, int speed, Animation[] animations, Direction direction, Hero owner) {
		super(x, y, sprite, speed, direction, animations, owner);
		startMove();
		setName("Fireball");
	}

	@Override
	public void destroy() {
		explode();
	}

}
