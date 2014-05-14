package projectblast.model.core;

import java.util.List;

import projectblast.model.Direction;
import projectblast.model.entity.DestructibleBlock;
import projectblast.model.entity.Entity;
import projectblast.model.entity.MovableEntity;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Position;

public class ShockwaveCore extends Core{
	

	private int distance;

	public ShockwaveCore(int life, Position startPos, List<Direction> directionList){
		super(life, startPos, directionList);
		distance = 1;
	}
	
	@Override
	public void create() {
		setCreated(true);
	}

	@Override
	public boolean step(Entity intersectingEntity) {
		if(intersectingEntity instanceof DestructibleBlock){
			MovableEntity e = (MovableEntity) intersectingEntity;
			distance = e.getSpeed();
			e.startMove(getDirectionList().get(0));
			
		}
		return true;
	}

	@Override
	public Position getNextPosition() {
		int x = getStartingPosition().getX() + distance * getDirectionList().get(0).getX() * Constants.TILE_SIZE;
		int y = getStartingPosition().getY() + distance * getDirectionList().get(0).getY() * Constants.TILE_SIZE;
		return new Position(x, y);
		
	}
}

