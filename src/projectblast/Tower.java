package projectblast;

import org.newdawn.slick.Image;


public class Tower extends Entity implements Destructible{
	Image damagedTower; 		//add image
	Image gravelyDamagedTower;	//add image
	Image neutralTower; 		//add image
	int health = 3;
	
	
	public Tower(int x, int y, Image sprite) {
		super(x, y, sprite);
		// TODO Auto-generated constructor stub
	}


	public void capture(Team team){
	//Code to change color on sprite and give it to other team
	}
	@Override
	public void destroy() {
		if(health == 2){
		this.setSprite(damagedTower);
		}
		else if(health == 1){
		this.setSprite(gravelyDamagedTower);	
		}else if(health == 0){
		this.setSprite(neutralTower);
		}

	}
}
