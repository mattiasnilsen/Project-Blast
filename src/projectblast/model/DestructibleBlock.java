package projectblast.model;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.hero.Hero;

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
		return false;
	}
	
	@Override
	public void collide(Entity entity) {
		stopMove();
	}



	

}
