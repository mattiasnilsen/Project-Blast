package projectblast.model.title;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class TitleModel implements ITitleModel {
	private int selectedColumn = 0;
	private int selectedRow = 0;
	private Hero player1Hero = Hero.BOMBER;
	private Hero player2Hero = Hero.MAGE;
	private Map selectedMap = Map.FirstMap;
	
	private List<Column> columns = new ArrayList<Column>();
	
	private boolean selected;
	
	public TitleModel() {
		Column menuColumn = new Column("Menu");
		menuColumn.addItem(new Item("StartGame"));
		menuColumn.addItem(new Item("Settings"));
		menuColumn.addItem(new Item("ExitGame"));
		
		columns.add(menuColumn);
		
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
		getColumn(selectedColumn).selectItem(selectedRow);
	}
	
	private void move(int x, int y) {
		if(!selected) {
			selectedColumn += x;
			selectedRow += y;
			
			Column column = getColumn(selectedColumn);
			selectedRow = column.getRowPosition(selectedRow);
		}
	}
	
	private Column getColumn(int position) {
		if(position < 0) {
			position = columns.size() - position + 1;
		}
		
		position = position % columns.size();
		return columns.get(position);
	}

	@Override
	public Map getSelectedMap() {
		return selectedMap;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}
	
	public Item getSelectedItem() {
		for(Column column : columns) {
			for(Item item : column.getItems()) {
				if(item.isSelected()) {
					return item;
				}
			}
		}
		return null;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		Item selectedItem = getSelectedItem();
		if(selectedItem == null) {
			return;
		}
		
		switch(selectedItem.getName()) {
		case "StartGame":
			game.enterState(2);
			break;
		case "ExitGame":
			System.exit(0);
			break;
		}
	}
}
