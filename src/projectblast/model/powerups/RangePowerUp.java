package projectblast.model.powerups;

import projectblast.model.entity.hero.Hero;
/**
 * A powerUp which changes the Heroes range.
 * @author franton
 *
 */
public class RangePowerUp implements IPowerUp {
	
	private final int RANGE_MODIFIER = 1;
	
	@Override
	public void apply(Hero hero) {
		hero.setPower(hero.getPower() + RANGE_MODIFIER);
		
	}

	@Override
	public void reverse(Hero hero) {
		hero.setPower(hero.getPower() - RANGE_MODIFIER);
		
	}
	
}
