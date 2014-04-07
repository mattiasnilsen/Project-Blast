package projectblast.view;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import projectblast.model.BlastModel;
import projectblast.model.Entity;
import projectblast.model.Explosion;
import projectblast.model.ExplosionCore;
import projectblast.model.IBlastModel;
import projectblast.model.Player;

/**
 * 
 * @author A.Freudenthaler
 *
 */
public class BlastView implements IBlastView {
	
	private IBlastModel model;
	private ImageDatabase images;
	
	public BlastView(IBlastModel model){
		this.model = model;
		this.images = new ImageDatabase();
		
	}

	public void render(Graphics g) {
		//Draw all entities
		 for (Entity e: model.getEntities()){
		    	g.drawAnimation(images.getAnimation(e), e.getX(), e.getY()); 
		    	g.drawRect(e.getCollisionBox().getX(), e.getCollisionBox().getY(), e.getCollisionBox().getWidth(), e.getCollisionBox().getHeight());
		 }
		 for (ExplosionCore c: model.getExplosions()){
			 for (Explosion q: c.getParts()){
				 g.drawAnimation(images.getAnimation(q),q.getX(),q.getY());
			 }
		 }
	}
	
	public void rendery(Graphics g) {
			
    
			//Draw all entities
		    for (Entity e: model.getEntities()){
		    	e.draw(g);
		    }
		    
		    //Draw all heroes
		    for (Player p: model.getPlayers()){
		    	
		    	p.getHero().draw(g);
		    	
		    	//TODO Remove this debug drawing of collision box
		    	Rectangle r = p.getHero().getCollisionBox();
		    	g.setColor(Color.cyan);
		    	g.drawRect(r.getX(),r.getY(),r.getWidth(),r.getHeight());
		    	
		    }
		    
		    //Draw debug text
		    g.setColor(Color.yellow);
		    g.drawString("Entities: " + model.getEntities().size() + " Players: " + model.getPlayers().size(), 0, 0);
		
	}
}

