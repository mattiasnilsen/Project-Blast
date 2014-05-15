package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projectblast.model.BlastModel;
import projectblast.model.Direction;
import projectblast.model.entity.SolidBlock;
import projectblast.model.entity.explosive.Fireball;
import projectblast.model.entity.hazard.Paralyzer;
import projectblast.model.entity.hero.Mage;
import projectblast.model.helper.Position;

public class FireballTest {
	
	private Fireball fireball;
	private Mage mage; 
	
	@Before
	public void before() {
		mage = new Mage(new Position(0,0), null, null);
		fireball = new Fireball(new Position(5, 5), 4, Direction.NORTH, mage);
	}
	
	@Test
	public void testCollide() {
		fireball.collide(new Paralyzer(new Position(0,0)));
		assertFalse(fireball.isDestroyed());
		fireball = new Fireball(new Position(5, 5), 4, Direction.NORTH, mage);
		fireball.collide(new SolidBlock(new Position(0,0)));
		assertTrue(fireball.isDestroyed());
		fireball = new Fireball(new Position(5, 5), 4, Direction.NORTH, mage);
		fireball.collide(mage);
		assertFalse(fireball.isDestroyed());
		fireball = new Fireball(new Position(5, 5), 4, Direction.NORTH, mage);
		fireball.collide(new Mage(new Position(0,0), null, null));
		assertTrue(fireball.isDestroyed());
		
	}
	

	@Test
	public void testMoving() {
		fireball.update();
		assertTrue(fireball.isMoving());
		fireball.setDirection(Direction.EAST);
		assertTrue(fireball.isMoving());
		fireball.stopMove();
		assertFalse(fireball.isMoving());
		fireball.startMove();
		assertTrue(fireball.isMoving());
		BlastModel model = new BlastModel();
		model.addEntity(new SolidBlock(new Position(39,1)));
		fireball.setSpeed(32);
		fireball.update();
		System.out.println(fireball.getY());
		System.out.println(fireball.getX());
	}
	
	@Test
	public void testAllowPassage(){
		assertTrue(fireball.allowPassage(new Paralyzer(new Position(0,0))));
		assertTrue(fireball.allowPassage(new SolidBlock(new Position(0,0))));
	}
	
	
	
	

}
