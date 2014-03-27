package projectblast;

import org.newdawn.slick.Image;

//TODO extends movableEntity
public abstract class Hero {
	private Image sprite;
	private int x,y;

	public abstract void primaryAbility();
		
	public abstract void secondaryAbility();
	
}
