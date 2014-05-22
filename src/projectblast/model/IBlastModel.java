package projectblast.model;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Team;
import projectblast.model.entity.Entity;
import projectblast.model.entity.MovableEntity;

public interface IBlastModel {
	
	public void movePlayer(int playerID, Direction dir);
	public void stopPlayer(int playerID);
	
	public void primary(int playerID);
	public void secondary(int playerID);
	
	public void update(GameContainer gc, StateBasedGame game, int delta);
	
	/**
	 * @return an unsorted list of entities.
	 */
	public List<Entity> getEntities();
	public List<Player> getPlayers();
	
	public void addEntity(Entity e);
	public void removeEntity(Entity e);

	public void increaseScaleFactor(int add);

	public void setScaleFactor(int scaleFactor);

	public int getScaleFactor();

	public void shiftBalance(int factor);

	public int getBalance();

	public int getTowerBalance();

	
	public void endGame(Team winner);
	
	public int isGameOver();
	
	public Entity getIntersectingEntity(Entity entity);
	
	public Entity getIntersectingEntity(Rectangle r);
	
	public Team getWinner();
	

	
}
