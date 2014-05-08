package projectblast.control;

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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TitleState extends BasicGameState implements InputProviderListener {
	
	InputProvider provider;
	int choice = 0;
	StateBasedGame game;
	Image playGame;
	Image gameSettings;
	Image exitGame;
	Image arrow;
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		provider = new InputProvider(gc.getInput());
	    provider.addListener(this);
	    provider.bindCommand(new KeyControl(Input.KEY_UP), new BasicCommand("UP"));
	    provider.bindCommand(new KeyControl(Input.KEY_DOWN), new BasicCommand("DOWN"));
	    provider.bindCommand(new KeyControl(Input.KEY_ENTER), new BasicCommand("ENTER"));
	    
	    playGame = new Image("data/image/PlayGame.png");
		gameSettings = new Image("data/image/GameSettings.png");
		exitGame = new Image("data/image/ExitGame.png");
		arrow = new Image("data/image/Arrow.png");
		
	    this.game = game;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		//TODO refactor to view
		
		g.drawString("Blasting fun!", 100, 50);
		playGame.draw(100, 100);
		gameSettings.draw(100, 200);
		exitGame.draw(100, 300);
		arrow.draw(400, (choice+1)*100);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void controlPressed(Command c) {
		//TODO refactor to model
		BasicCommand b = (BasicCommand) c;
		if(b.getName().equals("DOWN")){
			choice = (choice+1) % 3;
		}
		if(b.getName().equals("UP")){
			choice = (choice+2) % 3;
		}
		if(b.getName().equals("ENTER")){
			if(choice == 0){
				game.enterState(2);
			} else if(choice == 2){
				System.exit(0);
			}
		}
	}

	@Override
	public void controlReleased(Command c) {
		
		
	}

}
