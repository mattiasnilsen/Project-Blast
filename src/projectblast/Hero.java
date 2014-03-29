package projectblast;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Hero extends MovableEntity{

	private int power;
	private int bombCount;
	private List<Explosive> explosives = new ArrayList<Explosive>();
	
    public Hero(int x, int y, Image sprite, int speed, Animation[] animations) {
        super(x, y, sprite, speed, animations);
    }
    
    public void addExplosive(Explosive explosive) {
    	explosives.add(explosive);
    }
    
    public void removeExplosive(Explosive explosive) {
    	explosives.remove(explosive);
    }

    @Override
    public void update() {
    	super.update();
    	for(Explosive explosive : explosives) {
    		explosive.update();
    	}
    }
    
    @Override
    public void draw(Graphics g) {
    	super.draw(g);
    	for(Explosive explosive : explosives) {
    		explosive.draw(g);
    	}
    }
    
	public abstract void primaryAbility();
		
	public abstract void secondaryAbility();
}
