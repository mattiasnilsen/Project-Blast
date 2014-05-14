package projectblast.model.title;

import java.util.List;

import projectblast.model.title.ITitleModel.HeroChoice;

public class HeroColumn extends Column{

	private HeroChoice selectedHero = HeroChoice.BOMBER;
	
	public HeroColumn(String name) {
		super(name);
	}
	
	public HeroColumn(String name, List<Item> items) {
		super(name, items);
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
