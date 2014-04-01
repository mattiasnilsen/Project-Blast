package control;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.BlastModel;
import model.Entity;
import model.Movable;
import model.Movable.Direction;

import org.newdawn.slick.Color;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;


import view.BlastView;

/**
 * @author Axel Savén Östebo
 * revised by Mattias Nilsen
 * revised by Axel Savén Östebo - Added entity drawing using Maps
 */
public class GameplayState extends BasicGameState implements InputProviderListener {

	
	private BlastModel model;
	private BlastView view;
	
    public GameplayState()  {
    	model = new BlastModel();
    	view  = new BlastView();
    }
    
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
	    
	    InputProvider provider = new InputProvider(gc.getInput());
	    provider.addListener(this);
	    provider.bindCommand(new KeyControl(Input.KEY_W), new BasicCommand("up"));
	    provider.bindCommand(new KeyControl(Input.KEY_S), new BasicCommand("down"));
	    provider.bindCommand(new KeyControl(Input.KEY_A), new BasicCommand("left"));
	    provider.bindCommand(new KeyControl(Input.KEY_D), new BasicCommand("right"));
	    provider.bindCommand(new KeyControl(Input.KEY_Q), new BasicCommand("primary"));
	    provider.bindCommand(new KeyControl(Input.KEY_E), new BasicCommand("secondary"));
	    
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		view.render(gc, game, g);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		model.update();
		
	}

	public boolean isFree(Rectangle r){
		
		for (Entity e: entities){
	    	if (e.getCollisionBox().intersects(r)){
	    		return false;
	    	}
	    }
		return true;
	}
	
	
	@Override
	public int getID() {
		//This is the identifier ID for the gameplay state
		return 1;
	}

    @Override
    public void controlPressed(Command command) {
    	BasicCommand bCommand = (BasicCommand)command;
    	//TODO Change to switch case.
    	if(bCommand.getName().equals("up")) {
    		hero.startMove(Movable.Direction.NORTH);
    	} else if(bCommand.getName().equals("down")) {
    		hero.startMove(Movable.Direction.SOUTH);
    	} else if(bCommand.getName().equals("left")) {
    		hero.startMove(Movable.Direction.WEST);
    	} else if(bCommand.getName().equals("right")) {
    		hero.startMove(Movable.Direction.EAST);
    	} else if(bCommand.getName().equals("primary")){
    		hero.primaryAbility();
    	} else if(bCommand.getName().equals("secondary")){
    		hero.secondaryAbility();
    	}
    }

    @Override
    public void controlReleased(Command command) {
    	BasicCommand bCommand = (BasicCommand)command;
    	//TODO Change to switch case.
    	if(bCommand.getName().equals("up")) {
    		hero.stopMove(Movable.Direction.NORTH);
    	} else if(bCommand.getName().equals("down")) {
    		hero.stopMove(Movable.Direction.SOUTH);
    	} else if(bCommand.getName().equals("left")) {
    		hero.stopMove(Movable.Direction.WEST);
    	} else if(bCommand.getName().equals("right")) {
    		hero.stopMove(Movable.Direction.EAST);
    	} 
    }
}
