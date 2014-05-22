package projectblast.model.entity.hazard;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.attribute.Position;
import projectblast.model.entity.Entity;
import projectblast.model.entity.Id;
import projectblast.model.entity.Movable;
import projectblast.model.helper.Constants;

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
