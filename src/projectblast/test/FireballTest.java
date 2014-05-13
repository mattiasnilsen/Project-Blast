package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projectblast.model.Movable.Direction;
import projectblast.model.entity.explosive.Fireball;
import projectblast.model.entity.hazard.Paralyzer;
import projectblast.model.entity.hero.Mage;
import projectblast.model.helper.Position;

public class FireballTest {
	
	private Fireball fireball;
	
	@Before
	public void before() {
		fireball = new Fireball(new Position(5, 5), 4, Direction.NORTH, new Mage(new Position(0,0), null, null));
	}
	
	@Test
	public void testCollide() {
		fireball.collide(new Paralyzer(new Position(0,0)));
		assertFalse(fireball.isDestroyed());
	}
	
	//A fireball should move as soon as it's created.
	@Test
	public void testMoving() {
		assertTrue(fireball.isMoving());
	}

}
