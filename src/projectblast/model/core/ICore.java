package projectblast.model.core;

import java.util.List;

import projectblast.model.Entity;
import projectblast.model.Position;
import projectblast.model.hazard.Hazard;
/**
 * Common interface for all Cores.
 * A core is something that creates a chain of objects in different directions.
 */
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
	 * Returns all of this cores parts
	 * @return this cores parts.
	 */
	public List<Hazard> getParts();
	/**
	 * Are all parts of this core created.
	 * @return true if all parts are created.
	 */
	public boolean isCreated();
	/**
	 * Create next part at the position returned by getNextPosition()
	 * A call to step must be done before to make sure this position is valid.
	 */
	public void create();
	/**
	 * Returns true if a part can be created on intersectingEntity
	 * @param intersectingEntity The entity at position from getNextPosition(), can be null
	 * @return true if a part can be created on intersectingEntity
	 */
	public boolean step(Entity intersectingEntity);
	/**
	 * Returns the position where the next part should be placed.
	 * If there's an entity at that position it step should be called with that entity.
	 * @return The position where the next part should be placed
	 */
	public Position getNextPosition();
}
