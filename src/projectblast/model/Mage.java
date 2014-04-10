package projectblast.model;



import projectblast.control.GameplayState;
/**
 * 
 * @author franton
 *
 */
public class Mage extends Hero {
	public Mage(Position position,  int speed, Direction direction, Team team) {
		super(position, speed, direction,  team);
		setName("Mage");
	}

	@Override
	public Explosive primaryAbility() {
		

		//TODO remove bombcount?
		
		
		return new Fireball(new Position(snapToGrid(getX()) + getDirection().getX()*0, snapToGrid(getY()) + getDirection().getY()*0),  4,  getDirection(), this);
		
	}

	@Override
	public void secondaryAbility() {
		
	}

}
