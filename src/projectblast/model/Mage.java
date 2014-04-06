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
		setName("Mage");
	}

	@Override
	public Explosive primaryAbility() {
		

		//TODO remove bombcount?
		
		
		return new Fireball(snapXToGrid(getX()) + getDirection().getX()*32, snapYToGrid(getY()) + getDirection().getY()*32, null, 4, null, getDirection(), this);
		
	}

	@Override
	public void secondaryAbility() {
		
	}

}
