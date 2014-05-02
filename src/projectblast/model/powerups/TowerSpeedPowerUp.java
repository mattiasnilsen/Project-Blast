package projectblast.model.powerups;

import projectblast.model.hero.Hero;

/**
 * The speed powerup a character can get from a tower.
 * @author Mattias
 *
 */
public class TowerSpeedPowerUp implements IPowerUp {

	private final int SPEED_MODIFIER = 2;
	
	@Override
	public void apply(Hero hero) {
		hero.setSpeed(hero.getSpeed() + SPEED_MODIFIER);
	}

	@Override
	public void reverse(Hero hero) {
		hero.setSpeed(hero.getSpeed() - SPEED_MODIFIER);
	}
}
