package projectblast.model.attribute.powerup;

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
		int i = hero.getPower() + Constants.RANGE_POWERUP_MODIFIER;
		if (i < Constants.RANGE_MAX){
			hero.setPower(i);
		}
	}

	@Override
	public void reverse(Hero hero) {
		int i = hero.getPower() - Constants.RANGE_POWERUP_MODIFIER;
		if (i > 0){
			hero.setPower(i);
		}
	}
	
	@Override
	public String toString(){
		return "Range";
	}
	
}
