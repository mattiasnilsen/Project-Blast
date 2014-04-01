package projectblast.model;

import org.newdawn.slick.Color;
import java.awt.List;
import java.util.ArrayList;
/**
 * 
 * @author Alex Tao
 *
 */
public class Team {
	
	//TODO change colors of heroes and towers
	
	private String teamName;
	private int score;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private ArrayList<Tower> towerList = new ArrayList<Tower>();
	private Color teamColor;

	public Team(String teamName, Color teamColor){
		this.teamName = teamName;
		this.teamColor = teamColor;
	}
	
	public Team(String teamName, Tower startingTower, Color teamColor,Player player1){
		this(teamName, teamColor);
		playerList.add(player1);
		
		towerList.add(startingTower);
	}
	
	public Team(String teamName, Tower startingTower, Color teamColor,Player player1, Player player2){
		this(teamName, startingTower, teamColor, player1);
		playerList.add(player2);
	}
	

	public void capturedTower(Tower tower){
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
	
	public Color getColor(){
		return teamColor;
	}
	
	@Override
	public String toString(){
		return teamName;
		
	}
}
