package projectblast.model.entity;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.attribute.Position;
import projectblast.model.helper.Constants;

public abstract class Entity implements Updatable, Comparable<Entity>{
	
	private final Rectangle collisionBox;
	
	private Position position;
	private Id name;

	public Entity(Position position,  Rectangle box) {
		this.position = position;
		this.collisionBox = box;
	}

	/**
	 * Checks if this entity is on the grid. It is if x and y are both divisible by grid size.
	 * @return
	 */
	public boolean isOnGrid(){
		return (position.getX() % Constants.TILE_SIZE == 0 && position.getY() % Constants.TILE_SIZE == 0);
	}
	
	public void setX(int x) {
	    position.setX(x);
		getCollisionBox().setX(x + Constants.TILE_SIZE/2 - getCollisionBox().getWidth() / 2);
	}
	
	public int getX() {
	    return position.getX();
	}
	
	public void setY(int y) {
	    position.setY(y);
		getCollisionBox().setY(y + Constants.TILE_SIZE/2 - getCollisionBox().getHeight() / 2);
	}
	
	public int getY() {
	    return position.getY();
	}
	
	public Position getPosition(){
		return position;
	}
	
	public void setPosition(Position p){
		setPosition(p.getX(),p.getY());
		getCollisionBox().setX(p.getX() + Constants.TILE_SIZE/2 - getCollisionBox().getWidth() / 2);
		getCollisionBox().setY(p.getY() + Constants.TILE_SIZE/2 - getCollisionBox().getHeight() / 2);
	}
	
	public void setPosition(int x, int y){
		position = new Position(x,y);
	}

	public Rectangle getCollisionBox() {
		return collisionBox;
	}
	//Called before another object has collided with this object to determine if it can.
	public abstract boolean allowPassage(Entity entity);
	//Called when another object has collided with this object.
	public abstract void collide(Entity entity);
	
	public void setName(Id name){
		this.name = name;
	}
	
	public Id getName(){
		return name;
	}
	@Override
	public int compareTo(Entity otherEntity){
		if(this.getX() >= otherEntity.getX()){
			return 1;
		}
		return -1;
	}
}
