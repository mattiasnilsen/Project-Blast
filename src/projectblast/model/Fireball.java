package projectblast.model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
/**
 * 
 * @author A.Freudenthaler
 *
 */
public class Fireball extends Explosive {
	private Hero owner;
	
	public Fireball(int x, int y, Image sprite, int speed, Animation[] animations, Direction direction, Hero owner) {
		super(x, y, sprite, speed, direction, animations, owner);
		startMove();
		setName("Fireball");
		this.owner = owner;
	}

	@Override
	public Explosion destroy() {
		if(owner.getCollisionBox().intersects(this.getCollisionBox())|| this.getCollisionBox().intersects(owner.getCollisionBox())){
			throw new NullPointerException("Fireball hit its owner");
		}else{
			return explode();
		}
		}

}
