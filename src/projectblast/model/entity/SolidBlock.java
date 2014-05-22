package projectblast.model.entity;

import projectblast.model.attribute.Position;

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
	}

}
