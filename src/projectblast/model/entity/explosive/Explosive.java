package projectblast.model.entity.explosive;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.BlastModel;
import projectblast.model.Destructible;
import projectblast.model.Direction;
import projectblast.model.Movable;
import projectblast.model.core.ExplosionCore;
import projectblast.model.core.ICore;
import projectblast.model.entity.Entity;
import projectblast.model.entity.MovableEntity;
import projectblast.model.entity.hero.Hero;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Position;

public abstract class Explosive extends MovableEntity implements Destructible {
	private Hero owner;
	private int power;
	private int life = 1; //Fix til fireball uses same system;
	
	public Explosive(Position position,  int speed, Direction direction, Hero owner) {
		super(position,  speed, direction, new Rectangle(position.getX(), position.getY(), 28, 28));
		this.owner = owner;
		power = owner.getPower();
	
	}
	
	public Hero getOwner(){
		return owner;
	}
	
	public void destroy(){
		setLife(0);
	}
	
	public boolean isDestroyed(){
		return life <= 0;
	}
	
	public void setLife(int life){
		this.life = life;
	}
	
	public int getLife(){
		return life;
	}
	
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
	
	public ICore getCore() {
		List<Direction> directionList = new ArrayList<Direction>();
		directionList.add(Direction.EAST);
		directionList.add(Direction.NORTH);
		directionList.add(Direction.WEST);
		directionList.add(Direction.SOUTH);
		ExplosionCore core = new ExplosionCore(Constants.EXPLOSION_TIME, BlastModel.snapToGrid(getPosition()), 4, directionList);//TODO remove magic number
		
		return core;
	}

}
