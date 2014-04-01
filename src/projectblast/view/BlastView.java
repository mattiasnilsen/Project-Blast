package projectblast.view;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import projectblast.model.BlastModel;
import projectblast.model.Entity;
import projectblast.model.IBlastModel;



public class BlastView implements IBlastView {
	
	private IBlastModel model;
	
	public BlastView(IBlastModel model){
		this.model = model;
	}
	public void render(Graphics g) {
			
    
			//Draw all entities
		    for (Entity e: model.getEntities()){
		    	e.draw(g);
		    }
		    
		    
		    
		    /*
		    //Draw the test
		    g.setColor(Color.red);
		    g.drawString("" + isFree(hero.getCollisionBox()), hero.getX(), hero.getY() + 32);
			*/
		
	}
}

