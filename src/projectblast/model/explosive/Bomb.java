package projectblast.model.explosive;

import projectblast.model.Movable;
import projectblast.model.Position;
import projectblast.model.Movable.Direction;
import projectblast.model.hero.Hero;


public class Bomb extends Explosive {
	private boolean isDestroyed = false;
	
	public Bomb(Position position, int speed, Direction direction,
			 Hero owner) {
		super(position, speed, direction, owner);
		setName("Bomb");
		setLife(120);
	}

	@Override
	public void destroy() {
		isDestroyed = true;	
	}
	
	public boolean isDestroyed(){
		return isDestroyed;
	}
	
	
	
	public void update(){
		setLife(getLife()- 1);
	}

}
