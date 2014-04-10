package projectblast.model;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Movable.Direction;
/**
 * 
 * @author Alex
 * revised by Mattias Nilsen
 */

public class Tower extends Entity implements Destructible {
	private static final int STARTING_HEALTH = 3;
	private int health;
	private int power;
	private int turningTime; //The time it takes for the turret to turn 90 degrees in milliseconds.
	private Direction turretDirection;
	private Team owner;
	
	public Tower(Position position) {
		super(position, new Rectangle(position.getX() + 1, position.getY() + 1, 24, 30));
		
		setName("Tower");
		health = STARTING_HEALTH;
		owner = null;
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

	public void capture(Team team){
		health = STARTING_HEALTH;
		owner = team;
	}
	@Override
	public Explosion destroy() {
		if(health > 0 ) {
			health--;
		}
		return null;
	}
	@Override
	public void update() {
		
	}
}
