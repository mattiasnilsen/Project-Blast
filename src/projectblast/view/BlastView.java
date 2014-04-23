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
	private StatusBar statusBar;
	
	public BlastView(IBlastModel model){
		this.model  = model;
		this.images = new ImageDatabase();
		statusBar   = new StatusBar();
	}

	public void render(Graphics g) {
		//Draw all entities
		 for (Entity e: model.getEntities()){
		    	g.drawAnimation(images.getAnimation(e), e.getX(), e.getY()); 
		    	g.drawRect(e.getCollisionBox().getX(), e.getCollisionBox().getY(), e.getCollisionBox().getWidth(), e.getCollisionBox().getHeight());
		 }
		 
		 //Draw statusbar
		 statusBar.render(g,model.getPlayers());
		 
		 
	}
}

