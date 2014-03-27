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

    public static void main(String args[]) {
        try {
            AppGameContainer container = new AppGameContainer(new BlastGame("Blast Game"));
            container.setDisplayMode(800, 600, false);
            container.setTargetFrameRate(60);
            container.start();
        } catch (Exception e) { //TODO Proper exception handling. 
            
        }
    }
    
	public BlastGame(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
	    addState(new GameplayState());
	}

}