package projectblast.model;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
/**
 * 
 * @author Alex
 *
 */

public class Tower extends Entity implements Destructible{
	private Image damagedTower; 		//add image
	private Image gravelyDamagedTower;	//add image
	int health = 3;
	
	
	public Tower(int x, int y, Image sprite) {
		super(x, y, sprite, new Rectangle(x+1,y+1,24,30));
		setName("Tower");
	}


	public void capture(Team team){
	//Code to change color on sprite and give it to other team
	}
	@Override
	public Explosion destroy() {
		if(health == 2){
		this.setSprite(damagedTower);
		}
		else if(health == 1){
		this.setSprite(gravelyDamagedTower);	
		}else if(health == 0){
		this.setSprite(this.getSprite());
		//Tell team to remove it from its list and change its color to neutral
		//Perhaps use a propertyChangeListener?
		}
		return null;
	}
}
