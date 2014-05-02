package projectblast.model.hero;


import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Constants;
import projectblast.model.Destructible;
import projectblast.model.ICore;
import projectblast.model.MovableEntity;
import projectblast.model.Position;
import projectblast.model.Team;
import projectblast.model.explosive.Explosive;
import projectblast.model.powerups.IPowerUp;

public abstract class Hero extends MovableEntity implements Destructible{
	
	private int bombPower;
	private int bombCount;
	private Direction direction;

	private Team team;
	
	private int respawnTime;
	private boolean isRespawning;
	private Position startPos;
	
	private List<IPowerUp> powerUps = new ArrayList<IPowerUp>();

	
	
    public Hero(Position position, int speed, Direction direction,  Team team) {
        super(position, speed, direction, new Rectangle(position.getX(), position.getY(), 32, 32));
        bombPower = 1;
        bombCount = 1;
        this.team = team;
        this.direction = direction;
        this.startPos = new Position(position.getX(), position.getY());
        
    }
    
    protected abstract void addInitialPowerUps();

    public int snapToGrid(int coordinate){
    	return (int)Math.round(coordinate/(double)Constants.TILE_SIZE)*Constants.TILE_SIZE;
    }
    
    public int getPower(){
    	return bombPower;
    }
    
    public void setPower(int power){
    	bombPower = power;
    }
    
    public int getAmmo(){
    	return bombCount;
    }
    
    public void setAmmo(int ammo){
    	bombCount = ammo;
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
	
	public void addPowerUp(IPowerUp powerUp) {
		removeAllPowerUps();
		powerUps.add(powerUp);
		addAllPowerUps();
	}
	
	public void removeAllPowerUps() {
		for(IPowerUp powerUp : powerUps) {
			powerUp.reverse(this);
		}
	}
	
	public void addAllPowerUps() {
		for(IPowerUp powerUp : powerUps) {
			powerUp.apply(this);
		}
	}
	
	public abstract Explosive primaryAbility();
		
	public abstract ICore secondaryAbility();
}
