package projectblast.model;

import java.util.ArrayList;
import java.util.List;
import projectblast.model.Movable.Direction;

public class ExplosionCore extends Core {
	private int power;
	
	private int currentDir;
	private int distance;
	
	private boolean stopNextStep;
	
	private List<Direction> directionList;
	
	
	public ExplosionCore(){
		this(0,new Position(0,0),0,new ArrayList<Direction>());
	}
	
	public ExplosionCore(int life, Position startPos, int power, List<Direction> directionList){
		super(life, startPos);
		this.power = power;
		currentDir = -1;
		distance = 1;
		stopNextStep = false;
		this.directionList = directionList;
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
			stopNextStep = true; //Place an object on the current position but stop after that.
		} else if(intersectingEntity instanceof Block) {
			if(currentDir == directionList.size() - 1) { //If we have already looped through all directions then we are created.
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
		if(currentDir == -1) { //Special case to make sure an explosion is placed at starting position.
			return new Position(getStartingPosition());
	/*	} else if(currentDir == directionList.size()-1 && (distance*directionList.get(currentDir).getX() == power*directionList.get(currentDir).getX() || distance*directionList.get(currentDir).getY() == power*directionList.get(currentDir).getY())){
			//Should return null.
			return new Position(getStartingPosition());*/
		} else {
		
			int x = getStartingPosition().getX() + distance * directionList.get(currentDir).getX() * Constants.TILE_SIZE;
			int y = getStartingPosition().getY() + distance * directionList.get(currentDir).getY() * Constants.TILE_SIZE;

			return new Position(x, y);
		}
	}
}
