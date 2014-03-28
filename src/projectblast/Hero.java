package projectblast;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public abstract class Hero extends MovableEntity{

    public Hero(int x, int y, Image sprite, Animation[] animations) {
        super(x, y, sprite, animations);
    }

	public abstract void primaryAbility();
		
	public abstract void secondaryAbility();
	
}
