package projectblast.model;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;



public class Explosion extends Entity {

	public Explosion(int x, int y, Image sprite) {
		super(x, y, sprite, new Rectangle(x,y,32,32));
	}

}
