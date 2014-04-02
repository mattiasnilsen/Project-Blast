package projectblast.model;


import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public abstract class Hero extends MovableEntity{
	
	private int bombPower;
	private int bombCount;
	private List<Explosive> explosives = new ArrayList<Explosive>();
	private Team team;
	
    public Hero(int x, int y, Image sprite, int speed, Direction direction, Animation[] animations, Team team) {
        super(x, y, sprite, speed, direction, new Rectangle(x,y,16,28), animations);
        bombPower = 1;
        bombCount = 1;
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
    @Override
    public void draw(Graphics g, Color teamColor){
    	super.draw(g, teamColor);
    	for(Explosive explosive : explosives) {
    		explosive.draw(g);
    	}
    }

    
    public Team getTeam(){
    	return team;
    }
	public abstract Explosive primaryAbility();
		
	public abstract void secondaryAbility();
}
