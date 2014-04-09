package projectblast.model;




/**
 * 
 * @author Mattias Nilsen
 *
 */
public class Bomber extends Hero {

    public Bomber(Position position,  int speed, Direction direction,  Team team) {
        super(position, speed, direction, team);
        setName("Bomber");
    }

    @Override
    public Explosive primaryAbility() {
  
		
        return new Bomb(new Position(getX() ,getY())  ,0 ,Direction.NORTH ,this);

    }

    @Override
    public void secondaryAbility() {
        // TODO Auto-generated method stub

    }

}
