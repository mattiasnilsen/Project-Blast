package projectblast.model.hero;

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

    public Bomber(Position position,  int speed, Direction direction,  Team team) {
        super(position, speed, direction, team);
        setName(Id.BOMBER);
    }

    @Override
    public Explosive primaryAbility() {
        return new Bomb(new Position(snapToGrid(getX()) + getDirection().getX()*0, snapToGrid(getY()) + getDirection().getY()*0)  ,0 ,Direction.NORTH ,this);
    }

    @Override
    public ICore secondaryAbility() {
       	return null;
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
