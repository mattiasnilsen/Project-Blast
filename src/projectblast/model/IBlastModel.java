package projectblast.model;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public interface IBlastModel {
	
	void left(int playerID);
	void right(int playerID);
	void up(int playerID);
	void down(int playerID);
	
	void primary(int playerID);
	void secondary(int playerID);
	
	void stop(int playerID);
	
	void update();
	
	/**
	 * @return an unsorted list of entities.
	 */
	List<Entity> getEntities();
}
