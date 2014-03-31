package projectblast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import projectblast.Movable.Direction;

public class BlastModel implements IBlastModel {
	
	private static List<Entity> entities;
	private ArrayList<Player> players;
	
	public BlastModel(ArrayList<Player> players){
		entities = new LinkedList();
		this.players = players;  
	}
	@Override
	public void left(int playerID) {
		players.get(playerID-1).getHero().move(Direction.WEST);

	}

	@Override
	public void right(int playerID) {
		players.get(playerID-1).getHero().move(Direction.EAST);

	}

	@Override
	public void up(int playerID) {
		players.get(playerID-1).getHero().move(Direction.NORTH);

	}

	@Override
	public void down(int playerID) {
		players.get(playerID-1).getHero().move(Direction.SOUTH);

	}

	@Override
	public void primary(int playerID) {
		players.get(playerID-1).getHero().primaryAbility();

	}

	@Override
	public void secondary(int playerID) {
		players.get(playerID-1).getHero().secondaryAbility();
	}

	@Override
	public List<Entity> getEntities() {
		
		return entities;
	}
	
	public static void addEntity(Entity e){
		entities.add(e);
		for(Entity a : entities){
			System.out.print(a.toString());
		}
	}

}
