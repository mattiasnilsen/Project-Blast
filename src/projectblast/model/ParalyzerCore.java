package projectblast.model;

import projectblast.model.Movable.Direction;

public class ParalyzerCore extends Core {
	private Direction direction;
	private int dist = 1;
	
	public ParalyzerCore(int life, Position startPos, Direction dir){
		super(life, startPos);
		System.out.println("Creating stunbeam");
		this.direction = dir;
	}
	
	public boolean step(Entity entity){
		if(entity instanceof Block){
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
		return new Position(getStartingPosition().getX() + direction.getX() * dist * Constants.TILE_SIZE, getStartingPosition().getY() + direction.getY() * dist * Constants.TILE_SIZE);
	}
	
}
