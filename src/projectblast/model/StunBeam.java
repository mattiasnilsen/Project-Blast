package projectblast.model;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Movable.Direction;

public class StunBeam implements HazardMaker{
	private List<Paralyzer> parts;
	private int lifetime;
	private Direction direction;
	private boolean isCreated = false;
	private Position startPos;
	private int dist = 1;
	
	public StunBeam(int life, Direction dir, Position startPos){
		System.out.println("Creating stunbeam");
		this.parts = new ArrayList<Paralyzer>();
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
	
	public List<Paralyzer> getParts(){
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
		startPos.setX(snapToGrid(startPos.getX()));
		startPos.setY(snapToGrid(startPos.getY()));

		Rectangle check = new Rectangle(startPos.getX()+2, startPos.getY()+2, Constants.TILE_SIZE-4,Constants.TILE_SIZE-4);
		
		check.setX(startPos.getX() + direction.getX() * dist * Constants.TILE_SIZE);
		check.setY(startPos.getY() + direction.getY() * dist * Constants.TILE_SIZE);
		
		parts.add(new Paralyzer(new Position(startPos.getX() + direction.getX() * dist * Constants.TILE_SIZE,startPos.getY() + direction.getY() * dist * Constants.TILE_SIZE)));
		dist++;	
		

		
	}
	
	public Position getNextPosition(){
		return new Position(startPos.getX() + direction.getX() * dist * Constants.TILE_SIZE, startPos.getY() + direction.getY() * dist * Constants.TILE_SIZE);
	}
	
	private int snapToGrid(int i){
		return (int)Math.round(i/Constants.TILE_SIZE)*Constants.TILE_SIZE;
		
	}
}
