package projectblast.model.hero;

import projectblast.model.*;
import projectblast.model.explosive.Explosive;
import projectblast.model.explosive.Fireball;
import projectblast.model.powerups.*;
/**
 * 
 * @author franton
 *
 */
public class Mage extends Hero {
	public Mage(Position position, Direction direction, Team team) {
		super(position, direction,  team);
		setName(Id.MAGE);
		
	}

	@Override
	public Explosive primaryAbility() {
		if(getAmmo()<= 0){
			return null;
		}else{
			setAmmo(getAmmo()-1);
			Fireball fireball = new Fireball(new Position(BlastModel.snapToGrid(getX()), BlastModel.snapToGrid(getY())),  4,  facingDirection, this);
			addExplosive(fireball);
			return fireball;
		}
	}

	@Override
	public ICore secondaryAbility() {
		return new ParalyzerCore(Constants.PARALYZER_TIME, new Position(BlastModel.snapToGrid(getX()), BlastModel.snapToGrid(getY())), facingDirection);
	}

	@Override
	protected void addInitialPowerUps() {
		
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new RangePowerUp());
		addPowerUp(new AmmoPowerUp());
		addPowerUp(new AmmoPowerUp());
		addPowerUp(new AmmoPowerUp());
	}
}
