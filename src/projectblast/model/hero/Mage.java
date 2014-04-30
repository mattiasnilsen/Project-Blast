package projectblast.model.hero;



import projectblast.control.GameplayState;
import projectblast.model.Id;
import projectblast.model.Movable;
import projectblast.model.Position;
import projectblast.model.Team;
import projectblast.model.Movable.Direction;
import projectblast.model.explosive.Explosive;
import projectblast.model.explosive.Fireball;
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
		//TODO remove bombcount
		return new Fireball(new Position(snapToGrid(getX()), snapToGrid(getY())),  4,  getDirection(), this);
		
	}

	@Override
	public void secondaryAbility() {
		
	}

}
