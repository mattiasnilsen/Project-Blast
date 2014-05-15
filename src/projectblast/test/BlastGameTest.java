/**
 * 
 */
package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import projectblast.control.BlastGame;

/**
 * @author osaxel
 *
 */
public class BlastGameTest {

	@Test
	public void testInitStatesList() {
		BlastGame game = new BlastGame("");
		GameContainer gc;
		try {
			gc = new AppGameContainer(game);
			game.initStatesList(gc);
		} catch (SlickException e) {
			fail("SlickException was thrown...");
		}
	}

}
