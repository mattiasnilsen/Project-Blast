package projectblast.model.entity.explosive;



import java.util.List;

import projectblast.model.Direction;
import projectblast.model.entity.Entity;
import projectblast.model.entity.Tower;
import projectblast.model.entity.hazard.Paralyzer;
import projectblast.model.entity.hero.Hero;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Position;

public class Drone extends Explosive{
	int range;
	Entity target;
	public Drone(Position position, int speed, Direction direction, Hero owner) {
		super(position, speed, direction, owner);
		setLife(300);
		range = 5;
		target = null;
		
	}
	//TODO fix bad name
	public Boolean directionToTargetHasChanged(Direction dir){
		if(getDirection() != dir){
			return true;
		}
		return false;
	}
	
	public void findTarget(List<Hero> heroes, List<Tower> towers){
		if(target == null){
		outerloop:	
		for(Hero hero:heroes){
			if(targetIsInRange(hero)){
				target = hero;
				break outerloop;
			}
		}
		outerloop2:
		for(Tower tower:towers){
			if(targetIsInRange(tower)){
				target = tower;
				break outerloop2;
			}
		}
		}
	}
	
	public void setTarget(Entity target){
		this.target=target;
	}
	
	public Entity getTarget(){
		return target;
	}
	public void moveDirection(List<Hero> heroes, List<Tower> towers){
		if(getDirection() != null){
		startMove();
			if(target instanceof Hero || target instanceof Tower && directionToTargetHasChanged(getDirectionToTarget(target))){
				stopMove();
				setDirection(getDirectionToTarget(target));
				startMove();
			}
		}
	}
	

	
	public Direction getDirectionToTarget(Entity target){
	
		if(getX() < target.getX()){
			return Direction.EAST;
			
		}else if(getX() > target.getX()){
			return Direction.WEST;
			
		}else if(getY() < target.getY()){
			return Direction.NORTH;
			
		}else if(getY() < target.getY()){
			return Direction.SOUTH;
		}
		return null;
	}
	
	public Boolean targetIsInRange(Entity target){
		if(target != getOwner()){
			if(Math.abs(target.getX()-getX()) <= range* Constants.TILE_SIZE){
				return true;
			}else if(Math.abs(target.getY()-getY()) <= range*Constants.TILE_SIZE){
				return true;
			}
		}
		return false;
	}
	
	public void update(){
		setLife(getLife()-1);
		if(getLife() == 0){
			destroy();
		}
		
	}
	
	@Override
	public void collide(Entity entity) {
		if(entity != getOwner() && !(entity instanceof Paralyzer)) {
			this.destroy();
		}
	}
	
	public boolean allowPassage(Entity entity){
		if(entity == getOwner()){
			return true;
		}
		
		return false;
	}

}
