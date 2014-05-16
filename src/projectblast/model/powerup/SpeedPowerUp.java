package projectblast.model.powerup;

import projectblast.model.entity.hero.Hero;
import projectblast.model.helper.Constants;

/**
 * A powerUp which changes the Heroes speed.
 * @author Mattias
 *
 */
public class SpeedPowerUp implements IPowerUp {
	
	@Override
	public void apply(Hero hero) {
		hero.setSpeed(hero.getSpeed() + Constants.SPEED_POWERUP_MODIFIER);
	}

	@Override
	public void reverse(Hero hero) {
		hero.setSpeed(hero.getSpeed() - Constants.SPEED_POWERUP_MODIFIER);
	}
	
	@Override
	public String toString(){
		return "Speed";
	}
}
