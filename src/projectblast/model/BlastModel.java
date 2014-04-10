package projectblast.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import projectblast.model.Movable.Direction;
import projectblast.model.explosive.Explosive;
import projectblast.model.hero.Hero;
import projectblast.view.Jukebox;


public class BlastModel implements IBlastModel {
	
	private List<Entity> entities;
	private List<Player> players;
	private List<Explosive> explosives;
	private List<ExplosionCore> explosions;
	private List<Tower> towers;
	
	public BlastModel(){ //Ska bytas ut mot BlastFactory??
		this(new LinkedList<Player>());
	}
	
	
	public BlastModel(List<Player> players){
		this.players = players;  
		this.entities = new ArrayList<Entity>();
		this.explosives = new ArrayList<Explosive>();
		this.explosions = new ArrayList<ExplosionCore>();
		this.towers = new ArrayList<Tower>();
		
		try {
			entities.addAll(MapReader.createEntities(new TiledMap("data/map/Map.tmx")));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		System.out.println(players.get(0));
		
		for(Player p: players){
			entities.add(p.getHero());
		}
		
		for(Entity e : entities) {
			if(e.getName().equals("Tower")) {
				towers.add((Tower)e);
			}
		}
		
	}
	
	
	@Override
	public void movePlayer(int playerID, Direction dir) {
		Hero hero = players.get(playerID-1).getHero();
		int distance = hero.getSpeed();
		
		hero.setDirection(dir);
		
		if(dir.getX() != 0 && dir.getY() != 0) {
		    distance = distance - 1; //TODO fix properly
		}
		
		while(distance > 0) {
		    if(isFree(hero, hero.getDirection(), 1)) {
                hero.move(dir);
            } else if(dir.getX() != 0 && dir.getY() != 0) { //Moving diagonally
		        if(isFree(hero, Direction.getDirection(dir.getX(), 0), 1)) {
		            hero.move(Direction.getDirection(dir.getX(), 0));
		        } else if(isFree(hero, Direction.getDirection(0, dir.getY()), 1)) {
                    hero.move(Direction.getDirection(0, dir.getY()));
                }
		    } 
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
	
	public List<ExplosionCore> getExplosions() {
		return explosions;
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public void removeEntity(Entity e){
		entities.remove(e);
	}
	
	
	
	public void update(GameContainer gc, StateBasedGame game, int delta){
		//TODO remove hardcoding
		
		//List of entities to throw away later
		List<Entity> trashCan = new LinkedList<Entity>();
		for(Entity e: entities){
			e.update();
			if (e instanceof Destructible){
				Destructible d = (Destructible)e;
				if (d.isDestroyed()){
					trashCan.add(e);
				}
			}
		}
		//Throw the destroyed entities away
		entities.removeAll(trashCan);
		
		for (ExplosionCore c: explosions){
			c.tick();
			if (c.isDead()){
				entities.removeAll(c.getParts());
			}
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
			
			if(!isFree(ex)){
				//Checks whether the fireballs CollisionBox will intersect with the Owners.
				//if(!ex.getOwner().getCollisionBox().intersects(ex.getCollisionBox())|| !ex.getCollisionBox().intersects(ex.getOwner().getCollisionBox())){
					
				removeEntity(ex);
				createExplosion(ex.getPosition(), 3);
				tmp.add(ex);
	
			}
		}
		explosives.removeAll(tmp);	
	}
	
	private void handleTowers() {
		for(Tower tower : towers) {
			Direction[] directions = {Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH};
			for(int i = 0; i < directions.length; ++i) {
				int power = tower.getPower();
				for(int j = 1; j <= power; ++j) {
					int x = tower.getX() + (directions[i].getX() * power * Constants.TILE_SIZE);
					int y = tower.getY() + (directions[i].getY() * power * Constants.TILE_SIZE);
					Rectangle check = new Rectangle(x, y, Constants.TILE_SIZE, Constants.TILE_SIZE);
					Entity e = getClosestIntersectingEntity(check);
					if(e != null && e instanceof Hero) { //TODO instanceof == bad? Maybe change.
						
					}
				}
			}
		}
	}
	
	private void handleTowerFire(Tower tower) {
		
	}

	@Override
	public void stop(int playerID) {
		players.get(playerID-1).getHero().stopMove();
		
	}
	
	public boolean isFree(MovableEntity entity){
		return isFree(entity,entity.getDirection(),entity.getSpeed());
	}
	
	public boolean isFree(Entity entity){
		return isFree(entity,Direction.NONE, 0);
	}
	
	public boolean isFree(Entity entity, Direction dir, int length){
		Rectangle c = entity.getCollisionBox();
		Rectangle testBox = new Rectangle (c.getX() + dir.getX() * length, c.getY() + dir.getY() * length, c.getWidth(),c.getHeight());
		
		for (Entity e: entities){
			//TODO remove this instanceof - It is only here to prevent collision with itself
	    	if (!(e.getName().equals(entity.getName())) && e.getCollisionBox().intersects(testBox) ){
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
	
	private Entity getClosestIntersectingEntity(Rectangle rectangle) {
		List<Entity> intersectingEntitys = new ArrayList<Entity>();
		for(Entity entity : entities) {
			if(entity.getCollisionBox().intersects(rectangle)) {
				intersectingEntitys.add(entity);
			}
		}
		double smallestDistance = Double.MAX_VALUE;
		Entity ent = null;
		Vector2f rectLocation = rectangle.getLocation();
		for(Entity entity : intersectingEntitys) {
			if(smallestDistance > rectLocation.distance(entity.getCollisionBox().getLocation())) {
				smallestDistance =  rectLocation.distance(entity.getCollisionBox().getLocation());
				ent = entity;
			}
		}
		return ent;
	}
	
	private int snapToGrid(int i){
		return (int)Math.round(i/32.0)*32;
		
	}
	
	public ExplosionCore createExplosion(Position p, int power){
		Jukebox.Sounds.EXPLOSION.getSound().play((float)(0.5 + Math.random()), 0.05f);
		
		p.setX(snapToGrid(p.getX()));
		p.setY(snapToGrid(p.getY()));
		
		List<Explosion> l = new ArrayList<Explosion>();

		//Add the center one
		l.add(new Explosion(p));
		
		Direction[] d = {Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH};
		for (int i = 0; i < 4; i++){
			int dist = 0;
			Rectangle check = new Rectangle(p.getX()+2, p.getY()+2, Constants.TILE_SIZE-4,Constants.TILE_SIZE-4);
			while (dist <= power){
				check.setX(p.getX() + d[i].getX() * dist * Constants.TILE_SIZE);
				check.setY(p.getY() + d[i].getY() * dist * Constants.TILE_SIZE);
				
				Entity e = getBlocker(check);
				if (e instanceof Destructible){
					((Destructible) e).destroy();
					l.add(new Explosion(new Position(p.getX() + d[i].getX() * dist * Constants.TILE_SIZE,p.getY() + d[i].getY() * dist * Constants.TILE_SIZE)));
					break;
				} else if (e instanceof Block){
					break;
				} else if (e instanceof Tower){
					((Tower) e).takeDamage();
					break;
				}
				
				l.add(new Explosion(new Position(p.getX() + d[i].getX() * dist * Constants.TILE_SIZE,p.getY() + d[i].getY() * dist * Constants.TILE_SIZE)));
					
				
				dist++;
				
			}

		}
		
		ExplosionCore core = new ExplosionCore(l,Constants.EXPLOSION_TIME);
		
		explosions.add(core);
		entities.addAll(l);
		
		return core;
	}
	

}
