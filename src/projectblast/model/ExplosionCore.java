package projectblast.model;

import java.awt.Rectangle;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import projectblast.model.Movable.Direction;

public class ExplosionCore extends Core {
	private int power;
	
	private int currentDir;
	private int distance;
	
	private boolean stopNextStep;
	
	private List<Direction> directionList;
	
	
	public ExplosionCore(int life, Position startPos, int power, List<Direction> directionList){
		super(life, startPos);
		this.power = power;
		currentDir = -1;
		distance = 1;
		stopNextStep = false;
		this.directionList = directionList;
	}
	
	@Override
	public void create() {
		addPart(new Explosion(new Position(getNextPosition().getX(), getNextPosition().getY())));
		
		if(currentDir == -1) {
			currentDir++;
		} else {
			distance++;
		}
		
		if(distance == power || stopNextStep) {
			if(currentDir < directionList.size() - 1) {
				stopNextStep = false;
				currentDir++;
				distance = 1;
			}
		}
		
		if((distance == power || stopNextStep) && currentDir < directionList.size() - 1) {
			stopNextStep = false;
			currentDir++;
			distance = 1;
		}
	}

	@Override
	public boolean step(Entity intersectingEntity) {
		if(currentDir >= directionList.size() - 1 && power == distance || stopNextStep) {
			setCreated(true);
			return false;
		} 
		
		if(intersectingEntity instanceof DestructibleBlock || intersectingEntity instanceof Tower) {
			stopNextStep = true;
		} else if(intersectingEntity instanceof Block) {
			if(currentDir == directionList.size() - 1) {
				setCreated(true);
			} else {
				currentDir++;
				distance = 1;
			}
			return false;
		}
		
		return true;
	}

	@Override
	public Position getNextPosition() {
		if(currentDir == -1) {
			return new Position(getStartingPosition());
		} else {
		
			int x = getStartingPosition().getX() + distance * directionList.get(currentDir).getX() * Constants.TILE_SIZE;
			int y = getStartingPosition().getY() + distance * directionList.get(currentDir).getY() * Constants.TILE_SIZE;

			return new Position(x, y);
		}
	}
}
