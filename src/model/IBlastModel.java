package model;

import java.util.List;

public interface IBlastModel {
	
	void left(int playerID);
	void right(int playerID);
	void up(int playerID);
	void down(int playerID);
	
	void primary(int playerID);
	void secondary(int playerID);
	
	/**
	 * @return an unsorted list of entities.
	 */
	List<Entity> getEntities();
}
