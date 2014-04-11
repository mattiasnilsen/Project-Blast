package projectblast.model.hero;

import projectblast.model.Id;
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
        setName(Id.BOMBER);
    }

    @Override
    public Explosive primaryAbility() {
  
		
        return new Bomb(new Position(snapToGrid(getX()) + getDirection().getX()*64, snapToGrid(getY()) + getDirection().getY()*64)  ,0 ,Direction.NORTH ,this);

    }

    @Override
    public void secondaryAbility() {
        // TODO Auto-generated method stub

    }

}
