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

	private Team team;
	
    public Hero(int x, int y, Image sprite, int speed, Direction direction, Animation[] animations, Team team) {
        super(x, y, sprite, speed, direction, new Rectangle(x,y,24,30), animations);
        bombPower = 1;
        bombCount = 1;
        this.team = team;
    }
    
    

    @Override
    public void update() {
    	
    }
    
    @Override
    public void draw(Graphics g) {
    	
    }
    @Override
    public void draw(Graphics g, Color teamColor){
    	
    }

    
    public Team getTeam(){
    	return team;
    }
	public abstract Explosive primaryAbility();
		
	public abstract void secondaryAbility();
}
