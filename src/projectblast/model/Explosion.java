package projectblast.model;

import org.newdawn.slick.geom.Rectangle;



public class Explosion extends Entity {

	private int life = 60;
	
	public Explosion(Position position) {
		super(position,  new Rectangle(position.getX() + 1, position.getY() + 1, 30, 30));
		setName(Id.EXPLOSION);
	}

	
	public void update(){
		life--;
	}
	
	public boolean isDead(){
		return life <= 0;
	}
	
}
