package projectblast;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * 
 * @author franton
 * revised by Mattias Nilsen
 */

public class MovableEntity extends Entity implements Movable, Updatable {

	private int speed = 4;
	private Direction direction;
	private boolean moving;
	private Animation[] animations;

	
	public MovableEntity(int x, int y, Image sprite, int speed, Direction direction) {
		super(x, y, sprite);
		this.speed = speed;
		this.direction = direction;
	}

	public MovableEntity(int x, int y, Image sprite, int speed, Direction direction, Animation[] animations){
		this(x,y,sprite, speed, direction);
		this.animations = animations;
	}
	@Override
	public void draw(Graphics g){
		g.drawAnimation(animations[0], getX(), getY());
	}
	@Override
	public void move(int dx, int dy) {
		place(getX() + dx, getY() + dy);
	}
	@Override
	public void place(int x, int y) {
		setX(x);
		setY(y);
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
		place(getX() + (getSpeed() * direction.getX()), getY() + (getSpeed() * direction.getY()));
	}

	@Override
	public void startMove(Direction direction) {
		this.direction = direction;
		moving = true;
	}

	@Override
	public void stopMove() {
		moving = false;
	}
	
	public boolean isMoving() {
		return moving;
	}
	@Override
	public void update() {
		if(isMoving()) {
			move(direction);
		}
	}
}
