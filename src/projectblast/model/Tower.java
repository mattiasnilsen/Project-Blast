package projectblast.model;

import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Movable.Direction;
import projectblast.model.hero.Hero;
import projectblast.model.powerups.IPowerUp;
import projectblast.model.explosive.*;
/**
 * 
 * @author Alex
 * revised by Mattias Nilsen
 */

public class Tower extends Entity implements Destructible {
	public final int RANGE = 6;

	private int health;
	private int power;
	private Team owner;
	private IPowerUp powerUp;
	private int powerUpInterval;
	
	private int cannonDelay;
	
	private Explosion lastExplosion = null;
	
	public Tower(Position position) {
		super(position, new Rectangle(position.getX() + 1, position.getY() + 1, 24, 30));
		
		setName(Id.TOWER);
		health = Constants.TOWER_STARTING_HEALTH;
		owner = Team.getNeutralTeam();
		power = 4;
		powerUpInterval = Constants.TOWER_POWERUP_INTERVAL;
		cannonDelay = 0;
		
	}
	
	public int getPower() {
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public Team getOwner() {
		return owner;
	}
	
	public IPowerUp getPowerUp() {
		return powerUp;
	}
	
	public void setPowerUp(IPowerUp powerUp) {
		this.powerUp = powerUp;
	}

	/**
	 * Capture the tower in the name of the team
	 * @param team - Team who takes this tower
	 */
	public void capture(Team team){
		if(owner == team) {
			return;
		}
		System.out.println("Tower captured");
		owner = team;
		health = Constants.TOWER_STARTING_HEALTH;
	}
	
	/**
	 * Make tower defences take damage
	 */
	public void takeDamage(){
		System.out.println("Tower taking damage");
		if (health > 0){
			health--;
			if(health == 0) {
				owner = Team.getNeutralTeam();
			}
		}
	}
	
	@Override
	public void update() {
		powerUpInterval--;
		if(powerUpInterval < 0) {
			powerUpInterval = Constants.TOWER_POWERUP_INTERVAL;
		}
		
		if (cannonDelay > 0){
			cannonDelay--;
			if (cannonDelay == 0){
				
			}
		}
	}

	

	@Override
	public boolean allowPassage(Entity entity) {
		if(entity instanceof Hero && (((Hero)entity).getTeam() == owner) || health == 0 ) {
			return true;
		}/* else if (entity instanceof Fireball) {
			return true;
		}*/else{
			return false;
		}
	}

	@Override
	public void collide(Entity entity) {
		if(entity instanceof Hero) {
			Hero hero = (Hero)entity;
			if(!hero.getTeam().equals(owner)) {
				capture(hero.getTeam());
			}
		} else if(entity instanceof Explosion) {
			if(entity != lastExplosion) {
				takeDamage();
				lastExplosion = (Explosion)entity;
			}
		}
	}

	public int getPowerupTimer() {
		return powerUpInterval;
	}

	@Override
	public void destroy() {
		takeDamage();
	}

	@Override
	public boolean isDestroyed() {
		return health == 0;
	}
	
	public Hero getClosestTarget(List<Hero> targets, int range){
		Direction[] dirs = {Direction.EAST,Direction.NORTH,Direction.WEST,Direction.SOUTH};
		for (int i = 1; i <= range; i++){
			for (Direction d: dirs){
				int q = Constants.TILE_SIZE;
				Rectangle r = new Rectangle(getX() + d.getX() * q * i,getY() + d.getY() * q * i, q, q);
				for (Hero h: targets){
					if (!h.getTeam().equals(owner) && r.intersects(h.getCollisionBox())){
						return h;
					}
				}
			}
		}
		
		//TODO Null is bad...
		return null;
	}
	
	//TODO This is a duplicate of getClosestTarget except for return type
	public Direction getClosestTargetDirection(List<Hero> targets, int range){
		Direction[] dirs = {Direction.EAST,Direction.NORTH,Direction.WEST,Direction.SOUTH};
		for (int i = 1; i <= range; i++){
			for (Direction d: dirs){
				int q = Constants.TILE_SIZE;
				Rectangle r = new Rectangle(getX() + d.getX() * q * i,getY() + d.getY() * q * i, q, q);
				for (Hero h: targets){
					if (r.intersects(h.getCollisionBox())){
						return d;
					}
				}
			}
		}
		
		//TODO Null is bad...
		return null;
	}
	
	
	public boolean isCannonReady(){
		return cannonDelay <= 0;
	}
	
	public ExplosionCore fireCannon(Direction dir, int range) {
		if (dir == null){
			dir = Direction.NONE;
		}
		System.out.println("Tower is firing");
		cannonDelay = 100;
		Direction[] dirs = {dir};
		List<Direction> d = Arrays.asList(dirs);
		
		Position newPos = new Position(getPosition().getX() + dir.getX()*Constants.TILE_SIZE,
				getPosition().getY() + dir.getY()*Constants.TILE_SIZE);
		ExplosionCore c = new ExplosionCore(40, newPos, range,d);
		c.create();
		System.out.println("It has " + c.getParts().size() + " parts!");
		return c;
		
		//TODO Play a sound
	}
}
