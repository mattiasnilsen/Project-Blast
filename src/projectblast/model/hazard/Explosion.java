package projectblast.model.hazard;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Destructible;
import projectblast.model.DestructibleBlock;
import projectblast.model.Entity;
import projectblast.model.Id;
import projectblast.model.Position;
import projectblast.model.hero.Hero;


public class Explosion extends Entity implements IHazard {
	
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
