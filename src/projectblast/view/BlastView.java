package projectblast.view;


import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import projectblast.model.BlastModel;
import projectblast.model.IBlastModel;
import projectblast.model.Player;
import projectblast.model.core.ExplosionCore;
import projectblast.model.entity.Entity;
import projectblast.model.entity.hazard.Explosion;
import projectblast.model.helper.Constants;

/**
 * 
 * @author A.Freudenthaler
 *
 */
public class BlastView implements IBlastView {
	
	private IBlastModel model;
	private ImageDatabase images;
	private StatusBar statusBar;
	private List<Entity> entities;
	private List[] entityRows;
	
	public BlastView(IBlastModel model){
		this.model  = model;
		this.images = new ImageDatabase();
		statusBar   = new StatusBar(model);
		this.entities = model.getEntities();
		this.entityRows = new ArrayList[22];
		for(int i = 0; i < entityRows.length; i++){
			entityRows[i] = new ArrayList<Entity>();
		}
		
	}
	
	//This method sorts all entities in the y Position
		private void sortEntities(){
			int square;
			//Go through every row and sorts the entityRows list 
			for(int i = 0; i < 22; i++){
				square = (i)*32;
				
				for(Entity e: entities){
					if(e.getY() >= square && e.getY() < square+32){
					entityRows[i].add(e);
					}	

				}
			}
			
			
		//Removes all entities then adds them in the correct order	
			entities.removeAll(entities);		

			for(int j = 0; j < 22; j++){
				
			for(int i=0;i< entityRows.length; i++){
			Collections.sort(entityRows[i]);
			
			}
			entities.addAll(entityRows[j]);
			
			entityRows[j].removeAll(entityRows[j]);
			}
				
		}
	

	public void render(Graphics g) {
		//Draw all entities
		sortEntities();
		 for (Entity e: entities){
		    	g.drawAnimation(images.getAnimation(e), e.getX(), e.getY()); 
		    	g.drawRect(e.getCollisionBox().getX(), e.getCollisionBox().getY(), e.getCollisionBox().getWidth(), e.getCollisionBox().getHeight());
		 }
		 
		 //Draw statusbar
		 statusBar.render(g,model.getPlayers());
		 
		/* //Debug drawing of tiles
		 for(int x = 0; x < Constants.GAME_WIDTH; x += 32) {
		     for(int y = 0; y < Constants.GAME_HEIGHT; y += 32) {
		         g.drawRect(x, y, Constants.TILE_SIZE, Constants.TILE_SIZE);
		     }
		 }*/
		 
		 
	}
}

