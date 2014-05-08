package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Test;

import projectblast.model.Position;

public class PositionTest {

	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEquals(){
		Position p1 = new Position(13,37);
		Position p2 = new Position(37,13);
		Position p3 = new Position(13,37);
		
		assertTrue(p1.equals(p3));
		assertFalse(p2.equals(null));
		assertFalse(p1.equals(p2));
	}
	
	@Test
	public void testHashCode(){
		Position p1 = new Position(13,37);
		Position p2 = new Position(37,13);
		Position p3 = new Position(13,37);
		
		assertTrue(p1.hashCode() == p3.hashCode());
		assertFalse(p1.hashCode() == p2.hashCode());
	}

}
