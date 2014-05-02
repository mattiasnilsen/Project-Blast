package projectblast.model.explosive;

import projectblast.model.*;
import projectblast.model.hero.*;



public class Bomb extends Explosive {
	
	
	public Bomb(Position position, int speed, Direction direction,
			 Hero owner) {
		super(position, speed, direction, owner);
		setName(Id.BOMB);
		setLife(120);
	}


	
	
	public void update(){
		setLife(getLife()- 1);
		if(getLife()==0){
			
			destroy();
		}
	}
	
	public boolean allowPassage(Entity entity){
		
		if(getOwner() == entity && getOwner().getCollisionBox().intersects(getCollisionBox())){
			return true;
		}
		
		return false;
	}

}
