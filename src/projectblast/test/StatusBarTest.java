package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.Graphics;

import projectblast.model.BlastModel;
import projectblast.view.StatusBar;

public class StatusBarTest {

	@Test
	public void testRender() {
		BlastModel m = new BlastModel();
		StatusBar s = new StatusBar(m);
		Graphics g = new Graphics();
		
		try {
			s.render(g, m.getPlayers());
			fail("NullPointer should have been thrown...");
		} catch (NullPointerException e){
			
		} catch (IndexOutOfBoundsException e){
			
		}
		
		
	}

}
