package projectblast.model.explosive;

import projectblast.model.Entity;
import projectblast.model.Id;
import projectblast.model.Position;
import projectblast.model.hero.Hero;

public class Fist extends Explosive {

	public Fist(Position position, int speed, Direction direction, Hero owner) {
		super(position, speed, direction, owner);
		setName(Id.FIST);
		setLife(20);
	}
	
	@Override
	public boolean allowPassage(Entity entity){
		return true;
	}
	
	public void update(){
		setLife(getLife()- 1);
		if(getLife()==0){
			destroy();
		}
	}

}
