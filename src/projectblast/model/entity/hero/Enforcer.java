package projectblast.model.entity.hero;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.attribute.Team;
import projectblast.model.attribute.powerup.AmmoPowerUp;
import projectblast.model.attribute.powerup.RangePowerUp;
import projectblast.model.attribute.powerup.SpeedPowerUp;
import projectblast.model.core.ICore;
import projectblast.model.entity.Id;
import projectblast.model.entity.explosive.Bomb;
import projectblast.model.entity.explosive.Drone;
import projectblast.model.entity.explosive.Explosive;
import projectblast.model.helper.Constants;

public class Enforcer extends Hero{

	public Enforcer(Position position, Direction direction, Team team) {
		super(position, direction, team);
		setName(Id.ENFORCER);
	}

	@Override
	protected void addInitialPowerUps() {
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new RangePowerUp());
		addPowerUp(new AmmoPowerUp());
	}

	@Override
	public Explosive primaryAbility() {
		if(getAmmo() <= 0){
		return null;
		} else {
			setAmmo(getAmmo()-1);
			Drone drone = new Drone(snapPosition(getPosition()), Constants.BOMB_SPEED, getDirection(), this);
			addExplosive(drone);
			return drone;
		}
		
	}

	@Override
	public ICore secondaryAbility() {
		//TODO Implement secondary
		return null;
	}

}
