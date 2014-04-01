package projectblast.view;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import projectblast.model.BlastModel;
import projectblast.model.Entity;
import projectblast.model.IBlastModel;
import projectblast.model.Player;



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
		    
		    //Draw all heroes
		    for (Player p: model.getPlayers()){
		    	p.getHero().draw(g);
		    }
		    
		    //Draw debug text
		    g.setColor(Color.yellow);
		    g.drawString("Entities: " + model.getEntities().size() + " Players: " + model.getPlayers().size(), 0, 0);
		
	}
}

