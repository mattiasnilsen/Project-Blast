package projectblast.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import projectblast.model.IBlastModel;
import projectblast.model.Player;
import projectblast.model.entity.Entity;
import projectblast.model.entity.hero.Hero;
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
		this.images = ImageDatabase.getImageDatabase();
		statusBar   = new StatusBar(model);
		this.entities = model.getEntities();
		this.entityRows = new ArrayList[30];
		for(int i = 0; i < entityRows.length; i++){
			entityRows[i] = new ArrayList<Entity>();
		}
		
	}
	//TODO Magic numbers
	//This method sorts all entities in the y Position
		private void sortEntities(){
			int square;
			//Go through every row and sorts the entityRows list 
			for(int i = 0; i < 30; i++){
				square = (i)*32;
				
				for(Entity e: entities){
					if(e.getY() >= square && e.getY() < square+32){
					entityRows[i].add(e);
					}	

				}
			}
			
			
		//Removes all entities then adds them in the correct order	
			entities.removeAll(entities);		

			for(int j = 0; j < 30; j++){
				
			for(int i=0;i< entityRows.length; i++){
			Collections.sort(entityRows[i]);
			
			}
			entities.addAll(entityRows[j]);
			
			entityRows[j].removeAll(entityRows[j]);
			}
				
		}
	

	public void render(Graphics g) {
		
		//Sort entities
		sortEntities();
		
		//Draw all entities
		for (Entity e: entities){
		   	g.drawAnimation(images.getAnimation(e), e.getX(), e.getY()); 
		   //	g.drawRect(e.getCollisionBox().getX(), e.getCollisionBox().getY(), e.getCollisionBox().getWidth(), e.getCollisionBox().getHeight());
		}
		
		//Draw mana bars if necessary
		List<Hero> heroes = new ArrayList<Hero>();
		for (Player p: model.getPlayers()){
			heroes.add(p.getHero());
		}
		for(Hero h: heroes){
			if (h.getMana() < 100){
				g.setColor(Color.black);
				g.fillRect(h.getX(),h.getY()+Constants.TILE_SIZE,Constants.TILE_SIZE,8);
				g.setColor(h.getTeam().getColor());
				g.fillRect(h.getX(),h.getY()+Constants.TILE_SIZE,(Constants.TILE_SIZE * (h.getMana()/100f)),8);
			}
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

