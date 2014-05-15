package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.Sound;

import projectblast.view.Jukebox;

public class JukeboxTest {

	@Test
	public void testGetSound() {
		Sound s = Jukebox.Sounds.EXPLOSION.getSound();
		s.play();
		assertTrue(s.playing());
		s.stop();
		assertFalse(s.playing());
	}

}
