package projectblast.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public interface ITitleModel {

	public enum Column {
		MENU,
		PLAYER1HERO,
		PLAYER2HERO,
		MAP
	}
	
	public enum MenuItem {
		STARTGAME,
		SETTINGS,
		EXIT,
	}
	
	public enum Hero {
		BOMBER,
		MAGE,
		BRUTE,
	}
	
	public enum Map {
		FirstMap,
		SecondMap,
	}
	
	public Column getSelectedColumn();
	public MenuItem getSelectedMenuItem();
	public Hero getPlayerHero(int player);
	public Map getSelectedMap();
	
	public boolean isSelected();
	
	public void up();
	public void down();
	public void left();
	public void right();
	public void select();
	
	public void update(GameContainer gc, StateBasedGame game, int delta);
}
