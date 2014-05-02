package projectblast.model;

import java.util.List;

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
}
