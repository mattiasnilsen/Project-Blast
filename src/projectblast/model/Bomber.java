package projectblast.model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

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
    public void primaryAbility() {
        // TODO Auto-generated method stub

    }

    @Override
    public void secondaryAbility() {
        // TODO Auto-generated method stub

    }

}