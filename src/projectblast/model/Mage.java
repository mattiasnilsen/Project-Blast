package projectblast.model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import projectblast.control.GameplayState;
/**
 * 
 * @author franton
 *
 */
public class Mage extends Hero {
	public Mage(int x, int y, Image sprite, int speed, Direction direction, Animation[] animations, Team team) {
		super(x, y, sprite, speed, direction, animations, team);
	}
	private Fireball lastFireball;
	@Override
	public Explosive primaryAbility() {
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
			lastFireball = new Fireball(getX(), getY(), new Image("/data/image/SnowballDown.png"), 8, animations, getDirection(), this);
			addExplosive(lastFireball);
		} catch (SlickException e) {
			
			e.printStackTrace();
		}
		
		//TODO remove bombcount?
		
		
		return lastFireball;
		
	}

	@Override
	public void secondaryAbility() {
		if (lastFireball != null){
			removeExplosive(lastFireball); //sluta rita ut
			lastFireball.destroy();
		}
	}

}
