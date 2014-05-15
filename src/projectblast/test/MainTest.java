package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Test;

import projectblast.control.Main;

public class MainTest {
	
	@Test
	public void testMain(){
		String[] args = {"null",null,"","0"};
		Main.main(args);
		assertTrue(true);
	}

}
