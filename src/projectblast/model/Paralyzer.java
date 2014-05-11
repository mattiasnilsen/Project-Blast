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
		return true;
	}

	@Override
	public void collide(Entity entity) {
		if(entity instanceof Movable){
			((Movable)entity).stopMove(Constants.STUN_DURATION);
		}
		
	}
}
