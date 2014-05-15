package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Test;

import projectblast.model.Direction;
import projectblast.model.helper.Position;

public class DirectionTest {

	@Test
	public void testClockwise() {
		assertTrue(Direction.NORTH.clockwise() == Direction.NORTHEAST);
		assertTrue(Direction.NORTHEAST.clockwise() == Direction.EAST);
		assertTrue(Direction.EAST.clockwise() == Direction.SOUTHEAST);
		assertTrue(Direction.SOUTHEAST.clockwise() == Direction.SOUTH);
		assertTrue(Direction.SOUTH.clockwise() == Direction.SOUTHWEST);
		assertTrue(Direction.SOUTHWEST.clockwise() == Direction.WEST);
		assertTrue(Direction.WEST.clockwise() == Direction.NORTHWEST);
		assertTrue(Direction.NORTHWEST.clockwise() == Direction.NORTH);
		assertTrue(Direction.NONE.clockwise() == Direction.NONE);
	}

	@Test
	public void testOpposite() {
		for(Direction direction : Direction.values()) {
			Direction opposite = direction.opposite();
			if(direction.getX() == 0) {
				assertTrue(direction.getX() == opposite.getX());
			} else {
				assertFalse(direction.getX() == opposite.getX());
			}
			
			if(direction.getY() == 0) {
				assertTrue(direction.getY() == opposite.getY());
			} else {
				assertFalse(direction.getY() == opposite.getY());
			}
		}
	}
	
	@Test
	public void testGetRelativeDirection() {
		Position center = new Position(100, 100);
		Position east = new Position(200, 100);
		Position west = new Position(50, 100);
		Position south = new Position(100, 200);
		Position north = new Position(100, 50);
		
		assertTrue(Direction.getRelativeDirection(center, east) == Direction.EAST);	
		assertTrue(Direction.getRelativeDirection(center, west) == Direction.WEST);	
		assertTrue(Direction.getRelativeDirection(center, south) == Direction.SOUTH);	
		assertTrue(Direction.getRelativeDirection(center, north) == Direction.NORTH);	
	}
	
	@Test
	public void testGetDirection() {
		for(Direction direction : Direction.values()) {
			assertTrue(Direction.getDirection(direction.getX(), direction.getY()) == direction);
		}
		assertTrue(Direction.getDirection(100, 200) == Direction.NONE); //Invalid numbers should return none.
	}
}
