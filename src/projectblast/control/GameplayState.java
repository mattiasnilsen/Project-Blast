package projectblast.control;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import projectblast.model.BlastModel;
import projectblast.model.Constants;
import projectblast.model.IBlastModel;
import projectblast.model.Movable.Direction;
import projectblast.model.SimulatedOptions;
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
    	//TODO Remove simulation when options menu is complete
    	model = SimulatedOptions.getSimulatedModel();
    	view  = SimulatedOptions.getSimulatedView();
    }
    
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		
	    //Bind keys to commands
	    InputProvider provider = new InputProvider(gc.getInput());
	    //TODO Enable again....    provider.addListener(this);
	    
	    //Player 1 controls
	    provider.bindCommand(new KeyControl(Input.KEY_W), new BasicCommand("1up"));
	    provider.bindCommand(new KeyControl(Input.KEY_S), new BasicCommand("1down"));
	    provider.bindCommand(new KeyControl(Input.KEY_A), new BasicCommand("1left"));
	    provider.bindCommand(new KeyControl(Input.KEY_D), new BasicCommand("1right"));
	    provider.bindCommand(new KeyControl(Input.KEY_Q), new BasicCommand("1primary"));
	    provider.bindCommand(new KeyControl(Input.KEY_E), new BasicCommand("1secondary"));
	    
	    //Player 2 controls
	    provider.bindCommand(new KeyControl(Input.KEY_UP), new BasicCommand("2up"));
	    provider.bindCommand(new KeyControl(Input.KEY_DOWN), new BasicCommand("2down"));
	    provider.bindCommand(new KeyControl(Input.KEY_LEFT), new BasicCommand("2left"));
	    provider.bindCommand(new KeyControl(Input.KEY_RIGHT), new BasicCommand("2right"));
	    provider.bindCommand(new KeyControl(Input.KEY_1), new BasicCommand("2primary"));
	    provider.bindCommand(new KeyControl(Input.KEY_2), new BasicCommand("2secondary"));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		view.render(g);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		model.update(gc, game, delta);
		handleInput(gc.getInput());
	}

	
	
	
	@Override
	public int getID() {
		//This is the identifier ID for the gameplay state
		return 2;
	}

	public void handleInput(Input i){
		if (i.isKeyDown(Input.KEY_W)){
			model.movePlayer(1,Direction.NORTH);
		}
		if (i.isKeyDown(Input.KEY_S)){
			model.movePlayer(1,Direction.SOUTH);
		}
		if (i.isKeyDown(Input.KEY_A)){
			model.movePlayer(1,Direction.WEST);
		}
		if (i.isKeyDown(Input.KEY_D)){
			model.movePlayer(1,Direction.EAST);
		}
		if (i.isKeyPressed(Input.KEY_Q)){
			model.primary(1);
		}
		if (i.isKeyPressed(Input.KEY_E)){
			model.secondary(1);
		}
		
		if (i.isKeyDown(Input.KEY_UP)){
			model.movePlayer(2,Direction.NORTH);
		}
		if (i.isKeyDown(Input.KEY_DOWN)){
			model.movePlayer(2,Direction.SOUTH);
		}
		if (i.isKeyDown(Input.KEY_LEFT)){
			model.movePlayer(2,Direction.WEST);
		}
		if (i.isKeyDown(Input.KEY_RIGHT)){
			model.movePlayer(2,Direction.EAST);
		}
		if (i.isKeyPressed(Input.KEY_1)){
			model.primary(2);
		}
		if (i.isKeyPressed(Input.KEY_2)){
			model.secondary(2);
		}
		
		
		if (i.isKeyPressed(Input.KEY_T)){
			model.createExplosion(Constants.TILE_SIZE * 8,Constants.TILE_SIZE * 8,4);
		}
	}
	
    @Override //TODO This method is now unused
    public void controlPressed(Command command) {
    	BasicCommand bCommand = (BasicCommand)command;
    	
    	//Split the command name into a number and a name
    	int playerID = Integer.parseInt("" + bCommand.getName().charAt(0));
    	String com   = bCommand.getName().substring(1);
    	
    	switch(com){
    		case "up":
    			model.movePlayer(1,Direction.NORTH);
    			break;
    		case "down":
    			model.movePlayer(1,Direction.SOUTH);
    			break;
    		case "left":
    			model.movePlayer(1,Direction.WEST);
    			break;
    		case "right":
    			model.movePlayer(1,Direction.EAST);
    			break;
    		case "primary":
    			model.primary(playerID);
    			break;
    		case "secondary":
    			model.secondary(playerID);
    			break;
    		default:
    			break;
    			
    	}
    	
    	
    }

    @Override
    public void controlReleased(Command command) {
    	BasicCommand bCommand = (BasicCommand)command;
    	
    	//Split the command name into a number and a name
    	int playerID = Integer.parseInt("" + bCommand.getName().charAt(0));
    	String com   = "" + bCommand.getName().substring(1);
    	
    	switch(com){
			case "up":
				
				model.stop(playerID);
				break;
			case "down":
				
				model.stop(playerID);
				break;
			case "left":
				
				model.stop(playerID);
				break;
			case "right":
				
				model.stop(playerID);
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
