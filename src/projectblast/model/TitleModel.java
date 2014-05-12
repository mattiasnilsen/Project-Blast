package projectblast.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class TitleModel implements ITitleModel {
	private Column selectedColumn = Column.MENU;
	private MenuItem selectedMenuItem = MenuItem.STARTGAME;
	private Hero player1Hero = Hero.BOMBER;
	private Hero player2Hero = Hero.MAGE;
	private Map selectedMap = Map.FirstMap;
	
	
	private boolean selected;
	
	public TitleModel() {
		selected = false;
	}
	
	@Override
	public Column getSelectedColumn() {
		return selectedColumn;
	}
	@Override
	public MenuItem getSelectedMenuItem() {
		return selectedMenuItem;
	}
	@Override
	public Hero getPlayerHero(int player) {
		if(player == 1) {
			return player1Hero;
		} else {
			return player2Hero;
		}
	}
	@Override
	public void down() {
	
	}
	@Override
	public void up() {
		
	}
	@Override
	public void left() {
		
	}
	@Override
	public void right() {
		
	}
	@Override
	public void select() {
		
	}

	@Override
	public Map getSelectedMap() {
		return selectedMap;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		
	}
	
}
