package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.core.ExplosionCore;
import projectblast.model.core.ShockwaveCore;
import projectblast.model.entity.Id;
import projectblast.model.entity.SolidBlock;
import projectblast.model.entity.explosive.Fist;
import projectblast.model.entity.hero.Brute;
import projectblast.model.helper.Constants;

public class FistTest {

	private Fist fist;
	private Brute brute; 
	
	@Before
	public void before() {
		brute = new Brute(new Position(5,5), null, null);
		fist = new Fist(new Position(5, 5), 4, Direction.NORTH, brute);
	}
	
	@Test
	public void testFist() {
		fist.getName().equals(Id.FIST);
		assertFalse(fist.getDirection() == Direction.NONE);
		assertFalse(fist.isMoving());
		assertFalse(fist.isDestroyed());
		assertTrue(fist.isOnGrid());
		assertFalse(fist.getLife()<=0);
	}
	
	@Test
	public void testAllowPassage(){
		assertFalse(fist.allowPassage(brute));
		assertFalse(fist.allowPassage(new SolidBlock(new Position(10,10))));
	}
	
	@Test
	public void testGetCore(){
		assertTrue(fist.getCore() instanceof ExplosionCore);
	}
	
	@Test
	public void testUpdate() {
		assertTrue(fist.getLife() == Constants.FIST_TIME);
		fist.update();
		assertFalse(fist.isMoving());
		assertFalse(fist.getLife() == Constants.FIST_TIME);
		for(int i = 0; i < Constants.FIST_TIME-1; i++){
			fist.update();
		}
		assertTrue(fist.isDestroyed());
	}

}
