package projectblast.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Color;

import projectblast.model.*;
import projectblast.model.Team.Side;
import projectblast.model.entity.Tower;
import projectblast.model.entity.Tower.CannonStatus;
import projectblast.model.entity.hazard.Explosion;
import projectblast.model.entity.hero.*;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Position;
import projectblast.model.powerup.SpeedPowerUp;

public class TowerTest {

	private Tower tower;
	
	@Before
	public void before() {
		tower = new Tower(new SpeedPowerUp(), new Position(1, 1));
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
	public void testCollide() {
		Team team1 = new Team("Test", Color.black, Side.LEFT);
		Hero hero = new Mage(new Position(0, 0), Direction.NORTH, team1);
		Explosion explosion = new Explosion(new Position(0, 0));
		
		assertTrue(tower.getHealth() == Constants.TOWER_STARTING_HEALTH);
		tower.collide(explosion);
		assertTrue(tower.getHealth() == Constants.TOWER_STARTING_HEALTH - 1);
		//A tower should only take damage the first time an explosion hits it.
		tower.collide(explosion);
		assertTrue(tower.getHealth() == Constants.TOWER_STARTING_HEALTH - 1);
		
		while(tower.getHealth() > 0) {
			tower.takeDamage();
		}
		
		assertTrue(tower.getHealth() == 0);
		assertTrue(tower.getOwner() == Team.getNeutralTeam());
		tower.collide(hero);
		assertTrue(tower.getOwner() == team1);
	}
	
	@Test
	public void testAllowPassage() {
		Hero hero = new Mage(new Position(200, 200), Direction.EAST, new Team("Test Team", Color.red, Team.Side.LEFT));
		assertFalse(tower.allowPassage(hero));
		for(int i = 0; i < Constants.TOWER_STARTING_HEALTH; ++i) {
			tower.takeDamage();
		}
		assertTrue(tower.allowPassage(hero));
		tower.capture(hero.getTeam());
		assertTrue(tower.allowPassage(hero));
	}
	
	@Test
	public void testCycleStatus(){
		assertTrue(tower.getStatus() == CannonStatus.WAITING);
		tower.cycleStatus(20);
		assertTrue(tower.getStatus() == CannonStatus.READYING);
		tower.cycleStatus(20);
		assertTrue(tower.getStatus() == CannonStatus.RELOADING);
		tower.cycleStatus(20);
		assertTrue(tower.getStatus() == CannonStatus.WAITING);
		
	}
	
	@Test 
	public void testGetClosestTarget() {
		List<Hero> targets = new ArrayList<Hero>();
		targets.add(new Mage(new Position(32, 32), Direction.EAST, new Team("Test Team", Color.red, Team.Side.LEFT)));
		targets.add(new Mage(new Position(32, 64), Direction.EAST, new Team("Test Team", Color.red, Team.Side.LEFT)));
		targets.add(new Mage(new Position(32, 96), Direction.EAST, new Team("Test Team", Color.red, Team.Side.LEFT)));
		tower.setPosition(new Position(32, 130));
		assertTrue(targets.get(2) == tower.getClosestTarget(targets, 5));
		
	}
	
}
