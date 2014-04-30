package projectblast.model;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Movable.Direction;
import projectblast.model.hero.Hero;
/**
 * 
 * @author Alex
 * revised by Mattias Nilsen
 */

public class Tower extends Entity {
	private static final int STARTING_HEALTH = 3;
	private int health;
	private int power;
	private Team owner;
	
	public Tower(Position position) {
		super(position, new Rectangle(position.getX() + 1, position.getY() + 1, 24, 30));
		
		setName(Id.TOWER);
		health = STARTING_HEALTH;
		owner = Team.getNeutralTeam();
		power = 4;
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
		health = STARTING_HEALTH;
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
}
