package projectblast;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Axel Savén Östebo
 *
 */
public class BlastGame extends StateBasedGame{

    
    
	public BlastGame(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
	    addState(new GameplayState());
	}

}