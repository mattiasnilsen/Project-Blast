package projectblast.model.powerup;

import projectblast.model.entity.hero.Hero;
import projectblast.model.helper.Constants;
/**
 * A powerUp which changes the Heroes range.
 * @author franton
 *
 */
public class RangePowerUp implements IPowerUp {
	
	@Override
	public void apply(Hero hero) {
		hero.setPower(hero.getPower() + Constants.RANGE_POWERUP_MODIFIER);
		
	}

	@Override
	public void reverse(Hero hero) {
		hero.setPower(hero.getPower() - Constants.RANGE_POWERUP_MODIFIER);
		
	}
	
	@Override
	public String toString(){
		return "Range";
	}
	
}
