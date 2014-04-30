package projectblast.model.hero;


import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Constants;
import projectblast.model.Destructible;
import projectblast.model.Entity;
import projectblast.model.HazardMaker;
import projectblast.model.Movable;
import projectblast.model.MovableEntity;
import projectblast.model.Position;
import projectblast.model.Team;
import projectblast.model.Movable.Direction;
import projectblast.model.explosive.Explosive;

public abstract class Hero extends MovableEntity implements Destructible{
	
	private int bombPower;
	private int bombCount;
	private Direction direction;

	private Team team;
	
	private int respawnTime;
	private boolean isRespawning;
	private Position startPos;

	
	
    public Hero(Position position, int speed, Direction direction,  Team team) {
        super(position, speed, direction, new Rectangle(position.getX(), position.getY(), 32, 32));
        bombPower = 1;
        bombCount = 1;
        this.team = team;
        this.direction = direction;
        this.startPos = new Position(position.getX(), position.getY());
        
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
    
    public void destroy(){
    	System.out.println(getTeam().getName() + " just lost a teammate!");
    	respawnTime = 480;
    	isRespawning = true;
    	place(startPos);
    }
    
    public boolean isDestroyed(){
    	if(respawnTime > 0){
    		//System.out.println("Hero respawning");
    		respawnTime--;
    	} else {
    		isRespawning = false;
    	}
    	return false; // a hero is never destroyed!
    }
    
    public boolean isRespawning(){
    	return isRespawning;
    }
    
    public Position getStartPosition(){
		 return startPos;
    }
	
	public void setStartPosition(Position startPosOne){
		this.startPos = startPosOne;
	}
	
	public abstract Explosive primaryAbility();
		
	public abstract HazardMaker secondaryAbility();
}
