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
	private Direction direction;

	private Team team;
	
    public Hero(int x, int y, Image sprite, int speed, Direction direction, Animation[] animations, Team team) {
        super(x, y, sprite, speed, direction, new Rectangle(x,y,24,30), animations);
        bombPower = 1;
        bombCount = 1;
        this.team = team;
        this.direction = direction;
    }
    
    

    @Override
    public void update() {
    	
    }
    
    public int snapXToGrid(int x){
    	int xToGrid = Math.round(x/32.0f)*32;
    	return xToGrid;
    	}
    	
    
    public int snapYToGrid(int y){
    int yToGrid = Math.round(y/32.0f)*32;;
 
    		return yToGrid;

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
