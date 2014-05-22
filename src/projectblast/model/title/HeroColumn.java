package projectblast.model.title;

import java.util.List;

import projectblast.model.helper.Constants;
import projectblast.model.helper.Constants.HeroChoice;

public class HeroColumn extends Column{

	private Constants.HeroChoice selectedHero;
	
	public HeroColumn(String name, Constants.HeroChoice selectedHero) {
		super(name);
		this.selectedHero = selectedHero;
	}
	
	public HeroColumn(String name, List<Item> items, Constants.HeroChoice selectedHero) {
		super(name, items);
		this.selectedHero = selectedHero;
	}
	
	public Constants.HeroChoice getSelectedHero() {
		return selectedHero;
	}
	
	public void nextHero() {
		selectedHero = Constants.HeroChoice.values()[(selectedHero.ordinal() + 1) % Constants.HeroChoice.values().length];
	}
	
	public void previousHero() {
		if(selectedHero.ordinal() == 0) {
			selectedHero = Constants.HeroChoice.values()[Constants.HeroChoice.values().length - 1];
		} else {
			selectedHero = Constants.HeroChoice.values()[selectedHero.ordinal() - 1];
		}
	}

}
