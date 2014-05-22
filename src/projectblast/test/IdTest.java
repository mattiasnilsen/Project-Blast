package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Test;

import projectblast.model.entity.Id;

public class IdTest {

	@Test
	public void testGetName() {
		String s = Id.BOMBER.getName();
		assertTrue(s.equals("Bomber"));
	}

}
