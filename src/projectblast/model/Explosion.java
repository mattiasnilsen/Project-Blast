package projectblast.model;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;



public class Explosion extends Entity {

	private int life = 60;
	private List<Explosion> parts;
	
	public Explosion(int x, int y, Image sprite, int power) {
		super(x, y, sprite, new Rectangle(x,y,32,32));
		expand(power);
	}

	
	public void update(){
		life--;
	}
	
	public boolean isDead(){
		return life <= 0;
	}
	
	private void expand(int pow){
		for (int i = 0; i < pow; i++){
			int size = Constants.TILE_SIZE;
			parts.add(new Explosion(getX() + size,getY(),getSprite(),1));
			parts.add(new Explosion(getX() - size,getY(),getSprite(),1));
			parts.add(new Explosion(getX(),getY() + size,getSprite(),1));
			parts.add(new Explosion(getX(),getY() - size,getSprite(),1));
		}
	}
	
}
