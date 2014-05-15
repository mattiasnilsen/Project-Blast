package projectblast.model.title;

import java.util.ArrayList;
import java.util.List;

public class Column {
	private List<Item> items = new ArrayList<Item>();
	private String name;
	
	public Column(String name) {
		if(name == null) {
			throw new NullPointerException("projectblast.model.title.Column: name cannot be null");
		}
		this.name = name;
	}
	
	public Column(String name, List<Item> items) {
		this(name);

		if(items == null) {
			throw new NullPointerException("projectblast.model.title.Column: items cannot be null");
		}
		
		for(Item item : items) { //Make a deep copy of all the items.
			Item newItem = new Item(item);
			this.items.add(newItem);
		}
		
	}
	
	public Item getItem(int row) {
		row = getRowPosition(row);
		
		return items.get(row);
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void addItem(Item item) {
		if(item == null) {
			throw new NullPointerException("projectblast.model.title.column.addItem() item cannot be null.");
		}
		
		items.add(item);
	}
	
	public int getRowPosition(int row) {
		if(row < 0) {
			row = getRowCount() - row + 1;
		}
		
		row = row % getRowCount();
		
		return row;
	}
	
	public void selectItem(int row) {
		row = getRowPosition(row);
		
		Item item = items.get(row);
		item.setSelected(!item.isSelected());
	}
	
	public int getRowCount() {
		return items.size();
	}
	
	public String getName() {
		return name;
	}
}
