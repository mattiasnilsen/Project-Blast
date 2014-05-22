package projectblast.model.entity;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.BlastModel;
import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;

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
	
	public DestructibleBlock(Position p, int speed, Direction dir, Rectangle rect){
		super(p,speed,dir,rect);
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
	public void stopMove() {
		super.stopMove();
		snapToGrid();
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
