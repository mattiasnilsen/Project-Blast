package projectblast.model.powerups;

import projectblast.model.entity.hero.Hero;

/**
 * A powerUp which changes the Heroes range.
 * @author franton
 *
 */
public class AmmoPowerUp implements IPowerUp {
	
	private final int AMMO_MODIFIER = 1;
	
	@Override
	public void apply(Hero hero) {
		hero.setAmmo(hero.getAmmo() + AMMO_MODIFIER);
		
	}

	@Override
	public void reverse(Hero hero) {
		hero.setAmmo(hero.getAmmo() - AMMO_MODIFIER);
		
	}
	
}
