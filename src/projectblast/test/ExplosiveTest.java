package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.core.ExplosionCore;
import projectblast.model.entity.Id;
import projectblast.model.entity.explosive.Bomb;
import projectblast.model.entity.explosive.Explosive;
import projectblast.model.entity.hero.Bomber;

public class ExplosiveTest {

	private Explosive bomb;
	private Bomber bomber; 
	
	@Before
	public void before() {
		bomber = new Bomber(new Position(5,5), null, null);
		bomb = new Bomb(new Position(5, 5), 4, Direction.NORTH, bomber);
	}
	
	@Test
	public void testExplosive() {
		assertTrue(bomb.getName().equals(Id.BOMB));
		assertFalse(bomb.getDirection() == Direction.NONE);
		assertFalse(bomb.isMoving());
		assertFalse(bomb.isDestroyed());
		assertTrue(bomb.isOnGrid());
		assertFalse(bomb.getLife()<=0);
		bomb.setPower(42);
		assertTrue(bomb.getPower() == 42);
		bomb.destroy();
		assertTrue(bomb.isDestroyed());
	}
	
	@Test
	public void testGetCore(){
		assertTrue(bomb.getCore() instanceof ExplosionCore);
	}
	

}
