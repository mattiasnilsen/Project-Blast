package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Test;



import org.newdawn.slick.Color;

import projectblast.model.*;
import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.attribute.Team;
import projectblast.model.attribute.Team.Side;
import projectblast.model.attribute.powerup.AmmoPowerUp;
import projectblast.model.entity.explosive.Explosive;
import projectblast.model.entity.hero.*;


public class MageTest {
	@Test
	public void testGetSpeed(){
		Position pos = new Position(1,1);
		Direction dir = Direction.EAST;
		Team team = new Team("Test", Color.red, Side.LEFT );
		Hero mage = new Mage(pos,dir,team);
		
		assertTrue(mage.getSpeed() == 4);
	}
	
	

	@Test
	public void testDestroy() {
		Position pos = new Position(1,1);
		Direction dir = Direction.EAST;
		Team team = new Team("Test", Color.red, Side.LEFT );
		Hero mage = new Mage(pos,dir,team);
		mage.destroy();
		assertTrue(mage.isDestroyed());
	}
	@Test
	public void testAddPowerUp(){
		Position pos = new Position(1,1);
		Direction dir = Direction.EAST;
		Team team = new Team("Test", Color.red, Side.LEFT );
		Hero mage = new Mage(pos,dir,team);
		AmmoPowerUp apu = new AmmoPowerUp();
		
		assertTrue(mage.getAmmo() == 3);
		
		for (int i = 0; i < 3; i++){
			mage.addPowerUp(apu);
		}	
		assertTrue(mage.getAmmo() == 6);
		
		for (int i = 0; i < 10; i++){
			apu.reverse(mage);
		}
		//Ammo should never be allowed to be below 0.
		assertTrue(mage.getAmmo() < 0);
	}
	@Test
	public void testPrimaryAbility(){
	Position pos = new Position(1,1);
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
