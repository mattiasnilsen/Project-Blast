package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Test;

import projectblast.model.entity.DestructibleBlock;
import projectblast.model.entity.Entity;
import projectblast.model.entity.Tower;
import projectblast.model.helper.Position;
import projectblast.view.ImageDatabase;

public class ImageDatabaseTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetAnimation(){
		ImageDatabase i = new ImageDatabase();
		Entity d = new DestructibleBlock(new Position(13,37));
		Entity t = new Tower(new Position(11,11));
		
		assertFalse( i.getAnimation(d).equals(i.getAnimation(t)) );
		
		
		
	}

}
