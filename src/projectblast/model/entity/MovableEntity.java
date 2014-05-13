package projectblast.model.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

import projectblast.model.BlastModel;
import projectblast.model.Movable;
import projectblast.model.Movable.Direction;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Position;

/**
 * 
 * @author franton
 * revised by Mattias Nilsen
 */

public class MovableEntity extends Entity implements Movable {

	private int speed;
	private Direction direction;
	private boolean moving;
	private int stopDuration = 0;
	

	
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
	
	@Override
	public void place(Position p) {
		place(p.getX(),p.getY());
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
	
	public boolean isMoving() {
		return moving;
	}
	
	@Override
	public void update() {
		if(getStopDuration() > 0){
			stopMove();
			setStopDuration(getStopDuration() - 1);
		}
		
		
		if(isMoving()){
			Direction dir = getDirection();
			int distance = getSpeed();
			if(dir.getX() != 0 && dir.getY() != 0) {
			    distance = distance - 1; //TODO fix properly
			}
			while(distance > 0) {
				if(BlastModel.isFree(this, dir, 1)) {
	                move(dir); //TODO want to tell hero to start move instead
	            } else if(dir.getX() != 0 && dir.getY() != 0) { //Moving diagonally
			        if(BlastModel.isFree(this, Direction.getDirection(dir.getX(), 0), 1)) {
			            move(Direction.getDirection(dir.getX(), 0));//TODO want to tell hero to start move instead
			        } else if(BlastModel.isFree(this, Direction.getDirection(0, dir.getY()), 1)) {
	                    move(Direction.getDirection(0, dir.getY()));//TODO want to tell hero to start move instead
	                }
			    } else {
			    	stopMove();
			    }
				distance--;
			}
			
		}
	}


	
	public boolean allowPassage(Entity entity){
		return true;
	}

	@Override
	public void collide(Entity entity) {
		//stopMove();
		
	}

	@Override
	public void stopMove(int stopDuration) {
		this.setStopDuration(stopDuration);
	}


	public int getStopDuration() {
		return stopDuration;
	}


	public void setStopDuration(int stopDuration) {
		this.stopDuration = stopDuration;
	}

}
