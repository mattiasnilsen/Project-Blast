package projectblast.model.entity.hero;

import java.util.ArrayList;
import java.util.List;

import projectblast.model.BlastModel;
import projectblast.model.Direction;
import projectblast.model.Team;
import projectblast.model.core.ICore;
import projectblast.model.core.ShockwaveCore;
import projectblast.model.entity.explosive.Explosive;
import projectblast.model.entity.explosive.Fist;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Id;
import projectblast.model.helper.Position;
import projectblast.model.powerup.AmmoPowerUp;
import projectblast.model.powerup.RangePowerUp;
import projectblast.model.powerup.SpeedPowerUp;


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
			Fist fist = new Fist(new Position(BlastModel.snapToGrid(getX()) + getDirection().getX() * Constants.TILE_SIZE, BlastModel.snapToGrid(getY()) + getDirection().getY() * Constants.TILE_SIZE), 0, getDirection(), this);
			addExplosive(fist);
			return fist;
		}
	}

	@Override
	public ICore secondaryAbility() {
		List<Direction> tmp = new ArrayList<Direction>();
		tmp.add(getDirection());
		return new ShockwaveCore(20, new Position(BlastModel.snapToGrid(getX() + (getDirection().getX()/2 - 1)),
				BlastModel.snapToGrid(getY() + (getDirection().getY()/2 - 1))), tmp);
	}

}
