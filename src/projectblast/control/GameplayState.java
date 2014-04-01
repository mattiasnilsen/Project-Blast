package projectblast.control;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


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

import projectblast.model.BlastModel;
import projectblast.model.Entity;
import projectblast.model.IBlastModel;
import projectblast.model.Movable;
import projectblast.model.Movable.Direction;
import projectblast.view.BlastView;
import projectblast.view.IBlastView;



/**
 * @author Axel Savén Östebo
 * revised by Mattias Nilsen
 * revised by Axel Savén Östebo - Added entity drawing using Maps
 */
public class GameplayState extends BasicGameState implements InputProviderListener {

	
	private IBlastModel model;
	private IBlastView view;
	
    public GameplayState()  {
    	model = new BlastModel(); //Ska antagligen hämta en Model och View på ngt sätt.
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
		view.render(g);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		model.update();
		
	}

	
	
	
	@Override
	public int getID() {
		//This is the identifier ID for the gameplay state
		return 1;
	}

    @Override
    public void controlPressed(Command command) {
    	BasicCommand bCommand = (BasicCommand)command;
    	switch(bCommand.getName()){
    		case "up":
    			model.up(1);
    			break;
    		case "down":
    			model.down(1);
    			break;
    		case "left":
    			model.left(1);
    			break;
    		case "right":
    			model.right(1);
    			break;
    		case "primary":
    			model.primary(1);
    			break;
    		case "secondary":
    			model.secondary(1);
    			break;
    		default:
    			break;
    			
    			
    			
    	}
    	
    	
    }

    @Override
    public void controlReleased(Command command) {
    	BasicCommand bCommand = (BasicCommand)command;
    	switch(bCommand.getName()){
			case "up":
				
				
			case "down":
				
				
			case "left":
				
				
			case "right":
				
				model.stop(1);
				break;
			case "primary":
				
				break;
			case "secondary":
				
				break;
			default:
				break;

    	}
    }
}
