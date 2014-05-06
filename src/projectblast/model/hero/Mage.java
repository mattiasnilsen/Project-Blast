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
	public Mage(Position position,  int speed, Direction direction, Team team) {
		super(position, speed, direction,  team);
		setName(Id.MAGE);
		
	}

	@Override
	public Explosive primaryAbility() {
		if(getAmmo()<= 0){
			return null;
		}else{
			setAmmo(getAmmo()-1);
			Fireball fireball = new Fireball(new Position(BlastModel.snapToGrid(getX()), BlastModel.snapToGrid(getY())),  4,  getDirection(), this);
			addExplosive(fireball);
			return fireball;
		}
	}

	@Override
	public ICore secondaryAbility() {
		return new ParalyzerCore(180, new Position(BlastModel.snapToGrid(getX()), BlastModel.snapToGrid(getY())), getDirection());
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
