package projectblast;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

/**
 * 
 * @author franton
 *
 */
public class MovableEntity extends Entity implements Movable {
	private int speed = 4;
	private Animation[] animations;
	
	public MovableEntity(int x, int y, Image sprite) {
		super(x, y, sprite);
	}
	
	public MovableEntity(int x, int y, Image sprite, Animation[] animations){
		this(x,y,sprite);
		this.animations = animations;
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
		
	

}
