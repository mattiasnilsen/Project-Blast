package projectblast.model;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Entity implements Updatable{
	private Image sprite;
	private final Rectangle collisionBox;
	
	private Position position;
	private String name = "Entity";

	public Entity(Position position, Image sprite, Rectangle box) {
		this.position = position;
		this.sprite = sprite;
		this.collisionBox = box;
		
		//TODO Remove this hack
		if (box.getWidth() == 32){
			box.setWidth(31);
			box.setHeight(31);
		}
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public boolean isOnGrid(){
		//TODO Remove hard code
		final int GRID = 32;
		return (position.getX() % GRID == 0 && position.getY() % GRID == 0);
	}
	
	public void setX(int x) {
	    position.setX(x);
	}
	
	public int getX() {
	    return position.getX();
	}
	
	public void setY(int y) {
	    position.setY(y);
	}
	
	public int getY() {
	    return position.getY();
	}

	public Rectangle getCollisionBox() {
		return collisionBox;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}


	@Override
	public void update() {
		
		
	}



}
