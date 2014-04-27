package projectblast.view;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import projectblast.model.Constants;
import projectblast.model.Team;
import projectblast.model.Player;


public class StatusBar {
	private int balance;
	private int scaleFactor;
	
	public StatusBar(){
		balance     = 0;
		scaleFactor = 1;
	}
	
	public void shiftBalance(int factor){
		balance += factor;
	}
	
	public int isGameOver(){
		if (balance <= -500){
			return -1;
		} else if (balance >= 500) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public void render(Graphics g, List<Player> players){
		List<Player> left = new ArrayList<Player>();
		List<Player> right = new ArrayList<Player>();
		
		left.add(players.get(0));
		int count = 1;
		//Split players into teams
		for (Player p: players){
			if (p.getHero().getTeam().equals(players.get(0).getHero().getTeam())){
				left.add(p);
			} else {
				right.add(p);
			}
		}
		if (right.isEmpty() || left.isEmpty()){
			throw new NullPointerException("There must be at least one player per team! \nCheck StatusBar render method!");
		}
		
		
		g.setColor(Color.orange);
		g.fillRect(0, 0, Constants.GAME_WIDTH, 64);
		
		//Left team
		g.setColor(left.get(0).getHero().getTeam().getColor());
		g.drawString(left.get(0).getHero().getTeam().getName(), 0, 0);
		g.drawString("Score: " + left.get(0).getHero().getTeam().getScore(), 0, 24);
		
		//Right team
		g.setColor(right.get(0).getHero().getTeam().getColor());
		g.drawString(right.get(0).getHero().getTeam().getName(), Constants.GAME_WIDTH - 128, 0);
		g.drawString("Score: " + right.get(0).getHero().getTeam().getScore(), Constants.GAME_WIDTH - 128, 24);
		
		//Tug of War
		g.setColor(right.get(0).getHero().getTeam().getColor());
		g.fillRect(Constants.GAME_WIDTH/2 - 500, 16, 1000, 32);
		g.setColor(left.get(0).getHero().getTeam().getColor());
		g.fillRect(Constants.GAME_WIDTH/2 - 500, 16, balance + 500, 32);
	}

}