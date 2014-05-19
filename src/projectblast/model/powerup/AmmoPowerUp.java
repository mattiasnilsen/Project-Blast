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
		int i = hero.getAmmo() + Constants.AMMO_POWERUP_MODIFIER;
		if (i <= Constants.AMMO_MAX){
			hero.setAmmo(i);
		}
	}

	@Override
	public void reverse(Hero hero) {
		int i = hero.getAmmo() - Constants.AMMO_POWERUP_MODIFIER;
		if (i > 0){
			hero.setAmmo(i);
		}
	}
	
	@Override
	public String toString(){
		return "Ammo";
	}
	
}
