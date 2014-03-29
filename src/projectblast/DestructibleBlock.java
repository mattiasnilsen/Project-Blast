package projectblast;

import org.newdawn.slick.Image;
/**
 *
 * @author Alex Tao
 */
public class DestructibleBlock extends Block implements Destructible, Movable{

	private Image destroyedSprite; //Create a sprite for a destroyed block.
	
	public DestructibleBlock(int x, int y, Image sprite) {
		super(x, y, sprite);

	}

	@Override
	public void destroy() {
	this.setSprite(destroyedSprite);
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

}
