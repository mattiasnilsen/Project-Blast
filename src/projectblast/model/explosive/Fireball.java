package projectblast.model.explosive;

import projectblast.model.Position;
import projectblast.model.hero.Hero;


/**
 * @author A.Freudenthaler
 *
 */
public class Fireball extends Explosive {
	private Hero owner;
	private boolean isDestroyed;
	
	public Fireball(Position position,  int speed,  Direction direction, Hero owner) {
		super(position,  speed, direction,  owner);
		startMove();
		setName("Fireball");
		this.owner = owner;
	}

	@Override
	public void destroy() {
		isDestroyed = true;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

}
