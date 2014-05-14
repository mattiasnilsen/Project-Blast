package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Test;



import org.newdawn.slick.Color;

import projectblast.model.*;
import projectblast.model.Team.Side;
import projectblast.model.Movable.*;
import projectblast.model.explosive.Explosive;
import projectblast.model.hero.*;
import projectblast.model.powerups.AmmoPowerUp;

public class MageTest {
	@Test
	public void testGetSpeed(){
		Position pos = new Position(1,1);
		int speed = 1;
		Direction dir = Direction.EAST;
		Team team = new Team("Test", Color.red, Side.LEFT );
		Hero mage = new Mage(pos,dir,team);
		
		assertTrue(mage.getSpeed() == 4);
	}
	
	
	
	@Test
	public void testPrimaryAbility(){
	Position pos = new Position(1,1);
	int speed = 100;
	Direction dir = Direction.EAST;
	Team team = new Team("Test", Color.red, Side.LEFT );
	Hero mage = new Mage(pos,dir,team);
	
	mage.setAmmo(-1);
	Explosive testExplosive = mage.primaryAbility();
	assertTrue(testExplosive == null);
	
	mage.setAmmo(0);
	testExplosive = mage.primaryAbility();
	assertTrue(testExplosive == null);
	
	mage.setAmmo(1);
	testExplosive = mage.primaryAbility();
	assertTrue(mage.getAmmo() == 0);
	
	
	//Should test stun ammo as well.
	}
}
