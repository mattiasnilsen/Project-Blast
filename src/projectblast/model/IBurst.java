package projectblast.model;

import projectblast.model.hero.Hero;

public interface IBurst {
	/**
	 * Update this part
	 */
	public void update();
	
	/**
	 * Is this part dead?
	 * @return true if it's dead
	 */
	public boolean isDead();
	
	/**
	 * Affect the Hero with an effect if it touches this part
	 * @param h - Hero
	 */
	public void touchEffect(Hero h);
}
