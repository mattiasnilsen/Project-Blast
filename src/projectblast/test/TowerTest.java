package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Color;

import projectblast.model.*;
import projectblast.model.Team.Side;
import projectblast.model.hero.*;

public class TowerTest {

	private Tower tower;
	
	@Before
	public void before() {
		tower = new Tower(new Position(1, 1));
	}
	
	@Test
	public void testTakeDamage() {
		assertTrue(tower.getHealth() == Constants.TOWER_STARTING_HEALTH);
		
		for(int i = 1; i <= Constants.TOWER_STARTING_HEALTH; ++i) {
			tower.takeDamage();
			assertTrue(tower.getHealth() == Constants.TOWER_STARTING_HEALTH - i);
		}
		assertTrue(tower.getHealth() == 0);
		
	}
	
	@Test
	public void testCapture() {
		assertTrue(tower.getOwner() == Team.getNeutralTeam());	
		Team team1 = new Team("Test", Color.black, Side.LEFT);
		
		tower.takeDamage();
		tower.capture(team1);
		
		assertTrue(tower.getHealth() == Constants.TOWER_STARTING_HEALTH);
		assertTrue(tower.getOwner() == team1);
		
	}
	
	@Test
	public void testAllowPassage() {
		Hero hero = new Mage(new Position(200, 200), 4, Movable.Direction.EAST, new Team("Test Team", Color.red, Team.Side.LEFT));
		assertFalse(tower.allowPassage(hero));
		for(int i = 0; i < Constants.TOWER_STARTING_HEALTH; ++i) {
			tower.takeDamage();
		}
		assertTrue(tower.allowPassage(hero));
		tower.capture(hero.getTeam());
		assertTrue(tower.allowPassage(hero));
	}
}
