package projectblast;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/**
 * 
 * @author franton
 *
 */
public class Mage extends Hero {
	public Mage(int x, int y, Image sprite, int speed, Animation[] animations) {
		super(x, y, sprite, speed, animations);
	}

	@Override
	public void primaryAbility() {
		Animation[] animations = new Animation[4];
    	Image[] images = new Image[1];
    	try {
			images[0] = new Image("/data/image/SnowballDown.png");

		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	animations[0] = new Animation(images, 1000);
		try {
			new Fireball(getX(), getY(), new Image("/data/image/SnowballDown.png"), 8, animations, Direction.SOUTH, this );
		} catch (SlickException e) {
			
			e.printStackTrace();
		}
		//remove bombcount
		
	}

	@Override
	public void secondaryAbility() {
		// TODO Auto-generated method stub
		
	}

}
