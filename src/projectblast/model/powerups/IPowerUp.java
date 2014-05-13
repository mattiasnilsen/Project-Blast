package projectblast.model.powerups;

import projectblast.model.entity.hero.Hero;

/**
 * Interface for all powerups
 * @author Mattias
 *
 */
public interface IPowerUp {
	/**
	 * Applies the powerup effect on a hero.
	 * @param hero The hero to apply the effect on.
	 */
	public void apply(Hero hero);
	/**
	 * Applies the reverse effect of the powerup on a hero
	 * @param hero The hero to apply the effect on.
	 */
	public void reverse(Hero hero);
}
