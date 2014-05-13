package projectblast.model.title;

public class Item {

	private String name;
	private boolean selected;
	
	public Item(String name) {
		if(name == null) {
			throw new NullPointerException("projectblast.model.title.item: name cannot be null");
		}
		this.name = name;
	}
	
	public Item(Item other) {
		if(name == null) {
			throw new NullPointerException("projectblast.model.title.item: other cannot be null");
		}
		this.name = new String(other.getName());
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
