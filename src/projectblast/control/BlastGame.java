package projectblast.control;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



/**
 * @author Axel Savén Östebo
 *
 */
public class BlastGame extends StateBasedGame{
	private BasicGameState titleState, settingsState, gameplayState;

	public BlastGame(String name) {
		super(name);
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gameplayState = new GameplayState();
		titleState = new TitleState();
		addState(titleState);
	    addState(gameplayState);
	    //enterState(gameplayState.getID());
	    enterState(titleState.getID());
	}

}