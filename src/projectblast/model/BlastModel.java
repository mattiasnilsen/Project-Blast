package projectblast.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import projectblast.model.Movable.Direction;


public class BlastModel implements IBlastModel {
	
	private List<Entity> entities;
	private List<Player> players;
	private List<Explosive> explosives;
	
	public BlastModel(){ //Ska bytas ut mot BlastFactory??
		this(new LinkedList<Player>());
	}
	
	
	public BlastModel(List<Player> players){
		this.players = players;  
		this.entities = new LinkedList<Entity>();
		this.explosives = new ArrayList<Explosive>();
		try {
			entities.addAll(MapReader.createEntities(new TiledMap("data/map/Map.tmx")));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		System.out.println(players.get(0));
		
		for(Player p: players){
			entities.add(p.getHero());
		}
		
	}
	
	
	@Override
	public void movePlayer(int playerID, Direction dir) {
		Hero hero = players.get(playerID-1).getHero();
		int distance = hero.getSpeed();
		
		hero.setDirection(dir);
		
		while (distance > 0 && isFree(hero.getCollisionBox(),hero.getDirection())){
			hero.move(dir);
			distance--;
		}

	}


	@Override
	public void primary(int playerID) {
		//TODO check if hero can use primary
		Explosive tmp = players.get(playerID-1).getHero().primaryAbility();
		entities.add(tmp);
		explosives.add(tmp);

	}

	@Override
	public void secondary(int playerID) {
		players.get(playerID-1).getHero().secondaryAbility();
	}

	@Override
	public List<Entity> getEntities() {
		return entities;
	}
	
	@Override
	public List<Player> getPlayers() {
		return players;
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public void removeEntity(Entity e){
		entities.remove(e);
	}
	
	
	
	public void update(GameContainer gc, StateBasedGame game, int delta){
		//TODO remove hardcoding
		for(Entity e: entities){
			e.update();
		}
		List<Explosive> tmp = new ArrayList<Explosive>();
		
		/**
		for(Explosive ex: explosives){
			
			if(willCollide(ex)){
				removeEntity(ex);
				Explosion t = ex.explode();
				entities.addAll(createExplosion(t.getX(), t.getY(), 3));
				tmp.add(ex);
				
			}
		}
		**/
		
		//HARD CODED "fix" for fireball, should make a better code later.
		for(Explosive ex: explosives){
			
			if(willCollide(ex)){
				//Checks whether the fireballs CollisionBox will intersect with the Owners.
				if(!ex.getOwner().getCollisionBox().intersects(ex.getCollisionBox())|| !ex.getCollisionBox().intersects(ex.getOwner().getCollisionBox())){
					removeEntity(ex);
					Explosion t = ex.explode();
					entities.addAll(createExplosion(t.getX(), t.getY(), 3));
					tmp.add(ex);

				}else{
					//If it collides this will be typed out.
				System.out.println("I hit myself with a my own fireball");
			
				}
			}
		}
		explosives.removeAll(tmp);
		
	}

	@Override
	public void stop(int playerID) {
		players.get(playerID-1).getHero().stopMove();
		
	}
	
	public boolean willCollide(Entity entity){
		for(Entity e: entities){
			if(e.getName() != entity.getName()){ // TODO proper equals method
				if(entity.getCollisionBox().intersects(e.getCollisionBox())){					
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean isFree(Rectangle r){
		for (Entity e: entities){
			//TODO remove this instanceof - It is only here to prevent collision with itself
	    	if (!(e instanceof Hero) && e.getCollisionBox().intersects(r) ){
	    		return false;
	    	}
	    	
	    }
		return true;
	}
	
	public Entity getBlocker(Rectangle r){
		for (Entity e: entities){
	    	if (e.getCollisionBox().intersects(r)){
	    		return e;
	    	}
	    }
		return null;
	}
	
	public boolean isFree(Rectangle r, Direction d){
		return isFree(new Rectangle(r.getX() + d.getX(),r.getY() + d.getY(),r.getWidth(),r.getHeight()));
	}

	private int snapYToGrid(int y){
		int yToGrid;
		yToGrid = (y/32)*32;
		return yToGrid;
		
	}
	private int snapXToGrid(int x){
		int xToGrid;
		xToGrid = (x/32)*32;
		return xToGrid;
		
	}
	
	public List<Explosion> createExplosion(int x, int y, int power){
		
		x = snapXToGrid(x);
		y = snapYToGrid(y);
		
		List<Explosion> l = new ArrayList<Explosion>();
		Image sprite = null, center = null;
		try {
			sprite = new Image("data/image/Explosion.png");
			center = new Image("data/image/ExplosionCenter.png");
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("Something went wrong! Could not find Explosion.png!");
		}
		if (sprite == null || center == null){
			System.out.println("NULL!!! NULL IN THE EXPLOSION MAKING!!!");
			System.exit(0);
		}
		
		//Add the center one
		l.add(new Explosion(x,y,center));
		
		Rectangle r = new Rectangle(x, y, Constants.TILE_SIZE, Constants.TILE_SIZE);
		Direction[] d = {Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH};
		for (int i = 0; i < 4; i++){
			int dist = 1;
			Rectangle check = new Rectangle(x + d[i].getX() * Constants.TILE_SIZE, 
					y + d[i].getY() * Constants.TILE_SIZE,Constants.TILE_SIZE,Constants.TILE_SIZE);
			while (dist <= power){
				Entity e = getBlocker(check);
				if (e instanceof Destructible){
					((Destructible) e).destroy();
					System.out.println("Destroyed that one!");
					break;
				} else if (e instanceof Block){
					System.out.println("Hit a block!");
					break;
				}
				
				l.add(new Explosion(x + d[i].getX() * dist * Constants.TILE_SIZE,y + d[i].getY() * dist * Constants.TILE_SIZE,sprite));
					
				check.setX(x + d[i].getX() * dist * Constants.TILE_SIZE);
				check.setY(y + d[i].getY() * dist * Constants.TILE_SIZE);
				dist++;
				
			}

		}
		
		System.out.println("Wow! That explosion covers " + l.size() + " blocks!");
		
		entities.addAll(l);
		return l;
	}
	

}
