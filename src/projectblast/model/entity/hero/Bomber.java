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
import projectblast.model.entity.explosive.Explosive;
import projectblast.model.helper.Constants;

/**
 * 
 * @author Mattias Nilsen
 *	
 */
public class Bomber extends Hero {

    public Bomber(Position position, Direction direction,  Team team) {
        super(position, direction, team);
        setName(Id.BOMBER);
    }

    @Override
    public Explosive primaryAbility() {
    	if(getAmmo()<= 0){
			return null;
		}else{
			setAmmo(getAmmo()-1);
			Bomb bomb = new Bomb(snapPosition(getPosition()), Constants.BOMB_SPEED, getDirection(), this);
			addExplosive(bomb);
			return bomb;
		}
    }
    /**
     * Bomber secondaryAbility destroys all of his bombs,
     * making them explode sooner than expected!
     */
    @Override
    public ICore secondaryAbility() {
    	for(Explosive e: getExplosives()){
			e.destroy();
		}
    	return null; //No ICore needed
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
	}
}
