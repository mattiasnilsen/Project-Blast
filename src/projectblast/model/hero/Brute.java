package projectblast.model.hero;

import projectblast.model.BlastModel;
import projectblast.model.Constants;
import projectblast.model.Id;
import projectblast.model.Position;
import projectblast.model.Team;
import projectblast.model.Movable.Direction;
import projectblast.model.core.ICore;
import projectblast.model.core.ShockwaveCore;
import projectblast.model.explosive.Bomb;
import projectblast.model.explosive.Explosive;
import projectblast.model.explosive.Fist;
import projectblast.model.powerups.AmmoPowerUp;
import projectblast.model.powerups.RangePowerUp;
import projectblast.model.powerups.SpeedPowerUp;

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
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new RangePowerUp());
		addPowerUp(new AmmoPowerUp());
		
	}

	@Override
	public Explosive primaryAbility() {
		if(getAmmo()<= 0){
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
		
		return new ShockwaveCore(20, new Position(BlastModel.snapToGrid(getX()), BlastModel.snapToGrid(getY())), getDirection());
	}

}
