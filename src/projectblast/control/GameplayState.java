package projectblast.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



import projectblast.model.Direction;
import projectblast.model.IBlastModel;
import projectblast.model.helper.Constants;
import projectblast.model.helper.SimulatedOptions;
import projectblast.view.BlastView;
import projectblast.view.IBlastView;

/**
 * @author Axel Savén Östebo
 * revised by Mattias Nilsen
 * revised by Axel Savén Östebo - Added entity drawing using Maps
 */
public class GameplayState extends BasicGameState implements InputProviderListener {

    public enum Keys {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        PRIMARY,
        SECONDARY,
    }
    
	private IBlastModel model;
	private IBlastView view;
	
	private List<List<String>> keysPressed;
	
	InputProvider provider;
	
    public GameplayState()  {
    	//TODO Remove simulation when options menu is complete
    	model = SimulatedOptions.getSimulatedModel();
    	view  = new BlastView(model);
    	//SimulatedOptions.getSimulatedView();
    	
    	keysPressed = new ArrayList<List<String>>();
    	for(int i = 0; i < 3; ++i) {//TODO Change 3 to number of players + 1.
    	    keysPressed.add(new ArrayList<String>());
    	}
    }
    	
    
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		
	    //Bind keys to commands
	    provider = new InputProvider(gc.getInput());
	    provider.addListener(this);
	    
	    //TODO get this input from options set by the players.
	    List<Map<Keys, Integer>> playerKeys = new ArrayList<Map<Keys, Integer>>();
	    Map<Keys, Integer> keys = new HashMap<Keys, Integer>();
	    keys.put(Keys.UP, Input.KEY_W);
	    keys.put(Keys.DOWN, Input.KEY_S);
	    keys.put(Keys.LEFT, Input.KEY_A);
	    keys.put(Keys.RIGHT, Input.KEY_D);
	    keys.put(Keys.PRIMARY, Input.KEY_Q);
	    keys.put(Keys.SECONDARY, Input.KEY_E);
	    
	    playerKeys.add(keys);
	    keys = new HashMap<Keys, Integer>();
	    
	    keys.put(Keys.UP, Input.KEY_UP);
        keys.put(Keys.DOWN, Input.KEY_DOWN);
        keys.put(Keys.LEFT, Input.KEY_LEFT);
        keys.put(Keys.RIGHT, Input.KEY_RIGHT);
        keys.put(Keys.PRIMARY, Input.KEY_1);
        keys.put(Keys.SECONDARY, Input.KEY_2);
        playerKeys.add(keys);
        
	    setKeyBindings(playerKeys);
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
		
		for(int playerID = 1; playerID < keysPressed.size(); ++playerID) {
		    List<String> keys = keysPressed.get(playerID);
		    if(!keys.isEmpty()) {
		        int totalX = 0;
		        int totalY = 0;
		        for(String inputCommand : keys) {
		            Direction dir = getDirection(inputCommand);
		            totalX += dir.getX();
		            totalY += dir.getY();
		        }
		        Direction direction = Direction.getDirection(totalX, totalY);
		        model.movePlayer(playerID, direction);
		    }
		}
	}
	
	public void setKeyBindings(List<Map<Keys, Integer>> keys) {
	    for(int i = 0; i < keys.size(); ++i) {
	       int playerID = i + 1;
	       for(Map.Entry<Keys, Integer> entry : keys.get(i).entrySet()) {
	           provider.bindCommand(new KeyControl(entry.getValue()), new BasicCommand(playerID + entry.getKey().toString()));
	       }
	    }
	}

	private Direction getDirection(String inputCommand) {
	    Keys key = Keys.valueOf(inputCommand);
	    switch(key) {
	    case UP:
	        return Direction.NORTH;
	    case DOWN:
	        return Direction.SOUTH;
	    case LEFT:
	        return Direction.WEST;
	    case RIGHT:
	        return Direction.EAST;   
	    default:
	        return Direction.NONE;
	    }
	}
	
	
	@Override
	public int getID() {
		return Constants.GAME_STATE_ID;
	}
	
    @Override
    public void controlPressed(Command command) {
    	BasicCommand bCommand = (BasicCommand)command;
    	
    	//Split the command name into a number and a name
    	int playerID = Integer.parseInt("" + bCommand.getName().charAt(0));
    	String com   = bCommand.getName().substring(1);
    	
    	if(playerID > keysPressed.size() - 1) {
    	    return; //TODO Proper exception handling.
    	}
    	if(com.equals(Keys.PRIMARY.toString())) {
    	    model.primary(playerID);
    	} else if(com.equals(Keys.SECONDARY.toString())) {
    	    model.secondary(playerID);
    	} else { //Movement key.
    	    keysPressed.get(playerID).add(com);
    	}
    }

    @Override
    public void controlReleased(Command command) {
    	BasicCommand bCommand = (BasicCommand)command;
    	
    	//Split the command name into a number and a name
    	int playerID = Integer.parseInt("" + bCommand.getName().charAt(0));
    	String com   = "" + bCommand.getName().substring(1);
    	
        if(playerID > keysPressed.size() - 1) {
            return; //TODO Proper exception handling.
        }
        
        keysPressed.get(playerID).remove(com);
        model.stopPlayer(playerID); //TODO is this ok?!
    }
}
