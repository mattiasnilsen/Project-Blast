package projectblast.model;

import org.newdawn.slick.geom.Rectangle;
/**
 * 
 * @author Alex
 *
 */

public class Tower extends Entity implements Destructible{
	int health = 3;
	
	
	public Tower(Position position) {
		super(position, new Rectangle(position.getX() + 1, position.getY() + 1, 24, 30));
		setName("Tower");
	}


	public void capture(Team team){
	//Code to change color on sprite and give it to other team
	}
	@Override
	public Explosion destroy() {

		return null;
	}
}
