package projectblast;

import org.newdawn.slick.Image;

/**
 * 
 * @author franton
 * revised by Mattias Nilsen
 */
public class MovableEntity extends Entity implements Movable, Updatable {

	private int speed = 4;
	private Direction moveDirection = null;
	
	public MovableEntity(int x, int y, Image sprite) {
		super(x, y, sprite);
	}
	
	public void move(int dx, int dy) {
		place(getX() + dx, getY() + dy);
	}

	
	public void place(int x, int y) {
		setX(x);
		setY(y);
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	@Override
	public void move(Direction direction) {
		place(getX() + (getSpeed() * direction.getX()), getY() + (getSpeed() * direction.getY()));
	}


	@Override
	public void startMove(Direction direction) {
		this.moveDirection = direction;
	}


	@Override
	public void stopMove() {
		moveDirection = null;
	}

	@Override
	public void update() {
		if(moveDirection != null) {
			move(moveDirection);
		}
	}
		
	

}
