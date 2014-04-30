package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Color;

import projectblast.model.*;
import projectblast.model.Team.Side;

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

}
