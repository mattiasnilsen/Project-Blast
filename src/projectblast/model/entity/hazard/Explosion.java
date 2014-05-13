package projectblast.model.entity.hazard;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Destructible;
import projectblast.model.entity.DestructibleBlock;
import projectblast.model.entity.Entity;
import projectblast.model.entity.hero.Hero;
import projectblast.model.helper.Id;
import projectblast.model.helper.Position;


public class Explosion extends Hazard {
	
	public Explosion(Position position) {
		super(position,  new Rectangle(position.getX() + 1, position.getY() + 1, 30, 30));
		setName(Id.EXPLOSION);
	}


	@Override
	public boolean allowPassage(Entity entity) {
		return true;
	}

	@Override
	public void collide(Entity entity) {
		if(entity instanceof DestructibleBlock || entity instanceof Hero) {
			Destructible dest = (Destructible)entity;
			dest.destroy();
		}
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
