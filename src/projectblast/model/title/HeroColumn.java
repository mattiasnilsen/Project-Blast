package projectblast.model.title;

import java.util.List;

import projectblast.model.title.ITitleModel.HeroChoice;

public class HeroColumn extends Column{

	private HeroChoice selectedHero;
	
	public HeroColumn(String name, HeroChoice selectedHero) {
		super(name);
		this.selectedHero = selectedHero;
	}
	
	public HeroColumn(String name, List<Item> items, HeroChoice selectedHero) {
		super(name, items);
		this.selectedHero = selectedHero;
	}
	
	public HeroChoice getSelectedHero() {
		return selectedHero;
	}
	
	public void nextHero() {
		selectedHero = HeroChoice.values()[(selectedHero.ordinal() + 1) % HeroChoice.values().length];
	}
	
	public void previousHero() {
		if(selectedHero.ordinal() == 0) {
			selectedHero = HeroChoice.values()[HeroChoice.values().length - 1];
		} else {
			selectedHero = HeroChoice.values()[selectedHero.ordinal() - 1];
		}
	}

}
