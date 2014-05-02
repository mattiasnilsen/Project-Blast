package projectblast.model.hero;



import projectblast.control.GameplayState;
import projectblast.model.*;
import projectblast.model.Movable.Direction;
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
			return new Fireball(new Position(snapToGrid(getX()), snapToGrid(getY())),  4,  getDirection(), this);
		}
	}

	@Override
	public ICore secondaryAbility() {
		return new ParalyzerCore(180, getDirection(), new Position(snapToGrid(getX()), snapToGrid(getY())));
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
