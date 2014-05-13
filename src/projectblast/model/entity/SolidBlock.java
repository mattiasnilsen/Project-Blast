package projectblast.model.entity;

import projectblast.model.Id;
import projectblast.model.Position;
import projectblast.model.entity.explosive.Fireball;

/**
 *
 * @author Alex Tao
 */
public class SolidBlock extends Block{
	
	public SolidBlock(Position position) {
		super(position);
		setName(Id.SOLIDBLOCK);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean allowPassage(Entity entity) {
		/*if(entity instanceof Fireball){
			return true;
		}*/
		return false;
	}

	@Override
	public void collide(Entity entity) {
		// TODO Auto-generated method stub
		
	}

}
