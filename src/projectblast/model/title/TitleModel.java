package projectblast.model.title;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import projectblast.model.ITitleModel;
import projectblast.model.ITitleModel.Hero;
import projectblast.model.ITitleModel.Map;

public class TitleModel implements ITitleModel {
	private int selectedColumn = 0;
	private int selectedRow = 0;
	private Hero player1Hero = Hero.BOMBER;
	private Hero player2Hero = Hero.MAGE;
	private Map selectedMap = Map.FirstMap;
	
	
	private boolean selected;
	
	public TitleModel() {
		selected = false;
	}
	
	@Override
	public int getSelectedColumn() {
		return selectedColumn;
	}
	@Override
	public int getSelectedRow() {
		return selectedRow;
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
		move(0, 1);
	}
	@Override
	public void up() {
		move(0, -1);
	}
	@Override
	public void left() {
		move(-1, 0);
	}
	@Override
	public void right() {
		move(1, 0);
	}
	@Override
	public void select() {
		selected = !selected;
	}
	
	private void move(int x, int y) {
		if(!selected) {
			selectedColumn += x;
			selectedRow += y;
			
			if(selectedColumn < 0) {
				selectedColumn = 0;
			}
			if(selectedRow < 0) {
				selectedRow = 0;
			}
			
			switch(selectedColumn) {
			case 0:
				selectedRow = selectedRow % 3;
				break;
			}
		}
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
		if(selected) {
			switch(selectedRow) {
			case 0:
				game.enterState(2);
				break;
			case 2:
				System.exit(0);
				break;
			}
		}
	}
	
}
