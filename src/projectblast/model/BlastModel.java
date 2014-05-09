package projectblast.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import projectblast.model.Movable.Direction;
import projectblast.model.explosive.Explosive;
import projectblast.model.hero.Hero;
import projectblast.model.powerups.SpeedPowerUp;
import projectblast.view.Jukebox;


public class BlastModel implements IBlastModel {
	
	private static List<Entity> entities;
	private List<Player> players;
	private List<Explosive> explosives;
	private List<Tower> towers;
	private List<ICore> ICores; //should be a secondary interface.
	
	private HashMap<String, Entity> entityMap;
	
	private int balance;
	private int scaleFactor;
	private int tick;
	
	public BlastModel(){ //Ska bytas ut mot BlastFactory??
		this(new LinkedList<Player>());
	}
	
	
	public BlastModel(List<Player> players){
		this.players = players;  
		this.entities = new ArrayList<Entity>();
		this.explosives = new ArrayList<Explosive>();
		this.towers = new ArrayList<Tower>();
		this.ICores = new ArrayList<ICore>();
		
		this.entityMap = new HashMap<String, Entity>();
		
		try {
			entities.addAll(MapReader.createEntities(this,new TiledMap("data/map/Map.tmx")));

		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		//System.out.println(players.get(0));
		
		for(Player p: players){
			entities.add(p.getHero());
		}
		
		for(Entity e : entities) {
			if(e instanceof Tower) {
				//TODO Implement proper setting of power ups this is just for testing.
				Tower tower = (Tower)e;
				tower.setPowerUp(new SpeedPowerUp());
				towers.add(tower);
			}
		}
	}
	
	
	@Override
	public void movePlayer(int playerID, Direction dir) {
		Hero hero = players.get(playerID-1).getHero();
		//System.out.println(hero.getY());
		//int distance = hero.getSpeed();
		hero.setDirection(dir);
		hero.startMove();
		/*if(dir.getX() != 0 && dir.getY() != 0) {
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
		}*/
	}


	@Override
	public void primary(int playerID) {
		//TODO check if hero can use primary
		
		Explosive tmp = players.get(playerID-1).getHero().primaryAbility();
		if(tmp != null){
			entities.add(tmp);
			explosives.add(tmp);
		}
		System.out.println("PrimaryClicked");
		
	}

	@Override
	public void secondary(int playerID) {
		ICore tmp = players.get(playerID-1).getHero().secondaryAbility();
		if(tmp != null){
			ICores.add(tmp);
		}
		//createParalyzer(players.get(playerID-1).getHero().getPosition(), players.get(playerID-1).getHero().getDirection());
		System.out.println("SecondaryClicked");
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
		tick++;
		
		if (tick%60 == 0){
			shiftBalance(getTowerBalance());
			switch (isGameOver()){
			case 0:
				break;
			case -1:
				for (Player p: players){
					if (p.getHero().getTeam().getSide() == Team.Side.LEFT){
						endGame(p.getHero().getTeam());
						return;
					}
				}
				break;
			case 1:
				for (Player p: players){
					if (p.getHero().getTeam().getSide() == Team.Side.RIGHT){
						endGame(p.getHero().getTeam());
						return;
					}
				}
				break;
			}
		}
		
		
		for(Entity entity : entities) {
			Entity other = getIntersectingEntity(entity);
			if(other != null) {
				entity.collide(other);
			}
		}
		
		//List of entities to throw away later
		List<Entity> trashCan = new LinkedList<Entity>();
		
		for (Entity e: entities){
			e.update();
			if(e instanceof Destructible) {
				Destructible d = (Destructible)e;
				if(d.isDestroyed()) {
					trashCan.add(e);
				}
			}
			
		}
		
		entities.removeAll(trashCan);
		
		List<Explosive> trash = new ArrayList<Explosive>();
		for (Explosive e: explosives){
			if(e.isDestroyed()) {
				removeEntity(e);
				ICores.add(e.getCore());
				trash.add(e);
			}
		}
		
		explosives.removeAll(trash);
		
		for (ICore c: ICores){
			if (c.isCreated()){
				c.tick();
				if (c.isDead()){
					entities.removeAll(c.getParts());
				}
			} else {
				while(!c.isCreated()){	
					if(c.step(getIntersectingEntity(new Rectangle(c.getNextPosition().getX()+2, c.getNextPosition().getY()+2, Constants.TILE_SIZE-4, Constants.TILE_SIZE-4)))){
						c.create();
					}else if(c.isCreated()) {
						for (IHazard ib : c.getParts()){
							if (ib instanceof Entity){
								Entity e = (Entity)ib;
								entities.add(e);
							}
						}
					}
				}
			}
		}
		
	    handleTowers();
	}
	
	private void handleTowers() {
		for(Tower tower : towers) {
			if(tower.getPowerupTimer() == 0) {
				for(Player player : players) {
					if(player.getHero().getTeam().equals(tower.getOwner())) {
						player.getHero().addPowerUp(tower.getPowerUp());
					}
				}
			}
			
			Direction[] directions = {Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH};
			
			List<Hero> targets = new ArrayList<Hero>();
			for(Player player : players) {
				targets.add(player.getHero());
			}
			
			Hero closest = tower.getClosestTarget(targets,tower.RANGE);
			if (closest != null){
				if (!tower.isDestroyed() && tower.isCannonReady()){
					ICores.add( tower.fireCannon(tower.getClosestTargetDirection(targets,tower.RANGE), tower.RANGE) );
				}
			}
			
		}
	}
	
	/*public static boolean isFree(MovableEntity entity){
		return isFree(entity,entity.getDirection(),entity.getSpeed());
	}*/
	
	/*public static boolean isFree(Entity entity){
		return isFree(entity,Direction.NONE, 0);
	}*/
	
	public static boolean isFree(MovableEntity entity, Direction dir, int length){
		Rectangle c = entity.getCollisionBox();
		Rectangle testBox = new Rectangle (c.getX() + dir.getX() * length, c.getY() + dir.getY() * length, c.getWidth(),c.getHeight());
		
		for (Entity e: entities){
			//TODO remove this instanceof - It is only here to prevent collision with itself
	    	if (!(e.getName().equals(entity.getName())) && e.getCollisionBox().intersects(testBox) && !(e.allowPassage(entity)) ){
	    		return false;
	    	}
	    	
	    }
		
		return true;
	}
	
	public Entity getIntersectingEntity(Entity entity){
		for (Entity e: entities){
	    	if (e.getCollisionBox().intersects(entity.getCollisionBox()) && e != entity){
	    		return e;
	    	}
	    }
		return null;
	}
	
	public Entity getIntersectingEntity(Rectangle r) {
		for (Entity e: entities){
	    	if (e.getCollisionBox().intersects(r)){
	    		return e;
	    	}
	    }
		return null;

	}
	
	private List<Entity> getAllIntersectingEntities(Rectangle rectangle) {
		List<Entity> intersectingEntitys = new ArrayList<Entity>();
		for(Entity entity : entities) {
			if(entity.getCollisionBox().intersects(rectangle)) {
				intersectingEntitys.add(entity);
			}
		}
		return intersectingEntitys;
	}
	
	private Entity getClosestEntity(List<Entity> entities, Position pos) {
		double smallestDistance = Double.MAX_VALUE;
		Entity ent = null;
		for(Entity entity : entities) {
			Vector2f rectLocation = entity.getCollisionBox().getLocation();
			if(smallestDistance > rectLocation.distance(new Vector2f(pos.getX(), pos.getY()))) {
				smallestDistance = rectLocation.distance(new Vector2f(pos.getX(), pos.getY()));
				ent = entity;
			}
		}
		return ent;
	}
	
	public static int snapToGrid(int i){
		return (int)Math.round(i / (double)Constants.TILE_SIZE) * Constants.TILE_SIZE;
	}
	
	public static Position snapToGrid(Position pos) {
		int x = snapToGrid(pos.getX());
		int y = snapToGrid(pos.getY());
		
		return new Position(x, y);
	}
	
	public int isGameOver(){
		if (balance <= -500){
			return -1;
		} else if (balance >= 500) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public int getBalance() {
		return balance;
	}

	@Override
	public void shiftBalance(int factor) {
		this.balance += factor;
	}

	@Override
	public int getScaleFactor() {
		return scaleFactor;
	}

	@Override
	public void setScaleFactor(int scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
	
	@Override
	public void increaseScaleFactor(int add) {
		this.scaleFactor += add;
	}
	
	@Override
	public int getTowerBalance(){
		int out = 0;
		for (Tower t: towers){
			switch(t.getOwner().getSide()){
			case LEFT:
				out++;
				break;
			case RIGHT:
				out--;
				break;
			default:
				break;
			}
		}
		
		return out;
	}
	
	@Override
	public void endGame(Team winner){
		throw new NullPointerException(winner.getName() + " has won!");
	}
	
	
}
