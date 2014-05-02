package projectblast.model;

import java.util.ArrayList;
import java.util.HashMap;
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
import projectblast.view.Jukebox;


public class BlastModel implements IBlastModel {
	
	private List<Entity> entities;
	private List<Player> players;
	private List<Explosive> explosives;
	private List<ExplosionCore> explosions;
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
		this.explosions = new ArrayList<ExplosionCore>();
		this.towers = new ArrayList<Tower>();
		this.ICores = new ArrayList<ICore>();
		
		this.entityMap = new HashMap<String, Entity>();
		
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
			if(e instanceof Tower) {
				towers.add((Tower)e);
			}
		}
	}
	
	
	@Override
	public void movePlayer(int playerID, Direction dir) {
		Hero hero = players.get(playerID-1).getHero();
		System.out.println(hero.getY());
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
	    Entity entity = getIntersectingEntity(hero.getCollisionBox());
	    if(entity instanceof Tower) {
	    	Tower tower = (Tower)entity;
	    	tower.capture(hero.getTeam());
	    }
	    
	}


	@Override
	public void primary(int playerID) {
		//TODO check if hero can use primary
		Explosive tmp = players.get(playerID-1).getHero().primaryAbility();
		entities.add(tmp);
		//sortEntities();
		explosives.add(tmp);
		System.out.println("PrimaryClicked");
		
	}

	@Override
	public void secondary(int playerID) {
		ICore tmp = players.get(playerID-1).getHero().secondaryAbility();
		ICores.add(tmp);
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
		
		//Perhaps put this sorting elsewhere?
		//sortEntities();
		
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

		//Check for dead explosions and remove them
		for (ExplosionCore c: explosions){
			c.tick();
			if (c.isDead()){
				entities.removeAll(c.getParts());
			}
		}
		
		List<Explosive> tmp = new ArrayList<Explosive>();
		
		
		//HARD CODED "fix" for fireball, should make a better code later.
		for(Explosive ex: explosives){
			if(!isFree(ex)){
				//Checks whether the fireballs CollisionBox will intersect with the Owners.
				if(!ex.getOwner().getCollisionBox().intersects(ex.getCollisionBox()) || !ex.getCollisionBox().intersects(ex.getOwner().getCollisionBox())){
					ex.setLife(0);
				}
			}
			if(ex.shouldExplode()){ 
				removeEntity(ex);
				createExplosion(ex.getPosition(), ex.getPower());
				tmp.add(ex);
			} 
			
		}
		
		
		for(ICore stun: ICores){
			while(!stun.isCreated()){
				
				if(stun.step(getIntersectingEntity(new Rectangle(stun.getNextPosition().getX()+2, stun.getNextPosition().getY()+2, Constants.TILE_SIZE-4, Constants.TILE_SIZE-4)))){
					stun.create();
				}else {
					for (IBurst ib : stun.getParts()){
						if (ib instanceof Entity){
							Entity e = (Entity)ib;
							entities.add(e);
						}
					}
				}
			}
			
		}
		
		List<ICore> trashCantwo = new LinkedList<ICore>();
		for(ICore stun: ICores){
			if(stun.isCreated()){
				stun.tick();
				if(stun.isDead()){
					trashCantwo.add(stun);
					entities.removeAll(stun.getParts());
				}
			}
		}
		ICores.removeAll(trashCantwo);
		explosives.removeAll(tmp);	
	    handleTowers();
	}
	
	private void handleTowers() {
		for(Tower tower : towers) {
			Direction[] directions = {Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH};
			for(int i = 0; i < directions.length; ++i) {
				int power = tower.getPower();
				int width = directions[i].getX() * (power - directions[i].getX()) * Constants.TILE_SIZE + Constants.TILE_SIZE;
				int height = directions[i].getY() * (power - directions[i].getY()) * Constants.TILE_SIZE + Constants.TILE_SIZE;
				int x = tower.getX();
				int y = tower.getY();
				if(directions[i].equals(Direction.EAST)) {
				    x += Constants.TILE_SIZE;
				} else if(directions[i].equals(Direction.SOUTH)) {
				    y += Constants.TILE_SIZE;
				}
				Rectangle check = new Rectangle(x, y, width, height);
				Entity e = getClosestIntersectingEntity(check);
				if(e != null && e instanceof Hero) { //TODO instanceof == bad? Maybe change.
					System.out.println(directions[i].toString() + ": " + e.getName().toString());
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
	    	if (!(e.getName().equals(entity.getName())) && e.getCollisionBox().intersects(testBox) && !(e.allowPassage(entity)) ){
	    		return false;
	    	}
	    	
	    }
		
		return true;
	}
	
	public Entity getIntersectingEntity(Rectangle r){
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
		double smallestDistance = -Double.MAX_VALUE;
		Entity ent = null;
		Vector2f rectLocation = rectangle.getLocation();
		for(Entity entity : intersectingEntitys) {
			if(smallestDistance > rectLocation.distance(entity.getCollisionBox().getLocation())) {
				smallestDistance = rectLocation.distance(entity.getCollisionBox().getLocation());
				ent = entity;
			}
		}
		return ent;
	}
	
	private int snapToGrid(int i){
		return (int)Math.round(i/32.0)*32;
		
	}
	
	public void createExplosion(Position p, int power){
		Jukebox.Sounds.EXPLOSION.getSound().play((float)(0.5 + Math.random()), 0.05f);
		p.setX(snapToGrid(p.getX()));
		p.setY(snapToGrid(p.getY()));
		
		List<IBurst> l = new ArrayList<IBurst>();

		//Add the center one
		l.add(new Explosion(p));
		
		Direction[] d = {Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH};
		for (int i = 0; i < 4; i++){
			int dist = 0;
			Rectangle check = new Rectangle(p.getX()+2, p.getY()+2, Constants.TILE_SIZE-4,Constants.TILE_SIZE-4);
			while (dist <= power){
				check.setX(p.getX() + d[i].getX() * dist * Constants.TILE_SIZE);
				check.setY(p.getY() + d[i].getY() * dist * Constants.TILE_SIZE);
				
				Entity e = getIntersectingEntity(check);
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
		//TODO Make this better
		for (IBurst ib: l){
			if (ib instanceof Entity){
				Entity e = (Entity) ib;
				entities.add(e);
			}
		}
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
