package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.Color;

import projectblast.model.helper.Position;
import projectblast.model.Team;
import projectblast.model.Direction;
import projectblast.model.Team.Side;
import projectblast.model.entity.explosive.Explosive;
import projectblast.model.entity.hero.*;
import projectblast.model.powerup.AmmoPowerUp;

public class HeroTest {

	@Test
	public void testAddPowerUp(){
		Position pos = new Position(1,1);
		int speed = 100;
		Direction dir = Direction.EAST;
		Team team = new Team("Test", Color.red, Side.LEFT );
		Hero mage = new Mage(pos,dir,team);
		AmmoPowerUp apu = new AmmoPowerUp();
		
		int ammo = mage.getAmmo();
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
	public void testUpdate(){
		Position pos = new Position(1,1);
		int speed = 100;
		Direction dir = Direction.EAST;
		Team team = new Team("Test", Color.red, Side.LEFT );
		Hero mage = new Mage(pos,dir,team);
		Explosive testExplosive = mage.primaryAbility();
		
		mage.setAmmo(1);
		testExplosive = mage.primaryAbility();
		assertTrue(mage.getAmmo() == 0);
		
		testExplosive.destroy();
		mage.update();
		assertTrue(mage.getAmmo() == 1);
	}
	@Test
	public void testDestroy() {
		Position pos = new Position(1,1);
		int speed = 100;
		Direction dir = Direction.EAST;
		Team team = new Team("Test", Color.red, Side.LEFT );
		Hero mage = new Mage(pos,dir,team);
		mage.destroy();
		assertTrue(mage.isDestroyed());
	}
}
