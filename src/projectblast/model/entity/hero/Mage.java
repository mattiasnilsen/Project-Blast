package projectblast.model.entity.hero;

import java.util.ArrayList;
import java.util.List;

import projectblast.model.*;
import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.attribute.Team;
import projectblast.model.attribute.powerup.AmmoPowerUp;
import projectblast.model.attribute.powerup.RangePowerUp;
import projectblast.model.attribute.powerup.SpeedPowerUp;
import projectblast.model.core.ICore;
import projectblast.model.core.ParalyzerCore;
import projectblast.model.entity.Id;
import projectblast.model.entity.explosive.Explosive;
import projectblast.model.entity.explosive.Fireball;
import projectblast.model.helper.Constants;

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
		Fireball fireball = new Fireball(snapPosition(getPosition()),  getSpeed() + 1,  getDirection(), this);
		if(getAmmo()<= 0 || !BlastModel.isFree(fireball, fireball.getDirection(), fireball.getSpeed())){
			return null;
		}else{
			setAmmo(getAmmo()-1);
			addExplosive(fireball);
			return fireball;
		}
	}

	@Override
	public ICore secondaryAbility() {
		List<Direction> directionList = new ArrayList<Direction>();
		directionList.add(getDirection());
		if (hasEnoughMana(Constants.PARALYZER_MANA_COST)){
			decreaseMana(Constants.PARALYZER_MANA_COST);
			return new ParalyzerCore(Constants.PARALYZER_TIME, snapPosition(getPosition()), directionList);
		} else {
			return null;
		}
		
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
}
