package projectblast.model;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Entity implements Updatable{
	private Image sprite;
	private final Rectangle collisionBox;
	
	private int x,y;
	private String name = "Entity";

	public Entity(int x, int y, Image sprite, Rectangle box) {
		this.x = x;
		this.y = y;
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
		return (x%GRID == 0 && y%GRID == 0);
	}
	
	public void setX(int x) {
	    this.x = x;
	}
	
	public int getX() {
	    return x;
	}
	
	public void setY(int y) {
	    this.y = y;
	}
	
	public int getY() {
	    return y;
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
