package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Test;

import projectblast.model.attribute.Position;
import projectblast.model.entity.SolidBlock;

public class EntityTest {

	@Test
	public void testIsOnGrid() {

		Position pos = new Position(96,96);
		SolidBlock block = new SolidBlock(pos);
		
		Position pos2 = new Position(97,97);
		SolidBlock block2 = new SolidBlock(pos2);
		
		assertTrue(block.isOnGrid() == (block.getX()/32.0 == 3 && block.getY()/32.0 == 3));
		assertTrue(block2.isOnGrid() == (block2.getX()/32.0 == 3 && block2.getY()/32.0 == 3));
	}

	@Test
	
	public void testSetGetXY(){

		Position pos = new Position(96,96);
		SolidBlock block = new SolidBlock(pos);
		
		block.setX(100);
		assertTrue(block.getX() == 100);
		
		block.setY(120);
		assertTrue(block.getY() == 120);
	
	}
	
	@Test
	public void testSetGetPosition(){
		Position pos = new Position(96,96);
		SolidBlock block = new SolidBlock(pos);
		
		block.setPosition(1,1);
		
		assertTrue(block.getX() == 1 && block.getY() == 1);
		block.setPosition(pos);
		assertTrue(block.getPosition().equals(pos));
	}	
	
	
}
