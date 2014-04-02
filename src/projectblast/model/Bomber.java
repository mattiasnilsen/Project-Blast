package projectblast.model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * 
 * @author Mattias Nilsen
 *
 */
public class Bomber extends Hero {

    public Bomber(int x, int y, Image sprite, int speed, Direction direction, Animation[] animations, Team team) {
        super(x, y, sprite, speed, direction, animations, team);
    }

    @Override
    public Explosive primaryAbility() {
    	Animation[] a = new Animation[1];
    	Bomb b = null;
    	a[0] = new Animation();
        try {
        	b = new Bomb(getX() ,getY() ,new Image("data/image/Bomb.png") ,0 ,Direction.NORTH,a ,this);
		} catch (SlickException e) {
			e.printStackTrace();
		}
        return b;

    }

    @Override
    public void secondaryAbility() {
        // TODO Auto-generated method stub

    }

}
