package projectblast.model.helper;

import org.newdawn.slick.Color;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.attribute.Team;
import projectblast.model.entity.hero.Bomber;
import projectblast.model.entity.hero.Brute;
import projectblast.model.entity.hero.Hero;
import projectblast.model.entity.hero.Mage;
import projectblast.model.helper.Constants.HeroChoice;

public class Options {
	private Hero player1Hero;
	private Hero player2Hero;
	
	private Team team1 = new Team("Main", new Color(255, 0, 128), Team.Side.LEFT);
	private Team team2 = new Team("Secondary", new Color(0, 255, 128), Team.Side.RIGHT);
	
	private Position startPosition1 = new Position(32, 640);
	private Position startPosition2 = new Position(1200, 96);
	
	private static Options options = null;
	
	private static final int NUMBER_OF_PLAYERS = 2;
	
	private Options() {
		
	}
	
	public static Options getOptions() {
		if(options == null){
			options = new Options();
		}
		return options;
	}
	
	public Hero getPlayer2Hero() {
		return player2Hero;
	}
	public void setPlayer2Hero(Constants.HeroChoice choice) {
		this.player2Hero = getHero(choice, startPosition2, team2);
	}
	public Hero getPlayer1Hero() {
		return player1Hero;
	}
	public void setPlayer1Hero(Constants.HeroChoice choice) {
		this.player1Hero = getHero(choice, startPosition1, team1);
	}
	
	public int getNumberOfPlayers() {
		return NUMBER_OF_PLAYERS;
	}
	
	private Hero getHero(Constants.HeroChoice heroChoice, Position pos, Team team) {
		switch(heroChoice) {
		case BOMBER:
			return new Bomber(pos, Direction.NONE, team);
		case MAGE:
			return new Mage(pos, Direction.NONE, team);
		case BRUTE:
			return new Brute(pos, Direction.NONE, team);
		default:
				return null;
		}
	}
}
