package projectblast;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;

public class Team {
	
	//TODO change colors of heroes and towers
	
	private String teamName;
	private int score;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private ArrayList<Tower> towerList = new ArrayList<Tower>();
	private Color teamColor;
	private int colorRed = teamColor.getRed();
	private int colorGreen = teamColor.getGreen();
	private int colorBlue = teamColor.getBlue();
	
	//Möjligen byta ut hero mot Player.
	
	public Team(String teamName, Tower startingTower, Color teamColor,Player player1){
		this.teamName = teamName;	
		playerList.add(player1);
		this.teamColor = teamColor;
		
		for(int i = 0; i<playerList.size(); i++){
		playerList.get(i).getHero().getSprite().setImageColor(colorRed, colorGreen, colorBlue);
		}
		
		towerList.add(startingTower);
		towerList.get(0).getSprite().setImageColor(colorRed, colorGreen, colorBlue);
	}
	
	public Team(String teamName, Tower startingTower, Color teamColor,Player player1, Player player2){
		this(teamName, startingTower, teamColor, player1);
		playerList.add(player2);
	}
	

	public void capturedTower(Tower tower){
		tower.getSprite().setImageColor(colorRed, colorGreen, colorBlue);
		towerList.add(tower);
		addScore(10);
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
