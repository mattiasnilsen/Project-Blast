package projectblast.model;

import java.util.List;

import org.newdawn.slick.geom.Shape;

public interface ICore {
	
	/**
	 * Tick down this core
	 */
	public void tick();
	
	/**
	 * Returns true if this core is dead and should be removed
	 * @return true if dead
	 */
	public boolean isDead();
	
	/**
	 * Return all parts
	 * @return
	 */
	public List<IBurst> getParts();

	public boolean isCreated();

	public void create();

	public boolean step(Entity intersectingEntity);

	public Position getNextPosition();
}
