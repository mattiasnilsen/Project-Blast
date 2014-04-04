package projectblast.model;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;



public class Explosion extends Entity {

	private int life = 60;
	private List<Explosion> parts;
	
	public Explosion(int x, int y, Image sprite) {
		super(x, y, sprite, new Rectangle(x+1,y+1,30,30));
		setName("Explosion");
	}

	
	public void update(){
		life--;
	}
	
	public boolean isDead(){
		return life <= 0;
	}
	
}
