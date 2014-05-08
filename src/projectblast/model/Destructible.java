package projectblast.model;
/**
 * 
 * @author Alex Tao
 */
public interface Destructible {
	/**
	 * Destroy is called upon when this destructible should be destroyed.
	 * May take several calls to actually destroy the destructible.
	 */
	public void destroy();
	/**
	 * Checks if this destructible is destroyed.
	 * if this returns true it will be permanently removed from the game.
	 * @return true it is destroyed.
	 */
	public boolean isDestroyed();
}
