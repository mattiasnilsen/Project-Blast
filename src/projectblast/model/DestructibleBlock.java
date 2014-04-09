package projectblast.model;

/**
 *
 * @author Alex Tao
 */
public class DestructibleBlock extends Block implements Destructible, Movable{


	
	public DestructibleBlock(Position position) {
		super(position);

		setName("DestructibleBlock");
	}

	@Override
	public Explosion destroy() {

	return null;
	//Tell parent to remove this block.
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

}
