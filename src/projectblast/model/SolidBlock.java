package projectblast.model;

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
		return false;
	}

}
