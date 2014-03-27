package projectblast;

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

/**
 * @author Axel Savén Östebo
 *
 */
public class GameplayState extends BasicGameState implements InputProviderListener {

    public GameplayState() {
        hero = new Bomber(200, 200, null);
    }
    
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
	    
	    InputProvider provider = new InputProvider(gc.getInput());
	    provider.addListener(this);
	    provider.bindCommand(new KeyControl(Input.KEY_W), new BasicCommand("run"));
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Remove hardcoding
	    hero.draw(g);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int iDontKnowWhatThisDoes)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		//This is the identifier ID for the gameplay state
		return 1;
	}

    @Override
    public void controlPressed(Command command) {
        hero.move(10, 10);
    }

    @Override
    public void controlReleased(Command command) {
        // TODO Auto-generated method stub
        
    }

    private Hero hero;
}
