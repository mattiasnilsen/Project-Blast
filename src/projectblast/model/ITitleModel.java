package projectblast.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public interface ITitleModel {	
	public enum Hero {
		BOMBER,
		MAGE,
		BRUTE,
	}
	
	public enum Map {
		FirstMap,
		SecondMap,
	}
	
	public int getSelectedColumn();
	public int getSelectedRow();
	public boolean isSelected();
	
	public Hero getPlayerHero(int player);
	public Map getSelectedMap();
	
	public void up();
	public void down();
	public void left();
	public void right();
	public void select();
	
	public void update(GameContainer gc, StateBasedGame game, int delta);
}
