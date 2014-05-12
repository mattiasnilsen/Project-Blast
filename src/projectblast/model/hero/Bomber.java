package projectblast.model.hero;


import projectblast.model.BlastModel;
import projectblast.model.ICore;
import projectblast.model.Id;
import projectblast.model.Position;
import projectblast.model.Team;
import projectblast.model.explosive.Bomb;
import projectblast.model.explosive.Explosive;
import projectblast.model.powerups.*;


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
			Bomb bomb = new Bomb(new Position(BlastModel.snapToGrid(getX()), BlastModel.snapToGrid(getY())), 0, Direction.NORTH, this);
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
