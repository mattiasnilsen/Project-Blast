package projectblast.model.entity;

import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Destructible;
import projectblast.model.Direction;
import projectblast.model.Team;
import projectblast.model.powerup.IPowerUp;
import projectblast.model.core.ExplosionCore;
import projectblast.model.entity.hazard.Explosion;
import projectblast.model.entity.hazard.Hazard;
import projectblast.model.entity.hero.Hero;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Id;
import projectblast.model.helper.Position;
/**
 * 
 * @author Alex
 * revised by Mattias Nilsen
 */

public class Tower extends Entity implements Destructible {

	private int health;
	private int power;
	private Team owner;
	private IPowerUp powerUp;
	private int powerUpInterval;
	private Direction cannonDir;
	
	
	private CannonStatus cannonStatus;
	private int timer;
	
	private Hazard lastExplosion = null;
	
	
	public enum CannonStatus{
		WAITING, READYING, RELOADING;
		
		public CannonStatus next(){
			return values()[(ordinal() + 1) % values().length];
		}
	}
	
	public Tower(IPowerUp powerUp, Position position) {
		super(position, new Rectangle(position.getX() + 1, position.getY() + 1, 24, 30));
		
		setName(Id.TOWER);
		health = Constants.TOWER_STARTING_HEALTH;
		owner = Team.getNeutralTeam();
		power = Constants.TOWER_FIRING_RANGE;
		powerUpInterval = Constants.TOWER_POWERUP_INTERVAL;
		timer = 0;
		cannonStatus = CannonStatus.WAITING;
		this.powerUp = powerUp;
		
	}
	
	public int getPower() {
		return power;
	}
	
	public CannonStatus getStatus(){
		return cannonStatus;
	}
	
	public void cycleStatus(int wait){
		cannonStatus = cannonStatus.next();
		timer = wait;
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
		owner = team;
		health = Constants.TOWER_STARTING_HEALTH;
	}
	
	/**
	 * Make tower defences take damage
	 */
	public void takeDamage(){
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
		
		if (timer > 0 && cannonStatus != CannonStatus.WAITING){
			timer--;
		}
	}

	

	@Override
	public boolean allowPassage(Entity entity) {
		if(health == 0 || entity instanceof Hero && (((Hero)entity).getTeam() == owner)) {
			return true;
		} else {
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
		return false; //Tower should never return true
	}
	
	public Hero getClosestTarget(List<Hero> targets){
		Direction[] dirs = {Direction.EAST,Direction.NORTH,Direction.WEST,Direction.SOUTH};
		for (int i = 1; i <= power; i++){
			for (Direction d: dirs){
				int q = Constants.TILE_SIZE;
				Rectangle r = new Rectangle(getX() + d.getX() * q * i + 4,getY() + d.getY() * q * i + 4, q - 8, q - 8);
				for (Hero h: targets){
					if (!h.getTeam().equals(owner) && r.intersects(h.getCollisionBox())){
						return h;
					}
				}
			}
		}
		return null;
	}

	public boolean isCannonReadyToFire(){
		return cannonStatus == CannonStatus.READYING && timer == 0;
	}
	
	public boolean isCannonReadyToSearch(){
		return cannonStatus == CannonStatus.WAITING;
	}
	
	public boolean isCannonReadyToReload(){
		return cannonStatus == CannonStatus.RELOADING && timer == 0;
	}
	
	
	
	public ExplosionCore fireCannon(Direction dir) {
		if (dir == null || dir == Direction.NONE){
			throw new NullPointerException("Trying to fire cannon in direction null or NONE");
		}
		
		Direction[] dirs = {dir};
		List<Direction> directions = Arrays.asList(dirs);
		
		Position newPos = new Position(getPosition().getX() + dir.getX()*Constants.TILE_SIZE,
		getPosition().getY() + dir.getY()*Constants.TILE_SIZE);
		ExplosionCore c = new ExplosionCore(Constants.EXPLOSION_TIME, newPos, power, directions);
		
		return c;
	}

	public Direction getCannonDir() {
		return cannonDir;
	}

	public void setCannonDir(Direction cannonDir) {
		this.cannonDir = cannonDir;
	}
}
