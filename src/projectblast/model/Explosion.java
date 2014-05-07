package projectblast.model;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.hero.Hero;



public class Explosion extends Entity implements IBurst {

	private int life = 60;
	
	public Explosion(Position position) {
		super(position,  new Rectangle(position.getX() + 1, position.getY() + 1, 30, 30));
		setName(Id.EXPLOSION);
	}

	
	@Override
	public void update(){
		life--;
	}
	
	@Override
	public boolean isDead(){
		return life <= 0;
	}


	@Override
	public boolean allowPassage(Entity entity) {
		return true;
	}


	@Override
	public boolean isMovable() {
		return false;
	}


	@Override
	public void touchEffect(Hero h) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void collide(Entity entity) {
		if(entity instanceof DestructibleBlock || entity instanceof Hero) {
			Destructible dest = (Destructible)entity;
			dest.destroy();
		}
		
	}
	
}
