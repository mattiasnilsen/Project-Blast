package projectblast.model.explosive;

import projectblast.model.*;
import projectblast.model.hero.*;



public class Bomb extends Explosive {
	private boolean isDestroyed = false;
	
	public Bomb(Position position, int speed, Direction direction,
			 Hero owner) {
		super(position, speed, direction, owner);
		setName(Id.BOMB);
		setLife(120);
	}

	@Override
	public void destroy() {
		setLife(0);
		isDestroyed = true;	
	}
	
	public boolean isDestroyed(){
		return isDestroyed;
	}
	
	
	
	public void update(){
		setLife(getLife()- 1);
	}

}
