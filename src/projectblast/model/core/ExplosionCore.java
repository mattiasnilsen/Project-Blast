package projectblast.model.core;

import java.util.ArrayList;
import java.util.List;

import projectblast.model.Direction;
import projectblast.model.entity.Block;
import projectblast.model.entity.DestructibleBlock;
import projectblast.model.entity.Entity;
import projectblast.model.entity.Tower;
import projectblast.model.entity.hazard.Explosion;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Position;

public class ExplosionCore extends Core {
	private int power;
	
	private int currentDir;
	private int distance;
	
	private boolean stopNextStep;
	
	
	
	public ExplosionCore(){
		this(0,new Position(0,0),0,new ArrayList<Direction>());
	}
	
	public ExplosionCore(int life, Position startPos, int power, List<Direction> directionList){
		super(life, startPos, directionList);
		this.power = power;
		currentDir = -1;
		distance = 0;
		stopNextStep = false;
	}
	
	public int getCurrentDir(){
		return currentDir;
	}
	public int getDistance(){
		return distance;
	}
	@Override
	public void create() {
		addPart(new Explosion(new Position(getNextPosition().getX(), getNextPosition().getY())));
		
		if(currentDir == -1) { //Special case to handle starting position.
			currentDir++;
		} else {
			distance++;
		}
		
		if((distance == power || stopNextStep) && currentDir < getDirectionList().size() - 1) {
			stopNextStep = false;
			currentDir++;
			distance = 0;
		}
	}

	@Override
	public boolean step(Entity intersectingEntity) {
		if(currentDir >= getDirectionList().size() - 1 && power == distance || stopNextStep) {
			setCreated(true);
			return false;
		} 
		
		if(intersectingEntity instanceof DestructibleBlock || intersectingEntity instanceof Tower) {
			stopNextStep = true; //Place an object on the current position but stop after that.
		} else if(intersectingEntity instanceof Block) {
			if(currentDir == getDirectionList().size() - 1) { //If we have already looped through all directions then we are created.
				setCreated(true);
			} else {
				currentDir++;
				distance = 0;
			}
			return false;
		}
		
		return true;
	}

	@Override
	public Position getNextPosition() {
		if(currentDir == -1) { //Special case to make sure an explosion is placed at starting position.
			return new Position(getStartingPosition());
		} else {
		
			int x = getStartingPosition().getX() + (distance + 1) * getDirectionList().get(currentDir).getX() * Constants.TILE_SIZE;
			int y = getStartingPosition().getY() + (distance + 1) * getDirectionList().get(currentDir).getY() * Constants.TILE_SIZE;

			return new Position(x, y);
		}
	}
}
