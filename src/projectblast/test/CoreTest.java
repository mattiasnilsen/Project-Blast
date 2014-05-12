package projectblast.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import projectblast.model.Core;
import projectblast.model.DestructibleBlock;
import projectblast.model.Entity;
import projectblast.model.IHazard;
import projectblast.model.Paralyzer;
import projectblast.model.ParalyzerCore;
import projectblast.model.Position;
import projectblast.model.ShockwaveCore;
import projectblast.model.SolidBlock;
import projectblast.model.Movable.Direction;
import projectblast.model.explosive.Fireball;
import projectblast.model.hero.Mage;

public class CoreTest {
	
	
	@Test
	public void coreTest() {
		Core c = new ShockwaveCore(20, new Position(1,1), Direction.EAST);
		assertTrue(c.getStartingPosition().equals(new Position(1,1)));
		assertTrue(c.getNextPosition().equals(new Position(33,1)));
		
		assertFalse(c.isCreated());
		assertFalse(c.isDead());
		c.tick();
		assertFalse(c.isDead());
	}
	
	@Test
	public void stepTest() {
		Core c = new ShockwaveCore(20, new Position(1,1), Direction.EAST);
		Entity intersectingEntity = new Fireball(new Position(5, 5), 4, Direction.NORTH, new Mage(new Position(0,0), null, null));
		assertTrue(c.step(intersectingEntity));
		assertTrue(intersectingEntity.getPosition().equals(new Position(5, 5)));
		intersectingEntity = new DestructibleBlock(new Position(5, 5));
		assertTrue(c.step(intersectingEntity));
		intersectingEntity.update();
		assertFalse(intersectingEntity.getPosition().equals(new Position(5, 5)));
		
		c = new ParalyzerCore(20, new Position(1,1), Direction.EAST);
		intersectingEntity = new Fireball(new Position(5, 5), 4, Direction.NORTH, new Mage(new Position(0,0), null, null));
		assertTrue(c.step(intersectingEntity));
		intersectingEntity = new SolidBlock(new Position(5, 5));
		assertFalse(c.step(intersectingEntity));
	}
	
	@Test
	public void tickTest() {
		Core c = new ParalyzerCore(20, new Position(1,1), Direction.EAST);
		assertFalse(c.isDead());
		for(int i = 0; i < 20; i++){
			c.tick();
		}
		assertTrue(c.isDead());
	}
	
	@Test
	public void createTest() {
		Core c = new ParalyzerCore(20, new Position(1,1), Direction.EAST);
		c.create();
		c.create();
		List<IHazard> l = c.getParts();
		assertFalse(l.isEmpty());
		assertTrue(l.size() == 2);
		IHazard h = l.get(0);
		Paralyzer p = (Paralyzer) h;
		assertTrue(p.getPosition().equals(new Position(33,1)));
		h = l.get(1);
		p = (Paralyzer) h;
		assertTrue(p.getPosition().equals(new Position(65,1)));
	}

}
