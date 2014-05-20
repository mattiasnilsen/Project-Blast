package projectblast.model.entity.hazard;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Movable;
import projectblast.model.entity.Entity;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Id;
import projectblast.model.helper.Position;

public class Paralyzer extends Hazard {
	
	public Paralyzer(Position position) {
		super(position,  new Rectangle(position.getX() + 1, position.getY() + 1, 30, 30));
		setName(Id.PARALYZER);
	}

	@Override
	public void update() {
	}

	@Override
	public boolean allowPassage(Entity entity) {
		return true;
	}

	@Override
	public void collide(Entity entity) {
		if(entity instanceof Movable){
			((Movable)entity).stopMove(Constants.STUN_TIME);
		}
		
	}
}
