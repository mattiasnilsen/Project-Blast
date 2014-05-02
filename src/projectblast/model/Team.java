package projectblast.model;

import org.newdawn.slick.Color;
/**
 * 
 * @author Alex Tao
 *
 */
public class Team {
	private String teamName;
	private Color teamColor;
	private final Side teamSide;
	
	public enum Side{
		LEFT, RIGHT, NULL;
	}
	
	private static Team neutralTeam = null;

	public Team(String teamName, Color teamColor, Side teamSide){
		this.teamName  = teamName;
		this.teamColor = teamColor;
		this.teamSide  = teamSide;
	}
	
	public static Team getNeutralTeam() {
		if(neutralTeam == null) {
			neutralTeam = new Team("Neutral", Color.white, Side.NULL);
		}
		return neutralTeam;
	}
	
	public Color getColor(){
		return teamColor;
	}
	
	@Override
	public String toString(){
		return teamColor.toString() + " " + teamName;
	}
	
	public String getName(){
		return teamName;
	}

	public Side getSide() {
		return teamSide;
	}	
}
