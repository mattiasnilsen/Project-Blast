package projectblast.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Movable.Direction;
import projectblast.model.hero.Hero;
import projectblast.model.powerups.IPowerUp;
/**
 * 
 * @author Alex
 * revised by Mattias Nilsen
 */

public class Tower extends Entity implements Destructible {
	private final int RANGE = 6;

	private int health;
	private int power;
	private Team owner;
	private IPowerUp powerUp;
	private int powerUpInterval;
	
	private int cannonDelay;
	private Direction cannonDir;
	private List<Explosion> cannonFire;
	private int fireDelay;
	
	private Explosion lastExplosion = null;
	
	private IBlastModel model;
	
	public Tower(IBlastModel model, Position position) {
		super(position, new Rectangle(position.getX() + 1, position.getY() + 1, 24, 30));
		
		setName(Id.TOWER);
		health = Constants.TOWER_STARTING_HEALTH;
		owner = Team.getNeutralTeam();
		power = 4;
		powerUpInterval = Constants.TOWER_POWERUP_INTERVAL;
		cannonDelay = 0;
		cannonFire = new ArrayList<Explosion>();
		
		this.model = model;
		
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
			if (cannonDelay == 0 && health > 0){
				fireCannon(cannonDir);
			}
		} else {
			List<Player> players = model.getPlayers();
			List<Hero> heroes = new LinkedList<Hero>();
			for (Player p: players){
				heroes.add(p.getHero());
			}
			getClosestTarget(heroes);
		}
		
		if (fireDelay > 0){
			fireDelay--;
			if (fireDelay == 0){
				for (Entity e: cannonFire){
					model.removeEntity(e);
				}
				cannonFire.clear();
			}
		}
	}

	

	@Override
	public boolean allowPassage(Entity entity) {
		if(entity instanceof Hero && (((Hero)entity).getTeam() == owner) || health == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isMovable() {
		return false;
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
		return false;
	}
	
	private Hero getClosestTarget(List<Hero> targets){
		Direction[] dirs = {Direction.EAST,Direction.NORTH,Direction.WEST,Direction.SOUTH};
		for (int i = 1; i <= RANGE; i++){
			for (Direction d: dirs){
				int q = Constants.TILE_SIZE;
				Rectangle r = new Rectangle(getX() + d.getX() * q,getY() + d.getY() * q, q, q);
				for (Hero h: targets){
					if (r.intersects(h.getCollisionBox())){
						cannonDir = d;
						cannonDelay = 60;
						return h;
					}
				}
			}
		}
		
		//TODO Null is bad...
		return null;
		
	}
	
	private void fireCannon(Direction dir) {
		System.out.println("Tower is firing");
		fireDelay = 60;
		
		for (int i = 1; i <= RANGE; i++) {
			cannonFire.add(new Explosion(new Position(getX() + dir.getX() * Constants.TILE_SIZE * i,
					getY() + dir.getY() * Constants.TILE_SIZE * i)));
		}
		
		for (Entity e: cannonFire){
			model.addEntity(e);
		}
		
		
		//TODO Play a sound
	}
}
