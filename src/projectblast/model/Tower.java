package projectblast.model;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;
/**
 * 
 * @author Alex
 *
 */

public class Tower extends Entity implements Destructible{
	private final int MAX_HEALTH = 3;
	int health = MAX_HEALTH;
	private Team owner;
	
	public Tower(Position position) {
		super(position, new Rectangle(position.getX() + 1, position.getY() + 1, 24, 30));
		setName("Tower");
		
		owner = new Team("Neutral",Color.white);
	}

	/**
	 * Capture the tower in the name of the team
	 * @param team - Team who takes this tower
	 */
	public void capture(Team team){
		owner = team;
		health = MAX_HEALTH;
	}
	
	/**
	 * Make tower defences take damage
	 */
	public void takeDamage(){
		if (health > 0){
			health--;
		}
		if (health == 0){
			destroy();
		}
	}
	
	public Team getOwner(){
		return owner;
	}
	
	public int getHealth(){
		return health;
	}
	
	@Override
	public void destroy() {
		
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
