package projectblast.model;

/**
 *
 * @author Alex Tao
 */
public class DestructibleBlock extends Block implements Destructible, Movable{
	private boolean isDestroyed;

	public DestructibleBlock(Position position) {
		super(position);

		setName(Id.DESTRUCTIBLEBLOCK);
	}

	@Override
	public void destroy() {
		isDestroyed = true;
	}

	@Override
	public void move(int dx, int dy) {
	while(true){
	this.setX(getX()+dx);
	this.setY(getY()+dy);	
	}
	}

	@Override
	public void place(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(Direction direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startMove(Direction direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopMove() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void startMove() {
        // TODO Auto-generated method stub
        
    }

	@Override
	public void stopMove(Direction direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDestroyed() {
		return isDestroyed;
	}

	@Override
	public boolean allowPassage(Entity entity) {
		return false;
	}

	@Override
	public boolean isMovable() {
		return true;
	}

	

}
