package projectblast.model;

import java.util.ArrayList;
import java.util.List;

public class Tile {
	private List<Entity> holds = new ArrayList<Entity>();
	private int cellX, cellY;
	
	public Tile(int x, int y){
		cellX = x;
		cellY = y;
	}
	
	public List<Entity> getContent(){
		return holds;
	}
	
	public void addContent(Entity e){
		holds.add(e);
	}
	
	public void removeContent(Entity e){
		holds.remove(e);
	}
	
	public void clear(){
		holds.clear();
	}
}
