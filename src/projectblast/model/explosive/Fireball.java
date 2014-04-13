package projectblast.model.explosive;

import projectblast.model.Entity;
import projectblast.model.Id;
import projectblast.model.Position;
import projectblast.model.hero.Hero;


/**
 * @author A.Freudenthaler
 *
 */
public class Fireball extends Explosive {
	private boolean isDestroyed;
	
	public Fireball(Position position,  int speed,  Direction direction, Hero owner) {
		super(position,  speed, direction,  owner);
		startMove();
		setName(Id.FIREBALL);
		
	}

	@Override
	public void destroy() {
		setLife(0);
		isDestroyed = true;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}
	
	@Override
	public boolean allowPassage(Entity entity){
		return true;
	}
	

}
