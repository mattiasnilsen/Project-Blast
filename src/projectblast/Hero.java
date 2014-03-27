package projectblast;

import org.newdawn.slick.Image;

public abstract class Hero extends MovableEntity{

    public Hero(int x, int y, Image sprite) {
        super(x, y, sprite);
    }

	public abstract void primaryAbility();
		
	public abstract void secondaryAbility();
	
}
