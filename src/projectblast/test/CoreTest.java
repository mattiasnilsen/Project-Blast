package projectblast.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.core.Core;
import projectblast.model.core.ParalyzerCore;
import projectblast.model.core.ShockwaveCore;
import projectblast.model.entity.DestructibleBlock;
import projectblast.model.entity.Entity;
import projectblast.model.entity.SolidBlock;
import projectblast.model.entity.explosive.Fireball;
import projectblast.model.entity.hazard.Hazard;
import projectblast.model.entity.hazard.Paralyzer;
import projectblast.model.entity.hero.Mage;

public class CoreTest {
	Core c;
	Core b;
	
	
	@Before
	public void before(){
		List<Direction>tmp = new ArrayList<Direction>();
		tmp.add(Direction.EAST);
		c = new ShockwaveCore(20, new Position(1,1), tmp);
		b = new ParalyzerCore(20, new Position(1,1), tmp);
	}
	@Test
	public void coreTest() {
		assertTrue(c.getStartingPosition().equals(new Position(1,1)));
		assertTrue(c.getNextPosition().equals(new Position(33,1)));
		
		assertFalse(c.isCreated());
		assertFalse(c.isDead());
		c.tick();
		assertFalse(c.isDead());
	}
	
	@Test
	public void stepTest() {
		Entity intersectingEntity = new Fireball(new Position(5, 5), 4, Direction.NORTH, new Mage(new Position(0,0), null, null));
		assertTrue(c.step(intersectingEntity));
		assertTrue(intersectingEntity.getPosition().equals(new Position(5, 5)));
		intersectingEntity = new DestructibleBlock(new Position(5, 5));
		assertTrue(c.step(intersectingEntity));
		intersectingEntity.update();
		assertFalse(intersectingEntity.getPosition().equals(new Position(5, 5)));
		
		intersectingEntity = new Fireball(new Position(5, 5), 4, Direction.NORTH, new Mage(new Position(0,0), null, null));
		assertTrue(b.step(intersectingEntity));
		intersectingEntity = new SolidBlock(new Position(5, 5));
		assertFalse(b.step(intersectingEntity));
	}
	
	@Test
	public void tickTest() {
		assertFalse(b.isDead());
		for(int i = 0; i < 20; i++){
			b.tick();
		}
		assertTrue(b.isDead());
	}
	
	@Test
	public void createTest() {
		b.create();
		b.create();
		List<Hazard> l = b.getParts();
		assertFalse(l.isEmpty());
		assertTrue(l.size() == 2);
		Hazard h = l.get(0);
		Paralyzer p = (Paralyzer) h;
		assertTrue(p.getPosition().equals(new Position(33,1)));
		h = l.get(1);
		p = (Paralyzer) h;
		assertTrue(p.getPosition().equals(new Position(65,1)));
	}

}
