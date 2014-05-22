package projectblast.model.entity;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;

/**
 * @author franton
 * revised by Mattias Nilsen
 * An interface for all movable things in Project Blast.
 */
public interface Movable {
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
	 * Set this object to stop moving for a duration
	 * @param duration number of frames. 
	 */
	public void stopMove(int duration);
	/**
	 * Place this object at location.
	 * @param x The x coordinate to place this object.
	 * @param y The y coordinate to place this object.
	 */
	public void place(int x, int y);
	/**
	 * Place this object at location.
	 * @param p the position to place it at.
	 */
	public void place(Position p);
}
