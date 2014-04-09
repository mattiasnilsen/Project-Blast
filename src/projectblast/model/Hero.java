package projectblast.model;


import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;

import org.newdawn.slick.Graphics;

import org.newdawn.slick.geom.Rectangle;

public abstract class Hero extends MovableEntity{
	
	private int bombPower;
	private int bombCount;
	private Direction direction;

	private Team team;
	
    public Hero(Position position, int speed, Direction direction,  Team team) {
        super(position, speed, direction, new Rectangle(position.getX(), position.getY(), 24, 30));
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

    
    public Team getTeam(){
    	return team;
    }
	public abstract Explosive primaryAbility();
		
	public abstract void secondaryAbility();
}
