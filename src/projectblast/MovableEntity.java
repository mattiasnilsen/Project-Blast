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
	private Direction moveDirection = null;
	private Animation[] animations;

	
	public MovableEntity(int x, int y, Image sprite, int speed) {
		super(x, y, sprite);
		this.speed = speed;
	}

	
	public MovableEntity(int x, int y, Image sprite, int speed, Animation[] animations){
		this(x,y,sprite, speed);
		this.animations = animations;
	}
	
	@Override
	public void draw(Graphics g){
		g.drawAnimation(animations[0], getX(), getY());
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
