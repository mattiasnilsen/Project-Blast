package projectblast.model.entity.hero;

import java.util.ArrayList;
import java.util.List;

import projectblast.model.BlastModel;
import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.attribute.Team;
import projectblast.model.attribute.powerup.AmmoPowerUp;
import projectblast.model.attribute.powerup.RangePowerUp;
import projectblast.model.attribute.powerup.SpeedPowerUp;
import projectblast.model.core.ICore;
import projectblast.model.core.ShockwaveCore;
import projectblast.model.entity.Id;
import projectblast.model.entity.explosive.Explosive;
import projectblast.model.entity.explosive.Fist;
import projectblast.model.helper.Constants;


public class Brute extends Hero {

	public Brute(Position position, Direction direction, Team team) {
		super(position, direction, team);
		setName(Id.BRUTE);
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
		}else{
			setAmmo(getAmmo()-1);
			Position position = new Position(getX() + getDirection().getX() * Constants.TILE_SIZE, getY() + getDirection().getY() * Constants.TILE_SIZE);
			position = snapPosition(position);
			Fist fist = new Fist(position, Constants.FIST_SPEED, getDirection(), this);
			addExplosive(fist);
			return fist;
		}
	}

	@Override
	public ICore secondaryAbility() {
		List<Direction> tmp = new ArrayList<Direction>();
		tmp.add(getDirection());
		return new ShockwaveCore( Constants.FIST_TIME, snapPosition(new Position(getX() + getDirection().getX()/2 - 1,
						getY() + (getDirection().getY()/2 - 1))), tmp );
	}

}
