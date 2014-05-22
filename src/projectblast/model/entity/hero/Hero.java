package projectblast.model.entity.hero;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.attribute.Team;
import projectblast.model.attribute.Team.Side;
import projectblast.model.attribute.powerup.IPowerUp;
import projectblast.model.core.ICore;
import projectblast.model.entity.Destructible;
import projectblast.model.entity.MovableEntity;
import projectblast.model.entity.explosive.Explosive;
import projectblast.model.helper.Constants;


public abstract class Hero extends MovableEntity implements Destructible{
	
	private int bombPower;
	private int bombCount;
	private int mana;
	
	private int deathCount;

	private Team team;
	
	private int respawnTime;
	private Position spawnPoint;
	
	private List<IPowerUp> powerUps = new ArrayList<IPowerUp>();
	private List<Explosive> explosives = new ArrayList<Explosive>();

	
	
    public Hero(Position position, Direction direction,  Team team) {
        super(position, Constants.HERO_START_SPEED, direction, new Rectangle(position.getX(), position.getY(), 32, 32));
        bombPower = 0;
        bombCount = 0;
        mana      = Constants.HERO_MANA_MAX_VALUE;
        setDeathCount(0);
        this.team = team;
        this.spawnPoint = new Position(position.getX(), position.getY());
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
    
    public int getRespawnTime(){
    	return respawnTime;
    }
    
    public void destroy(){
    	if (respawnTime == 0){
    		System.out.println(getTeam().getName() + " just lost a teammate!");
        	setDeathCount(getDeathCount() + 1);
        	respawnTime = Constants.HERO_RESPAWN_TIME;
        	place(spawnPoint);
    	}
    }
    
    public boolean isDestroyed(){
    	return false; // a hero is never destroyed!
    }
    
    public void addExplosive(Explosive e){
    	explosives.add(e);
    }
    
    public List<Explosive> getExplosives(){
    	return explosives;
    }
    
    public boolean isRespawning(){
    	return respawnTime > 0;
    }
    
    public Position getSpawnPoint(){
		 return spawnPoint;
    }
	
	public void setSpawnPoint(Position spawn){
		this.spawnPoint = spawn;
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
		
		if(respawnTime > 0){
    		respawnTime--;
    	}
		
		
		Iterator<Explosive> iter = explosives.iterator();
		while(iter.hasNext()){
			if(iter.next().isDestroyed()){
				iter.remove();
				setAmmo(getAmmo()+1);
			}
		}
		
	}
	
	/**
	 * Returns whether this hero has enough mana to perform a spell with the cost cost.
	 * @param cost - Cost of spell
	 * @return true if spell can be casted, false otherwise
	 */
	public boolean hasEnoughMana(int cost){
		return mana >= cost;
	}
	
	public int getMana(){
		return mana;
	}
	
	/**
	 * Mana is increased by amount. It can't go over 100.
	 * @param amount - how much to change mana
	 */
	public void increaseMana(int amount){
		if (amount > Constants.HERO_MANA_MIN_VALUE){
			mana = Math.min(mana + amount, Constants.HERO_MANA_MAX_VALUE);
		} else {
			mana = Math.max(mana + amount, Constants.HERO_MANA_MIN_VALUE);
		}
		
	}
	
	/**
	 * Mana is decreased by amount. It can't go below 0.
	 * @param amount - how much to change mana
	 */
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
