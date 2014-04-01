package projectblast;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BlastView {
	
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
			// TODO Remove hardcoding
    
			//Draw all entities
		    for (Entity e: entities){
		    	e.draw(g);
		    }
		    
		    //Draw the hero
		    hero.draw(g, Color.red);
		    
		    //Draw the test
		    g.setColor(Color.red);
		    g.drawString("" + isFree(hero.getCollisionBox()), hero.getX(), hero.getY() + 32);
			
		
	}
}

