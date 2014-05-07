package projectblast.model;

import java.util.List;

import projectblast.model.Movable.Direction;

public class ShockwaveCore extends Core implements ICore {
	
	private Direction direction;
	private int distance;

	public ShockwaveCore(int life, Position startPos, Direction direction){
		super(life, startPos);
		this.direction = direction;
		distance = 1;
		
	}
	
	@Override
	public void create() {
		setCreated(true);
	}

	@Override
	public boolean step(Entity intersectingEntity) {
		System.out.println("ShockwaveCore step!");
		if(intersectingEntity instanceof DestructibleBlock){
			Movable e = (Movable) intersectingEntity;
			e.startMove(direction);
			
		}
		return true;
	}

	@Override
	public Position getNextPosition() {

		int x = getStartingPosition().getX() + distance * direction.getX() * Constants.TILE_SIZE;
		int y = getStartingPosition().getY() + distance * direction.getY() * Constants.TILE_SIZE;

		return new Position(x, y);
		
	}
}

