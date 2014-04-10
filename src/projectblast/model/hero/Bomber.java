package projectblast.model.hero;

import projectblast.model.Movable;
import projectblast.model.Position;
import projectblast.model.Team;
import projectblast.model.Movable.Direction;
import projectblast.model.explosive.Bomb;
import projectblast.model.explosive.Explosive;




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
