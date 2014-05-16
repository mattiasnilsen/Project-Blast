package projectblast.model.powerup;

import projectblast.model.entity.hero.Hero;
import projectblast.model.helper.Constants;

/**
 * A powerUp which changes the Heroes ammunition.
 * @author franton
 *
 */
public class AmmoPowerUp implements IPowerUp {
	
	@Override
	public void apply(Hero hero) {
		hero.setAmmo(hero.getAmmo() + Constants.AMMO_POWERUP_MODIFIER);
		
	}

	@Override
	public void reverse(Hero hero) {
		hero.setAmmo(hero.getAmmo() - Constants.AMMO_POWERUP_MODIFIER);
		
	}
	
	@Override
	public String toString(){
		return "Ammo";
	}
	
}
