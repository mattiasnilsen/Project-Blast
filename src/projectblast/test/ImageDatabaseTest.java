package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import projectblast.control.BlastGame;
import projectblast.model.Direction;
import projectblast.model.entity.DestructibleBlock;
import projectblast.model.entity.Entity;
import projectblast.model.entity.Tower;
import projectblast.model.entity.explosive.Fireball;
import projectblast.model.entity.hero.Mage;
import projectblast.model.helper.Position;
import projectblast.model.powerup.SpeedPowerUp;
import projectblast.view.ImageDatabase;

public class ImageDatabaseTest {
	
	private Fireball fireball;
	private Mage mage;

	
	@Before
	public void before() {
		mage = new Mage(new Position(0,0), null, null);
		fireball = new Fireball(new Position(5, 5), 4, Direction.NORTH, mage);
	}
	
	@Test
	public void testImageDatabase(){
		
		
	}
	
	@Test
	public void testGetAnimation(){
		Entity d = new DestructibleBlock(new Position(13,37));
		Entity t = new Tower(new SpeedPowerUp(),new Position(11,11));
		
		//assertFalse( ImageDatabase.getImageDatabase().getAnimation(d).equals(ImageDatabase.getImageDatabase().getAnimation(t)) );
		
		
		
	}

}
