package projectblast.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import projectblast.model.core.ICore;
import projectblast.model.entity.Entity;
import projectblast.model.entity.MovableEntity;
import projectblast.model.entity.Tower;
import projectblast.model.entity.explosive.Explosive;
import projectblast.model.entity.hero.Hero;

import projectblast.model.helper.Constants;
import projectblast.model.helper.MapReader;
import projectblast.model.helper.Position;
import projectblast.model.powerup.SpeedPowerUp;


public class BlastModel implements IBlastModel {
	
	private static List<Entity> entities= new ArrayList<Entity>();
	private List<Player> players;
	private List<Tower> towers;
	private List<ICore> cores; //should be a secondary interface.
	
	private int balance;
	private int scaleFactor;
	private int tick;
	
	public BlastModel(){ //Ska bytas ut mot BlastFactory??
		this(new LinkedList<Player>());
	}
	
	
	public BlastModel(List<Player> players){
		this.players = players;  
		this.towers = new ArrayList<Tower>();
		this.cores = new ArrayList<ICore>();
		
		try {
			entities.addAll(MapReader.createEntities(new TiledMap("data/map/Map.tmx")));

		} catch (SlickException e) {
			e.printStackTrace();
		}
		
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
		hero.setDirection(dir);
		hero.startMove();
	}
	
	public void stopPlayer(int playerID){
		Hero hero = players.get(playerID-1).getHero();
		hero.stopMove();
	}


	@Override
	public void primary(int playerID) {
		//TODO check if hero can use primary
		
		Explosive explosive = players.get(playerID-1).getHero().primaryAbility();
		if(explosive != null){
			entities.add(explosive);
		}
		System.out.println("PrimaryClicked");
		
	}

	@Override
	public void secondary(int playerID) {
		ICore tmp = players.get(playerID-1).getHero().secondaryAbility();
		if(tmp != null){
			cores.add(tmp);
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
		gameOver();
		tick++;
		if (tick%Constants.FRAMERATE == 0){
			shiftBalance(getTowerBalance());
		}
		
		handleEntities();
		
		handleCores();
		
	    handleTowers();
	}


	private void handleEntities() {
		//List of entities to throw away later
		List<Entity> trash = new LinkedList<Entity>();
		
		for (Entity e: entities){
			Entity other = getIntersectingEntity(e);
			if(other != null) {
				e.collide(other);
			}
			
			e.update();
			
			if(e instanceof Destructible) {
				Destructible d = (Destructible)e;
				if(d.isDestroyed()) {
					trash.add(e);
					if(e instanceof Explosive) { //If this is an explosive we need to get its core before it is destroyed.
						Explosive ex = (Explosive)e;
						cores.add(ex.getCore());
					}
				}
			}
			
		}
		entities.removeAll(trash);
	}


	private void handleCores() {
		for (ICore c: cores){
			if (c.isCreated()){ //If its created, update it.
				c.tick();
				if (c.isDead()){
					entities.removeAll(c.getParts());
				}
			} else { // Create the core if its not created
				while(!c.isCreated()){	
					// Let the core decide if it can step to next position.
					if(c.step(getIntersectingEntity(new Rectangle(c.getNextPosition().getX()+2, c.getNextPosition().getY()+2, Constants.TILE_SIZE-4, Constants.TILE_SIZE-4)))){
						c.create();
					}else if(c.isCreated()) {
						entities.addAll(c.getParts());
					}
				}
			}
		}
	}


	private void gameOver() {
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
	
	private void handleTowers() {
		for(Tower tower : towers) {
			if(tower.getPowerupTimer() == 0) {
				for(Player player : players) {
					if(player.getHero().getTeam().equals(tower.getOwner())) {
						player.getHero().addPowerUp(tower.getPowerUp());
					}
				}
			}
			
			List<Hero> targets = new ArrayList<Hero>();
			for(Player player : players) {
				targets.add(player.getHero());
			}
			
			Hero closest = tower.getClosestTarget(targets,tower.RANGE);
			if (tower.getHealth() != 0){
				if(closest != null && tower.isCannonReadyToSearch()){ //If target is found, make ready to fire
					tower.setCannonDir(tower.getClosestTargetDirection(targets,tower.RANGE));
					tower.cycleStatus(50); //TODO Hardcode
				} else if (tower.isCannonReadyToFire()){ //Firing the cannon
					cores.add( tower.fireCannon(tower.getCannonDir(), tower.RANGE) );
					tower.cycleStatus(120); //TODO Hardcode
				} else if(tower.isCannonReadyToReload()){//Tower is now ready to find new target
					tower.cycleStatus(0);
				}
			}
			
		}
	}
	
	public static boolean isFree(MovableEntity entity, Direction dir, int length){
		Rectangle c = entity.getCollisionBox();
		Rectangle testBox = new Rectangle (c.getX() + dir.getX() * length, c.getY() + dir.getY() * length, c.getWidth(),c.getHeight());
		
		for (Entity e: entities){
	    	if (!(e.equals(entity)) && e.getCollisionBox().intersects(testBox) && !(e.allowPassage(entity)) ){
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
	
	//TODO Unused Method
	private List<Entity> getAllIntersectingEntities(Rectangle rectangle) {
		List<Entity> intersectingEntitys = new ArrayList<Entity>();
		for(Entity entity : entities) {
			if(entity.getCollisionBox().intersects(rectangle)) {
				intersectingEntitys.add(entity);
			}
		}
		return intersectingEntitys;
	}
	
	//TODO Unused Method
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
