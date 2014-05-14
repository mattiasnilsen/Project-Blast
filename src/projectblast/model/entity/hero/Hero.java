package projectblast.model.entity.hero;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Destructible;
import projectblast.model.Direction;
import projectblast.model.Team;
import projectblast.model.core.ICore;
import projectblast.model.entity.MovableEntity;
import projectblast.model.entity.explosive.Explosive;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Position;
import projectblast.model.powerup.IPowerUp;


public abstract class Hero extends MovableEntity implements Destructible{
	
	private int bombPower;
	private int bombCount;
	private int mana;
	
	private int deathCount;

	private Team team;
	
	private int respawnTime;
	private boolean isRespawning;
	private Position startPos;
	
	private List<IPowerUp> powerUps = new ArrayList<IPowerUp>();
	private List<Explosive> explosives = new ArrayList<Explosive>();

	
	
    public Hero(Position position, Direction direction,  Team team) {
        super(position, Constants.HERO_START_SPEED, direction, new Rectangle(position.getX(), position.getY(), 32, 32));
        bombPower = 0;
        bombCount = 0;
        mana      = 100;
        setDeathCount(0);
        this.team = team;
        this.startPos = new Position(position.getX(), position.getY());
        addInitialPowerUps();
        
    }
    
    protected abstract void addInitialPowerUps();

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
    	setDeathCount(getDeathCount() + 1);
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
    
    public void addExplosive(Explosive e){
    	explosives.add(e);
    }
    
    public List<Explosive> getExplosives(){
    	return explosives;
    }
    
    public boolean isRespawning(){
    	return isRespawning;
    }
    
    public Position getStartPosition(){
		 return startPos;
    }
	
	public void setStartPosition(Position startPos){
		this.startPos = startPos;
	}
	
	public void addPowerUp(IPowerUp powerUp) {
		reverseAllPowerUps();
		powerUps.add(powerUp);
		applyAllPowerUps();
	}
	
	private void reverseAllPowerUps() {
		for(IPowerUp powerUp : powerUps) {
			powerUp.reverse(this);
		}
	}
	
	private void applyAllPowerUps() {
		for(IPowerUp powerUp : powerUps) {
			powerUp.apply(this);
		}
	}
	
	public void update(){
		super.update();
		Iterator<Explosive> iter = explosives.iterator();
		while(iter.hasNext()){
			if(iter.next().isDestroyed()){
				iter.remove();
				setAmmo(getAmmo()+1);
			}
		}
		
	}
	
	public boolean hasEnoughMana(int i){
		return mana >= i;
	}
	
	public int getMana(){
		return mana;
	}
	
	public void increaseMana(int amount){
		if (amount > 0){
			mana = Math.min(mana + amount,100);
		} else {
			mana = Math.max(mana + amount,0);
		}
		
	}
	
	public void decreaseMana(int amount){
		increaseMana(amount * -1);
	}
	
	public abstract Explosive primaryAbility();
		
	public abstract ICore secondaryAbility();

	public int getDeathCount() {
		return deathCount;
	}

	public void setDeathCount(int deathCount) {
		this.deathCount = deathCount;
	}
}