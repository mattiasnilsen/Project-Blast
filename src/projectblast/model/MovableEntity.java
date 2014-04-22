package projectblast.model;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

/**
 * 
 * @author franton
 * revised by Mattias Nilsen
 */

public class MovableEntity extends Entity implements Movable {

	private int speed;
	private Direction direction;
	private boolean moving;

	
	public MovableEntity(Position position, int speed, Direction direction, Rectangle box) {
		super(position, box);
		this.speed = speed;
		this.direction = direction;
	}


	@Override
	public void move(int dx, int dy) {
		place(getX() + dx, getY() + dy);
	}
	@Override
	public void place(int x, int y) {
		setX(x);
		setY(y);
		getCollisionBox().setX(x + Constants.TILE_SIZE/2 - getCollisionBox().getWidth() / 2);
		getCollisionBox().setY(y + Constants.TILE_SIZE/2 - getCollisionBox().getHeight() / 2);
	}
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void move(Direction direction) {
		place(getX() + (direction.getX()), getY() + (direction.getY()));
	}
	
	
	public void move(Direction direction, int speed) {
		place(getX() + (speed * direction.getX()), getY() + (speed * direction.getY()));
	}

	@Override
	public void startMove(Direction direction) {
		this.direction = direction;
		moving = true;
		
		
	}
	@Override
	public void startMove() {
	    moving = true;
	}

	@Override
	public void stopMove() {
		moving = false;
	}
	
	public void stopMove(Direction direction) {
		if(this.direction == direction){
			moving = false;
		}
	}
	
	public boolean isMoving() {
		return moving;
	}
	@Override
	public void update() {
		if(isMoving()) {
			move(direction,speed);
		}
	}


	
	public boolean allowPassage(Entity entity){
		return false;
	}


	@Override
	public boolean isMovable() {
		return true;
	}



}
