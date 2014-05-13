package projectblast.model;

import projectblast.model.entity.hero.Hero;

/**
 * 
 * @author Alex
 *
 */
public class Player {

	private Hero hero;
	
	
	public Player(Hero hero){
		this.hero = hero;
		
	}
	
	public Hero getHero(){
		return hero;
	}
	
	
}
