package projectblast.model.core;

import projectblast.model.Movable;
import projectblast.model.Movable.Direction;
import projectblast.model.entity.DestructibleBlock;
import projectblast.model.entity.Entity;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Position;

public class ShockwaveCore extends Core{
	
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

