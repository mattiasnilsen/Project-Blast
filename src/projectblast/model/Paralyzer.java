package projectblast.model;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.hero.Hero;

public class Paralyzer extends Entity implements IBurst {
	
	public Paralyzer(Position position) {
		super(position,  new Rectangle(position.getX() + 1, position.getY() + 1, 30, 30));
		setName(Id.PARALYZER);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean allowPassage(Entity entity) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isMovable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void touchEffect(Hero h) {
		// TODO Auto-generated method stub
		
	}

}
