package projectblast.model.entity;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Destructible;
import projectblast.model.Direction;
import projectblast.model.helper.Id;
import projectblast.model.helper.Position;

/**
 *
 * @author Alex Tao
 */
public class DestructibleBlock extends MovableEntity implements Destructible{
	private boolean isDestroyed;

	public DestructibleBlock(Position position) {
		super(position, 1, Direction.NONE, new Rectangle(position.getX() + 1, position.getY() + 1, 30, 30));

		setName(Id.DESTRUCTIBLEBLOCK);
		setSpeed(4);
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
