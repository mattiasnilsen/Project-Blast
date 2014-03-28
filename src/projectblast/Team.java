package projectblast;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;

public class Team {
	
	//TODO change colors of heroes and towers
	
	String teamName;
	int score;
	ArrayList<Hero> heroList = new ArrayList<Hero>();
	ArrayList<Tower> towerList = new ArrayList<Tower>();
	Color teamColor;
	
	//Möjligen byta ut hero mot Player.
	
	public Team(String teamName, Tower startingTower, Color teamColor,Hero hero1){
		this.teamName = teamName;	
		heroList.add(hero1);
		this.teamColor = teamColor;
		
		for(int i = 0; i<heroList.size(); i++){
		heroList.get(i).getSprite().setImageColor(teamColor.getRed(), this.teamColor.getGreen(), teamColor.getBlue());
		}
		
		towerList.add(startingTower);
		towerList.get(0).getSprite().setImageColor(teamColor.getRed(), teamColor.getGreen(), teamColor.getBlue());
	}
	
	public Team(String teamName, Tower startingTower, Color teamColor,Hero hero1, Hero hero2){
		this(teamName, startingTower, teamColor, hero1);
		heroList.add(hero2);
	}
	

	public void capturedTower(Tower tower){
		towerList.add(tower);
		addScore(10);
		//Change color of that tower
	}
	
	public void lostTower(Tower tower){
		towerList.remove(tower);
	}
	
	public void addScore(int score){
		this.score += score;
	}
	public int getScore(){
		return score;
	}
	
	@Override
	public String toString(){
		return teamName;
		
	}
}
