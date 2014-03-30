package projectblast;

/**
 * @author franton
 * revised by Mattias Nilsen
 * An interface for all movable things in Project Blast.
 */
public interface Movable {
    /**
     * Possible directions an object can be moved.
     */
	public enum Direction {
		NORTH(0, -1),
		SOUTH(0, 1),
		WEST(-1, 0),
		EAST(1, 0);
		
		private int x;
		private int y;
		
		Direction(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	}
	/**
	 * Move this object one step in the supplied direction.
	 * @param direction The direction to move.
	 */
	public void move(Direction direction);
	/**
	 * Move this object.
	 * @param dx The change to the x coordinate.
	 * @param dy The change to the y coordinate.
	 */
	public void move(int dx, int dy);
	/**
	 * Set this object to move forward at it's speed in the supplied direction every tick
	 * @param direction The direction to move.
	 */
	public void startMove(Direction direction);
	/**
	 * Set this object to move forward at its speed every tick.
	 */
	public void startMove();
	/**
	 * Set this object to stop moving.
	 */
	public void stopMove();
	/**
	 * Place this object at location.
	 * @param x The x coordinate to place this object.
	 * @param y The y coordinate to place this object.
	 */
	public void place(int x, int y);
}
