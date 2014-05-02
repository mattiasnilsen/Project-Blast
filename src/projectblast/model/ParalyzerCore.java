package projectblast.model;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Movable.Direction;

public class ParalyzerCore implements ICore{
	private List<IBurst> parts;
	private int lifetime;
	private Direction direction;
	private boolean isCreated = false;
	private Position startPos;
	private int dist = 1;
	
	public ParalyzerCore(int life, Direction dir, Position startPos){
		System.out.println("Creating stunbeam");
		this.parts = new ArrayList<IBurst>();
		this.lifetime = life;
		this.direction = dir;
		this.startPos = startPos;
	}
	
	public void tick(){
		lifetime--;
	}
	
	public boolean isDead(){
		return lifetime < 0;
	}
	
	public boolean isCreated(){
		return isCreated;
	}
	
	public List<IBurst> getParts(){
		return parts;
	}
	
	public boolean step(Entity entity){
		if(entity instanceof Block){
			isCreated = true;
			return false;
		}
		return true;
	}
	
	public void create(){
		
		int x = getNextPosition().getX();
		int y = getNextPosition().getY();

		parts.add(new Paralyzer(new Position(x, y)));
		dist++;	
		
	}
	
	public Position getNextPosition(){
		return new Position(startPos.getX() + direction.getX() * dist * Constants.TILE_SIZE, startPos.getY() + direction.getY() * dist * Constants.TILE_SIZE);
	}
	
}
