package projectblast.model;

import org.newdawn.slick.geom.Rectangle;

public class Paralyzer extends Entity implements IHazard {
	
	public Paralyzer(Position position) {
		super(position,  new Rectangle(position.getX() + 1, position.getY() + 1, 30, 30));
		setName(Id.PARALYZER);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean allowPassage(Entity entity) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isMovable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void collide(Entity entity) {
		// TODO Auto-generated method stub
		
	}
}
