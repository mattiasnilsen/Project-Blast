package projectblast.model.core;

import java.util.List;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.entity.Block;
import projectblast.model.entity.DestructibleBlock;
import projectblast.model.entity.Entity;
import projectblast.model.entity.hazard.Paralyzer;
import projectblast.model.helper.Constants;

public class ParalyzerCore extends Core {

	private int dist = 1;
	
	public ParalyzerCore(int life, Position startPos, List<Direction> dir){
		super(life, startPos, dir );
		System.out.println("Creating stunbeam");
	}
	
	public boolean step(Entity entity){
		if(entity instanceof Block || entity instanceof DestructibleBlock){
			setCreated(true);
			return false;
		}
		return true;
	}
	
	public void create(){
		
		int x = getNextPosition().getX();
		int y = getNextPosition().getY();

		addPart(new Paralyzer(new Position(x, y)));
		dist++;	
		
	}
	
	public Position getNextPosition(){
		return new Position(getStartingPosition().getX() + getDirectionList().get(0).getX() * dist * Constants.TILE_SIZE, getStartingPosition().getY() + getDirectionList().get(0).getY() * dist * Constants.TILE_SIZE);
	}
	
}
