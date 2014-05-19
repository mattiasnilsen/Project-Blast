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
		int i = hero.getSpeed() + Constants.SPEED_POWERUP_MODIFIER;
		if (i < Constants.SPEED_MAX){
			hero.setSpeed(i);
		}
	}

	@Override
	public void reverse(Hero hero) {
		int i = hero.getSpeed() - Constants.SPEED_POWERUP_MODIFIER;
		if (i > 0){
			hero.setSpeed(i);
		}
	}
	
	@Override
	public String toString(){
		return "Speed";
	}
}
