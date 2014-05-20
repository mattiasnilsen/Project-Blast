package projectblast.model.entity.explosive;

import projectblast.model.*;
import projectblast.model.entity.Entity;
import projectblast.model.entity.hero.*;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Id;
import projectblast.model.helper.Position;



public class Bomb extends Explosive {
	
	
	public Bomb(Position position, int speed, Direction direction,
			 Hero owner) {
		super(position, speed, direction, owner);
		setName(Id.BOMB);
		setLife(Constants.BOMB_TIME);
		setPosition(BlastModel.snapToGrid(getPosition()));
	}


	public void update(){
		setLife(getLife()- 1);
		if(getLife()==0){
			destroy();
		}
	}
	
	public boolean allowPassage(Entity entity){
		if((getOwner() == entity) && (getOwner().getCollisionBox().intersects(getCollisionBox()))){
			return true;
		}
		return false;
	}

}
