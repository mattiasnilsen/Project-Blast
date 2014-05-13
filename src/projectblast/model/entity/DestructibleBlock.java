package projectblast.model.entity;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Destructible;
import projectblast.model.Id;
import projectblast.model.Position;
import projectblast.model.Movable.Direction;
import projectblast.model.entity.explosive.Fireball;
import projectblast.model.entity.hero.Hero;

/**
 *
 * @author Alex Tao
 */
public class DestructibleBlock extends MovableEntity implements Destructible{
	private boolean isDestroyed;

	public DestructibleBlock(Position position) {
		super(position, 1, Direction.NONE, new Rectangle(position.getX() + 1, position.getY() + 1, 30, 30));

		setName(Id.DESTRUCTIBLEBLOCK);
	}

	@Override
	public void destroy() {
		isDestroyed = true;
	}

	@Override
	public boolean isDestroyed() {
		return isDestroyed;
	}

	@Override
	public boolean allowPassage(Entity entity) {
		/*if(entity instanceof Fireball){
			return true;
		}*/
		return false;
	}
	
	@Override
	public void collide(Entity entity) {
		
	}



	

}
