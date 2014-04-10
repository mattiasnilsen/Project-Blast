package projectblast.model;


import org.newdawn.slick.geom.Rectangle;

public abstract class Hero extends MovableEntity{
	
	private int bombPower;
	private int bombCount;
	private Direction direction;

	private Team team;
	
    public Hero(Position position, int speed, Direction direction,  Team team) {
        super(position, speed, direction, new Rectangle(position.getX(), position.getY(), 24, 30));
        bombPower = 1;
        bombCount = 1;
        this.team = team;
        this.direction = direction;
    }
    
    

    @Override
    public void update() {
    	
    }
    
    public int snapToGrid(int coordinate){
    	return (int)Math.round(coordinate/(double)Constants.TILE_SIZE)*Constants.TILE_SIZE;
    }
    
    public int getPower(){
    	return bombPower;
    }
    
    public int getBombCount(){
    	return bombCount;
    }
    	

    
    public Team getTeam(){
    	return team;
    }
	public abstract Explosive primaryAbility();
		
	public abstract void secondaryAbility();
}
